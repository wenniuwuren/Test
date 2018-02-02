package com.wenniuwuren.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjDoubleConsumer;

/**
 * hbase-site.xml
 *
 */
public class HBaseHelper {

    private static final byte[] TABLE_NAME = Bytes.toBytes("users");
    private static final byte[] CF_INFO = Bytes.toBytes("info");

    private static final byte[] COL_NAME = Bytes.toBytes("name");
    private static final byte[] COL_EMAIL = Bytes.toBytes("email");
    private static final byte[] COL_PASSWORD = Bytes.toBytes("password");

    static final String rowKey = "row1";
    static Configuration conf;
    static Admin admin;
    static Connection connection;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.243.128");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建表
     * @param tableName
     * @param columns
     * @throws Exception
     */
    public static void createTable(String tableName, String[] columns) throws Exception {
        TableName tn = TableName.valueOf(tableName);
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tn);
        for (String columnName : columns) {
            HColumnDescriptor column = new HColumnDescriptor(columnName);
            column.setMaxVersions(1); // 默认保存3个版本，流水号不需要多个版本保留1个就行
            hTableDescriptor.addFamily(column);
        }
        admin.createTable(hTableDescriptor);
        System.out.println("create table successed");
    }

    /**
     * 根据表名删除表
     * @param tableName
     * @throws Exception
     */
    public static void dropTable(String tableName) throws Exception {
        TableName tn = TableName.valueOf(tableName);
        // 打日志 删了什么表
        if (admin.tableExists(tn)) {
            admin.disableTable(tn);
            admin.deleteTable(tn);
        }
        System.out.println("drop table successed");
    }

    /**
     * 根据表名查看表存不存在
     * @param tableName
     * @return
     * @throws Exception
     */
    public static boolean isTableExists(String tableName) throws Exception{
        TableName tn = TableName.valueOf(tableName);
        return admin.tableExists(tn);
    }

    /**
     * 插入数据
     * @param tableName
     * @param map
     * @param rowKey
     * @throws Exception
     */
    public static void insert(String tableName, Map<String, String> map, String rowKey) throws Exception {
        TableName tn = TableName.valueOf(tableName);
        Table hTable = connection.getTable(tn);
        byte[] row1 = Bytes.toBytes(rowKey);
        Put p1 = new Put(row1);
        for (String columnName : map.keySet()) {
            byte[] value = Bytes.toBytes(map.get(columnName));
            String[] str = columnName.split(":");
            byte[] family = Bytes.toBytes(str[0]);
            byte[] qualifier = null;
            if (str.length > 1) {
                qualifier = Bytes.toBytes(str[1]);
            }
            p1.addColumn(family, qualifier, value);
        }
        hTable.put(p1);
        Get g1 = new Get(row1);
        Result result = hTable.get(g1);
        System.out.println("Get: " + result);
        System.out.println("insert successed");

        hTable.close();
    }


    /**
     * 删除数据
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static void delete(String tableName, String rowKey) throws Exception {
        TableName tn = TableName.valueOf(tableName);
        Table hTable = connection.getTable(tn);

        List<Delete> list = new ArrayList<Delete>();
        Delete d1 = new Delete(Bytes.toBytes(rowKey));
//        d1.addColumn(CF_INFO, COL_EMAIL); // 只删某一个单元，否则删整行
        list.add(d1);
        hTable.delete(list);
        Get g1 = new Get(Bytes.toBytes(rowKey));
        Result result = hTable.get(g1);
        System.out.println("Get: " + result);
        System.out.println("delete successed");
        hTable.close();
    }

    /**
     * 根据 rowKey 查看最新一行数据
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static void selectOne(String tableName, String rowKey) throws Exception {
        TableName tn = TableName.valueOf(tableName);
        Table hTable = connection.getTable(tn);
        Get g1 = new Get(Bytes.toBytes(rowKey));
//        g1.addColumn(CF_INFO, COL_NAME); // 不加column 则返回所有列
        Result result = hTable.get(g1);
        foreach(result);
        System.out.println("selectOne end");
        hTable.close();
    }


    private static void foreach(Result result) throws Exception {
        for (KeyValue keyValue : result.raw()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Bytes.toString(keyValue.getRow())).append("\t"); // 将字节码转回 String
            sb.append(Bytes.toString(keyValue.getFamily())).append("\t");
            sb.append(Bytes.toString(keyValue.getQualifier())).append("\t");
            sb.append(keyValue.getTimestamp()).append("\t");
            sb.append(Bytes.toString(keyValue.getValue())).append("\t");
            System.out.println(sb.toString());
        }
    }


    /**
     * 查看表中所有数据
     * @param tableName
     * @throws Exception
     */
    public static void selectAll(String tableName) throws Exception {
        TableName tn = TableName.valueOf(tableName);
        Table hTable = connection.getTable(tn);
        Scan scan = new Scan();
        ResultScanner resultScanner = null;
        try {
            resultScanner = hTable.getScanner(scan);
            for (Result result : resultScanner) {
                foreach(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultScanner != null) {
                resultScanner.close();
            }
        }
        System.out.println("selectAll end");
        hTable.close();
    }

    /**
     * 按 rowKey 范围查找
     * @param tableName
     * @param startRow
     * @param endRow
     * @throws Exception
     */
    public static void selectByRowKeyRange(String tableName, String startRow, String endRow) throws Exception{
        TableName tn = TableName.valueOf(tableName);
        Table hTable = connection.getTable(tn);
//        byte[] starR = Bytes.padTail(Bytes.toBytes(startRow), Long.SIZE);
//        byte[] endR = Bytes.padTail(Bytes.toBytes(endRow), Long.SIZE);
//        Scan scan = new Scan(starR, endR);
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(endRow));
        // 如果 + 1 的话，就把结束的位置包含进查询结果了
        // Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(endRow + 1));
        ResultScanner resultScanner = null;
        try {
            resultScanner = hTable.getScanner(scan);
            for (Result result : resultScanner) {
                foreach(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultScanner != null) {
                resultScanner.close();
            }
        }
        System.out.println("selectByRowKeyRange end");
        hTable.close();
    }

    /**
     * 查询包含某个字符串的结果
     * @param tableName
     * @param cf
     * @param qual
     * @param filterStr
     * @throws Exception
     */
    public static void selectByFilterString(String tableName, String cf, String qual, String filterStr) throws Exception{
        TableName tn = TableName.valueOf(tableName);
        Table hTable = connection.getTable(tn);
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(cf), Bytes.toBytes(qual));
        Filter f = new ValueFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(".*" + filterStr + ".*"));
        scan.setFilter(f);

        ResultScanner resultScanner = null;
        try {
            resultScanner = hTable.getScanner(scan);
            for (Result result : resultScanner) {
                foreach(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultScanner != null) {
                resultScanner.close();
            }
        }
        System.out.println("selectByFilterString end");
        hTable.close();

    }

    public static void main(String[] args) throws Exception {
        String tableName = "twits";
//        String[] columns = new String[]{"column_A", "column_B"};
        String[] columns = new String[]{"twits"};
//        createTable(tableName, columns);
        Map<String, String> map = new HashMap<String, String>();
        map.put("twits:dt", "1513739471000");
        map.put("twits:twit", "bbbbbbbbb");
        String rowK = "b";
//        insert(tableName, map, rowK);
//        selectOne(tableName, rowK);
//        selectAll(tableName);
        selectByRowKeyRange(tableName, "a", "b");
//        selectByFilterString(tableName, "twits", "twit", "llo");
//        delete(tableName, rowKey);
//        dropTable(tableName);
    }
}
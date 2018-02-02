package com.wenniuwuren.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by zhaojy on 2017/4/21.
 */
public class HBaseDemo {
    private static Configuration config=null;
    private static HBaseAdmin admin=null;
   //zookeeper集群地址
    private final static String   HB_ZK_QUORUM="zhuwm";
    //zk端口号
    private final static String HB_ZK_PORT="2181";
    private static HTablePool tp = null;

    static {
        try {
            config= HBaseConfiguration.create();
            config.set("hbase.zookeeper.property.clientPort", HB_ZK_PORT);
            config.set("hbase.zookeeper.quorum",HB_ZK_QUORUM);
            admin = new HBaseAdmin(config);
            tp = new HTablePool(config, 10);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//获取表信息
    public static HTableInterface getTable(String tableName){
        return tp.getTable(tableName);
    }


    public static void main(String args[]){
        //analytics_demo 表名
        HTableInterface table = getTable("yanxuan_pinpoint:AgentInfo");
        //　获取row=Info的数据
        Get get = new Get(Bytes.toBytes("Info"));
        try {
            System.out.println("table.get before");
            Result result = table.get(get);

            System.out.println("result size:" + result.size());
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                // 表用完关闭，资源回到连接池
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
package com.wenniuwuren.mongodb.utils;

import com.mongodb.*;
import org.bson.types.BasicBSONList;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * MongoDB工具包
 *
 */
public class MongoUtil {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(MongoUtil.class);

    /**
     * @param dbName
     * @return
     * @throws Exception
     */
    public static DB getDB(String dbName) throws MongoException {
        return MongoDBManager.getInstance().getDB(dbName);
    }

    /**
     * 判断集合是否存在
     *
     * @param db
     * @param collectionName
     * @return
     */
    public static boolean collectionExists(DB db, String collectionName) {
        return db.collectionExists(collectionName);
    }

    /**
     * 获取集合
     *
     * @param db
     * @param collectionName
     * @return
     */
    public static DBCollection getCollection(DB db, String collectionName) {
        return db.getCollection(collectionName);
    }

    /**
     * 单条新增
     *
     * @param db
     * @param collectionName
     * @param dbObject
     */
    public static void saveDbObject(DB db, String collectionName,
        DBObject dbObject) {
        getCollection(db, collectionName).save(dbObject);
    }

    /**
     * 单条删除
     *
     * @param db
     * @param collectionName
     * @param dbObject
     */
    public static void deleteDbObject(DB db, String collectionName,
        DBObject dbObject) {
        getCollection(db, collectionName).remove(dbObject);
    }

    /**
     * 单条更新
     *
     * @param db
     * @param collectionName
     * @param oldObject
     * @param newObject
     * @return
     */
    public static int updateDbObject(DB db, String collectionName,
        DBObject oldObject, DBObject newObject) {
        return getCollection(db, collectionName).update(oldObject, newObject)
            .getN();
    }

    /**
     * 批量更新
     * @param dbName
     * @param collectionName
     * @param query
     * @param update
     * @return
     */
    public static int updateMulti(String dbName, String collectionName, Map<String, Object> query, Map<String, Object> update) {
        DBCollection collection = getCollection(getDB(dbName), collectionName);
        DBObject queryObj = getMapped(query);
        DBObject updateObj = getMapped(update);
        return collection.updateMulti(queryObj, updateObj).getN();
    }

    /**
     * 查询单个,按主键查询 <br>
     *
     * @param id
     * @param collectionName
     */
    public static void findById(String id, DB db, String collectionName) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id", new ObjectId(id));
        findOne(dbObject, db, collectionName);
    }

    /**
     * 查询单个
     *
     */
    public static DBObject findOne(DBObject dbObject, DB db,
        String collectionName) {
        DBObject object = getCollection(db, collectionName).findOne(dbObject);
        return object;
    }

    /**
     * 查询全部
     *
     * @param cursor
     * @param db
     * @param collectionName
     */
    public static DBCursor findAll(DBObject dbObject, CursorObject cursor,
        DB db, String collectionName) {
        return find(dbObject, cursor, db, collectionName);
    }

    /**
     * 查询汇总数
     *
     * @param map
     * @param db
     * @param collectionName
     * @return
     */
    public static long count(Map<String, Object> map, DB db,
        String collectionName) {
        DBObject dbObject = getMapped(map);
        return getCollection(db, collectionName).count(dbObject);

    }

    /**
     * 通过map查询结果
     *
     * @param map
     * @param cursor
     * @param db
     * @param collectionName
     */
    public static DBCursor find(Map<String, Object> map, CursorObject cursor,
        DB db, String collectionName) {
        DBObject dbObject = getMapped(map);
        return find(dbObject, cursor, db, collectionName);
    }

    /**
     * 排序方法实现
     *
     * @param dbObject
     * @param cursor
     * @param db
     * @param collectionName
     */
    public static DBCursor find(DBObject dbObject, final CursorObject cursor,
        DB db, String collectionName) {
        CursorPreparer cursorPreparer = cursor == null ? null
            : new CursorPreparer() {
                @SuppressWarnings("unused")
                public DBCursor prepare(DBCursor dbCursor) {
                    if (cursor == null) {
                        return dbCursor;
                    }
                    if (cursor.getLimit() <= 0 && cursor.getSkip() <= 0
                        && cursor.getSortObject() == null) {
                        return dbCursor;
                    }
                    DBCursor cursorToUse = dbCursor;
                    if (cursor.getSkip() > 0) {
                        cursorToUse = cursorToUse.skip(cursor.getSkip());
                    }
                    if (cursor.getLimit() > 0) {
                        cursorToUse = cursorToUse.limit(cursor.getLimit());
                    }
                    if (cursor.getSortObject() != null) {
                        cursorToUse = cursorToUse.sort(cursor.getSortObject());
                    }
                    return cursorToUse;
                }
            };
        return find(dbObject, cursor, cursorPreparer, db, collectionName);
    }

    /**
     * 查询结果并过滤排序
     *
     * @param dbObject
     * @param cursor
     * @param cursorPreparer
     * @param db
     * @param collectionName
     */
    public static DBCursor find(DBObject dbObject, CursorObject cursor,
        CursorPreparer cursorPreparer, DB db, String collectionName) {
        DBCursor dbCursor = getCollection(db, collectionName).find(dbObject);
        if (cursorPreparer != null) {
            dbCursor = cursorPreparer.prepare(dbCursor);
        }
        return dbCursor;
    }

    /**
     * Map转DBObject
     *
     * @param map
     * @return
     */
    private static DBObject getMapped(Map<String, Object> map) {
        DBObject dbObject = new BasicDBObject();
        Iterator<Entry<String, Object>> iterable = map.entrySet().iterator();
        while (iterable.hasNext()) {
            Entry<String, Object> entry = iterable.next();
            Object value = entry.getValue();
            String key = entry.getKey();
            if (key.startsWith("$") && value instanceof Map) {
                BasicBSONList basicBSONList = new BasicBSONList();
                Map<String, Object> conditionsMap = ((Map) value);
                Set<String> keys = conditionsMap.keySet();
                for (String k: keys) {
                    Object conditionsValue = conditionsMap.get(k);
                    if (conditionsValue instanceof Collection) {
                        conditionsValue = convertArray(conditionsValue);
                    }
                    DBObject dbObject2 = new BasicDBObject(k, conditionsValue);
                    basicBSONList.add(dbObject2);
                }
                value = basicBSONList;
            } else if (value instanceof Collection) {
                value = convertArray(value);
            } else if (!key.startsWith("$") && value instanceof Map) {
                value = getMapped(((Map) value));
            }
            dbObject.put(key, value);
        }
        return dbObject;
    }

    /**
     * Collection对象转换为数组
     *
     * @param value
     * @return
     */
    private static Object[] convertArray(Object value) {
        Object[] values = ((Collection) value).toArray();
        return values;
    }

}
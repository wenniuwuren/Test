package com.wenniuwuren.mongodb.utils;

import com.mongodb.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * mongo管理器（单例）
 *
 */
public class MongoDBManager {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory
        .getLogger(MongoDBManager.class);

    // 唯一实例
    private static volatile MongoDBManager instance;

    private Mongo mongo;

    /**
     * 实例初始化、参数构造完成
     *
     * @param
     * @return
     * @throws Exception
     */
    public static MongoDBManager getInstance() throws MongoException {
        try {
            if (instance == null) {
                synchronized (MongoDBManager.class) {
                    if (instance == null) {
                        instance = new MongoDBManager();
                        instance.init();
                    }
                }
            }
        } catch (Exception e) {
            throw new MongoException("SYSTEM_DB_ACCESS_ERROR", e);
        }
        return instance;
    }


    /**
     * 获取DB
     *
     * @param dbName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public DB getDB(String dbName) {
        return mongo.getDB(dbName);
    }

    /**
     * 获取mongo实例
     *
     * @return
     */
    public Mongo getMongo() {
        return mongo;
    }

    /**
     * mongo初始化
     *
     * @throws UnknownHostException
     * @throws Exception
     */
    private void init() throws UnknownHostException {
        if (mongo == null) {
            // 集群地址列表
            List<ServerAddress> addressList = MongoConfig.getAddressList();

            // mongo配置项
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            int connections = MongoConfig.getConnectionsPerHost();
            int connTimeoutMS = MongoConfig.getConnectionTimeOutMS();
            int socTimeoutMS = MongoConfig.getSocketTimeOutMS();
            int waitTimeoutMS = MongoConfig.getWaitTimeOutMS();
            builder.connectionsPerHost(connections);
            builder.connectTimeout(connTimeoutMS);
            builder.socketTimeout(socTimeoutMS);
            builder.maxWaitTime(waitTimeoutMS);
            MongoClientOptions options = builder.build();

            // 数据库授权
            List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
            String name = MongoConfig.getDatabaseName();
            String userName = MongoConfig.getUserName();
            String passWord = MongoConfig.getPassword();
            if(!StringUtils.isBlank(userName) && !StringUtils.isBlank(passWord)){
                mongoCredentialList.add(MongoCredential
                        .createCredential(userName, name,passWord.toCharArray()));
            }
            mongo = new MongoClient(addressList, mongoCredentialList, options);
            // 开启 replica set 读
            mongo.setReadPreference(ReadPreference.secondaryPreferred());
            // 开启写关注
            mongo.setWriteConcern(WriteConcern.JOURNALED);
        }
    }

}
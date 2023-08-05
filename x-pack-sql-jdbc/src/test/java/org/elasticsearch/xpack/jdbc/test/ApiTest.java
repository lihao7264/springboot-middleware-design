package org.elasticsearch.xpack.jdbc.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class ApiTest {

    static Logger logger = LoggerFactory.getLogger(ApiTest.class);

    public static void main(String[] args) throws Exception {
        String address = "jdbc:es://http://127.0.0.1:9200";
        Connection connection = DriverManager.getConnection(address, new Properties());
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT  userId, userNickName, userHead, userPassword, createTime, updateTime FROM atlihao");
        while (results.next()) {
            logger.info(" userId：{} useNickName：{} userHead：{} userPassword：{} updateTime：{}",
                    results.getString("userNickName"),
                    results.getString("userHead"),
                    results.getString("userPassword"),
                    results.getString("createTime"),
                    results.getString("updateTime"));
        }
    }

}

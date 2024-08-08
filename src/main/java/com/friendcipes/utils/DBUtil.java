package com.friendcipes.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);
    private SecretsUtil secretsUtil;
    private static final String JDBC_PREFIX = "jdbc:postgresql://";

    @Inject
    public DBUtil(SecretsUtil secretsUtil){
        this.secretsUtil = secretsUtil;
    }

    public Connection establishConnection() throws SQLException {
        logger.info("Establishing database connection: ");
        String url = JDBC_PREFIX + secretsUtil.getSecret("RDS_URL") + ":" + secretsUtil.getSecret("RDS_PORT");
        String username = secretsUtil.getSecret("RDS_USER");
        String password = secretsUtil.getSecret("RDS_PASSWORD");
        logger.info("Database connection established. ");
        return DriverManager.getConnection(url, username, password);
    }
}

package org.gimeast.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    void testConnection() throws Exception {
        //given
        Class.forName("org.mariadb.jdbc.Driver");

        //when
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "12345678"
        );

        //then
        Assertions.assertNotNull(connection);

        connection.close();

    }

    @Test
    void testHikariCP() throws Exception {
        //given
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("12345678");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        //when
        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        //then
        System.out.println(connection);

        connection.close();

    }



}

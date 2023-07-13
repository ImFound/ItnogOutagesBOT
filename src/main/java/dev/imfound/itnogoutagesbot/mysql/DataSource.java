package dev.imfound.itnogoutagesbot.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.imfound.itnogoutagesbot.obj.settings.MySQLSettings;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    private static final int MAXIMUM_POOL_SIZE = (Runtime.getRuntime().availableProcessors() * 2) + 1;

    private static final int MINIMUM_IDLE = 30000;

    private static final long CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(10);

    public DataSource(MySQLSettings settings) {

        config.setJdbcUrl("jdbc:mysql://" + settings.getMysqlHost() + ":" + settings.getMysqlPort() + "/" + settings.getMysqlDatabase() + "?autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false");
        config.setUsername(settings.getMysqlUsername());
        config.setPassword(settings.getMysqlPassword());
        config.setPoolName("ITNOGOutagesBOT-pool");
        config.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
        config.setMinimumIdle(MINIMUM_IDLE);
        config.setMaxLifetime(30000);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);


    }

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    @SneakyThrows
    public void close() {
        if(!dataSource.isClosed()) {
            dataSource.close();
        }
    }

}

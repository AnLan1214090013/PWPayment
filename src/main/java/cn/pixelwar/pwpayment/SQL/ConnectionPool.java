package cn.pixelwar.pwpayment.SQL;

import cn.pixelwar.pwpayment.PWPayment;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

    private final HikariDataSource hikariDataSource;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = PWPayment.config.getString("mysql.username");
    private final String PASSWORD = PWPayment.config.getString("mysql.password");
    private final String IP = PWPayment.config.getString("mysql.address");
    private final String PORT = PWPayment.config.getString("mysql.port");
    private final String DATABASE = PWPayment.config.getString("mysql.database");
    private final String URL = "jdbc:mysql://"+IP+":"+PORT+"/"+DATABASE+"?charactorEncoding=utf-8&useSSL=false";
    private final long connectionTimeout = PWPayment.config.getLong("mysql.pool.connectionTimeout");
    private final long connectionIdleTimeout = PWPayment.config.getLong("mysql.pool.connectionIdleTimeout");
    private final int maxConnectionAmount = PWPayment.config.getInt("mysql.pool.maxConnectionAmount");
    public ConnectionPool(){
        this.hikariDataSource = new HikariDataSource();
        this.hikariDataSource.setDriverClassName(DRIVER);
        this.hikariDataSource.setJdbcUrl(URL);
        this.hikariDataSource.setUsername(USER);
        this.hikariDataSource.setPassword(PASSWORD);
        this.hikariDataSource.setPoolName("PWPayment");
        this.hikariDataSource.setConnectionTimeout(connectionTimeout);
        this.hikariDataSource.setIdleTimeout(connectionIdleTimeout);
        this.hikariDataSource.setMaximumPoolSize(maxConnectionAmount);
        this.hikariDataSource.setMinimumIdle(maxConnectionAmount);
    }





    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }
}

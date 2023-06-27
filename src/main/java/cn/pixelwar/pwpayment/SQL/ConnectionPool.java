package cn.pixelwar.pwpayment.SQL;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

    private final HikariDataSource hikariDataSource;

    public ConnectionPool(){
        this.hikariDataSource = new HikariDataSource();
        getHikariDataSource().setIdleTimeout();
    }

    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }
}

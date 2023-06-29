package cn.pixelwar.pwpayment.SQL;

import cn.pixelwar.pwpayment.PWPayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Execution {


    public void closeConnection(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet){
        try{
            if(!(connection.isClosed())) {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                connection.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (NullPointerException e2){
            e2.printStackTrace();

        }
    }

    public void createTables(Connection connection){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `player_rank`(`uuid` VARCHAR(100) NOT NULL, `playername` VARCHAR(100) NOT NULL, `rank` VARCHAR(100) NOT NULL, PRIMARY KEY ( `uuid` ))");
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(preparedStatement,connection,null);
        }

    }



}

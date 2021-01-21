package cn.sth.shop.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ClassName:DataBaseConnection
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/7 15:28
 * Author:沙天慧
 */
public class DataBaseConnection {
    public static final String DBDRIVER="com.mysql.cj.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC";
    public static final String DBUSER="root";
    public static final String DBPASSWARD="mysqladmin";
    public Connection conn=null;
    public DataBaseConnection(){
        try {
            Class.forName(DBDRIVER);
            this.conn= DriverManager.getConnection(DBURL,DBUSER,DBPASSWARD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.conn;
    }
    public void close(){
        if(this.conn!=null){
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

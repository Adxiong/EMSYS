package UTILS;

import java.sql.*;

public class DBManager {
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ms?useUnicode=true&characterEncoding=utf-8";
    private static final String USERNAME = "ms";
    private static final String PASSWORD = "ms";
    static {
        try {
            Class.forName(DRIVER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        Connection conn = null;
        try{
            conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn , Statement stmt){
        close(conn,stmt,null);
    }
    public static void close(Connection conn , Statement stmt , ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

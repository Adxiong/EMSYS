package DAO;

import DTO.UserDTO;
import UTILS.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn ;
    private PreparedStatement pstmt;
    private ResultSet rs ;

    public UserDTO checkLogin(String user, String pwd){
        UserDTO mgr = null;
        try{
            conn = DBManager.getConn();
            String sql = "select * from user where user=? and pwd=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user);
            pstmt.setString(2,pwd);
            rs = pstmt.executeQuery();
            if(rs.next()){
                int mid = rs.getInt("no");
                mgr = new UserDTO(mid , user ,pwd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt,rs);
        }
        return mgr;
    }

    public Boolean setNewPwd(String user, String pwd){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            String sql = "update user set pwd=? where user=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pwd);
            pstmt.setString(2,user);
            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }
}

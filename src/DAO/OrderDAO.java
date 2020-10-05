package DAO;

import DTO.CompanyDTO;
import DTO.OrderDTO;
import UTILS.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection conn ;
    private PreparedStatement pstmt;
    private ResultSet rs ;

    public Boolean addOrder(String name , String send , String tel , String desc ){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            CompanyDTO code = new CompanyDAO().searchCode(name);
            if(code==null){return false;}
            String no = code.getCode() + String.valueOf(System.currentTimeMillis() / 1000);
            String sql = "insert into ms.order(`no`,`send`,`tel`,`desc`,`cno`) values(?, ? , ? , ? , ? )";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,no);
            pstmt.setString(2,send);
            pstmt.setString(3,tel);
            pstmt.setString(4,desc);
            pstmt.setInt(5,code.getNo());

            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }

    public Boolean updateOrder(String no ,String tel , String desc){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            String sql = "update `order` set tel=? , `desc`=? where no=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,tel);
            pstmt.setString(2,desc);
            pstmt.setString(3,no);
            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }

    public Boolean delOrder(String no ){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            String sql = "delete from `order` where no=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,no);
            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }

    public List<OrderDTO> searchAllOrder(){
        List<OrderDTO> cm = new ArrayList<OrderDTO>();
        try{
            conn = DBManager.getConn();
            String sql = "select * from `order` where 1";
            pstmt=conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                OrderDTO c = new OrderDTO(rs.getString("no"),rs.getString("send"),rs.getString("tel"),rs.getString("desc"),rs.getInt("cno"));
                cm.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return cm;
    }

    public OrderDTO searchOrder(String no){
        OrderDTO cm = new OrderDTO();
        try{
            conn = DBManager.getConn();
            String sql = "select * from `order` where no=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,no);
            rs = pstmt.executeQuery();
            if(rs.next()){
                cm = new OrderDTO(rs.getString("no"),rs.getString("send"),rs.getString("tel"),rs.getString("desc"),rs.getInt("cno"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return cm;
    }

}

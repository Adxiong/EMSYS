package DAO;

import DTO.CompanyDTO;
import UTILS.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    private Connection conn ;
    private PreparedStatement pstmt;
    private ResultSet rs ;

    public Boolean addCompany(String name , String code){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            String sql = "insert into company(name ,code) values(? , ?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,code);
            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }

    public Boolean updateCompany(String name , String code){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            String sql = "update company set code=? where name=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,code);
            pstmt.setString(2,name);
            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }

    public Boolean delCompany(String name , String code){
        int rs = 0;
        try{
            conn = DBManager.getConn();
            String sql = "delete from company where name=? and code=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,code);
            rs = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }
        return rs>0?true:false;
    }

    public List<CompanyDTO> searchAllCompany(){
        List<CompanyDTO> cm = new ArrayList<CompanyDTO>();
        try{
            conn = DBManager.getConn();
            String sql = "select * from company where 1";
            pstmt=conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                CompanyDTO c = new CompanyDTO(rs.getInt("no"), rs.getString("name"),rs.getString("code"));
                cm.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return cm;
    }

    public CompanyDTO searchCompany(String name){
        CompanyDTO cm = new CompanyDTO();
        try{
            conn = DBManager.getConn();
            String sql = "select * from company where name=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            rs = pstmt.executeQuery();
            if(rs.next()){
                cm = new CompanyDTO(rs.getInt("no"), rs.getString("name"),rs.getString("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return cm;
    }

    public CompanyDTO searchCode(String name){
        CompanyDTO c = null;
        try{
            conn = DBManager.getConn();
            String sql = "select * from company where name=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()){
                c= new CompanyDTO(rs.getInt("no" ), rs.getString("name"),rs.getString("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return c;
    }
    public List<String> searchAllcompanyName(){
        List<String> c = new ArrayList<String>();
        try{
            conn = DBManager.getConn();
            String sql = "select name from company where 1";
            pstmt=conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String cc = rs.getString("name");
                c.add(cc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return c;
    }

    public String searchAllcompanyNameByNo(int no){
        String c = null;
        try{
            conn = DBManager.getConn();
            String sql = "select name from company where no=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,no);
            rs = pstmt.executeQuery();
            if (rs.next()){
                c = rs.getString("name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt , rs);
        }
        return c;
    }
}

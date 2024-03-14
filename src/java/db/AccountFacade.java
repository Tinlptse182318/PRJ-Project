/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import hashing.Hasher;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHT
 */
public class AccountFacade {
    public Account login(String email, String password) throws SQLException, NoSuchAlgorithmException{
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("select * from Account where email=? and password=?");
        //Cung cấp giá trị cho các tham số
        stm.setString(1, email);
        stm.setString(2, Hasher.hash(password));
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery();
        Account account = null;
        if (rs.next()) {
            account = new Account();
            //Doc mau tin hien hanh de vao doi tuong toy            
            account.setId(rs.getInt("id"));
            account.setEmail(rs.getString("email"));
            account.setFullName(rs.getString("fullName"));
            account.setRoleId(rs.getString("roleId"));
            account.setPassword(rs.getString("password"));
        }
        con.close();
        return account;
    }
  public List<Account> select() throws SQLException {
        List<Account> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from account");
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin hien hanh de vao doi tuong 
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setEmail(rs.getString("email"));
            acc.setFullName(rs.getString("fullName"));
            acc.setRoleId(rs.getString("roleId"));
            acc.setPassword(rs.getString("password"));
            //Them Account vao list
            list.add(acc);
        }
        con.close();
        return list;
    }

    public Account select(int id) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("select * from Account where id=?");
        //Cung cấp giá trị cho các tham số 
        stm.setInt(1, id);
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery();
        Account acc = new Account();
        if (rs.next()) {
            //Doc mau tin hien hanh de vao doi tuong 
            acc.setId(rs.getInt("id"));
            acc.setEmail(rs.getString("email"));
            acc.setFullName(rs.getString("fullName"));
            acc.setRoleId(rs.getString("roleId"));
            acc.setPassword(rs.getString("password"));
        }
        con.close();
        return acc;
    }
    
    public void create(Account acc) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("insert into Account values(?,?,?,?)");
        //Cung cấp giá trị cho các tham số 
        stm.setString(1, acc.getEmail());
        stm.setString(2, acc.getFullName());
        stm.setString(3, acc.getRoleId());
        stm.setString(4, acc.getPassword());
        //Thực thi lệnh INSERT
        int count = stm.executeUpdate();
    }

    public void update(Account acc) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("update Account set email=?, fullname=?, roleid=?, password=? where id=?");
        //Cung cấp giá trị cho các tham số 

        stm.setString(1, acc.getEmail());
        stm.setString(2, acc.getFullName());
        stm.setString(3, acc.getRoleId());
        stm.setString(4, acc.getPassword());
        stm.setInt(5, acc.getId());
        //Thực thi lệnh UPDATE
        int count = stm.executeUpdate();
        con.close();
    }

    public void delete(int id) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("delete Account where id=?");
        //Cung cấp giá trị cho các tham số 
        stm.setInt(1, id);
        //Thực thi lệnh DELETE
        int count = stm.executeUpdate();
        con.close();
    }

    public boolean checkEmailExistence(String email) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
         con = DBContext.getConnection();
        stm = con.prepareStatement("select * from Account where email = ?");
        stm.setString(1, email);
        rs = stm.executeQuery();
        
        if (rs.next()) {
            return true;
        }
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}

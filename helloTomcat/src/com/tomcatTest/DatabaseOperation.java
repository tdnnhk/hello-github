package com.tomcatTest;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseOperation {
    
    public static void main(String args[]) {
        DatabaseOperation.getAll();
        //DatabaseOperation.insert(new FoodModel("鹌鹑蛋", "鹌鹑蛋", "直接煮"));
        DatabaseOperation.getAll();
        //DatabaseOperation.update(new Student("Bean", "", "7"));
        //DatabaseOperation.delete("Achilles");
        DatabaseOperation.getAll();
    }
    
    
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3333/whatseat";
        String username = "root";
        String password = "mysql";
        Connection conn = null;
        try {
            System.out.println("in try");
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("conn"+conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static int insert(FoodModel food) {
        Connection conn = getConn();        
        int i = 0;
        String sql = "insert into foodmenutest (name,material,method) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, food.getTitle());
            System.out.println("food.getTitle()"+food.getTitle());
            pstmt.setString(2, food.getMaterial());
            pstmt.setString(3, food.getMethod());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    private static int delete(String name) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from foodmenutest where Name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    private static int update(FoodModel food) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update foodmenutest set material='" + food.getMaterial() + "' where name='" + food.getTitle() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    private static Integer getAll() {
        Connection conn = getConn();
        System.out.println("has connected"+conn);
        String sql = "select * from foodmenutest";
        PreparedStatement pstmt;
        try {
            conn.prepareStatement("use whatseat");
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                 }
                System.out.println("");
            }
                System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

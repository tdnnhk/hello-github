package com.tomcatTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseOperation {
    
    public static void main(String args[]) {
        //DatabaseOperation.getRandomRow();
        /*UserModel userModel = new UserModel();
        userModel.setUsername("name");
        userModel.setPassword("pass");
        userModel.setSalt("salt");
        DatabaseOperation.insertUser(userModel);*/
        
        try {

            System.out.println(Md5SaltTool.validPassword("cd","D1B215D611F94ACB36DE2AB1578D41EC11B468A7B8EAD327C1ABD221"));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //DatabaseOperation.getAll();
        //DatabaseOperation.update(new Student("Bean", "", "7"));
        //DatabaseOperation.delete("Achilles");
        //DatabaseOperation.getAll();
    }
    
    
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3333/whatseat";
        String username = "root";
        String password = "mysql";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
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
    
    public static void delete(String name) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from foodmenutest where Name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ;
    }
    
    private static int update(FoodModel food) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update foodmenutest set material='" + food.getMaterial() + "' where name='" + food.getTitle() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    private static Integer getAll() {
        Connection conn = getConn();
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
    
    public static ArrayList<FoodModel> getContent() {
        Connection conn = getConn();
        String sql = "select * from foodmenutest";
        PreparedStatement pstmt;
        ArrayList<FoodModel> foodList = new ArrayList<FoodModel>();
        try {
            conn.prepareStatement("use whatseat");
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();            
            while (rs.next()) {
                FoodModel fm = new FoodModel();
                for (int i = 2; i <= col; i++) {
                    String columnName = rs.getString(i);
                    if(i==2) {
                        fm.setTitle(columnName);
                    }else if (i==3) {
                        fm.setMaterial(columnName);
                    }else if (i==4) {
                        fm.setMethod(columnName);
                    }
                 }
                foodList.add(fm);
            }
            return foodList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    protected static FoodModel getRandomRow() {
        Connection conn = getConn();
        String sql = "select count(*) from foodmenutest";
        PreparedStatement pstmt;
        try {
            conn.prepareStatement("use whatseat");
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //获取总行数
            int col = rs.getInt(1);
            //对总行数进行随机，获取某一行内容
            Random rand =new Random();
            int i=0;
            i=rand.nextInt(col);
        
/*            System.out.println("============================");
            System.out.println(rs.getInt(1));
            System.out.println(getRandomRowContent(i).getTitle());
            System.out.println("============================");
*/
            return getRandomRowContent(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private static FoodModel getRandomRowContent(int count) {
        Connection conn = getConn();
        String sql = "select * from foodmenutest limit "+count+",1";
        PreparedStatement pstmt;
        try {
            conn.prepareStatement("use whatseat");
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();            
            rs.next();
            
            FoodModel fm = new FoodModel();
            for (int i = 2; i <= col; i++) {
                String columnName = rs.getString(i);
                if(i==2) {
                    fm.setTitle(columnName);
                }else if (i==3) {
                    fm.setMaterial(columnName);
                }else if (i==4) {
                    fm.setMethod(columnName);
                }
             }
            return fm;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    
    
    
    
    
    //user part
    public static int insertUser(UserModel user) {
        Connection conn = getConn();        
        int i = 0;
        String sql = "insert into usertable (username,password,salt) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSalt());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
  //user part
    public static UserModel getPasswordInDb(String username) {
        Connection conn = getConn();        
        //int i = 0;
        String sql = "select * from usertable where username = '" + username + "'";
        PreparedStatement pstmt;
        UserModel um = new UserModel();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);            
            ResultSet rs = pstmt.executeQuery();            
            rs.next();
            
            //FoodModel fm = new FoodModel();
            if(rs != null) {
                int col = rs.getMetaData().getColumnCount(); 
                System.out.println(col);
                for (int i = 2; i <= col; i++) {
                    String columnName = rs.getString(i);
                    if(i==2) {
                        um.setUsername(columnName);
                    }else if (i==3) {
                        um.setPassword(columnName);
                    }else if (i==4) {
                        um.setSalt(columnName);
                    }
                 }
            }
            
            
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return um;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

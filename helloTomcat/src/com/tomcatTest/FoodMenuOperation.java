package com.tomcatTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/FoodMenuOperation")
public class FoodMenuOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FoodMenuOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("type");
        
        if (type.equals("AddFood")) {
            addFood(request,response);
        }else if (type.equals("DeleteFood")) {
            deleteFood(request,response);
        }else if (type.equals("randomFood")) {
            randomFood(request,response);
        }
     
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void addFood(HttpServletRequest request, HttpServletResponse response) {
	    PrintWriter out;
        try {
            out = response.getWriter();
            //String title = "输入成功！";
            HttpSession session = request.getSession();
            
            // 处理中文
            String name =new String(request.getParameter("cook-name"));
            String material =new String(request.getParameter("material"));  
            String method =new String(request.getParameter("method"));
            String username = session.getAttribute("loginName").toString();
            String id = DatabaseOperation.getUserId(username);
            System.out.println("username："+username); 
            /*String docType = "<!DOCTYPE html> \n";
            out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>菜名</b>："
                + name + "\n" +
                "  <li><b>材料</b>："
                + material + "\n" +
                "  <li><b>做法</b>："
                + method + "\n" +
                "</ul>\n" +
                "</body></html>");*/
            FoodModel food = new FoodModel();
            food.setTitle(name);
            food.setMaterial(material);
            food.setMethod(method);
            food.setUsername(id);
            DatabaseOperation.insert(food);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
	
	private void deleteFood(HttpServletRequest request, HttpServletResponse response) {
	   
	    String title = "删除成功！";
        // 处理中文
        String name;
        try {
            PrintWriter out = response.getWriter();
            name = new String(request.getParameter("cook-name"));
            if(name!=null && name!="") {
                DatabaseOperation.delete(name);
                out.println(title);
            }else {
                out.println("请输入正确内容");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
	
	private void randomFood(HttpServletRequest request, HttpServletResponse response) {
	       
        // 处理中文
        //String name;
        try {
            PrintWriter out = response.getWriter();
            FoodModel food = DatabaseOperation.getRandomRow();
            out.println("<br>");
            out.println("菜名："+food.getTitle());
            out.println("材料："+food.getMaterial());
            out.println("做法："+food.getMethod());
            out.println("<br>");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}

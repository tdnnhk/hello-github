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
            String title = "输入成功！";
            
            // 处理中文
            String name =new String(request.getParameter("cook-name").getBytes("ISO8859-1"),"UTF-8");
            String material =new String(request.getParameter("material").getBytes("ISO8859-1"),"UTF-8");  
            String method =new String(request.getParameter("method").getBytes("ISO8859-1"),"UTF-8");        
            
            String docType = "<!DOCTYPE html> \n";
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
                "</body></html>");
            FoodModel food = new FoodModel();
            food.setTitle(name);
            food.setMaterial(material);
            food.setMethod(method);
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
            name = new String(request.getParameter("cook-name").getBytes("ISO8859-1"),"UTF-8");
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

}

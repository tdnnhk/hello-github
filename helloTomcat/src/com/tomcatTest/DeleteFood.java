package com.tomcatTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FoodMenu
 */
//@WebServlet("/DeleteFood")
public class DeleteFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        //DatabaseOperation dbOperation = new DatabaseOperation();
        System.out.println("in delete food");
        PrintWriter out = response.getWriter();
        String title = "删除成功！";
        // 处理中文
        String name =new String(request.getParameter("cook-name").getBytes("ISO8859-1"),"UTF-8");
        
        DatabaseOperation.delete(name);
        out.println(title);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

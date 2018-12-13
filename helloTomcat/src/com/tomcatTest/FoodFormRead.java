package com.tomcatTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FoodFormRead
 */
//@WebServlet("/FoodFormRead")
public class FoodFormRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodFormRead() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String pathName = "D:\\learn\\projects\\hello-github\\test.txt";
		File fileName = new File(pathName);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
		BufferedReader br = new BufferedReader(reader);
		String line ="";
		//每次调用readLine()就会跳到下一行，所以这里就刚好错过了菜名
		while((line = br.readLine()) != null) {
			//line = br.readLine();
			//String lines =new String(line.getBytes("ISO8859-1"),"UTF-8");
			System.out.println(line);
			out.println(line+"<br>");
		}
		//out.println("test");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

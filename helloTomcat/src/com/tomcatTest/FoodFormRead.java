package com.tomcatTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

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

	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("content-type","text/html;charset=UTF-8");
	    
	    ArrayList<FoodModel> fmList = DatabaseOperation.getContent();
	    PrintWriter out = response.getWriter();
	    for (Iterator<FoodModel> list = fmList.iterator(); list.hasNext();) {
	           FoodModel food = new FoodModel();
	           food = list.next();
	           out.println("<br>");
	           out.println("菜名："+food.getTitle());
	           out.println("材料："+food.getMaterial());
	           out.println("做法："+food.getMethod());
	           out.println("<br>");
	    }
	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.tomcatTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserOperation
 */
//@WebServlet("/SessionTest")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("SessionTest");
        test(request,response);

	}

	
	private void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        System.out.println("session: "+session.getAttribute("loginName"));
        String username = session.getAttribute("loginName").toString();
        String id = DatabaseOperation.getUserId(username);
        System.out.println("id: "+id);
        ArrayList<FoodModel> foodList = DatabaseOperation.getUserFoodList(id);
        //System.out.println("foodList: "+foodList);
        System.out.println("foodList: "+foodList.size());
        
        PrintWriter out = response.getWriter();
        //System.out.println("toJson: "+new Gson().toJson(foodList).toString());
        out.write(new Gson().toJson(foodList));


    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

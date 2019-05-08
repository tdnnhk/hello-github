package com.tomcatTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserOperation
 */
@WebServlet("/UserOperation")
public class UserOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("type");
        
        if (type.equals("submitInfo")) {
            signUp(request,response);
        }else if (type.equals("signin")) {
            signIn(request,response);
        }
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) {
	    
	    String name = request.getParameter("uesrname");
	    String pass = request.getParameter("password");
	    
	    UserModel userModel;
        try {
            userModel = Md5SaltTool.getEncryptedPwd(pass);
            userModel.setUsername(name);
            DatabaseOperation.insertUser(userModel);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }
	
	private void signIn(HttpServletRequest request, HttpServletResponse response) {
        
        String name = request.getParameter("uesrname");
        String pass = request.getParameter("password");
        
        //需要写从数据库通过用户名获取密码的function
        UserModel userModel = new UserModel();
        try {
            userModel = DatabaseOperation.getPasswordInDb(name);
            if(Md5SaltTool.validPassword(pass,userModel.getPassword())) {
                System.out.println("correct");
            }else {
                System.out.println("wrong");
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

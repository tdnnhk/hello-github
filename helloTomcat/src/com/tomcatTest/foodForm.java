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
 * Servlet implementation class HelloForm
 */
@WebServlet("/foodForm")
public class foodForm extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public foodForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "使用 GET方法读取表单数据";
        // 处理中文
        String name =new String(request.getParameter("cook-name").getBytes("ISO8859-1"),"UTF-8");
        String material =new String(request.getParameter("material").getBytes("ISO8859-1"),"UTF-8");        
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor=\"#f0f0f0\">\n" +
            "<h1 align=\"center\">" + title + "</h1>\n" +
            "<ul>\n" +
            "  <li><b>站点名</b>："
            + name + "\n" +
            "  <li><b>吃的</b>："
            + material + "\n" +
            "</ul>\n" +
            "</body></html>");
        
        File file = new File("E:\\User\\Guanyu\\learn\\helloTomcat\\test.txt");
        if(!file.exists()){
        	file.createNewFile();
        	System.out.print("创建文件");
        }
        FileOutputStream fop = new FileOutputStream(file,true);
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        
        writer.append("菜名：" + name);
        // 写入到缓冲区        
        writer.append("\r\n");
        //换行
        
        writer.append("材料：" + material);
        writer.append("\r\n");
        writer.append("\r\n");
        writer.append("\r\n");
        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入

        writer.close();
        //关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
        
        fop.close();
        // 关闭输出流,释放系统资源
        
    }
    
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
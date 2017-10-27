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
        // ������Ӧ��������
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "ʹ�� GET������ȡ������";
        // ��������
        String name =new String(request.getParameter("cook-name").getBytes("ISO8859-1"),"UTF-8");
        String material =new String(request.getParameter("material").getBytes("ISO8859-1"),"UTF-8");        
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor=\"#f0f0f0\">\n" +
            "<h1 align=\"center\">" + title + "</h1>\n" +
            "<ul>\n" +
            "  <li><b>վ����</b>��"
            + name + "\n" +
            "  <li><b>�Ե�</b>��"
            + material + "\n" +
            "</ul>\n" +
            "</body></html>");
        
        File file = new File("E:\\User\\Guanyu\\learn\\helloTomcat\\test.txt");
        if(!file.exists()){
        	file.createNewFile();
        	System.out.print("�����ļ�");
        }
        FileOutputStream fop = new FileOutputStream(file,true);
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        
        writer.append("������" + name);
        // д�뵽������        
        writer.append("\r\n");
        //����
        
        writer.append("���ϣ�" + material);
        writer.append("\r\n");
        writer.append("\r\n");
        writer.append("\r\n");
        // ˢ�»����,д�뵽�ļ�,��������Ѿ�û��д���������,ֱ��closeҲ��д��

        writer.close();
        //�ر�д����,ͬʱ��ѻ���������д���ļ�,���������ע�͵�
        
        fop.close();
        // �ر������,�ͷ�ϵͳ��Դ
        
    }
    
    // ���� POST ��������ķ���
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
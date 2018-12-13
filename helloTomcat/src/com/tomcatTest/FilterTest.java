package com.tomcatTest;

import javax.servlet.*;
import java.util.*;

public class FilterTest implements Filter{
	
	public FilterTest() {
        System.out.println("1.创建过滤器实例");
    }
	public void  init(FilterConfig config) throws ServletException {
		System.out.println("2.执行过滤器初始化方法");
        // 获取初始化参数
        String site = config.getInitParameter("Site"); 

        // 输出初始化参数
        System.out.println("网站名称: " + site); 
    }
	public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
		System.out.println("3.执行过滤器业务处理方法");
        // 输出站点名称
        System.out.println("站点网址：http://www.runoob.com");

        // 把请求传回过滤链
        chain.doFilter(request,response);
        System.out.println("5.Servlet处理完成，又回到过滤器");
    }
    public void destroy( ){
    	 System.out.println("6.销毁过滤器实例");
         //在 Filter 实例被 Web 容器从服务移除之前调用 
    }
}

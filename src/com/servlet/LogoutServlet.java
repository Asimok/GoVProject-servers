package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Logout;

import net.sf.json.JSONObject;


public class LogoutServlet extends HttpServlet {
	 @Override
	 protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        // TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
        System.out.println("连接成功反馈");// 测试是否成功连接
        StringBuffer json1 = new StringBuffer();// 字符流
        String line = null;
        BufferedReader reader = request.getReader();// 读取流
        while ((line = reader.readLine()) != null) {
            json1.append(line);// 接受的是JSON格式
        }

        System.out.println("传入的"+json1);//得到的是JSON格式
        
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
        
        String Token = jsonObject.getString("Token");//获取Json键值对
        boolean IsSuccess=Logout.DeleteToken(Token);
        if(IsSuccess)
        {
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"quit\"}";//组装json格式的字符串来传送
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        }
        else
        {
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"notquit\"}";//组装json格式的字符串来传送
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        }
	}

	 @Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	 

	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.flush();
	        out.close();
	    }

}

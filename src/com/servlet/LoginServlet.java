package com.servlet;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Login;
import com.tools.EmployeeNumberChecker;
import com.tools.FilterManage;
import com.tools.SearchZHandToken;
import com.tools.TokenUtil;
import com.tools.tojson;
import com.tools.tomd5;

import net.sf.json.JSONObject;
/*
 * 登录 servlet
 * 
 * */
public class LoginServlet extends HttpServlet {
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
        // System.out.println(json1.toString());//得到的是字符串
        // 把得到的字符串封装为JSON，再获取里面的传过来用户名
        
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
        
        String zhanghu = jsonObject.getString("zhanghu");//获取Json键值对
        String mima = tomd5.tomd5(jsonObject.getString("mima"));
       // tomd5.tomd5(mima);
        String Token=jsonObject.getString("Token");
        System.out.println("传入的账户 "+zhanghu);
        System.out.println("传入的密码 "+mima);
        System.out.println("传入的token "+Token);
        FilterManage check1 = new FilterManage();
        check1.addChecker(new EmployeeNumberChecker());
		if((check1.doChain_check(zhanghu)))
		{					       

        if(Token==null)
        {
        	response.setContentType("text/html;charset=utf-8"); 
   	        PrintWriter out = response.getWriter();     
   	        String  info_json  = "{\"status\":\"-3\"}";//token为空
   	        System.out.println(info_json);
   	        out.write(info_json);
   	        out.flush();
   	        out.close();
        }
        else {
        String Password = Login.selectNumber(zhanghu);//调用login类方法
      //  if(!Password==null) {
       if(Password.equals(mima))   //如果密码正确
        {
    	
    	  
    	  if(!SearchZHandToken.searchZH(zhanghu))
    	  {  
    		//如果该账户对应的token为空  插入
       	   Login.InsertToken(zhanghu,Token);
       	   System.out.println("插入token成功 ");
    	  }
    	  else {
    		  Login.UpdateToken(zhanghu,Token);//每登陆成功更新token
    	  }
        System.out.println("查出的密码 "+Password);
        System.out.println("查出的密码tomd5 "+tomd5.tomd5(Password));
        System.out.println("更新token ");
        PrintWriter out = response.getWriter();
        String  info_json  = "{\"status\":\"0\"}";//登陆成功
        System.out.println(info_json);
        out.write(info_json);
        out.flush();
        out.close();
    }
       
       else {//如果密码错误
    	   
    	   response.setContentType("text/html;charset=utf-8");
    	   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"-1\"}";//密码错误
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
 
       } }
		}
		else {
			response.setContentType("text/html;charset=utf-8");
	    	   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"-2\"}";//账户名非法
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
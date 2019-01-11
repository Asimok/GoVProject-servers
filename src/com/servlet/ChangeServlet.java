package com.servlet;
/*
 * 修改会议室    客户端 传入参数 41-47 行
 * 返回客户端 0/-1 状态
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Add;
import com.services.Change;
import com.tools.CompareToken;
import com.tools.TokenUtil;
import com.tools.tounicode;

import net.sf.json.JSONObject;


public class ChangeServlet extends HttpServlet {

	String isError;//isError 判断出错信息
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	response.setContentType("text/plain;charset=utf-8"); 
	      	System.out.println("连接成功反馈");// 测试是否成功连接
	        StringBuffer json = new StringBuffer();// 字符流
	        String line = null;
	        BufferedReader reader = request.getReader();// 读取流
	        while ((line = reader.readLine()) != null) {
	            json.append(line);// 接受的是JSON格式
	        }
	        System.out.println(json);//得到的是JSON格式
	        // System.out.println(json1.toString());//得到的是字符串
	        // 把得到的字符串封装为JSON，再获取里面的传过来用户名
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	        String Token = jsonObject.getString("Token");//获取Json键值对
	        String Time = tounicode.decodeUnicode(jsonObject.getString("Time"));//解码
	        String BuildingNumber =tounicode.decodeUnicode( jsonObject.getString("BuildNumber"));
	        String RoomNumber = jsonObject.getString("RoomNumber");
	        int Size = Integer.parseInt(jsonObject.getString("Size"));
	        String Function = tounicode.decodeUnicode( jsonObject.getString("Function"));
	        String MeetingRoomLevel =tounicode.decodeUnicode( jsonObject.getString("MeetingRoomLevel"));
	        String istoken=TokenUtil.verificationToken(Token);//解密token
	        if(!istoken.equals("成功")){
	    		   //如果本地数据库token不合法了
	    		   //需要做强制注销 重新登陆   		   
	    		   response.setContentType("text/html;charset=utf-8");
	        	   
	   	        PrintWriter out = response.getWriter();     
	   	        String  info_json  = "{\"status\":\"-2\"}";//组装json格式的字符串来传送
	   	        System.out.println(info_json);
	   	        out.write(info_json);
	   	        out.flush();
	   	        out.close();
	    		   
	    		   System.out.println("数据库中token不合法了 ");
	    	   }
	        else {
	        if(CompareToken.selectToken(Token)) {
	        if(istoken.equals("成功"))
	        {
	        	//获取插入状态
	        	String IsSuccess = Change.UpdateReserveRoom(Time, BuildingNumber, RoomNumber, Size, Function, MeetingRoomLevel);
	            System.out.println(IsSuccess);
	            if(IsSuccess.equals("success")) {
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	            String  info_json  = "{\"status\":\"0\"}";//组装json格式的字符串来传送
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            else
	            {
	            	response.setContentType("text/html;charset=utf-8");
		        	PrintWriter out = response.getWriter();
//		          封装成JSON格式发送回客户端       
		            String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
	            }
	        }
	        else {
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
//		      封装成JSON格式发送回客户端       
		        String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
	        }
	        else {
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
//		      封装成JSON格式发送回客户端       
		        String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
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

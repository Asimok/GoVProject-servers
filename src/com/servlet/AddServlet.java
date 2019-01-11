package com.servlet;
/*
 * 新增会议室的servlet
 * 客户端传入的参数 Token Time BuildNumber RoomNumber Size Function MeetingRoomLevel 
 * 详细传入参数解释参照工作笔记
 * TokenUtil.verificationToken(Token);	 解密token并且调用com.tools 下该方法判断传入token是否有效
 * 调用 tounicode.decodeUnicode() 进行unicode解码
 * 
 * 返回客户端参数 "{\"status\":\"-1\"}" / "{\"status\":\"0\"}"
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
import javax.servlet.jsp.el.FunctionMapper;

import com.services.Add;
import com.services.Regist;
import com.tools.CompareToken;
import com.tools.EmployeeNumberChecker;
import com.tools.FilterManage;
import com.tools.SqlInjChecker;
import com.tools.TokenUtil;
import com.tools.getUUID;
import com.tools.intChecker;
import com.tools.tomd5;
import com.tools.tounicode;

import net.sf.json.JSONObject;

public class AddServlet extends HttpServlet {
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
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	        
	        String Token = jsonObject.getString("Token");
	        String Time = tounicode.decodeUnicode(jsonObject.getString("Time"));//解码
	        String BuildingNumber = tounicode.decodeUnicode(jsonObject.getString("BuildingNumber"));//unicode 解码
	        String RoomNumber = jsonObject.getString("RoomNumber");
	       // int Size = Integer.parseInt(jsonObject.getString("Size"));
	       
	        String Function = tounicode.decodeUnicode( jsonObject.getString("Function"));
	        
	        String MeetingRoomLevel =tounicode.decodeUnicode( jsonObject.getString("MeetingRoomLevel"));	       
	          
	        String istoken=TokenUtil.verificationToken(Token);//token解码检查	    
	        if(CompareToken.selectToken(Token)) {
	        if(istoken.equals("成功"))
	        {
	        	//获取插入状态
	        	 FilterManage check1 = new FilterManage();
	             check1.addChecker(new SqlInjChecker());
	             FilterManage check2 = new FilterManage();
	             check2.addChecker(new intChecker());
	     		if((check1.doChain_check(BuildingNumber))&&(check1.doChain_check(RoomNumber))
	     				&&(check1.doChain_check(Time))&&(check2.doChain_check(jsonObject.getString("Size"))))
	        	{
	     			System.out.println("输入信息合法");
	     			int Size = Integer.parseInt(jsonObject.getString("Size"));
	     			if(!Add.CompareRoom(Time,BuildingNumber,RoomNumber))
	     			{
	     			
	     			boolean IsSuccess = Add.InsertRoomInfo (Time,BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel);
	            System.out.println(IsSuccess);
	            if(IsSuccess)
	            {
	            	System.out.println("新增房间成功");
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"0\"}";//组装json格式的字符串来传送
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            
	            else
	            {
	            	System.out.println("新增房间失败");
	            	response.setContentType("text/html;charset=utf-8");
		        	PrintWriter out = response.getWriter();  
		            String  info_json  = "{\"status\":\"-1\"}";
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
	            }
	     			}
	     			else
	     			{
	     				System.out.println("房间重复");
		            	response.setContentType("text/html;charset=utf-8");
			        	PrintWriter out = response.getWriter();  
			            String  info_json  = "{\"status\":\"-4\"}";
			            System.out.println(info_json);
			            out.write(info_json);
			            out.flush();
			            out.close(); 
	     			}
	            }
	     		else
	     		{
	            	response.setContentType("text/html;charset=utf-8");
		        	PrintWriter out = response.getWriter();  
		            String  info_json  = "{\"status\":\"-2\"}";//组装json格式的字符串来传送
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
	     		}
	        }
	        else {
	        	
	        	//需要强制注销
            	
            	System.out.println("token失效");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//组装json格式的字符串来传送
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
	        }
	        else {
	        	
	        	//需要强制注销
            	
            	System.out.println("token失效");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//组装json格式的字符串来传送
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

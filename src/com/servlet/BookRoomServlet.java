package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Add;
import com.services.BookRoom;
import com.services.Login;

import com.tools.CompareToken;

import com.tools.TokenUtil;
import com.tools.tojson;
import com.tools.tounicode;

import net.sf.json.JSONObject;

/**
 * 预定会议室
 */

public class BookRoomServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fail="失败";
		response.setContentType("text/html;charset=utf-8");
        System.out.println("连接成功反馈");// 测试是否成功连接
        StringBuffer json11 = new StringBuffer();// 字符流
        String line = null;
        BufferedReader reader = request.getReader();// 读取流
        while ((line = reader.readLine()) != null) {
            json11.append(line);// 接受的是JSON格式
        }

        System.out.println(json11);//得到的是JSON格式
        // System.out.println(json1.toString());//得到的是字符串
        // 把得到的字符串封装为JSON，再获取里面的传过来用户名
        
        JSONObject jsonObject = JSONObject.fromObject(json11.toString());
        
        
        String Token = jsonObject.getString("Token");
        
		String Time = tounicode.decodeUnicode(jsonObject.getString("Time"));
		
		String RoomNumber = jsonObject.getString("RoomNumber");
		
		String BuildingNumber = tounicode.decodeUnicode(jsonObject.getString("BuildingNumber"));
        System.out.println("传入的token值："+Token);
        String istoken=TokenUtil.verificationToken(Token);//token解码检查
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
		if(istoken.equals("成功")){
			boolean MeetingStatus = BookRoom.selectMeetingStatus(Time, BuildingNumber, RoomNumber);
            if(MeetingStatus)
            {
            	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
            	String Name=CompareToken.selectNameByEmployeeNumber(EmployeeNumber);
            	String AdminPower=Login.selectadminpower(EmployeeNumber);
            	String MeetingRoomLevel=BookRoom.selectMeetingRoomLevel(Time, BuildingNumber, RoomNumber);
            	if(Integer.parseInt(AdminPower)>=Integer.parseInt(MeetingRoomLevel)) {
                boolean IsSuccess=BookRoom.BookRoom(Time, Name, RoomNumber, BuildingNumber);
                if(IsSuccess)
                {
                	BookRoom.UpdateRoomInfoIsMeeting(Time, BuildingNumber, RoomNumber);
                	response.setContentType("text/html;charset=utf-8");
    	        	PrintWriter out = response.getWriter();
//    	          封装成JSON格式发送回客户端       
    	        	tojson<String, String> json=new tojson<>();
    	           json.put("Status", "0");
    	           json.put("EmployeeNumber", tounicode.gbEncoding(EmployeeNumber));
    	           json.put("Name", tounicode.gbEncoding(Name));
    	            System.out.println("0  "+json.toString());
    	            out.write(json.toString());
    	            out.flush();
    	            out.close(); 
                }
             //我觉得此处应该抛出异常   别删啊！！
                else
                {
                	response.setContentType("text/html;charset=utf-8");
    	        	PrintWriter out = response.getWriter();
//    	          封装成JSON格式发送回客户端       
    	         //   String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
    	        	tojson<String, String> info_json=new tojson<>();
    	        	info_json.put("Status", "-1");
    	        	info_json.put("EmployeeNumber", tounicode.gbEncoding(fail));
    	        	info_json.put("Name", tounicode.gbEncoding(fail));
    	            System.out.println("1  "+info_json.toString());
    	            out.write(info_json.toString());
    	            out.flush();
    	            out.close(); 
                }
                
            	}
            	 else
                 {
                 	response.setContentType("text/html;charset=utf-8");
     	        	PrintWriter out = response.getWriter();
//     	          封装成JSON格式发送回客户端       
     	         //   String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
     	        	tojson<String, String> info_json=new tojson<>();
     	        	info_json.put("Status", "-1");
     	        	info_json.put("EmployeeNumber", tounicode.gbEncoding(fail));
     	        	info_json.put("Name", tounicode.gbEncoding(fail));
     	            System.out.println("1  "+info_json.toString());
     	            out.write(info_json.toString());
     	            out.flush();
     	            out.close(); 
                 }
            }
            else
            {
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-1");
	        	info_json.put("EmployeeNumber", tounicode.gbEncoding(fail).toString());
	        	info_json.put("Name", tounicode.gbEncoding(fail).toString());
	            System.out.println("2  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
            }
			
        }
        else {
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          封装成JSON格式发送回客户端       
        	tojson<String, String> info_json=new tojson<>();
        	info_json.put("Status", "-1");
        	info_json.put("EmployeeNumber", tounicode.gbEncoding(fail));
        	info_json.put("Name", tounicode.gbEncoding(fail));
            System.out.println("3  "+info_json.toString());
            out.write(info_json.toString());
            out.flush();
            out.close(); 
        }
		
		}
        else {
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          封装成JSON格式发送回客户端       
        	tojson<String, String> info_json=new tojson<>();
        	info_json.put("Status", "-1");
        	info_json.put("EmployeeNumber", tounicode.gbEncoding(fail));
        	info_json.put("Name", tounicode.gbEncoding(fail));
            System.out.println("4  "+info_json.toString());
            out.write(info_json.toString());
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

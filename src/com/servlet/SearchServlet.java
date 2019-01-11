package com.servlet;

import java.io.BufferedReader;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.RoomMessage;
import com.services.Search;
import com.tools.CompareToken;
import com.tools.FilterManage;
import com.tools.TokenUtil;
import com.tools.tojson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchServlet extends HttpServlet {
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

        System.out.println(json1);//得到的是JSON格式
        // System.out.println(json1.toString());//得到的是字符串
        // 把得到的字符串封装为JSON，再获取里面的传过来用户名
        
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());

        //String zhanghu = jsonObject.getString("zhanghu");//获取Json键值对
        String Token = jsonObject.getString("Token");
        
       // System.out.println(zhanghu);
        System.out.println(Token);
        
//        连接本地数据库(采用MySql数据库 )
         FilterManage fm = new FilterManage();
          String istoken=TokenUtil.verificationToken(Token);
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
          if((!fm.doChain_check(istoken)))
  		{
  			//false
  			return ;
  			//error deal
  		}
          if(CompareToken.selectToken(Token)) {
		if(istoken.equals("成功")){
        //  if(true) {
        	//String sql = "select * from roominfo  where  UUID = '"+zhanghu+"'";
        	List<RoomMessage> data=Search.selectRoomInfo();
           if(data!=null) {
        	  JSONArray jsonArray=JSONArray.fromObject(data);
        	String data1=jsonArray.toString();
        	System.out.println("这里的data1    "+data1.toString());
        	
        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

            if(json.size()>0){
                for(int i=0;i<json.size();i++){
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//                    System.out.println("这里的解析的    "+jsonObj);
                }}
            System.out.println("第一行    "+json.getJSONObject(1));
//            tojson<String,String> json = new tojson<>();
//             json.put("Message", data1);
//        	System.out.println(json.toString());
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(data1.toString());
            out.flush();
            out.close();
            }
           else
           {
        	   response.setContentType("text/html;charset=utf-8");
           	PrintWriter out = response.getWriter();
//             封装成JSON格式发送回客户端       
           	System.out.println("这里出错了1");
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
//          封装成JSON格式发送回客户端   
        	System.out.println("这里出错了2");
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
//            封装成JSON格式发送回客户端   
          	System.out.println("这里出错了3");
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
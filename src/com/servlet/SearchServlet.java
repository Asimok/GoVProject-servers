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
        System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
        StringBuffer json1 = new StringBuffer();// �ַ���
        String line = null;
        BufferedReader reader = request.getReader();// ��ȡ��
        while ((line = reader.readLine()) != null) {
            json1.append(line);// ���ܵ���JSON��ʽ
        }

        System.out.println(json1);//�õ�����JSON��ʽ
        // System.out.println(json1.toString());//�õ������ַ���
        // �ѵõ����ַ�����װΪJSON���ٻ�ȡ����Ĵ������û���
        
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());

        //String zhanghu = jsonObject.getString("zhanghu");//��ȡJson��ֵ��
        String Token = jsonObject.getString("Token");
        
       // System.out.println(zhanghu);
        System.out.println(Token);
        
//        ���ӱ������ݿ�(����MySql���ݿ� )
         FilterManage fm = new FilterManage();
          String istoken=TokenUtil.verificationToken(Token);
          if(!istoken.equals("�ɹ�")){
   		   //����������ݿ�token���Ϸ���
   		   //��Ҫ��ǿ��ע�� ���µ�½   		   
   		   response.setContentType("text/html;charset=utf-8");
       	   
  	        PrintWriter out = response.getWriter();     
  	        String  info_json  = "{\"status\":\"-2\"}";//��װjson��ʽ���ַ���������
  	        System.out.println(info_json);
  	        out.write(info_json);
  	        out.flush();
  	        out.close();
   		   
   		   System.out.println("���ݿ���token���Ϸ��� ");
   	   }
          else {
          if((!fm.doChain_check(istoken)))
  		{
  			//false
  			return ;
  			//error deal
  		}
          if(CompareToken.selectToken(Token)) {
		if(istoken.equals("�ɹ�")){
        //  if(true) {
        	//String sql = "select * from roominfo  where  UUID = '"+zhanghu+"'";
        	List<RoomMessage> data=Search.selectRoomInfo();
           if(data!=null) {
        	  JSONArray jsonArray=JSONArray.fromObject(data);
        	String data1=jsonArray.toString();
        	System.out.println("�����data1    "+data1.toString());
        	
        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

            if(json.size()>0){
                for(int i=0;i<json.size();i++){
                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//                    System.out.println("����Ľ�����    "+jsonObj);
                }}
            System.out.println("��һ��    "+json.getJSONObject(1));
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
//             ��װ��JSON��ʽ���ͻؿͻ���       
           	System.out.println("���������1");
               String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
               System.out.println(info_json);
               out.write(info_json);
               out.flush();
               out.close(); 
           }
        }
        else {
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          ��װ��JSON��ʽ���ͻؿͻ���   
        	System.out.println("���������2");
            String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
            System.out.println(info_json);
            out.write(info_json);
            out.flush();
            out.close(); 
        }
          }
          else {
          	response.setContentType("text/html;charset=utf-8");
          	PrintWriter out = response.getWriter();
//            ��װ��JSON��ʽ���ͻؿͻ���   
          	System.out.println("���������3");
              String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
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
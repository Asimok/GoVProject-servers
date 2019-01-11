package com.servlet;
/*
 * ���������ҵ�servlet
 * �ͻ��˴���Ĳ��� Token Time BuildNumber RoomNumber Size Function MeetingRoomLevel 
 * ��ϸ����������Ͳ��չ����ʼ�
 * TokenUtil.verificationToken(Token);	 ����token���ҵ���com.tools �¸÷����жϴ���token�Ƿ���Ч
 * ���� tounicode.decodeUnicode() ����unicode����
 * 
 * ���ؿͻ��˲��� "{\"status\":\"-1\"}" / "{\"status\":\"0\"}"
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
	String isError;//isError �жϳ�����Ϣ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	response.setContentType("text/plain;charset=utf-8"); 
	      	System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
	        StringBuffer json = new StringBuffer();// �ַ���
	        String line = null;
	        BufferedReader reader = request.getReader();// ��ȡ��
	        while ((line = reader.readLine()) != null) {
	            json.append(line);// ���ܵ���JSON��ʽ
	        }
	        System.out.println(json);//�õ�����JSON��ʽ
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	        
	        String Token = jsonObject.getString("Token");
	        String Time = tounicode.decodeUnicode(jsonObject.getString("Time"));//����
	        String BuildingNumber = tounicode.decodeUnicode(jsonObject.getString("BuildingNumber"));//unicode ����
	        String RoomNumber = jsonObject.getString("RoomNumber");
	       // int Size = Integer.parseInt(jsonObject.getString("Size"));
	       
	        String Function = tounicode.decodeUnicode( jsonObject.getString("Function"));
	        
	        String MeetingRoomLevel =tounicode.decodeUnicode( jsonObject.getString("MeetingRoomLevel"));	       
	          
	        String istoken=TokenUtil.verificationToken(Token);//token������	    
	        if(CompareToken.selectToken(Token)) {
	        if(istoken.equals("�ɹ�"))
	        {
	        	//��ȡ����״̬
	        	 FilterManage check1 = new FilterManage();
	             check1.addChecker(new SqlInjChecker());
	             FilterManage check2 = new FilterManage();
	             check2.addChecker(new intChecker());
	     		if((check1.doChain_check(BuildingNumber))&&(check1.doChain_check(RoomNumber))
	     				&&(check1.doChain_check(Time))&&(check2.doChain_check(jsonObject.getString("Size"))))
	        	{
	     			System.out.println("������Ϣ�Ϸ�");
	     			int Size = Integer.parseInt(jsonObject.getString("Size"));
	     			if(!Add.CompareRoom(Time,BuildingNumber,RoomNumber))
	     			{
	     			
	     			boolean IsSuccess = Add.InsertRoomInfo (Time,BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel);
	            System.out.println(IsSuccess);
	            if(IsSuccess)
	            {
	            	System.out.println("��������ɹ�");
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"0\"}";//��װjson��ʽ���ַ���������
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            
	            else
	            {
	            	System.out.println("��������ʧ��");
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
	     				System.out.println("�����ظ�");
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
		            String  info_json  = "{\"status\":\"-2\"}";//��װjson��ʽ���ַ���������
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
	     		}
	        }
	        else {
	        	
	        	//��Ҫǿ��ע��
            	
            	System.out.println("tokenʧЧ");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//��װjson��ʽ���ַ���������
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
	        }
	        else {
	        	
	        	//��Ҫǿ��ע��
            	
            	System.out.println("tokenʧЧ");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//��װjson��ʽ���ַ���������
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

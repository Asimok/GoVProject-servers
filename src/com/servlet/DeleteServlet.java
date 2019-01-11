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
import com.services.Delete;
import com.tools.CompareToken;
import com.tools.TokenUtil;
import com.tools.tounicode;

import net.sf.json.JSONObject;

public class DeleteServlet extends HttpServlet {
	

	
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
        // System.out.println(json1.toString());//�õ������ַ���
        // �ѵõ����ַ�����װΪJSON���ٻ�ȡ����Ĵ������û���
        JSONObject jsonObject = JSONObject.fromObject(json.toString());
        String Token = jsonObject.getString("Token");//��ȡJson��ֵ��
        String Time = tounicode.decodeUnicode(jsonObject.getString("Time"));//����MD5����
        String BuildingNumber = tounicode.decodeUnicode(jsonObject.getString("BuildNumber"));
        String RoomNumber = jsonObject.getString("RoomNumber");
        String istoken=TokenUtil.verificationToken(Token);//token�ж�
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
        if(CompareToken.selectToken(Token)) {
        if(istoken.equals("�ɹ�"))
        {
        	//��ȡ����״̬
        	String IsSuccess = Delete.DeleteReserveRoom(Time, BuildingNumber, RoomNumber);
            System.out.println(IsSuccess);
            if(IsSuccess.equals("success")) {
            response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          ��װ��JSON��ʽ���ͻؿͻ���       
            String  info_json  = "{\"status\":\"0\"}";//��װjson��ʽ���ַ���������
            System.out.println(info_json);
            out.write(info_json);
            out.flush();
            out.close(); 
            }
            else
            {
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
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
//	      ��װ��JSON��ʽ���ͻؿͻ���       
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
//	      ��װ��JSON��ʽ���ͻؿͻ���       
	        String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
        }
	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.flush();
	        out.close();
}
}

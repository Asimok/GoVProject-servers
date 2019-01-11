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
 * ��¼ servlet
 * 
 * */
public class LoginServlet extends HttpServlet {
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

        System.out.println("�����"+json1);//�õ�����JSON��ʽ
        // System.out.println(json1.toString());//�õ������ַ���
        // �ѵõ����ַ�����װΪJSON���ٻ�ȡ����Ĵ������û���
        
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
        
        String zhanghu = jsonObject.getString("zhanghu");//��ȡJson��ֵ��
        String mima = tomd5.tomd5(jsonObject.getString("mima"));
       // tomd5.tomd5(mima);
        String Token=jsonObject.getString("Token");
        System.out.println("������˻� "+zhanghu);
        System.out.println("��������� "+mima);
        System.out.println("�����token "+Token);
        FilterManage check1 = new FilterManage();
        check1.addChecker(new EmployeeNumberChecker());
		if((check1.doChain_check(zhanghu)))
		{					       

        if(Token==null)
        {
        	response.setContentType("text/html;charset=utf-8"); 
   	        PrintWriter out = response.getWriter();     
   	        String  info_json  = "{\"status\":\"-3\"}";//tokenΪ��
   	        System.out.println(info_json);
   	        out.write(info_json);
   	        out.flush();
   	        out.close();
        }
        else {
        String Password = Login.selectNumber(zhanghu);//����login�෽��
      //  if(!Password==null) {
       if(Password.equals(mima))   //���������ȷ
        {
    	
    	  
    	  if(!SearchZHandToken.searchZH(zhanghu))
    	  {  
    		//������˻���Ӧ��tokenΪ��  ����
       	   Login.InsertToken(zhanghu,Token);
       	   System.out.println("����token�ɹ� ");
    	  }
    	  else {
    		  Login.UpdateToken(zhanghu,Token);//ÿ��½�ɹ�����token
    	  }
        System.out.println("��������� "+Password);
        System.out.println("���������tomd5 "+tomd5.tomd5(Password));
        System.out.println("����token ");
        PrintWriter out = response.getWriter();
        String  info_json  = "{\"status\":\"0\"}";//��½�ɹ�
        System.out.println(info_json);
        out.write(info_json);
        out.flush();
        out.close();
    }
       
       else {//����������
    	   
    	   response.setContentType("text/html;charset=utf-8");
    	   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"-1\"}";//�������
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
 
       } }
		}
		else {
			response.setContentType("text/html;charset=utf-8");
	    	   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"-2\"}";//�˻����Ƿ�
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
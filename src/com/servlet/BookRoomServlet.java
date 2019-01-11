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
 * Ԥ��������
 */

public class BookRoomServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fail="ʧ��";
		response.setContentType("text/html;charset=utf-8");
        System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
        StringBuffer json11 = new StringBuffer();// �ַ���
        String line = null;
        BufferedReader reader = request.getReader();// ��ȡ��
        while ((line = reader.readLine()) != null) {
            json11.append(line);// ���ܵ���JSON��ʽ
        }

        System.out.println(json11);//�õ�����JSON��ʽ
        // System.out.println(json1.toString());//�õ������ַ���
        // �ѵõ����ַ�����װΪJSON���ٻ�ȡ����Ĵ������û���
        
        JSONObject jsonObject = JSONObject.fromObject(json11.toString());
        
        
        String Token = jsonObject.getString("Token");
        
		String Time = tounicode.decodeUnicode(jsonObject.getString("Time"));
		
		String RoomNumber = jsonObject.getString("RoomNumber");
		
		String BuildingNumber = tounicode.decodeUnicode(jsonObject.getString("BuildingNumber"));
        System.out.println("�����tokenֵ��"+Token);
        String istoken=TokenUtil.verificationToken(Token);//token������
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
		if(istoken.equals("�ɹ�")){
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
//    	          ��װ��JSON��ʽ���ͻؿͻ���       
    	        	tojson<String, String> json=new tojson<>();
    	           json.put("Status", "0");
    	           json.put("EmployeeNumber", tounicode.gbEncoding(EmployeeNumber));
    	           json.put("Name", tounicode.gbEncoding(Name));
    	            System.out.println("0  "+json.toString());
    	            out.write(json.toString());
    	            out.flush();
    	            out.close(); 
                }
             //�Ҿ��ô˴�Ӧ���׳��쳣   ��ɾ������
                else
                {
                	response.setContentType("text/html;charset=utf-8");
    	        	PrintWriter out = response.getWriter();
//    	          ��װ��JSON��ʽ���ͻؿͻ���       
    	         //   String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
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
//     	          ��װ��JSON��ʽ���ͻؿͻ���       
     	         //   String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
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
//	          ��װ��JSON��ʽ���ͻؿͻ���       
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
//          ��װ��JSON��ʽ���ͻؿͻ���       
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
//          ��װ��JSON��ʽ���ͻؿͻ���       
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

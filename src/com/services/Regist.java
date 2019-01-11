package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
/*
 * selectPosition(String number)  ͨ��Ա���Ų�ְλ
 * Regist() ִ��ע�� �������ݿ�
 * */
import com.tools.SearchZHandToken;

public class Regist {

	public static String selectPosition(String number){
        String NotFound = null;
        String Number = null;//Ա����
        String Position = null;//ְλ
        //String Password = null;
        String sql = "select * from allinfo  where  EmployeeNumber = '"+number+"'";
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Number =rs.getString("EmployeeNumber");
            	Position = rs.getString("Position");//ְλ
                //��ȡְλ��Ա����
                return Position;//����ְλ
            }else {
                return NotFound="���޴���";//û�д�����Ϣ����ʾ�޷�ע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
            return NotFound="ִ��SQL������";
        }
   
    }
	
	//ע����Ϣ����
	public static String Regist(String EmployeeNumber,String Password, String Name, String Sex,
			String PhoneNumber,String Email, String Ministry,String Position) {
        String NotFound = null;
        String Sucess=null;
        int level=0; //Ȩ��
        switch (Position)
        {
        case "���³�" :
        	level =4;
        break;
        
        case "�ܾ���":
        	level =3;
        break;
        case "���ž���":
        	level =2;
        break;
        case "Ա��":
        	level =1;
        break;
        }
        String insql4 = "insert into admininfo values ("+EmployeeNumber
   			 +",'"+Password+"','"+Name+"',"+Sex+",'"+PhoneNumber
   			 +"','"+Email+"','"+Ministry+"',"+null+","+null+","+null+
   			 ","+level+","+System.currentTimeMillis()+")";
        
        System.out.println("insert into admininfo values ("+EmployeeNumber
      			 +",'"+Password+"','"+Name+"',"+Sex+",'"+PhoneNumber
       			 +"','"+Email+"','"+Ministry+"',"+0+","+null+","+0+
       			 ","+level+","+System.currentTimeMillis()+")");

        ConnDb connDb1 = new ConnDb();
        try {
        	if(!searchZH1(EmployeeNumber))
        	{
        	
        	 PreparedStatement ps1 =  connDb1.conn().prepareStatement(insql4);
             int rs1 =  ps1.executeUpdate();
             if(rs1==1)
             {
            	 System.out.println("����ע����Ϣ�ɹ���");
            	 return "ע��ɹ�";
             }
             else
             {   
            	 System.out.println("����ע����Ϣʧ�ܣ�");
            	 return "��Ա������ע�ᣡ";
             }
        }
        	else
            {     
        	 System.out.println("��Ա������ע�ᣡ");
           	 return "��Ա������ע�ᣡ";
            }
        }
        catch (SQLException e) {
           
            e.printStackTrace();
            System.out.println("��Ա������ע�ᣡ  catch�׳�");
            return NotFound="��Ա������ע�ᣡ";
        }

        } 
		        
public static boolean searchZH1 (String Number){
		
		
		String sql = "select * from admininfo where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		    	  
		        	return true;
		        
		      }else {
		          return false;
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("ִ��SQL������");
		      return false;
		  }
		
		
	}
    }


	


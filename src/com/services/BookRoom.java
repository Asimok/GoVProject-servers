package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.db.ConnDb;
import com.tools.getUUID;

public class BookRoom {
	public static boolean BookRoom(String Time,String Name,String RoomNumber,String BuildingNumber){
        String NotFound = null;
        String uuid = getUUID.getUUID32();
        String sql = ("insert into reserveroom values ('"+getUUID.getUUID32()+"','"+System.currentTimeMillis() +"','"+Time+"','"
        +Name+"','"+RoomNumber+"','"+BuildingNumber+"')");
        
        System.out.println("insert into reserveroom values ('"+getUUID.getUUID32()+"','"+System.currentTimeMillis() +"','"+Time+"','"
                +Name+"','"+RoomNumber+"','"+BuildingNumber+"')");
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            int rs =  ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ERROR");
            return false;
        }
    }
	 public static boolean selectMeetingStatus(String Time,String BuildingNumber,String RoomNumber){
	        String NotFound = null;
	        String Number = null;
	        String Password = null;
	        String sql = "select * from roominfo  where Time= '"+Time+"'and BuildingNumber = '"+BuildingNumber+"'"
	        		+ "and RoomNumber ='"+RoomNumber+"'";
	        //��ѯ���˻���Ϣ
	        System.out.println(sql);
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	if(rs.getString("IsMeeting").equals("0"))
	            	{
	                return  true;
	            	}
	            	else
	            	{
	            		return false;
	            	}
	            }else {
	                return false;//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return false;
	        }
	        
	    }
	 public static boolean UpdateRoomInfoIsMeeting(String Time,String BuildingNumber,String RoomNumber){
	        //�������ְλ�жϵȼ�
	      
	            ConnDb connDb = new ConnDb();
	            String sql = "update roominfo set IsMeeting = '1'"+" where Time = '"+Time+"' and BuildingNumber='"
   	       				+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";

	               System.out.println("update roominfo set IsMeeting = '1'"+" where Time = '"+Time+"' and BuildingNumber='"
	   	       				+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'");
	               try {
	               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	                    int rs =  ps.executeUpdate();
	               }
	               catch (SQLException e) {
	                   e.printStackTrace();
	                   System.out.println("ERROR");
	                   return false;
	               }             
	               return true;
	               }   
	 
	 public static String selectMeetingRoomLevel(String Time,String BuildingNumber,String RoomNumber){
	        String MeetingRoomLevel = null;
	        String sql = "select * from admininfo  where  Time= '"+Time+"' and BuildingNumber = '"+BuildingNumber+"' and RoomNumber='"+RoomNumber+"'";
	        //��ѯ���˻���Ϣ
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	MeetingRoomLevel=rs.getString("MeetingRoomLevel");
	                return  MeetingRoomLevel;
	            }else {
	                return "ERROR";//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return "ERROR";
	        }
	        
	    }
}

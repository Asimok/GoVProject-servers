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
        //查询该账户信息
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
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
	        //查询该账户信息
	        System.out.println(sql);
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
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
	                return false;//没有此人信息。提示未注册
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	            return false;
	        }
	        
	    }
	 public static boolean UpdateRoomInfoIsMeeting(String Time,String BuildingNumber,String RoomNumber){
	        //根据最低职位判断等级
	      
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
	        //查询该账户信息
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	MeetingRoomLevel=rs.getString("MeetingRoomLevel");
	                return  MeetingRoomLevel;
	            }else {
	                return "ERROR";//没有此人信息。提示未注册
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	            return "ERROR";
	        }
	        
	    }
}

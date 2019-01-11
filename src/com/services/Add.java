package com.services;
/*
 * ���ӻ�����
 * ���������ݿ���н��� 
 * InsertReserveRoom()  ����в�������������Ϣ
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.tools.getUUID;


public class Add {

	public static boolean InsertRoomInfo(String Time,String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel){
        String NotFound = null;
        String level=null;
        //�������ְλ�жϵȼ�
        switch (MeetingRoomLevel)
        {
        case "���³�" :
        	level ="4";
        break;
        
        case "�ܾ���":
        	level ="3";
        break;
        case "���ž���":
        	level ="2";
        break;
        }
            ConnDb connDb = new ConnDb();

            System.out.println("insert into roominfo values ('"+getUUID.getUUID32()+"','"
           			+Time +"','"+BuildingNumber+"','"+RoomNumber+"',"+Size+",'"+Function
           			+"',"+level+",'"+0+"')");
            //�������ķ�����Ϣ
            String sql = "insert into roominfo values ('"+getUUID.getUUID32()+"','"
            			+Time +"','"+BuildingNumber+"','"+RoomNumber+"',"+Size+",'"+Function
            			+"','"+level+"','"+0+"')";
              
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    if(rs==1) {
                    return true;
                    }
                    else
                    {
                    	return false;
                    }
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return false;
               }             
               
               }

    public static boolean CompareRoom(String Time,String BuildingNumber,String RoomNumber){

        String sql = "select * from roominfo  where  Time = '"+Time+"' and BuildingNumber = '"
        		      +BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
    
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
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





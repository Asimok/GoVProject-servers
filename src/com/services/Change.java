package com.services;

/*
 * UpdateReserveRoom() �޸Ļ����� �����ݿ���н���
 * */
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnDb;
import com.tools.getUUID;

public class Change {

	public static String UpdateReserveRoom(String Time,String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel){
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

            //�������ķ�����Ϣ
            String sql =  "update roominfo set Time = '"+Time+"', BuildingNumber = '"+BuildingNumber+
    				"', RoomNumber = '"+RoomNumber+"', Size = "+Size+", Functions = '"+Function
    				+"', MeetingRoomLevel = '"+level+"'"+"  where Time = '"+Time
    				+"' and BuildingNumber='"+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
            
//               System.out.println("update roominfo set Time = '"+Time+"', BuildNumber = '"+BuildingNumber+
//       				"', RoomNumber = '"+RoomNumber+"', Size = "+Size+", Function = '"+Function
//       				+"', MeetingRoomLevel = '"+level+"'"+" where Time = '"+Time
//       				+"' and BuildingNumber='"+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'");
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return NotFound="ERROR";
               }             
               return "success";//�޸ĳɹ�
               }     
        }


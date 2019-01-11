package com.services;

/*
 * UpdateReserveRoom() 修改会议室 与数据库进行交互
 * */
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnDb;
import com.tools.getUUID;

public class Change {

	public static String UpdateReserveRoom(String Time,String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel){
        String NotFound = null;
        String level=null;
        //根据最低职位判断等级
        switch (MeetingRoomLevel)
        {
        case "董事长" :
        	level ="4";
        break;
        
        case "总经理":
        	level ="3";
        break;
        case "部门经理":
        	level ="2";
        break;
        }
            ConnDb connDb = new ConnDb();

            //添加增添的房间信息
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
               return "success";//修改成功
               }     
        }


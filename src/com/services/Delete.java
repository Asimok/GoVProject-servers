package com.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnDb;
import com.tools.getUUID;

public class Delete {
	public static String DeleteReserveRoom(String Time,String BuildingNumber,String RoomNumber){
        String NotFound = null;
            ConnDb connDb = new ConnDb();

            //添加增添的房间信息
            String sql = "delete from roominfo where Time = '"+Time+"' and BuildingNumber='"
            				+BuildingNumber+"' and RoomNumber='"+RoomNumber+"'";
               System.out.println("delete from roominfo where Time = '"+Time+"' and BuildNumber='"
       				+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'");
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    System.out.println("rs"+rs);
                    if(rs==1) {
                    	return "success";
               }
                    else return "ERROR";
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return NotFound="ERROR";
               }             
               
	}          
        }


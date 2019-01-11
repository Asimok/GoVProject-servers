package com.services;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.tools.tounicode;

public class Search {

    public static  List<RoomMessage> selectRoomInfo(){
    	ArrayList<RoomMessage> data,data1;
        data=new ArrayList<RoomMessage>();
        String sql = "select * from roominfo";
        //查询该账户信息
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	RoomMessage msg=new RoomMessage();
            	
            	msg.setTime(tounicode.gbEncoding(rs.getString("Time")));
            	msg.setBuildingNumber(tounicode.gbEncoding(rs.getString("BuildingNumber")));
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	int Size = rs.getInt("Size");
            	String Size1=String.valueOf(Size);
            	msg.setSize(Size1);
            	msg.setFunctions(tounicode.gbEncoding(rs.getString("Functions")));
            	msg.setIsMeeting( rs.getString("IsMeeting"));
            	data.add(msg);
            	
                
            }
            System.out.println("while里的    "+data.toString());
//            	RoomMessage msg1=new RoomMessage();
//            	data1=new ArrayList<RoomMessage>();
//            	msg1.setError("ERROR");
//            	data1.add(msg1);
//            	System.out.println("data"+data.toString());
            	System.out.println("data1   "+data.get(1).getBuildingNumber());
                return data;//
               // return data;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
    }
}
package com.tools;
/*
 * getUUID32() Ëæ»úÉú³Éuuid
 * */
import java.util.UUID;

public class getUUID {
 
	  public static String getUUID32(){
		  	String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		  	return uuid;
		  //Â  return UUID.randomUUID().toString().replace("-", "").toLowerCase();
		  }

}

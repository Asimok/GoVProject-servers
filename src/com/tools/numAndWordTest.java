package com.tools;

public class numAndWordTest {
	public static boolean numAndWordTest(String str)
	{
		boolean isDigit1 = false;//定义一个boolean值，用来表示是否包含数字
    for(int i=0 ; i<str.length() ; i++ ){ //循环遍历字符串   
        if(Character.isDigit(str.charAt(i))||Character.isLetter(str.charAt(i))){     //用char包装类中的判断数字的方法判断每一个字符
        	//System.out.println("包含数字或字母");
            isDigit1 = true;   
        }
        else
        {
        	isDigit1 = false;
        	break;       	
        }
              
    }
    return isDigit1;
    
	}
//	public static void main(String[] args) throws Exception {
//	if(numAndWordTest("a\\a3"))
//	{
//	System.out.println(numAndWordTest("a\\a3"));}
//	else
//	{
//		System.out.println("非法");
//	}
//}
}

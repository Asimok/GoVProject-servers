package com.tools;

public class intChecker implements BaseFilter_Albert_Frank_Check{
	public boolean doCheck(String sin) {
	if(!intTest.intTest(sin))
	{	
		System.out.println(getClass().getName()+"  error");
		System.out.println(sin+"   容量非法，请重新输入");
		return false;
	}
	// TODO Auto-generated method stub
	System.out.println(sin+"    容量合法");
	return true;
}

}

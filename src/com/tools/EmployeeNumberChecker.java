package com.tools;
public class EmployeeNumberChecker implements BaseFilter_Albert_Frank_Check {
	
public boolean doCheck(String sin) {
		
		if(!numAndWordTest.numAndWordTest(sin))
		{	
			System.out.println(getClass().getName()+"  error");
			System.out.println(sin+"    账户名非法，请重新输入");
			return false;
		}
		// TODO Auto-generated method stub
		System.out.println(sin+"    账户名合法");
		return true;
	}

}

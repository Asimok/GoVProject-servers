package com.tools;

public class SqlInjChecker implements BaseFilter_Albert_Frank_Check {

	@Override
	public boolean doCheck(String sin) {
		
		if(sin.contains("'")||sin.contains("\"")||sin.contains(" ")||sin.contains("@")||sin.contains("(")||sin.contains(")")||sin.contains("*"))
		{
	
			System.out.println(getClass().getName()+"error");
			System.out.println(sin+"    非法输入，包含非法注入符号");
			return false;
		}
		// TODO Auto-generated method stub
		System.out.println(sin+"     输入合法");
		return true;
	}

}

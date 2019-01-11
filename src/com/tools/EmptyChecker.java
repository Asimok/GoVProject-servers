package com.tools;

public class EmptyChecker implements BaseFilter_Albert_Frank_Check {

	@Override
	public boolean doCheck(String sin) {
		
		if("".equals(sin))
		{
			System.out.println(getClass().getName()+" error");
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

}

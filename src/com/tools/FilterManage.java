package com.tools;

import java.util.ArrayList;



public class FilterManage {
	private ArrayList<BaseFilter_Albert_Frank_Check> chain = new ArrayList<BaseFilter_Albert_Frank_Check>();
	public boolean doChain_check(String sin)
	{
		for(BaseFilter_Albert_Frank_Check once : chain)
		{
			if(!once.doCheck(sin))
			{
				return false;
			}
		}
		return true;
	}
	public FilterManage  addChecker(BaseFilter_Albert_Frank_Check checker)
	{
		chain.add(checker);
		return this;
	}
	public FilterManage removeChceker(BaseFilter_Albert_Frank_Check checker)
	{
		chain.remove(checker);
		return this;
	}

}
package com.hgd.ebp.state;

import java.sql.*;

public class OrderMngQueryState extends PageQueryState {
	private Timestamp begin =null;
    private Timestamp end = null;
    
    public OrderMngQueryState() {}
    
	public OrderMngQueryState(int curPage, Timestamp ts_begin,Timestamp ts_end) {
		setCurPage(curPage);
		this.begin = ts_begin;
		this.end= ts_end;
	}

	public Timestamp getBegin() {
		return begin;
	}

	public Timestamp getEnd() {
		return end;
	}
	@Override
	public String toString(){
		String s=new String (begin+" 1"+" "+end);
		return s;
	}
}

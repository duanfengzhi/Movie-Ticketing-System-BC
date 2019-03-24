package com.hgd.ebp.state;

import java.sql.Timestamp;

public class UserQueryState extends PageQueryState{
	private Timestamp begin =null;
    private Timestamp end = null;
    
    public UserQueryState(){
    	
    }

    public UserQueryState(int curPage, Timestamp ts_begin,Timestamp ts_end) {
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

	

}

package com.hgd.ebp.state;

import java.sql.Timestamp;
import java.util.Date;

public class TicketQueryState extends PageQueryState{
	private Timestamp date = null;
	private Timestamp edate = null;
    private double price = 0;
    private Timestamp ts_begin =null;
    private Timestamp ts_end = null;
    
    public TicketQueryState() {}
    
	public TicketQueryState(int curPage, String d, String p) {
		setCurPage(curPage);
		String ds=d+" 00:00:00";
		this.date=Timestamp.valueOf(ds);
		
		Date two=new Date(this.date.getTime()+7*24*60*60*1000L);
		Timestamp ets=new Timestamp(two.getTime());
		
		this.edate=ets;
		this.price = Double.parseDouble(p);
	}
	
	public TicketQueryState(int curPage, Timestamp ts_begin,Timestamp ts_end) {
		setCurPage(curPage);
		this.ts_begin = ts_begin;
		this.ts_end= ts_end;
	}
	public Timestamp getBegin() {
		return ts_begin;
	}

	public Timestamp getEnd() {
		return ts_end;
	}
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp edate) {
		this.edate = edate;
	}
	public Timestamp getEdate() {
		return edate;
	}

	public void setEdate(Timestamp edate) {
		this.edate = edate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TicketQueryState [date=" + date + ", price=" + price
				+ ", getLastPage()=" + getLastPage() + ", getCurPage()="
				+ getCurPage() + "]";
	}
	
	
}

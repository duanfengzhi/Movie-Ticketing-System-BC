package com.hgd.ebp.state;

public class UserQueryState2 extends PageQueryState{
	private String username= null; 
    private String idCard= null;
    private String telno= null;
    private String name= null; 
    
    public UserQueryState2(){
    	
    }

    public UserQueryState2(int curPage,String username, String idCard,String telno,String name) {
		setCurPage(curPage);
		this.username = username;
		this.idCard=idCard;
		this.telno=telno;
		this.name=name;
	}

	public String getUsername() {
		return username;
	}
	
	public String getName() {
		return name;
	}

	public String getIdCard() {
		return idCard;
	}
	

	public String getTelno() {
		return telno;
	}

	

}
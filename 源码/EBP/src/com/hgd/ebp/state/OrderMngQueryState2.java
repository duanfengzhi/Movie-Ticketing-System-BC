package com.hgd.ebp.state;

public class OrderMngQueryState2 extends PageQueryState{
	private int oid=0;
    private String name = null;
    private String username = null;
    private String idCard = null;
    
    public OrderMngQueryState2(){
    	
    }

    public OrderMngQueryState2(int curPage, int oid,String name, String idCard,String username) {
		setCurPage(curPage);
		this.oid = oid;
		this.name=name;
		this.idCard= idCard;
		this.username=username;
		System.out.println("-----------");
		System.out.println(oid);
		System.out.println(idCard);
		System.out.println(username);
		System.out.println("-----------");
	}

	public int getOid() {
		return oid;
	}
	public void setOid(int o) {
		this.oid=o;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		this.name=n;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String u) {
		this.username=u;
	}

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String i) {
		this.idCard=i;
	}
	@Override 
	public String toString(){
		System.out.println("@@@"+oid);
		System.out.println("***"+idCard);
		System.out.println(username);
		String s=idCard;
		return s;
	}

}

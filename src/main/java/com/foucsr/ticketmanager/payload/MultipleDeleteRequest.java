package com.foucsr.ticketmanager.payload;

public class MultipleDeleteRequest 
{
	public Long[] ids;
	
	public String owner;

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public MultipleDeleteRequest(Long[] ids) {
		super();
		this.ids = ids;
	}

	public MultipleDeleteRequest(Long[] ids, String owner) {
		super();
		this.ids = ids;
		this.owner = owner;
	}
	
	
}

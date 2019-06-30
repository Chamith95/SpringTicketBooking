package com.middleware.ui.model.request;

public class EventRequestModel {
	private String name;
	private String orgid;
	private String date;
	private String venue;
	private String dresstype;
	private String description;
	private String ticketcount;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDresstype() {
		return dresstype;
	}
	public void setDresstype(String dresstype) {
		this.dresstype = dresstype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTicketcount() {
		return ticketcount;
	}
	public void setTicketcount(String ticketcount) {
		this.ticketcount = ticketcount;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	
}

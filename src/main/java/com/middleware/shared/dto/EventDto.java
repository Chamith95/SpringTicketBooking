package com.middleware.shared.dto;

import java.io.Serializable;

public class EventDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5498920310621491126L;

	private long id;
	private String name;
	private String orgid;
	private String eventid;
	private String date;
	private String venue;
	private String dresstype;
	private String description;
	private String ticketcount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
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
	
	
	
	
}

package com.middleware.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="events")
public class EventEntity implements Serializable {

	
	private static final long serialVersionUID = -8537741586217429016L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String eventid;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String orgid;
	
	@Column(nullable=false)
	private String date;
	
	@Column(nullable=false)
	private String venue;
	
	@Column(nullable=false)
	private String dresstype;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
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

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	
	
}

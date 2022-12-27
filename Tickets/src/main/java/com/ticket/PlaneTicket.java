package com.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class PlaneTicket {
	@Id
	@Column
	private int id;
	
	@Column
	private String fromAirport;
	@Column
	private String departure;
	@Column
	private String toAirport;
	@Column
	private String arrival;
	
}

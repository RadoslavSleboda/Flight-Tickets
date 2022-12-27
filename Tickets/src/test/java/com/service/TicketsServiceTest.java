package com.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import com.repository.TicketsRepository;
import com.ticket.PlaneTicket;

@SpringBootTest(classes= TicketsServiceTest.class)
@ActiveProfiles("test")
class TicketsServiceTest {
	
	@Mock
	TicketsRepository repository;
	
	@InjectMocks
	TicketsService service;
	
	@Test
	void getAllTickets() {
		when(repository.findAll()).thenReturn(dummyTickets());
		assertEquals(2, service.getAllTickets().size());
	}
	
	@Test
	void getById() {
		int myId = 1;
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(dummyTickets().get(0))
															 , Optional.ofNullable(dummyTickets().get(0))
															 , Optional.ofNullable(dummyTickets().get(1))
															 , Optional.ofNullable(dummyTickets().get(1)));
		assertEquals("Rome", repository.findById(myId).get().getToAirport());
		assertEquals(1, repository.findById(myId).get().getId());
		myId = 2;
		assertEquals("Paris", repository.findById(myId).get().getToAirport());
		assertEquals(2, repository.findById(myId).get().getId());
	}
	
	@Test
	public void addTicket() {
		
		when(repository.save(dummyTicket())).thenReturn(dummyTicket());
		assertEquals(dummyTicket(), service.addTicket(dummyTicket()));
	}
	
	@Test
	public void deleteTicketById() {
		service.removeTicketById(0);
		verify(repository,times(1)).deleteById(Mockito.anyInt());
		
	}
	
	private static List<PlaneTicket> dummyTickets() {
		
	List<PlaneTicket> tickets = new ArrayList<>();
		
		PlaneTicket ticket = new PlaneTicket();
		
		ticket.setId(1);
		ticket.setDeparture("10:00");
		ticket.setFromAirport("New York");
		ticket.setArrival("18:35");
		ticket.setToAirport("Rome");
		tickets.add(ticket);
		
		PlaneTicket ticket2 = new PlaneTicket();
		
		ticket2.setId(2);
		ticket2.setDeparture("12:00");
		ticket2.setFromAirport("Prague");
		ticket2.setArrival("15:15");
		ticket2.setToAirport("Paris");
		tickets.add(ticket2);
		return tickets;
	}
	
	private PlaneTicket dummyTicket() {
		
		PlaneTicket ticket = new PlaneTicket();
		
		ticket.setId(1);
		ticket.setDeparture("10:00");
		ticket.setFromAirport("New York");
		ticket.setArrival("18:35");
		ticket.setToAirport("Rome");
		return ticket;
		
	}

}

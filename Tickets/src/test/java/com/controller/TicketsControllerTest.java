package com.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.service.TicketsService;
import com.ticket.PlaneTicket;

@SpringBootTest(classes= TicketsControllerTest.class)
@ActiveProfiles("test")
class TicketsControllerTest {

	@Mock
	TicketsService service;
	
	@InjectMocks
	TicketsController controller;
	
	@Test
	void getAllTickets() {
		when( service.getAllTickets()).thenReturn(dummyTickets());
		ResponseEntity<List<PlaneTicket>> res = controller.getAllTickets();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2, controller.getAllTickets().getBody().size());
	}
	
	@Test
	void getTicketById() {
		int myId = 1;
		when(service.getTicketById(myId)).thenReturn(Optional.ofNullable(dummyTickets().get(0)));
		ResponseEntity<PlaneTicket> res = controller.getTicketById(myId);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(1, res.getBody().getId());
	}
	
	@Test
	void addTicket() {
		when(service.getTicketById(0)).thenReturn(Optional.ofNullable(dummyTicket()));
		ResponseEntity res = controller.addTicket(dummyTicket());
		assertEquals(dummyTicket(),controller.addTicket(dummyTicket()).getBody());
		assertEquals(HttpStatus.CREATED ,res.getStatusCode());
	}
	
	@Test
	void removeTicketById() {
		when(service.getTicketById(0)).thenReturn(Optional.ofNullable(dummyTicket()));
		ResponseEntity res = controller.removeTicketById(0);
		assertEquals(HttpStatus.OK, res.getStatusCode());

		
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

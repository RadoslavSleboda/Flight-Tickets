package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.TicketsService;
import com.ticket.PlaneTicket;

@ComponentScan("com.start")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = ControllerMockMvcTest.class)
public class ControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	TicketsService service;
	
	@InjectMocks
	TicketsController controller;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getAllTickets() throws Exception {
		when( service.getAllTickets()).thenReturn(dummyTickets());
		this.mockMvc.perform(get("/api/tickets")).andExpect(status().isFound()).andDo(print());
	}
	
	@Test
	public void getTicketsById() throws Exception {
		when(service.getTicketById(Mockito.anyInt())).thenReturn(Optional.ofNullable(dummyTicket()));
		this.mockMvc.perform(get("/api/ticket/{id}", 1))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath(".departure").value("10:00"))
		.andDo(print());
	}
	
	@Test
	public void addTicket() throws Exception {
		when(service.addTicket(dummyTicket())).thenReturn(dummyTicket());
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(dummyTicket());
		this.mockMvc.perform(post("/api/ticket")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	public void deleteTicket() throws Exception {
		when(service.getTicketById(Mockito.anyInt())).thenReturn(Optional.ofNullable(dummyTicket()));
		this.mockMvc.perform(delete("/api/ticket/{id}", 1)).andExpect(status().isOk());
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

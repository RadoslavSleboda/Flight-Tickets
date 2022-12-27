package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.repository.TicketsRepository;
import com.ticket.PlaneTicket;

@Service
public class TicketsService {
	
	@Autowired
	TicketsRepository repository;
	
	public Optional<PlaneTicket> getTicketById(int id) {
		return repository.findById(id);
	}
	
	public List<PlaneTicket> getAllTickets() {
		List<PlaneTicket> tickets = repository.findAll();
		return tickets;
	}
	public PlaneTicket addTicket(PlaneTicket ticket) {
		repository.save(ticket);
		return ticket;
	}

	public void removeTicketById(int id) {
			repository.deleteById(id);
	}

}

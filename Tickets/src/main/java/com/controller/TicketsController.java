package com.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.TicketsService;
import com.ticket.PlaneTicket;

@RestController
@RequestMapping("/api")
public class TicketsController {
@Autowired
TicketsService service;

@GetMapping("/tickets")
public ResponseEntity<List<PlaneTicket>> getAllTickets() {
	try {
		List<PlaneTicket> tickets = service.getAllTickets();
	return new ResponseEntity<List<PlaneTicket>>(tickets, HttpStatus.FOUND);
	} catch(Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
@GetMapping("/ticket/{id}")
public ResponseEntity<PlaneTicket> getTicketById(@PathVariable("id") int id ) {
	Optional<PlaneTicket> ticket = service.getTicketById(id);
	if(ticket.isPresent()) {
		return new ResponseEntity<PlaneTicket>(ticket.get(), HttpStatus.FOUND);
	} else {
	return new ResponseEntity<PlaneTicket>(HttpStatus.NOT_FOUND);
	}
	
	
}
@DeleteMapping("/ticket/{id}")
public ResponseEntity removeTicketById(@PathVariable("id") int id) {
	Optional<PlaneTicket> ticket = service.getTicketById(id);
	if(ticket.isPresent()) {
		service.removeTicketById(id);
		return new ResponseEntity(HttpStatus.OK);
	} else {
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
}

@PostMapping("/ticket")
public ResponseEntity<PlaneTicket> addTicket(@RequestBody PlaneTicket ticket) {
	try {
	service.addTicket(ticket);
	return new ResponseEntity<PlaneTicket>(ticket, HttpStatus.CREATED);
	} catch(Exception e) {
		return new ResponseEntity<PlaneTicket>(HttpStatus.EXPECTATION_FAILED);	
	}

	
}


}

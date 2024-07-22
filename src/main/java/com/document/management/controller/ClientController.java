package com.document.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.management.entity.Client;
import com.document.management.service.ClientService;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

	@Autowired
	private ClientService _clientService;

	@GetMapping()
	public List<Client> getClients() {
		List<Client> clients = _clientService.getList();

		return clients;

	}
}

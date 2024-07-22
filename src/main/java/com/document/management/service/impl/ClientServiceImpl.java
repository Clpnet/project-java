package com.document.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.document.management.entity.Client;
import com.document.management.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Override
	public List<Client> getList() {
		// TODO Auto-generated method stub
		Client c = new Client();
		return c.list();
	}

}

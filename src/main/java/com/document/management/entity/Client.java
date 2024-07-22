package com.document.management.entity;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public int id;
	public String name;
	public String lastname;
	public double debt;
	public String paymentDate;

	public Client(int id, String name, String lastname, double debt, String paymentDate) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.debt = debt;
		this.paymentDate = paymentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public Client() {}

	public List<Client> list() {
		List<Client> clientList = new ArrayList<>();

		clientList.add(new Client(1, "Cristhian", "Lopez", 100.00, "2024-06-21"));
		clientList.add(new Client(2, "Gonzalo", "Reynoso", 200.00, "2024-06-21"));
		clientList.add(new Client(3, "Ovadi", "Diaz", 300.00, "2024-06-21"));
		clientList.add(new Client(4, "Liliana", "Alvarez", 400.00, "2024-06-21"));
		clientList.add(new Client(5, "Luis", "Perales", 500.00, "2024-06-21"));
		clientList.add(new Client(6, "Miguel", "Giron", 600.00, "2024-06-21"));

		return clientList;
	}
}

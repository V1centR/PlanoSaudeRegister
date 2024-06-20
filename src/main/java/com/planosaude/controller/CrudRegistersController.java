package com.planosaude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planosaude.entity.BeneficiarioEntity;
import com.planosaude.impl.CrudRegisterImpl;


@RestController
@RequestMapping("/manage")
public class CrudRegistersController {
	
	@Autowired
	CrudRegisterImpl service;
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<BeneficiarioEntity> registerClient(@RequestBody BeneficiarioEntity postData) {
		System.out.println(postData);
		return new ResponseEntity<>(service.insert(postData), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public String getAllClients() {
		//return service.getTodayAgendamento();
		return "{OK}";
	}
	
	@CrossOrigin
	@GetMapping("/{clientID}")
	public String getDocsByIDClient(@PathVariable("clientID") String clientID) {
		//return service.getTodayAgendamento();
		return "{OK}";
	}
	
	@CrossOrigin
	@PutMapping("/{clientID}")
	public String updateClient() {
		//return service.getTodayAgendamento();
		return "{OK}";
	}
	
	@CrossOrigin
	@DeleteMapping("/{clientID}")
	public String deleteClient(@PathVariable("clientID") String clientID) {
		//return service.getTodayAgendamento();
		return "{OK}";
	}

}

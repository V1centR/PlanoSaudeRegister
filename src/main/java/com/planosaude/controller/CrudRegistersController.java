package com.planosaude.controller;

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

import com.planosaude.entity.BeneficiarioEntity;
import com.planosaude.entity.DocumentoEntity;
import com.planosaude.impl.CrudRegisterImpl;


@RestController
@RequestMapping("/manage")
public class CrudRegistersController {
	
	@Autowired
	CrudRegisterImpl service;
	
	@PostMapping
	public ResponseEntity<BeneficiarioEntity> registerClient(@RequestBody BeneficiarioEntity postData) {
		
		try {
			return new ResponseEntity<>(service.insert(postData), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BeneficiarioEntity>> getAllBeneficiarios() {
		try {
			List<BeneficiarioEntity> response = service.getAll();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{clientID}")
	public ResponseEntity<List<DocumentoEntity>> getDocsByIDClient(@PathVariable("clientID") String clientID) {
		try {
			List<DocumentoEntity> response = service.getAllDocs(clientID);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{clientID}")
	public ResponseEntity<BeneficiarioEntity> updateBeneficiario(@PathVariable("clientID") Integer clientID,@RequestBody BeneficiarioEntity beneficiarioDetails) {
		try {
			Optional<BeneficiarioEntity> updatedBeneficiario = service.updateBeneficiario(clientID, beneficiarioDetails);
			return updatedBeneficiario.map(beneficiario -> new ResponseEntity<>(beneficiario, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{clientID}")
	public ResponseEntity<String> deleteClient(@PathVariable("clientID") Integer clientID) {
		try {
			service.deleteBeneficiario(clientID);
			return new ResponseEntity<>("{\"ok\":\"ok\"}",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

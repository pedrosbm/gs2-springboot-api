package com.fe.neuroHub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.PacienteDao;
import com.fe.neuroHub.model.vo.Paciente;

@RestController
@RequestMapping(value = "/Paciente")
public class PacienteController {
	private PacienteDao pDao = new PacienteDao();
	
	@PostMapping(path = "/New")
	public ResponseEntity<Paciente> newPatient(@RequestBody Paciente patient){
		pDao.insert(patient);
		
		return ResponseEntity.ok(patient);
	}
	
	@PostMapping(path = "/Get/{id}")
	public ResponseEntity<Paciente> getPatient(@PathVariable int id){
		Paciente patient = pDao.selectById(id);
		
		return ResponseEntity.ok(patient);
	}
}

package com.fe.neuroHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.PacienteDao;
import com.fe.neuroHub.model.vo.Login;
import com.fe.neuroHub.model.vo.Paciente;

@RestController
@RequestMapping(value = "/Paciente")
public class PacienteController {
	private PacienteDao pDao;
	
	@Autowired
	public PacienteController(PacienteDao pDao) {
		this.pDao = pDao;
	}
	
	@PostMapping(path = "/New")
	public ResponseEntity<Paciente> newPatient(@RequestBody Paciente patient){
		patient.setId(pDao.selectLast()+1);
		pDao.insert(patient);
		
		return ResponseEntity.ok(patient);
	}
	
	@GetMapping(path = "/Get/{id}")
	public ResponseEntity<Paciente> getPatient(@PathVariable int id){
		Paciente patient = pDao.selectById(id);
		
		return ResponseEntity.ok(patient);
	}
}

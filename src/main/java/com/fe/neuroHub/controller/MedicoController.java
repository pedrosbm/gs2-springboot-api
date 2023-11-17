package com.fe.neuroHub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.MedicoDao;
import com.fe.neuroHub.model.vo.Medico;

@RestController
@RequestMapping(path = "/Medico")
public class MedicoController {
	private MedicoDao mDao = new MedicoDao();

	@PostMapping(path = "/New")
	public ResponseEntity<Medico> newDoctor(@RequestBody Medico doctor){
		mDao.insert(doctor);
		
		return ResponseEntity.ok(doctor);
	}
	
	@PostMapping(path = "/Get/{id}")
	public ResponseEntity<Medico> getDoctor(@PathVariable int id){
		Medico doctor = mDao.selectById(id);
		
		return ResponseEntity.ok(doctor);
	}
}

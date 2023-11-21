package com.fe.neuroHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	private MedicoDao mDao;
	
	@Autowired
	public MedicoController(MedicoDao mDao) {
		this.mDao = mDao;
	}

	@PostMapping(path = "/New")
	public ResponseEntity<Medico> newDoctor(@RequestBody Medico doctor){
		doctor.setId(mDao.selectLast()+1);
		mDao.insert(doctor);
		
		return ResponseEntity.ok(doctor);
	}
	
	@GetMapping(path = "/Get/{id}")
	public ResponseEntity<Medico> getDoctor(@PathVariable int id){
		Medico doctor = mDao.selectById(id);
		
		return ResponseEntity.ok(doctor);
	}
}

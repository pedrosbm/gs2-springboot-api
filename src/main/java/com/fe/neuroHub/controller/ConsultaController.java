package com.fe.neuroHub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.ConsultaDao;
import com.fe.neuroHub.model.vo.Consulta;

@RestController
@RequestMapping(value = "/Consulta")
public class ConsultaController {
	private ConsultaDao cDao = new ConsultaDao();
	
	@PostMapping(path = "/New")
	public ResponseEntity<Consulta> newConsulta(@RequestBody Consulta consulta){
		cDao.insert(consulta);
		
		return ResponseEntity.ok(consulta);
	}
	
	@GetMapping(path = "/Get/{id}")
	public ResponseEntity<Consulta> getConsulta(@PathVariable int id){
		Consulta c = cDao.selectById(id);
		
		return ResponseEntity.ok(c);
	}
}

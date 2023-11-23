package com.fe.neuroHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.ExameDao;
import com.fe.neuroHub.model.vo.Exame;

@RestController
@RequestMapping(value = "/Exame")
public class ExameController {
	private ExameDao eDao;
	
	@Autowired
	public ExameController(ExameDao eDao) {
		this.eDao = eDao;
	}
	
	@PostMapping(path = "/New")
	public ResponseEntity<Exame> newExame(@RequestBody Exame exame ){
		exame.setId(eDao.selectLast()+1);
		eDao.insert(exame);
		
		return ResponseEntity.ok(exame);
	}
	
	@GetMapping(path = "/Get/{id}")
	public ResponseEntity<Exame> getExame(@PathVariable int id){
		Exame e = eDao.selectById(id);
		
		return ResponseEntity.ok(e);
	}
	
	@GetMapping(path = "/List")
	public ResponseEntity<List<Exame>> getExams(){
		List<Exame> list = eDao.selectAll();
		
		return ResponseEntity.ok(list);
	}
}
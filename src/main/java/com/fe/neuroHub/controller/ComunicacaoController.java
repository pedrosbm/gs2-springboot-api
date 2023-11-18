package com.fe.neuroHub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.ComunicacaoDao;
import com.fe.neuroHub.model.vo.Comunicacao;

@RestController
@RequestMapping(value = "/Comunicacao")
public class ComunicacaoController {
	private ComunicacaoDao cDao = new ComunicacaoDao();
	
	@PostMapping(path = "/New")
	public ResponseEntity<Comunicacao> newComunicacao(@RequestBody Comunicacao c ){
		cDao.insert(c);
		
		return ResponseEntity.ok(c);
	}
	
	@GetMapping(path = "/Get/{id}")
	public ResponseEntity<Comunicacao> getComunicacao(@PathVariable int id){
		Comunicacao c = cDao.selectById(id);
		
		return ResponseEntity.ok(c);
	}
}

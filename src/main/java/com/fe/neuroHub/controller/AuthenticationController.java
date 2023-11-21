package com.fe.neuroHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fe.neuroHub.model.dao.MedicoDao;
import com.fe.neuroHub.model.dao.PacienteDao;
import com.fe.neuroHub.model.vo.Login;
import com.fe.neuroHub.model.vo.Medico;
import com.fe.neuroHub.model.vo.Paciente;

@RestController
@RequestMapping(value = "/Auth")
public class AuthenticationController {
	private PacienteDao pDao;
	private MedicoDao mDao;
	
	@Autowired
	public AuthenticationController(PacienteDao pdao, MedicoDao mDao) {
		this.pDao = pdao;
		this.mDao = mDao;
	}
	
	@PostMapping(path = "/Paciente")
	public ResponseEntity<Paciente> loginPaciente(@RequestBody Login login){
		Paciente p = pDao.selectByEmail(login.getEmail());
		try {
			if(p.getSenha().equals(login.getSenha())) {
				return ResponseEntity.ok(p) ;
			} else	{
				return ResponseEntity.ok(new Paciente());
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new Paciente());
		}
	}
	
	@PostMapping(path = "/Medico")
	public ResponseEntity<Medico> loginMedico(@RequestBody Login login){
		Medico m = mDao.selectByEmail(login.getEmail());
		try {
			if(m.getSenha().equals(login.getSenha())) {
				return ResponseEntity.ok(m) ;
			} else	{
				return ResponseEntity.ok(new Medico());
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new Medico());
		}
	}
	
}

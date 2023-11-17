package com.fe.neuroHub.model.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modelo do médico do banco de dados
 * @author pedro
 */
public class Medico {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("nmMedico")
	private String nmMedico;
	
	@JsonProperty("especialidade")
	private String especialidade;
	
	@JsonProperty("email")
	private String email;
	
	public Medico() {
		
	}
	
	@JsonCreator
	public Medico(@JsonProperty("nmMedico")String nmMedico,@JsonProperty("especialidade")String especialidade,@JsonProperty("email") String email) {
		this.nmMedico = nmMedico;
		this.especialidade = especialidade;
		this.email = email;
	}

	public Medico(int id, String nmMedico, String especialidade, String email) {
		this.id = id;
		this.nmMedico = nmMedico;
		this.especialidade = especialidade;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNmMedico() {
		return nmMedico;
	}
	public void setNmMedico(String nmMedico) {
		this.nmMedico = nmMedico;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

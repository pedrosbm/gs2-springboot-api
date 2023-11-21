package com.fe.neuroHub.model.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fe.neuroHub.model.bo.DateConv;

/**
 * Classe modelo do paciente do banco de dados
 * @author pedro
 */
public class Paciente {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("nome")
	private String nmPaciente;

	@JsonProperty("dtNasc")
	private Date dtNasc;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("senha")
	private String senha;
	
	public Paciente(){
	
	}
	
	public Paciente(int id, String nmPaciente, Date dtNasc, String email) {
		this.id = id;
		this.nmPaciente = nmPaciente;
		this.dtNasc = dtNasc;
		this.email = email;
	}

	@JsonCreator
	public Paciente(@JsonProperty("nome")String nmPaciente,@JsonProperty("dtNasc") String dtNasc,@JsonProperty("email") String email, @JsonProperty("senha")String senha) {
		this.nmPaciente = nmPaciente;
		this.dtNasc = DateConv.StringToDate(dtNasc);
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNmPaciente() {
		return nmPaciente;
	}
	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}

package com.fe.neuroHub.model.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fe.neuroHub.model.bo.DateConv;

/**
 * Classe modelo do médico do banco de dados
 * @author pedro
 */
public class Medico {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("nome")
	private String nmMedico;
	
	@JsonProperty("especialidade")
	private String especialidade;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("senha")
	private String senha;
	
	@JsonProperty("dtNasc")
	private Date dtNasc;
	
	public Medico() {
		
	}
	
	@JsonCreator
	public Medico(@JsonProperty("nome")String nmMedico,@JsonProperty("especialidade")String especialidade,@JsonProperty("email") String email, @JsonProperty("senha") String senha, @JsonProperty("dtNasc")String dtNasc) {
		this.nmMedico = nmMedico;
		this.especialidade = especialidade;
		this.email = email;
		this.senha = senha;
		this.dtNasc = DateConv.StringToDate(dtNasc);
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}

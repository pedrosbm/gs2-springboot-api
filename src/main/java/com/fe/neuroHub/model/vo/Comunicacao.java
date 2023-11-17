package com.fe.neuroHub.model.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modelo de Comunicação do bano de dados
 * @author pedro
 */
public class Comunicacao {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("dtEnvio")
	private Date dtEnvio;
	
	@JsonProperty("mensagem")
	private String mensagem;
	
	@JsonProperty("idPaciente")
	private int idPaciente;
	
	@JsonProperty("idMedico")
	private int idMedico;
	
	@JsonProperty("remetente")
	private String remetente;

	public Comunicacao(int id, Date dtEnvio, String mensagem, int idPaciente, int idMedico) {
		this.id = id;
		this.dtEnvio = dtEnvio;
		this.mensagem = mensagem;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
	}

	public Comunicacao() {
		
	}

	@JsonCreator	
	public Comunicacao(@JsonProperty("dtEnvio")Date dtEnvio,@JsonProperty("mensagem") String mensagem,@JsonProperty("idPaciente") int idPaciente,@JsonProperty("idMedico") int idMedico,  @JsonProperty("remetente")String remetente) {
		this.dtEnvio = dtEnvio;
		this.mensagem = mensagem;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.remetente = remetente;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDtEnvio() {
		return dtEnvio;
	}
	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
}

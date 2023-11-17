package com.fe.neuroHub.model.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modelo da Consulta do banco de dados
 * @author pedro
 */
public class Consulta {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("dtConsulta")
	private Date dtConsulta;
	
	@JsonProperty("comentMedico")
	private String comentMedico;
	
	@JsonProperty("idPaciente")
	private int idPaciente;
	
	@JsonProperty("idMedico")
	private int idMedico;
	
	@JsonCreator
	public Consulta(@JsonProperty("dtConsulta") Date dtConsulta,@JsonProperty("comentMedico") String comentMedico,@JsonProperty("idPaciente") int idPaciente,@JsonProperty("idMedico") int idMedico) {
		this.dtConsulta = dtConsulta;
		this.comentMedico = comentMedico;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
	}

	public Consulta(int id, Date dtConsulta, String comentMedico, int idPaciente, int idMedico) {
		this.id = id;
		this.dtConsulta = dtConsulta;
		this.comentMedico = comentMedico;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
	}
	
	public Consulta() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDtConsulta() {
		return dtConsulta;
	}
	public void setDtConsulta(Date dtConsulta) {
		this.dtConsulta = dtConsulta;
	}
	public String getComentMedico() {
		return comentMedico;
	}
	public void setComentMedico(String comentMedico) {
		this.comentMedico = comentMedico;
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
}

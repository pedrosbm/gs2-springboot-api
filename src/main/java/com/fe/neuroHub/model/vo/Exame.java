package com.fe.neuroHub.model.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modelo de exame do banco de dados
 * @author pedro
 */
public class Exame {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("acuracia")
	private float acuracia;
	
	@JsonProperty("resultado")
	private String resultado;
	
	@JsonProperty("idPaciente")
	private int idPaciente;
	
	@JsonCreator
	public Exame(@JsonProperty("acuracia") float acuracia,@JsonProperty("resultado") String resultado,@JsonProperty("idPaciente") int idPaciente) {
		this.acuracia = acuracia;
		this.resultado = resultado;
		this.idPaciente = idPaciente;
	}
	
	public Exame(int id, float acuracia, String resultado, int idPaciente) {
		this.id = id;
		this.acuracia = acuracia;
		this.resultado = resultado;
		this.idPaciente = idPaciente;
	}

	public Exame() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAcuracia() {
		return acuracia;
	}
	public void setAcuracia(float acuracia) {
		this.acuracia = acuracia;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
}

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
	
	@JsonProperty("tipoExame")
	private String tipoExame;
	
	@JsonProperty("resultado")
	private String resultado;
	
	@JsonProperty("idPaciente")
	private int idPaciente;
	
	@JsonCreator
	public Exame(@JsonProperty("tipoExame") String tipoExame,@JsonProperty("resultado") String resultado,@JsonProperty("idPaciente") int idPaciente) {
		this.tipoExame = tipoExame;
		this.resultado = resultado;
		this.idPaciente = idPaciente;
	}

	public Exame(int id, String tipoExame, String resultado, int idPaciente) {
		this.id = id;
		this.tipoExame = tipoExame;
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
	public String getTipoExame() {
		return tipoExame;
	}
	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
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

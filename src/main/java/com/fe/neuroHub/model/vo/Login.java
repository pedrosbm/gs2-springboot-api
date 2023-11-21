package com.fe.neuroHub.model.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("senha")
	private String senha;

	@JsonCreator
	public Login(@JsonProperty("email") String email, @JsonProperty("senha") String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Login() {
		
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

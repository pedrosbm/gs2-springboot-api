package com.fe.neuroHub.model.bo;

import java.sql.Date;

/**
 * Classe feita para abrigar métodos de conversão de data
 * @author pedro
 */
@SuppressWarnings("deprecation")
public class DateConv {
	/**
	 * Método responsável por converter uma String no modelo dd/mm/aaaa para um
	 * objeto java.sql.Date
	 * @author pedro
	 * @param string
	 * @return Date - java.sql.Date
	 */
	public static Date StringToDate(String string) {
		String[] date = string.split("/");
		int dia = Integer.parseInt(date[0]);
		int mes = Integer.parseInt(date[1]);
		int ano = Integer.parseInt(date[2]);
		
		return new Date(ano - 1900, mes - 1, dia);
	}
}
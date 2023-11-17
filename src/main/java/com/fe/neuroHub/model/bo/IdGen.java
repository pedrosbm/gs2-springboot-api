package com.fe.neuroHub.model.bo;

import com.fe.neuroHub.model.dao.MedicoDao;
import com.fe.neuroHub.model.dao.PacienteDao;

/**
 * Classe feita para consultar o banco de dados e gerar novos id's que não se repetem. Essa classe possúi diversos métodos geradores para cada classe modelo do nosso sistema
 * @author pedro
 */
public class IdGen {
	private PacienteDao pDao = new PacienteDao();
	private MedicoDao mDao = new MedicoDao();
	
	
	public int pacienteNewId() {
		int id = pDao.selectLast();
		
		return id + 1;
	}
	
	
}

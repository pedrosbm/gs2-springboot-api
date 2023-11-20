package com.fe.neuroHub.model.bo;

import com.fe.neuroHub.model.dao.ComunicacaoDao;
import com.fe.neuroHub.model.dao.ExameDao;
import com.fe.neuroHub.model.dao.MedicoDao;
import com.fe.neuroHub.model.dao.PacienteDao;
import com.fe.neuroHub.model.vo.Comunicacao;

/**
 * Classe feita para consultar o banco de dados e gerar novos id's que não se repetem. Essa classe possúi diversos métodos geradores para cada classe modelo do nosso sistema
 * @author pedro
 */
public class IdGen {
	private PacienteDao pDao = new PacienteDao();
	private MedicoDao mDao = new MedicoDao();
	private ExameDao eDao = new ExameDao();
	private ComunicacaoDao comDao = new ComunicacaoDao();
	
	
	public int pacienteNewId() {
		int id = pDao.selectLast();
		
		return id + 1;
	}
	
	public int medicoNewId() {
		int id = mDao.selectLast();
		
		return id + 1;
	}
	
	public int exameNewId() {
		int id = eDao.selectLast();
		
		return id + 1;
	}
	
	public int comunicacaoNewId() {
		int id = comDao.selectLast();
		
		return id + 1;
	}
}

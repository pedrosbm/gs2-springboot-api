package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fe.neuroHub.model.vo.Comunicacao;

@Component
public class ComunicacaoDao {
    private final DataSource dataSource;
    
    @Autowired
    public ComunicacaoDao(DataSource dataSource) {
    	this.dataSource = dataSource;
    }

    public String insert(Comunicacao comunicacao) {
        String sqlStatement = "INSERT INTO comunicacao VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, comunicacao.getId());
            statement.setString(2, comunicacao.getMensagem());
            statement.setDate(3, comunicacao.getDtEnvio());
            statement.setInt(4, comunicacao.getIdPaciente());
            statement.setInt(5, comunicacao.getIdMedico());

            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao inserir a comunicação");
            e.printStackTrace();
            return "Insert falhou";
        }

        return "Insert concluído";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM comunicacao WHERE ID_Comunicacao = ?";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);

            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao deletar a comunicação");
            e.printStackTrace();
            return "Delete falhou";
        }

        return "Delete concluído";
    }

    public List<Comunicacao> selectAll() {
        List<Comunicacao> comunicacoes = new ArrayList<>();
        String sqlStatement = "SELECT * FROM comunicacao";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comunicacao comunicacao = new Comunicacao();
                comunicacao.setId(resultSet.getInt("ID_Comunicacao"));
                comunicacao.setDtEnvio(resultSet.getDate("Data_Envio"));
                comunicacao.setMensagem(resultSet.getString("MENSAGEM"));
                comunicacao.setIdPaciente(resultSet.getInt("ID_Paciente"));
                comunicacao.setIdMedico(resultSet.getInt("ID_Medico"));

                comunicacoes.add(comunicacao);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar todas as comunicações");
            e.printStackTrace();
        }

        return comunicacoes;
    }

    public Comunicacao selectById(int id) {
        String sqlStatement = "SELECT * FROM comunicacao WHERE ID_Comunicacao = ?";
        Comunicacao comunicacao = new Comunicacao();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                comunicacao.setId(resultSet.getInt("ID_Comunicacao"));
                comunicacao.setDtEnvio(resultSet.getDate("Data_Envio"));
                comunicacao.setMensagem(resultSet.getString("MENSAGEM"));
                comunicacao.setIdPaciente(resultSet.getInt("ID_Paciente"));
                comunicacao.setIdMedico(resultSet.getInt("ID_Medico"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar a comunicação por ID");
            e.printStackTrace();
        }

        return comunicacao;
    }
    
    public List<Comunicacao> selectConversation(int idPaciente) {
        String sqlStatement = "SELECT * FROM comunicacao WHERE id_paciente = ? order by data_envio";
        List<Comunicacao> lista = new ArrayList<Comunicacao>();
        
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Comunicacao comunicacao = new Comunicacao();
            	
                comunicacao.setId(resultSet.getInt("ID_Comunicacao"));
                comunicacao.setDtEnvio(resultSet.getDate("Data_Envio"));
                comunicacao.setMensagem(resultSet.getString("MENSAGEM"));
                comunicacao.setIdPaciente(resultSet.getInt("ID_Paciente"));
                comunicacao.setIdMedico(resultSet.getInt("ID_Medico"));

                lista.add(comunicacao);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar a comunicação por ID");
            e.printStackTrace();
        }

        return lista;
    }
    
    public int selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM comunicacao ORDER BY ID_Comunicacao DESC) WHERE ROWNUM <= 1";
        int id = 0;
        
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID_Comunicacao");
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar a comunicação por ID");
            e.printStackTrace();
        }

        return id;
    }

}

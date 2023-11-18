package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fe.neuroHub.model.vo.Comunicacao;

public class ComunicacaoDao {
    private Connection conn = DatabaseConnection.getConnection();

    public String insert(Comunicacao comunicacao) {
        String sqlStatement = "INSERT INTO comunicacao (dtEnvio, mensagem, remetente, idPaciente, idMedico) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setDate(1, comunicacao.getDtEnvio());
            statement.setString(2, comunicacao.getMensagem());
            statement.setString(3, comunicacao.getRemetente());
            statement.setInt(4, comunicacao.getIdPaciente());
            statement.setInt(5, comunicacao.getIdMedico());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao inserir a comunicação");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Insert falhou";
        }

        return "Insert concluído";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM comunicacao WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao deletar a comunicação");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Delete falhou";
        }

        return "Delete concluído";
    }

    public List<Comunicacao> selectAll() {
        List<Comunicacao> comunicacoes = new ArrayList<>();
        String sqlStatement = "SELECT * FROM comunicacao";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comunicacao comunicacao = new Comunicacao();
                comunicacao.setId(resultSet.getInt("ID"));
                comunicacao.setDtEnvio(resultSet.getDate("DTENVIO"));
                comunicacao.setMensagem(resultSet.getString("MENSAGEM"));
                comunicacao.setRemetente(resultSet.getString("REMETENTE"));
                comunicacao.setIdPaciente(resultSet.getInt("IDPACIENTE"));
                comunicacao.setIdMedico(resultSet.getInt("IDMEDICO"));

                comunicacoes.add(comunicacao);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar todas as comunicações");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return comunicacoes;
    }

    public Comunicacao selectById(int id) {
        String sqlStatement = "SELECT * FROM comunicacao WHERE id = ?";
        Comunicacao comunicacao = new Comunicacao();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                comunicacao.setId(resultSet.getInt("ID"));
                comunicacao.setDtEnvio(resultSet.getDate("DTENVIO"));
                comunicacao.setMensagem(resultSet.getString("MENSAGEM"));
                comunicacao.setRemetente(resultSet.getString("REMETENTE"));
                comunicacao.setIdPaciente(resultSet.getInt("IDPACIENTE"));
                comunicacao.setIdMedico(resultSet.getInt("IDMEDICO"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar a comunicação por ID");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return comunicacao;
    }
    
    public int selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM comunicacao ORDER BY id DESC) WHERE ROWNUM <= 1";
        int id = 0;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar a comunicação por ID");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return id;
    }

}

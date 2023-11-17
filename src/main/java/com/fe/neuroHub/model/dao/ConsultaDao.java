package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fe.neuroHub.model.vo.Consulta;

public class ConsultaDao {
    private Connection conn = DatabaseConnection.getConnection();

    public String insert(Consulta consulta) {
        String sqlStatement = "INSERT INTO consulta (dtConsulta, comentMedico, idPaciente, idMedico) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setDate(1, new java.sql.Date(consulta.getDtConsulta().getTime()));
            statement.setString(2, consulta.getComentMedico());
            statement.setInt(3, consulta.getIdPaciente());
            statement.setInt(4, consulta.getIdMedico());
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao inserir a consulta");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na inserção da consulta";
        }

        return "Inserção da consulta concluída";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM consulta WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao excluir a consulta");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na exclusão da consulta";
        }

        return "Exclusão da consulta concluída";
    }

    public Consulta selectById(int id) {
        String sqlStatement = "SELECT * FROM consulta WHERE id = ?";
        Consulta consulta = new Consulta();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                consulta.setId(resultSet.getInt("ID"));
                consulta.setDtConsulta(resultSet.getDate("DTCONSULTA"));
                consulta.setComentMedico(resultSet.getString("COMENTMEDICO"));
                consulta.setIdPaciente(resultSet.getInt("IDPACIENTE"));
                consulta.setIdMedico(resultSet.getInt("IDMEDICO"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar a consulta por ID");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return consulta;
    }

    public List<Consulta> selectAll() {
        String sqlStatement = "SELECT * FROM consulta";
        List<Consulta> consultas = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultSet.getInt("ID"));
                consulta.setDtConsulta(resultSet.getDate("DTCONSULTA"));
                consulta.setComentMedico(resultSet.getString("COMENTMEDICO"));
                consulta.setIdPaciente(resultSet.getInt("IDPACIENTE"));
                consulta.setIdMedico(resultSet.getInt("IDMEDICO"));

                consultas.add(consulta);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar todas as consultas");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return consultas;
    }

    public String update(Consulta consulta) {
        String sqlStatement = "UPDATE consulta SET dtConsulta = ?, comentMedico = ?, idPaciente = ?, idMedico = ? WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setDate(1, new java.sql.Date(consulta.getDtConsulta().getTime()));
            statement.setString(2, consulta.getComentMedico());
            statement.setInt(3, consulta.getIdPaciente());
            statement.setInt(4, consulta.getIdMedico());
            statement.setInt(5, consulta.getId());
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao atualizar a consulta");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na atualização da consulta";
        }

        return "Atualização da consulta concluída";
    }
    
    public int selectLast() {
        String sqlStatement = "SELECT ID FROM (SELECT * FROM consulta ORDER BY id DESC) WHERE ROWNUM <= 1";
        int id = 0;

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar o último ID da consulta");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return id;
    }
}

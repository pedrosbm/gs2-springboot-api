package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fe.neuroHub.model.vo.Exame;

public class ExameDao {
    private Connection conn = DatabaseConnection.getConnection();

    public String insert(Exame exame) {
        String sqlStatement = "INSERT INTO exame (tipoExame, resultado, idPaciente) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, exame.getTipoExame());
            statement.setString(2, exame.getResultado());
            statement.setInt(3, exame.getIdPaciente());
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao inserir o exame");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro ao inserir o exame";
        }

        return "Insert concluído";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM exame WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao excluir o exame");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro ao excluir o exame";
        }

        return "Delete concluído";
    }

    public Exame selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM exame ORDER BY id DESC) WHERE ROWNUM <= 1";
        Exame exame = new Exame();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exame.setId(resultSet.getInt("ID"));
                exame.setTipoExame(resultSet.getString("TIPOEXAME"));
                exame.setResultado(resultSet.getString("RESULTADO"));
                exame.setIdPaciente(resultSet.getInt("IDPACIENTE"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar o último exame");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return exame;
    }
    
    public Exame selectById(int id) {
        String sqlStatement = "SELECT * FROM exame WHERE id = ?";
        Exame exame = new Exame();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exame.setId(resultSet.getInt("ID"));
                exame.setTipoExame(resultSet.getString("TIPOEXAME"));
                exame.setResultado(resultSet.getString("RESULTADO"));
                exame.setIdPaciente(resultSet.getInt("IDPACIENTE"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar o exame por ID");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return exame;
    }

    public List<Exame> selectAll() {
        String sqlStatement = "SELECT * FROM exame";
        List<Exame> exames = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Exame exame = new Exame();
                exame.setId(resultSet.getInt("ID"));
                exame.setTipoExame(resultSet.getString("TIPOEXAME"));
                exame.setResultado(resultSet.getString("RESULTADO"));
                exame.setIdPaciente(resultSet.getInt("IDPACIENTE"));

                exames.add(exame);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar todos os exames");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return exames;
    }
}

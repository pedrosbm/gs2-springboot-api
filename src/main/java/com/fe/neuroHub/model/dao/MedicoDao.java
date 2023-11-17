package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fe.neuroHub.model.vo.Medico;

public class MedicoDao {
    private Connection conn = DatabaseConnection.getConnection();
    
    public String insert(Medico medico) {
        String sqlStatement = "INSERT INTO medico (ID, NM_MEDICO, ESPECIALIDADE, EMAIL) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, medico.getId());
            statement.setString(2, medico.getNmMedico());
            statement.setString(3, medico.getEspecialidade());
            statement.setString(4, medico.getEmail());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na inserção";
        }

        return "Inserção concluída";
    }

    public String update(Medico medico) {
        String sqlStatement = "UPDATE medico SET NM_MEDICO = ?, ESPECIALIDADE = ?, EMAIL = ? WHERE ID = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, medico.getNmMedico());
            statement.setString(2, medico.getEspecialidade());
            statement.setString(3, medico.getEmail());
            statement.setInt(4, medico.getId());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na atualização";
        }

        return "Atualização concluída";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM medico WHERE ID = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na exclusão";
        }

        return "Exclusão concluída";
    }

    public ArrayList<Medico> selectAll() {
        ArrayList<Medico> medicos = new ArrayList<>();

        String sqlStatement = "SELECT * FROM medico";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medico medico = new Medico();
                medico.setId(resultSet.getInt("ID"));
                medico.setNmMedico(resultSet.getString("NM_MEDICO"));
                medico.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                medico.setEmail(resultSet.getString("EMAIL"));

                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return medicos;
    }

    public Medico selectById(int id) {
        String sqlStatement = "SELECT * FROM medico WHERE ID = ?";
        Medico medico = new Medico();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                medico.setId(resultSet.getInt("ID"));
                medico.setNmMedico(resultSet.getString("NM_MEDICO"));
                medico.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                medico.setEmail(resultSet.getString("EMAIL"));
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return medico;
    }
    
    public Medico selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM medico ORDER BY id DESC) WHERE ROWNUM <= 1";
        Medico medico = new Medico();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                medico.setId(resultSet.getInt("ID"));
                medico.setNmMedico(resultSet.getString("NM_MEDICO"));
                medico.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                medico.setEmail(resultSet.getString("EMAIL"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }
        return medico;
    }
}
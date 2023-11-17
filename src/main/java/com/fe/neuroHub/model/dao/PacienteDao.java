package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fe.neuroHub.model.vo.Paciente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe de acesso e modificação do Paciente no banco de dados
 * @author pedro
 */
public class PacienteDao {
    private Connection conn = DatabaseConnection.getConnection();
    
    public String insert(Paciente paciente) {
        String sqlStatement = "INSERT INTO paciente (ID, NM_PACIENTE, DT_NASC, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, paciente.getId());
            statement.setString(2, paciente.getNmPaciente());
            statement.setDate(3, paciente.getDtNasc());
            statement.setString(4, paciente.getEmail());
            statement.setString(5, paciente.getSenha());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
            return "Erro na inserção";
        }

        return "Inserção concluída";
    }

    public String update(Paciente paciente) {
        String sqlStatement = "UPDATE paciente SET NM_PACIENTE = ?, DT_NASC = ?, EMAIL = ?, SENHA = ? WHERE ID = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, paciente.getNmPaciente());
            statement.setDate(2, paciente.getDtNasc());
            statement.setString(3, paciente.getEmail());
            statement.setString(4, paciente.getSenha());
            statement.setInt(5, paciente.getId());

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
        String sqlStatement = "DELETE FROM paciente WHERE ID = ?";

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

    public ArrayList<Paciente> selectAll() {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        String sqlStatement = "SELECT * FROM paciente";

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt("ID"));
                paciente.setNmPaciente(resultSet.getString("NM_PACIENTE"));
                paciente.setDtNasc(resultSet.getDate("DT_NASC"));
                paciente.setEmail(resultSet.getString("EMAIL"));
                paciente.setSenha(resultSet.getString("SENHA"));

                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return pacientes;
    }

    public Paciente selectById(int id) {
        String sqlStatement = "SELECT * FROM paciente WHERE ID = ?";
        Paciente paciente = new Paciente();

        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("ID"));
                paciente.setNmPaciente(resultSet.getString("NM_PACIENTE"));
                paciente.setDtNasc(resultSet.getDate("DT_NASC"));
                paciente.setEmail(resultSet.getString("EMAIL"));
                paciente.setSenha(resultSet.getString("SENHA"));
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }

        return paciente;
    }

    public int selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM paciente ORDER BY id DESC) WHERE ROWNUM <= 1";
        int id = 0;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
            
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            DatabaseConnection.closeConnection();
            e.printStackTrace();
        }
        return id;
    }
}
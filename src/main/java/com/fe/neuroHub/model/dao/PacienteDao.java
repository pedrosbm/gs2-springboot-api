package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class PacienteDao {
    private DataSource dataSource;
    
    @Autowired
    public PacienteDao(DataSource dataSource) {
    	this.dataSource = dataSource;
    }
    
    public String insert(Paciente paciente) {
        String sqlStatement = "INSERT INTO paciente VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, paciente.getId());
            statement.setString(2, paciente.getNmPaciente());
            statement.setDate(3, paciente.getDtNasc());
            statement.setString(4, paciente.getEmail());
            statement.setString(5, paciente.getSenha());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
            return "Erro na inserção";
        }

        return "Inserção concluída";
    }

    public String update(Paciente paciente) {
        String sqlStatement = "UPDATE paciente SET Nome = ?, Data_Nascimento = ?, Email = ?, Senha = ? WHERE ID_Paciente = ?";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, paciente.getNmPaciente());
            statement.setDate(2, paciente.getDtNasc());
            statement.setString(3, paciente.getEmail());
            statement.setString(4, paciente.getSenha());
            statement.setInt(5, paciente.getId());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
            return "Erro na atualização";
        }

        return "Atualização concluída";
    }
    
    public String delete(int id) {
        String sqlStatement = "DELETE FROM paciente WHERE ID_Paciente = ?";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
            return "Erro na exclusão";
        }

        return "Exclusão concluída";
    }

    public ArrayList<Paciente> selectAll() {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        String sqlStatement = "SELECT * FROM paciente";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(resultSet.getInt("ID_Paciente"));
                paciente.setNmPaciente(resultSet.getString("Nome"));
                paciente.setDtNasc(resultSet.getDate("Data_Nascimento"));
                paciente.setEmail(resultSet.getString("EMAIL"));
                paciente.setSenha(resultSet.getString("SENHA"));

                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }

        return pacientes;
    }

    public Paciente selectById(int id) {
        String sqlStatement = "SELECT * FROM paciente WHERE ID_Paciente = ?";
        Paciente paciente = new Paciente();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("ID_Paciente"));
                paciente.setNmPaciente(resultSet.getString("Nome"));
                paciente.setDtNasc(resultSet.getDate("Data_Nascimento"));
                paciente.setEmail(resultSet.getString("EMAIL"));
                paciente.setSenha(resultSet.getString("SENHA"));
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }

        return paciente;
    }
    
    public Paciente selectByEmail(String email) {
        String sqlStatement = "SELECT * FROM paciente WHERE email = ?";
        Paciente paciente = new Paciente();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("ID_Paciente"));
                paciente.setNmPaciente(resultSet.getString("Nome"));
                paciente.setDtNasc(resultSet.getDate("Data_Nascimento"));
                paciente.setEmail(resultSet.getString("Email"));
                paciente.setSenha(resultSet.getString("Senha"));
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }

        return paciente;
    }

    public int selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM paciente ORDER BY ID_Paciente DESC) WHERE ROWNUM <= 1";
        int id = 0;
        
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID_Paciente");
            }
            
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }
        return id;
    }
}
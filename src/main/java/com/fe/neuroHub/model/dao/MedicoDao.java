package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fe.neuroHub.model.vo.Medico;

@Component
public class MedicoDao {
    private DataSource dataSource;
    
    @Autowired
    public MedicoDao(DataSource dataSource) {
    	this.dataSource = dataSource;
    }
    
    public String insert(Medico medico) {
        String sqlStatement = "INSERT INTO medico values(?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, medico.getId());
            statement.setString(2, medico.getNmMedico());
            statement.setString(3, medico.getEspecialidade());
            statement.setDate(4, medico.getDtNasc());
            statement.setString(5, medico.getEmail());
            statement.setString(6	, medico.getSenha());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
            return "Erro na inserção";
        }

        return "Inserção concluída";
    }

    public String update(Medico medico) {
        String sqlStatement = "UPDATE medico SET Nome_Medico = ?, ESPECIALIDADE = ?, EMAIL = ?, Senha = ? WHERE ID_Medico = ?";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, medico.getNmMedico());
            statement.setString(2, medico.getEspecialidade());
            statement.setString(3, medico.getEmail());
            statement.setString(4, medico.getSenha());
            statement.setInt(5, medico.getId());

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
            return "Erro na atualização";
        }

        return "Atualização concluída";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM medico WHERE ID_Medico = ?";

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

    public ArrayList<Medico> selectAll() {
        ArrayList<Medico> medicos = new ArrayList<>();

        String sqlStatement = "SELECT * FROM medico";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medico medico = new Medico();
                medico.setId(resultSet.getInt("ID_Medico"));
                medico.setNmMedico(resultSet.getString("Nome_Medico"));
                medico.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                medico.setEmail(resultSet.getString("EMAIL"));
                medico.setSenha(resultSet.getString("Senha"));

                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }

        return medicos;
    }

    public Medico selectById(int id) {
        String sqlStatement = "SELECT * FROM medico WHERE ID_Medico = ?";
        Medico medico = new Medico();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                medico.setId(resultSet.getInt("ID_Medico"));
                medico.setNmMedico(resultSet.getString("Nome_Medico"));
                medico.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                medico.setDtNasc(resultSet.getDate("Data_Nascimento"));
                medico.setEmail(resultSet.getString("EMAIL"));
                medico.setSenha(resultSet.getString("senha"));
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }

        return medico;
    }
    
    public Medico selectByEmail(String email) {
        String sqlStatement = "SELECT * FROM medico WHERE email = ?";
        Medico medico = new Medico();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                medico.setId(resultSet.getInt("ID_Medico"));
                medico.setNmMedico(resultSet.getString("Nome_Medico"));
                medico.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                medico.setEmail(resultSet.getString("Email"));
                medico.setSenha(resultSet.getString("Senha"));
            }
        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }

        return medico;
    }
    
    public int selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM medico ORDER BY ID_Medico DESC) WHERE ROWNUM <= 1";
        int id = 0;
        
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID_Medico");
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado");
            e.printStackTrace();
        }
        return id;
    }
}
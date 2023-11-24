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

import com.fe.neuroHub.model.vo.Exame;

@Component
public class ExameDao {
    private final DataSource dataSource;
    
    @Autowired
    public ExameDao(DataSource dataSource) {
    	this.dataSource = dataSource;
    }

    public String insert(Exame exame) {
        String sqlStatement = "INSERT INTO exame VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, exame.getId());
            statement.setFloat(2, exame.getAcuracia());
            statement.setString(3, exame.getResultado());
            statement.setInt(4, exame.getIdPaciente());
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao inserir o exame");
            e.printStackTrace();
            return "Erro ao inserir o exame";
        }

        return "Insert concluído";
    }

    public String delete(int id) {
        String sqlStatement = "DELETE FROM exame WHERE ID_Exame = ?";

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao excluir o exame");
            e.printStackTrace();
            return "Erro ao excluir o exame";
        }

        return "Delete concluído";
    }

    public int selectLast() {
        String sqlStatement = "SELECT * FROM (SELECT * FROM exame ORDER BY ID_Exame DESC) WHERE ROWNUM <= 1";
        int id = 0;
        
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID_Exame");
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar o último exame");
            e.printStackTrace();
        }

        return id;
    }
    
    public Exame selectById(int id) {
        String sqlStatement = "SELECT * FROM exame WHERE ID_Exame = ?";
        Exame exame = new Exame();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exame.setId(resultSet.getInt("ID_Exame"));
                exame.setAcuracia(resultSet.getFloat("acuracia"));
                exame.setResultado(resultSet.getString("RESULTADO"));
                exame.setIdPaciente(resultSet.getInt("ID_PACIENTE"));
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar o exame por ID");
            e.printStackTrace();
        }

        return exame;
    }

    public List<Exame> selectAll() {
        String sqlStatement = "SELECT * FROM exame";
        List<Exame> exames = new ArrayList<>();

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Exame exame = new Exame();
                exame.setId(resultSet.getInt("ID_Exame"));
                exame.setAcuracia(resultSet.getFloat("acuracia"));
                exame.setResultado(resultSet.getString("RESULTADO"));
                exame.setIdPaciente(resultSet.getInt("ID_PACIENTE"));

                exames.add(exame);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar todos os exames");
            e.printStackTrace();
        }

        return exames;
    }
    
    public List<Exame> selectByCliente(int idPaciente) {
        String sqlStatement = "SELECT * FROM exame where ID_Paciente = ? order by ID_Exame";
        List<Exame> exames = new ArrayList<>();

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Exame exame = new Exame();
                exame.setId(resultSet.getInt("ID_Exame"));
                exame.setAcuracia(resultSet.getFloat("acuracia"));
                exame.setResultado(resultSet.getString("RESULTADO"));
                exame.setIdPaciente(resultSet.getInt("ID_PACIENTE"));

                exames.add(exame);
            }

        } catch (SQLException e) {
            System.err.println("Algo deu errado ao selecionar todos os exames");
            e.printStackTrace();
        }

        return exames;
    }
}

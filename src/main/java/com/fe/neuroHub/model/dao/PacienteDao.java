package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fe.neuroHub.model.vo.Paciente;

public class PacienteDao {
	 private Connection conn = DatabaseConnection.getConnection();

	    public String insert(Paciente paciente) {
	        String sqlStatement = "INSERT INTO paciente (ID, NAME, PASSWORD, EMAIL, PHONE, DOCUMENT, ADDRESS, CITY, STATE, ZIP_CODE, BIRTHDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        try {
	            PreparedStatement statement = conn.prepareStatement(sqlStatement);
	            statement.setInt(1, paciente.getId());
	            statement.setString(2, paciente.getName());
	            statement.setString(3, paciente.getPassword());
	            statement.setString(4, paciente.getEmail());
	            statement.setString(5, paciente.getPhone());
	            statement.setString(6, paciente.getDocument());
	            statement.setString(7, paciente.getAddress());
	            statement.setString(8, paciente.getCity());
	            statement.setString(9, paciente.getState());
	            statement.setString(10, paciente.getZipCode());
	            statement.setString(11, paciente.getBirthday());

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
	        String sqlStatement = "UPDATE paciente SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, DOCUMENT = ?, ADDRESS = ?, CITY = ?, STATE = ?, ZIP_CODE = ?, BIRTHDAY = ? WHERE ID = ?";

	        try {
	            PreparedStatement statement = conn.prepareStatement(sqlStatement);
	            statement.setString(1, paciente.getName());
	            statement.setString(2, paciente.getPassword());
	            statement.setString(3, paciente.getEmail());
	            statement.setString(4, paciente.getPhone());
	            statement.setString(5, paciente.getDocument());
	            statement.setString(6, paciente.getAddress());
	            statement.setString(7, paciente.getCity());
	            statement.setString(8, paciente.getState());
	            statement.setString(9, paciente.getZipCode());
	            statement.setString(10, paciente.getBirthday());
	            statement.setInt(11, paciente.getId());

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
	                paciente.setName(resultSet.getString("NAME"));
	                paciente.setPassword(resultSet.getString("PASSWORD"));
	                paciente.setEmail(resultSet.getString("EMAIL"));
	                paciente.setPhone(resultSet.getString("PHONE"));
	                paciente.setDocument(resultSet.getString("DOCUMENT"));
	                paciente.setAddress(resultSet.getString("ADDRESS"));
	                paciente.setCity(resultSet.getString("CITY"));
	                paciente.setState(resultSet.getString("STATE"));
	                paciente.setZipCode(resultSet.getString("ZIP_CODE"));
	                paciente.setBirthday(resultSet.getString("BIRTHDAY"));

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
	                paciente.setName(resultSet.getString("NAME"));
	                paciente.setPassword(resultSet.getString("PASSWORD"));
	                paciente.setEmail(resultSet.getString("EMAIL"));
	                paciente.setPhone(resultSet.getString("PHONE"));
	                paciente.setDocument(resultSet.getString("DOCUMENT"));
	                paciente.setAddress(resultSet.getString("ADDRESS"));
	                paciente.setCity(resultSet.getString("CITY"));
	                paciente.setState(resultSet.getString("STATE"));
	                paciente.setZipCode(resultSet.getString("ZIP_CODE"));
	                paciente.setBirthday(resultSet.getString("BIRTHDAY"));
	            }
	        } catch (SQLException e) {
	            System.err.println("Algo deu errado");
	            DatabaseConnection.closeConnection();
	            e.printStackTrace();
	        }

	        return paciente;
	    }
}

package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fe.neuroHub.model.vo.Doutor;

public class DoutorDao {
	 private Connection conn = DatabaseConnection.getConnection();

	    public String insert(Doutor doutor) {
	        String sqlStatement = "INSERT INTO doutor (ID, NAME, EMAIL, PASSWORD, PHONE, DOCUMENT, CRM, ADDRESS, CITY, STATE, ZIP_CODE, BIRTHDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        try {
	            PreparedStatement statement = conn.prepareStatement(sqlStatement);
	            statement.setInt(1, doutor.getId());
	            statement.setString(2, doutor.getName());
	            statement.setString(3, doutor.getEmail());
	            statement.setString(4, doutor.getPassword());
	            statement.setString(5, doutor.getPhone());
	            statement.setString(6, doutor.getDocument());
	            statement.setString(7, doutor.getCrm());
	            statement.setString(8, doutor.getAddress());
	            statement.setString(9, doutor.getCity());
	            statement.setString(10, doutor.getState());
	            statement.setString(11, doutor.getZipCode());
	            statement.setString(12, doutor.getBirthday());

	            statement.execute();
	        } catch (SQLException e) {
	            System.err.println("Algo deu errado");
	            DatabaseConnection.closeConnection();
	            e.printStackTrace();
	            return "Erro na inserção";
	        }

	        return "Inserção concluída";
	    }

	    public String update(Doutor doutor) {
	        String sqlStatement = "UPDATE doutor SET NAME = ?, EMAIL = ?, PASSWORD = ?, PHONE = ?, DOCUMENT = ?, CRM = ?, ADDRESS = ?, CITY = ?, STATE = ?, ZIP_CODE = ?, BIRTHDAY = ? WHERE ID = ?";

	        try {
	            PreparedStatement statement = conn.prepareStatement(sqlStatement);
	            statement.setString(1, doutor.getName());
	            statement.setString(2, doutor.getEmail());
	            statement.setString(3, doutor.getPassword());
	            statement.setString(4, doutor.getPhone());
	            statement.setString(5, doutor.getDocument());
	            statement.setString(6, doutor.getCrm());
	            statement.setString(7, doutor.getAddress());
	            statement.setString(8, doutor.getCity());
	            statement.setString(9, doutor.getState());
	            statement.setString(10, doutor.getZipCode());
	            statement.setString(11, doutor.getBirthday());
	            statement.setInt(12, doutor.getId());

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
	        String sqlStatement = "DELETE FROM doutor WHERE ID = ?";

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

	    public ArrayList<Doutor> selectAll() {
	        ArrayList<Doutor> doutores = new ArrayList<>();

	        String sqlStatement = "SELECT * FROM doutor";

	        try {
	            PreparedStatement statement = conn.prepareStatement(sqlStatement);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                Doutor doutor = new Doutor();
	                doutor.setId(resultSet.getInt("ID"));
	                doutor.setName(resultSet.getString("NAME"));
	                doutor.setEmail(resultSet.getString("EMAIL"));
	                doutor.setPassword(resultSet.getString("PASSWORD"));
	                doutor.setPhone(resultSet.getString("PHONE"));
	                doutor.setDocument(resultSet.getString("DOCUMENT"));
	                doutor.setCrm(resultSet.getString("CRM"));
	                doutor.setAddress(resultSet.getString("ADDRESS"));
	                doutor.setCity(resultSet.getString("CITY"));
	                doutor.setState(resultSet.getString("STATE"));
	                doutor.setZipCode(resultSet.getString("ZIP_CODE"));
	                doutor.setBirthday(resultSet.getString("BIRTHDAY"));

	                doutores.add(doutor);
	            }
	        } catch (SQLException e) {
	            System.err.println("Algo deu errado");
	            DatabaseConnection.closeConnection();
	            e.printStackTrace();
	        }

	        return doutores;
	    }

	    public Doutor selectById(int id) {
	        String sqlStatement = "SELECT * FROM doutor WHERE ID = ?";
	        Doutor doutor = new Doutor();

	        try {
	            PreparedStatement statement = conn.prepareStatement(sqlStatement);
	            statement.setInt(1, id);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                doutor.setId(resultSet.getInt("ID"));
	                doutor.setName(resultSet.getString("NAME"));
	                doutor.setEmail(resultSet.getString("EMAIL"));
	                doutor.setPassword(resultSet.getString("PASSWORD"));
	                doutor.setPhone(resultSet.getString("PHONE"));
	                doutor.setDocument(resultSet.getString("DOCUMENT"));
	                doutor.setCrm(resultSet.getString("CRM"));
	                doutor.setAddress(resultSet.getString("ADDRESS"));
	                doutor.setCity(resultSet.getString("CITY"));
	                doutor.setState(resultSet.getString("STATE"));
	                doutor.setZipCode(resultSet.getString("ZIP_CODE"));
	                doutor.setBirthday(resultSet.getString("BIRTHDAY"));
	            }
	        } catch (SQLException e) {
	            System.err.println("Algo deu errado");
	            DatabaseConnection.closeConnection();
	            e.printStackTrace();
	        }

	        return doutor;
	    }
}

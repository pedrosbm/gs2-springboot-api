package com.fe.neuroHub.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;

@Service
public class CloseConnection {
	private final DataSource dataSource;

    @Autowired
    public CloseConnection (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PreDestroy
    public void fecharConexao() {
        try {
            Connection connection = dataSource.getConnection();
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}

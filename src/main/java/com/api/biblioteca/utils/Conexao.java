package com.api.biblioteca.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3356/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWD = "mysqlPW";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWD);
    }

}

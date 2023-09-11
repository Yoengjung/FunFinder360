package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {
	protected Connection connection = null;

	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "shopping";
		String password = "oracle";
		try {
			this.connection = DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public SuperDao() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

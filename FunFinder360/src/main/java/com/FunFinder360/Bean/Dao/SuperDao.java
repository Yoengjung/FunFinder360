package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {
	protected Connection connection = null;

	public Connection getConnection() {

		
		
		  String url ="jdbc:oracle:thin:@oraclefunfiner360_low?TNS_ADMIN=/Users/gohyoungjung/Desktop/Wallet_oracleFunFiner360";
		 
		 
//		String url = "jdbc:oracle:thin:@oraclefunfiner360_low?TNS_ADMIN=C:/Users/user/Desktop/Wallet_oracleFunFiner360";
		String id = "admin";
		String password = "oracleFunfinder12";
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
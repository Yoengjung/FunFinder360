package com.FunFinder360.Bean.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {
	   protected Connection connection = null;

<<<<<<< HEAD
	   public Connection getConnection() {
//	      String url = "jdbc:oracle:thin:@localhost:1521:xe";
	      String url = "jdbc:oracle:thin:@oraclefunfiner360_medium?TNS_ADMIN=C:/Users/user/Desktop/Wallet_oracleFunFiner360";
	      String id = "admin";
	      String password = "oracleFunfinder12";
	      try {
	         this.connection = DriverManager.getConnection(url, id, password);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return connection;
	   }
=======
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@oraclefunfiner360_medium?TNS_ADMIN=C:/Users/user/Desktop/Wallet_oracleFunFiner360";
		String id = "admin";
		String password = "oracleFunfinder12";
		try {
			this.connection = DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
>>>>>>> 98daff9ab94726d0031081ba355a36a50b3eb4c8

	   public SuperDao() {
	      String driver = "oracle.jdbc.driver.OracleDriver";
	      try {
	         Class.forName(driver);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
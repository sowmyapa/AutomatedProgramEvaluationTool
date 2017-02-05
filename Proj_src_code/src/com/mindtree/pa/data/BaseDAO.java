package com.mindtree.pa.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.AppConstants;

public class BaseDAO {
	private Connection dbConnection;

		protected Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apet?user=root1&password=password&autoReconnect=true&useSSL=false");
		dbConnection.setAutoCommit(false);
		return this.dbConnection;
	}

	public void cleanUp() throws DataAccessException {
		try {
			this.dbConnection.close();
		} catch (SQLException e) {
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), e);
		}
	}

}

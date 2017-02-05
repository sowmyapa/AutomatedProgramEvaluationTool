package com.mindtree.pa.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.pa.entity.User;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.AppConstants;

public class UserDAO extends BaseDAO{
	public void insert(User user) throws DataAccessException {
		
		String sql = "insert into user values(?,?,?,?)";
		try {
			
			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, user.getUid());
			st.setString(2, user.getPassword());
			st.setString(3, user.getName());
			st.setString(4, user.getEmailId());
				
						
			st.executeUpdate();
			st.close();
			connection.commit();
			connection.close();
			cleanUp();
		} catch (ClassNotFoundException exception) {
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception) {
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception) {
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}

	}

	public void update(User user) throws DataAccessException {

	}

	public void delete(User user) throws DataAccessException {

	}

	public int count() throws DataAccessException {

		int n = 0;

		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select count(*) from user");

			while (r.next()) {
				
				n = r.getInt(1);
			}
			r.close();
			con.commit();
			con.close();
		} catch (ClassNotFoundException exception) {
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception) {
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception) {
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}

		return n;
	}
	public User retrieve() throws DataAccessException {
		
		User user = new User();
		

		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from user";
			ResultSet res = st.executeQuery(sql);

		
			while (res.next()) {
				user = new User();
				int uid = res.getInt(1);
				String password = res.getString(2);
				String name = res.getString(3);
				String  mail= res.getString(4);
				

				user.setUid(uid);
				user.setName(name);
				user.setPassword(password);
				user.setEmailId(mail);
				
				

			}

			res.close();
			con.commit();
			con.close();
		} catch (ClassNotFoundException exception) {
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception) {
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception) {
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}

		return user;
	}


}
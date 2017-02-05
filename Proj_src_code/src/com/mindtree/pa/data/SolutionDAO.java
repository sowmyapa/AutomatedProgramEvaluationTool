package com.mindtree.pa.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.AppConstants;

public class SolutionDAO extends BaseDAO {
	
	public static java.sql.Date getCurrentJavaSqlDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	
	public void insert(Solution solution) throws DataAccessException {
		String sql = "insert into solution (pid,sourceCode,status,createdOn) values(?,?,?,?)";
		try {

			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, solution.getPid());
			st.setString(2, solution.getSourceCode());
			st.setBoolean(3, solution.isStatus());
			java.sql.Date date = getCurrentJavaSqlDate();
			st.setDate(4, date);
			
			

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

	public void insertCode(Solution solution) throws DataAccessException {
		String sql = "insert into solution (pid,sourceCode,createdOn) values(?,?,?)";
		try {

			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, solution.getPid());
			st.setString(2, solution.getSourceCode());
			java.sql.Date date = getCurrentJavaSqlDate();
			st.setDate(3, date);
			

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

	public void update(Solution solution) throws DataAccessException {

		String sql = "update solution set sourceCode=?,status=?,createdOn=? where pid=?";
		try {
			
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, solution.getSourceCode());
			st.setBoolean(2, solution.isStatus());
			java.sql.Date date = getCurrentJavaSqlDate();
			st.setDate(3, date);
			st.setInt(4, solution.getPid());

			st.executeUpdate();
			st.close();
			con.commit();
			
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

	public void updateCode(Solution solution) throws DataAccessException {

		String sql = "update solution set sourceCode=?,createdOn=? where pid=?";
		try {
			
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, solution.getSourceCode());
			java.sql.Date date = getCurrentJavaSqlDate();
			st.setDate(2, date);
			st.setInt(3, solution.getPid());

			st.executeUpdate();
			st.close();
			con.commit();
			
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

	public void updateStatus(Solution solution) throws DataAccessException {

		String sql = "update solution set status=?,report=? where pid=?";
		try {
			
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setBoolean(1, solution.isStatus());
			st.setString(2, solution.getReport());
			st.setInt(3, solution.getPid());
			

			st.executeUpdate();
			st.close();
			con.commit();
			
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

	public void delete(Solution solution) throws DataAccessException
	{
		String sql = "delete from solution where pid=?";
		try
		{

			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, solution.getPid());
			st.executeUpdate();
			st.close();
			con.commit();
			
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
	
	public int count() throws DataAccessException {

		int n = 0;

		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select count(*) from solution");

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

	public Solution retrieveById(int pid) throws DataAccessException {
		
		Solution solution = new Solution();
		
		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from solution where pid=" + pid + " ";
			ResultSet res = st.executeQuery(sql);

			while (res.next()) {
				solution = new Solution();
				int id = res.getInt(1);
				String scode = res.getString(2);
				Boolean status = res.getBoolean(3);
				Date date = res.getDate(4);
				String report= res.getString(5);

				solution.setPid(id);
				solution.setSourceCode(scode);
				solution.setStatus(status);
				solution.setCreatedOn(date);
				solution.setReport(report);
				

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

		return solution;

	}


	public Solution retrieveSourceCode(int pid) throws DataAccessException {
		
		Solution solution = new Solution();
		

		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select sourceCode from solution where pid=" + pid + " ";
			ResultSet res = st.executeQuery(sql);

			while (res.next()) {
				solution = new Solution();
		
				String scode = res.getString(1);
				
				solution.setSourceCode(scode);
								
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

		return solution;

	}
	
	public Solution retrieveSourceCode(Solution solution) throws DataAccessException {
		
		Solution solution1 = new Solution();
		
		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select sourceCode from solution where pid=" + solution.getPid()+ " ";
			ResultSet res = st.executeQuery(sql);

			while (res.next()) {
				solution1 = new Solution();
				
				String scode = res.getString(1);
				
				solution1.setSourceCode(scode);
				
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

		return solution1;

	}

	public Solution[] retrieve() throws DataAccessException {
		int num = count();
		Solution[] solution = new Solution[num];
		

		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from solution";
			ResultSet res = st.executeQuery(sql);

			int i = 0;
			while (res.next()) {
				solution[i] = new Solution();
				int pid = res.getInt(1);
				String scode = res.getString(2);
				Boolean status = res.getBoolean(3);
				Date date = res.getDate(4);

				solution[i].setPid(pid);
				solution[i].setSourceCode(scode);
				solution[i].setStatus(status);
				solution[i].setCreatedOn(date);
				
				i++;

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

		return solution;
	}
		public Boolean checkPid(Solution solution) throws DataAccessException {
			String sql = "select * from solution where pid = ?";
			try {

				Connection connection = getConnection();
				PreparedStatement st = connection.prepareStatement(sql);
				st.setInt(1, solution.getPid());
				ResultSet r = st.executeQuery();  
					if(r.next())
					{
						System.out.println("pid present,so call update");
						return true;
					}
					else
					{
						System.out.println("pid absent, so call insert");
					}
					r.close();
				
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
			return false;

		} 

	

	
	 

}
package com.mindtree.pa.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.pa.entity.Problem;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.AppConstants;

public class ProblemDAO extends BaseDAO {

	public static java.sql.Date getCurrentJavaSqlDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public void insert(Problem problem) throws DataAccessException {

		String sql = "insert into problem values(?,?,?,?,?,?,?,?,?)";
		try {

			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, problem.getPid());
			st.setString(2, problem.getName());
			st.setInt(3, problem.getSemester());
			st.setString(4, problem.getDescription());
			st.setInt(5, problem.getMarks());
			st.setInt(6, problem.getTime());
			java.sql.Date date = getCurrentJavaSqlDate();
			st.setDate(7, date);

			st.setString(8, problem.getCreatedBy());
			st.setString(9, problem.getPemail());
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

	public void update(Problem problem) throws DataAccessException {

	}

	public void delete(Problem problem) throws DataAccessException {
		String sql = "delete from problem where pid=?";
		try {

			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, problem.getPid());
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

		System.out.println("value inserted successfully");

	}
	

	public int count() throws DataAccessException {

		int n = 0;

		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select count(*) from problem");

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

	public Problem retrieveById(int id) throws DataAccessException {

		Problem prob = new Problem();

		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from problem where pid= " + id + " ";
			ResultSet res = st.executeQuery(sql);
			while (res.next()) {

				int pid = res.getInt(1);
				String name = res.getString(2);
				int sem = res.getInt(3);
				String desc = res.getString(4);
				int marks = res.getInt(5);
				int time = res.getInt(6);
				Date date = res.getDate(7);
				String createdBy = res.getString(8);
				String pemail = res.getString(9);

				prob.setPid(pid);
				prob.setName(name);
				prob.setSemester(sem);
				prob.setDescription(desc);
				prob.setMarks(marks);
				prob.setTime(time);
				prob.setCreatedOn(date);
				prob.setCreatedBy(createdBy);

				prob.setPemail(pemail);

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

		return prob;
	}

	public List<Problem> retrieve() throws DataAccessException {

		List<Problem> problem = new ArrayList();

		try {

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from problem";
			ResultSet res = st.executeQuery(sql);

			int i = 0;
			while (res.next()) {
				Problem prob = new Problem();

				int pid = res.getInt(1);
				String name = res.getString(2);
				int sem = res.getInt(3);
				String desc = res.getString(4);
				int marks = res.getInt(5);
				int time = res.getInt(6);
				Date date = res.getDate(7);
				String createdBy = res.getString(8);
				String pemail = res.getString(9);

				prob.setPid(pid);
				prob.setName(name);
				prob.setSemester(sem);
				prob.setDescription(desc);
				prob.setMarks(marks);
				prob.setTime(time);
				prob.setCreatedOn(date);
				prob.setCreatedBy(createdBy);

				prob.setPemail(pemail);

				problem.add(i, prob);
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

		return problem;
	}
	

	public Boolean checkPid(Problem problem) throws DataAccessException {
		String sql = "select * from problem where pid = ?";
		try {

			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, problem.getPid());
			ResultSet r = st.executeQuery();
			if (r.next()) {
				return true;
			} else {
				
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

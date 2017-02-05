package com.mindtree.pa.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.pa.entity.TestCase;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.AppConstants;

public class TestCaseDAO extends BaseDAO
{
	public void insert(TestCase testCase) throws DataAccessException
	{
		String sql = "insert into testcase values(?,?,?,?,?,?,?,?)";
		try
		{

			Connection connection = getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, testCase.getPid());
			st.setInt(2, testCase.getTid());
			st.setString(3, testCase.getInput());
			st.setString(4, testCase.getOutput());
			st.setString(5, testCase.getTitle());
			st.setString(6, testCase.getDescription());
			st.setBoolean(7, testCase.isStatus());
			st.setFloat(8, testCase.getWeightage());

			st.executeUpdate();
			st.close();
			connection.commit();
			connection.close();
			cleanUp();
		} catch (ClassNotFoundException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception)
		{
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}

	}

	public void updateStatus(TestCase testcase) throws DataAccessException {

		String sql = "update testcase set status=? where pid=? and tid=?";
		try {
			
			

			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setBoolean(1, testcase.isStatus());
			st.setInt(2, testcase.getPid());
			st.setInt(3, testcase.getTid());

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

	public void update(TestCase testCase) throws DataAccessException
	{

	}

	public void delete(TestCase testcase) throws DataAccessException
	{
		String sql = "delete from testcase where pid=?";
		try
		{

			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, testcase.getPid());
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
	

	public int count(int pid) throws DataAccessException
	{

		int n = 0;

		try
		{
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select count(*) from testcase where pid="+ pid+ "");

			while (r.next())
			{
				n = r.getInt(1);
			}
			r.close();
			con.commit();
			con.close();
		} catch (ClassNotFoundException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception)
		{
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}

		return n;
	}

	public int count() throws DataAccessException
	{

		int n = 0;

		try
		{
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select count(*) from testcase");

			while (r.next())
			{
				n = r.getInt(1);
			}
			r.close();
			con.commit();
			con.close();
		} catch (ClassNotFoundException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception)
		{
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}

		return n;
	}

	
	public TestCase retrieveById(int pid,int tid) throws DataAccessException
	{
		TestCase testcase = new TestCase();
		

		try
		{

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from testcase where  pid= "+pid+" and tid="+tid+"";
			ResultSet res = st.executeQuery(sql);

			
			while (res.next())
			{
				testcase = new TestCase();
				int pidTest = res.getInt(1);
				int tidTest = res.getInt(2);
				String input = res.getString(3);
				String output = res.getString(4);
				String title = res.getString(5);
				String desc = res.getString(6);
				Boolean status = res.getBoolean(7);
				
				Float score = res.getFloat(8);
				
				testcase.setPid(pidTest);
				testcase.setTid(tidTest);
				testcase.setInput(input);
				testcase.setOutput(output);
				testcase.setTitle(title);
				testcase.setDescription(desc);
				testcase.setStatus(status);
				testcase.setWeightage(score);
							

			}

			res.close();
			con.commit();
			con.close();
		} catch (ClassNotFoundException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception)
		{
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}


		return testcase;

	}
	
	public TestCase[] retrieve() throws DataAccessException
	{
		int num = count();
		TestCase[] testcase = new TestCase[num];
		

		try
		{

			Connection con = getConnection();
			Statement st = con.createStatement();

			String sql = "select * from testcase";
			ResultSet res = st.executeQuery(sql);

			int i = 0;
			while (res.next())
			{
				testcase[i] = new TestCase();
				int pid = res.getInt(1);
				int tid = res.getInt(2);
				String input = res.getString(3);
				String output = res.getString(4);
				String title = res.getString(5);
				String desc = res.getString(6);
				Boolean status = res.getBoolean(7);
				Float score = res.getFloat(8);

				testcase[i].setPid(pid);
				testcase[i].setTid(tid);
				testcase[i].setInput(input);
				testcase[i].setOutput(output);
				testcase[i].setTitle(title);
				testcase[i].setDescription(desc);
				testcase[i].setStatus(status);
				testcase[i].setWeightage(score);
				
				i++;

			}

			res.close();
			con.commit();
			con.close();
		} catch (ClassNotFoundException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("driverErrorMessage"), exception);
		} catch (SQLException exception)
		{
			throw new DataAccessException(AppConstants
					.getString("dbErrorMessage"), exception);
		} catch (Exception exception)
		{
			throw new DataAccessException(AppConstants
					.getString("generalErrorMessage"), exception);
		}


		return testcase;

	}

	}
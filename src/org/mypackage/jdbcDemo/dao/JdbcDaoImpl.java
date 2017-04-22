package org.mypackage.jdbcDemo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.mypackage.jdbcDemo.model.Apple;
import org.mypackage.jdbcDemo.model.Orange;

@Component
public class JdbcDaoImpl {

	//Source of database connection
	private DataSource dataSrc;
	//Central class of JDBC(Java Database Connectivity) core package.
	//It simplifies the use of JDBC and helps to avoid common errors. 
	//It executes core JDBC workflow, leaving application code to provide SQL and extract results. 
	//This class executes SQL queries or updates, initiating iteration over ResultSets and catching JDBC exceptions and translating them to the generic, more informative exception hierarchy defined in the org.springframework.dao package.
	//JDBC: A Java API that can access especially data stored in relational databases
	private JdbcTemplate jdbcTemp;
	//Template class with a basic set of JDBC operations, allowing the use of named parameters rather than traditional '?' placeholders
	private NamedParameterJdbcTemplate namJdbcTemp;
	
	public int getAppleCount() {
		//String for SQL query for returning a count of all apples from the Apple table
		String sql = "SELECT COUNT(*) FROM APPLE";
		//Return the execution of the query via the JdbcTemplate
		return jdbcTemp.queryForObject(sql, Integer.class);
	}
	
	public int getOrangeCount() {
		//String for the SQL query for returning a count of all oranges from the Orange table
		String sql = "SELECT COUNT(*) FROM ORANGE";
		//Return the return value of the execution of the query via the JdbcTemplate
		return jdbcTemp.queryForObject(sql, Integer.class);
	}
	
	public String getAppleDescription(int appleID) {
		//String for the SQL query for returning the description of the apple table where the ID matches the value in the parameter
		String sql = "SELECT description FROM apple WHERE id = ?";
		//Return the return value of the execution of the query via the JdbcTemplate 
		return jdbcTemp.queryForObject(sql, new Object[] {appleID}, String.class);
	}
	
	public String getOrangeDescription(int orangeID) {
		//String for the SQL query for returning the description of the orange table where the ID matches the value passed to the parameter
		String sql = "SELECT description FROM orange WHERE id = ?";
		//Return the return value of the execution of the query via the JdbcTemplate
		return jdbcTemp.queryForObject(sql, new Object[] {orangeID}, String.class);
	}
	
	public Apple getAppleForID(int appleID) {
		//String for the SQL query for returning all column values from the apple table where the ID matches the value in the parameter
		String sql = "SELECT * FROM apple WHERE id = ?";
		//Return the return value of the execution of the query via the JdbcTemplate
		return jdbcTemp.queryForObject(sql, new Object[] {appleID}, new AppleMapper());
	}
	
	public Orange getOrangeForID(int orangeID) {
		//String for the SQL query for returning a row from the orange table where the ID matches the value in the parameter
		String sql = "SELECT * FROM orange WHERE id = ?";
		//Return the return value of the execution of the query via the JdbcTemplate
		return jdbcTemp.queryForObject(sql, new Object[] {orangeID}, new OrangeMapper());
	}
	
	
	public List<Apple> getAllApples() {
		//String for the SQL query for returning all entries in the apple table
		String sql = "SELECT * FROM apple";
		//Return the return value of the execution of the query via the JdbcTemplate
		return jdbcTemp.query(sql, new AppleMapper());
	}
	
	public List<Orange> getAllOranges() {
		//String for the SQL query for returning all entries in the orange table
		String sql = "SELECT * FROM orange";
		//Return the return value of the execution of the query via the JdbcTemplate
		return jdbcTemp.query(sql, new OrangeMapper());
	}
	
	public void insertApple(Apple newApp){
		//String for the SQL query for inserting the values of the Apple passed to the parameter into the apples array
		String sql = "INSERT INTO apple (ID, DESCRIPTION) VALUES (:id, :description)";
		//Hashmap for named parameters to insert into the table
		Map<String, Object> namedParams = new HashMap<String, Object>();
		//Add to the named parameters hashmap a key of "id" and value of the new apple's ID
		namedParams.put("id", newApp.getID());
		//Add to the named parameters hasmap a key of "description" adn a value of the new apple's description
		namedParams.put("description", newApp.getDescription());
		//Have the named JDBC template update the table using the query and named parameters
		namJdbcTemp.update(sql, namedParams);
	}
	
	public void insertOrange(Orange newOra){
		//String for the SQL query for inserting the values of the Orange passed to the parameter into the orange array
		String sql = "INSERT INTO orange (ID, DESCRIPTION) VALUES (:id, :description)";
		//Hashmap for named parameters to insert into the table
		Map<String, Object> namedParams = new HashMap<String, Object>();
		//Add to the named parameters hashmap a key of "id" and value of the new orange's ID
		namedParams.put("id", newOra.getID());
		//Add to the named parameters hashmap a key of "description" and value of the new orange's description
		namedParams.put("description", newOra.getDescription());
		//Have the named JDBC template update the table using the query and named parameters
		namJdbcTemp.update(sql, namedParams);
	}
	
	public void removeApple(int appleID) {
		//String for the SQL query for deleting from the apples table where the ID matches that of the parameter value
		String sql = "DELETE FROM apple WHERE id = ?";
		//Hashmap for named parameters
		Map<String, Object> namedParams = new HashMap<String, Object>();
		//Add to the named parameters hashmap a key of "id" and value of the apple ID
		namedParams.put("id", appleID);
		//Have the named JDBC template update the table using the SQL and named parameters
		namJdbcTemp.update(sql, namedParams);
	}
	
	public void removeOrange(int orangeID) {
		//String for the SQL query or deleting from the apples table where the ID matches that of the parameter value
		String sql = "DELETE FROM orange WHERE id = ?";
		//Hashmap for named parameters
		Map<String, Object> namedParams = new HashMap<String, Object>();
		//Add to the named parameters hashmap a key of "id" and value of the orange ID
		namedParams.put("id", orangeID);
		//Have the named JDBC template update the table using the SQL and named parameters
		namJdbcTemp.update(sql, namedParams);
	}
	
	//DATA SOURCE FUNCTIONS
	
	public DataSource getDataSource() {
		//Return the data source object
		return dataSrc;
	}

	@Autowired
	public void setDataSource(DataSource dataSrc) {
		//Instantiate the JDBC template object as a new instance using the data source passed to the parameter
		this.jdbcTemp = new JdbcTemplate(dataSrc);
		//Instantiate the named parameter JDBC template object as a new instance using the data source passed to the parameter
		this.namJdbcTemp = new NamedParameterJdbcTemplate(dataSrc);
	}

	//JDBCTEMPLATE FUNCTIONS
	
	public JdbcTemplate getJdbcTemplate() {
		
		//Return the JDBC template object
		return jdbcTemp;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		//Set this instance's JDBC template as the one passed to the parameter
		this.jdbcTemp = jdbcTemp;
	}
	
	//INTERNAL CLASS: APPLEMAPPER
	private static final class AppleMapper implements RowMapper<Apple> {
		@Override
		public Apple mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			//New Apple object, using the class' alternate constructor for blank default value
			Apple newApp = new Apple();
			//Set the ID of the new Apple as the resultSet's "ID" field value
			newApp.setID(resultSet.getInt("ID"));
			//Set the description of the new Apple as the resultSet's "Description" field value
			newApp.setDescription(resultSet.getString("DESCRIPTION"));
			//Return the new Apple instance
			return newApp;
		}	
	}
	
	//INTERNAL CLASS: ORANGEMAPPER
		private static final class OrangeMapper implements RowMapper<Orange> {
			@Override
			public Orange mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				//Create a new Orange instance, using the class' alternate constructor for blank default value
				Orange newOra = new Orange();
				//Set the new Orange's ID as the resultSet's "ID" field value
				newOra.setID(resultSet.getInt("ID"));
				//Set the new Orange's description as the resultSet's "Description" field value
				newOra.setDescription(resultSet.getString("DESCRIPTION"));
				//Return the new Orange
				return newOra;
			}	
		}
}

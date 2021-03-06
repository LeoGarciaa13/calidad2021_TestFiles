package com.anahuac.calidad.dbunit;

import static org.junit.Assert.*;
// FIle Imports
import java.io.File;

// DB Unit test imports
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredTableMetaData;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.RowFilterTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
// Test Imports
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anahuac.calidad.DoublesDAO.Alumno;
import com.anahuac.calidad.dbunit.AlumnoDAOMySQL;

public class DAOALumnoTest extends DBTestCase{
	
	String nuevoCorreo = "hola003@gmail.com"; 
	// Declare DAO
	private AlumnoDAOMySQL daoMySql; 
	
	public DAOALumnoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.cj.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:33060/pruebas_db");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"secret");	
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
	}
	
	@Before
	public void setUp() throws Exception {
		// Initialize DAO
		daoMySql = new AlumnoDAOMySQL(); 
		// Set the initial condition of the database
		IDatabaseConnection connection = getConnection(); 
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch(Exception e) {
			fail("Error in setup: "+ e.getMessage()); 
		} finally {
			connection.close(); 
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 *  Test for adding student method
	 */
	
	@Test
	public void testAddAlumno() {
		Alumno alumno = new Alumno("004", "alumno004", "hola4@hola.com", 18);
		
		daoMySql.addAlumno(alumno);
		
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			ITable actualTable = databaseDataSet.getTable("alumnos_tbl");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}	
	}
	 
	
	/*
	 *  Test for deleting the fields of an student method
	 */
	
	@Test
	public void testDeleteAlumno() {
		Alumno alumno = new Alumno("003", "alumno003", "hola3@hola.com", 17);
		
		daoMySql.deleteAlumno(alumno);
		
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			ITable actualTable = databaseDataSet.getTable("alumnos_tbl");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/delete_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}	
	} 
	
	
	/*
	 *  Test for updating the email field method
	 */
	
	@Test
	public void testUpdateEmail() {
		Alumno alumno = new Alumno("003", "alumno003", "hola3@hola.com", 17);
		
		// Set the change in the email field
		alumno.setEmail(nuevoCorreo);
		// call update method
		daoMySql.updateEmail(alumno);
		
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			ITable actualTable = databaseDataSet.getTable("alumnos_tbl");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}	
	} 
	
	/*
	 *  Test for searching an specific student method
	 */
	
	@Test
	public void testSearchAlumno() {
		// call search method
		Alumno retrivied = daoMySql.searchAlumno("003");
		String query = "SELECT * FROM alumnos_tbl WHERE id = \"003\""; 
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			// Generate a temporal table with the select query only 
			QueryDataSet actualTable = new QueryDataSet(getConnection());
			actualTable.addTable("alumnosTemp_tbl", query);
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/select_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");
			
			// Verify 
			//System.out.print(actualTable.getTable("alumnosTemp_tbl").getValue(0, "nombre")); 
			Assertion.assertEquals(expectedTable, actualTable.getTable("alumnosTemp_tbl"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}	
	}
}

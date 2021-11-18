package com.anahuac.calidad.DoublesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DriverApp {

	public static void main(String[] args) {
		// Connect to MySql DataBase
		Alumno a = new Alumno("002", "alumno2", "hola@hola.com", 16); 
		Connection con;
		
		try {
			// Establish the driver connector
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			// Set the URI for connecting the MySql database
			con= DriverManager.getConnection(
					"jdbc:mysql://localhost:33060/pruebas_db","root", "secret");
			
			// Declare statement query to run 
			PreparedStatement preparedStatement; 
			preparedStatement = con.prepareStatement(
					"insert INTO alumnos_tbl(id,nombre,email,edad) values(?,?,?,?)");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, a.getId()); 
			preparedStatement.setString(2, a.getNombre()); 
			preparedStatement.setString(3, a.getEmail());
			preparedStatement.setInt(4, a.getEdad());
			
			// Return the result of connection nad statement
			int result = preparedStatement.executeUpdate(); 
			System.out.println(">> Return: " + result);
			
			// Close connection with the database
			con.close();
		}catch(Exception e) {
			System.out.println(e); 
		}
	}

}

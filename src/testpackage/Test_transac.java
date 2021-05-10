package testpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

public class Test_transac {

	public static void main(String[] args) {

		// Initialize the connection and save point to null
		Connection conn = null;
		Savepoint savep = null;
		
		// Try connecting to the DB
		try {
			conn = DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/dbdi24", "DBDI24", "notfound");
			
			// Catch SQL exception
		} catch (SQLException e) {
			
			System.err.println("There was an error getting the connection");
		}
		
		// Try disabling auto commit
		try {
			conn.setAutoCommit(false);
			System.err.println("The autocommit was disabled");
			
			// Catch SQL exception
		} catch (SQLException e) {
			
			System.err.println("There was an error disabling autocommit");
		}
		
		// Create the statement and perform updated
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate("HAU BETEEEEEEE");
			stmt.executeUpdate("HAU BETEEEEEEE");
			
			// Save a save point
			savep = conn.setSavepoint("Savepoint1");
			
			stmt.executeUpdate("HAU BETEEEEEEE");
			
			// Commit the transaction
			conn.commit();
			System.err.println("The transaction was successfully executed");
			
			// Catch the SQL exception
		} catch (SQLException e) {
			
			// Try rolling back to last save point
			try {
				conn.rollback(savep);
				System.err.println(e.getMessage());
				System.err.println("The transaction was rolled back to the las savepoint");
				
				// Catch SQL exception
			} catch (SQLException e2) {
				
				System.err.println("There was an error making the rollback");
			}
		}
	}

}

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
			conn = DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/dbdi20", "DBDI20", "notfound");
			
			// Catch SQL exception
		} catch (SQLException e) {
			
			System.err.println("There was an error getting the connection");
		}
		
		// Try disabling auto commit
		try {
			conn.setAutoCommit(false);
			System.out.println("The autocommit was disabled");
			
			// Catch SQL exception
		} catch (SQLException e) {
			
			System.err.println("There was an error disabling autocommit");
		}
		
		// Create the statement and perform updated
		try {
			Statement stmt = conn.createStatement();
			
			// Insert statement (correct)
			stmt.executeUpdate("INSERT INTO works_on VALUES ('123456789', 3, 20.0)");
			
			// Save a save point
			savep = conn.setSavepoint("BeforeInventedEssn");
			
			// Insert statement (using invented Essn value)
			stmt.executeUpdate("INSERT INTO works_on VALUES ('121212121', 2, 15.0)");
			
			// Commit the transaction
			conn.commit();
			System.out.println("The transaction was successfully executed!");
			
			// Catch the SQL exception
		} catch (SQLException e) {
			
			// Try rolling back to last save point and commit
			try {
				conn.rollback(savep);
				System.err.println(e.getMessage());
				System.err.println("\nThe transaction was rolled back to the las savepoint");
				conn.commit();
				System.out.println("The transaction commited succesfully after rollback!");
				
				// Catch SQL exception
			} catch (SQLException e2) {
				
				System.err.println("There was an error making the rollback");
			}
		}
	}

}

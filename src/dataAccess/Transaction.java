package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class Transaction {

	public String insertTransaction(String student) {

		// Initialize the connection and save point to null
		Connection conn = null;
		Savepoint savep = null;
		String result = "";

		// Starting connection
		System.out.println("~ Starting MySQL JDBC Connection ~");

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

			if (student.equals("Aritz Plazaola")) {

				// Insert statement (correct)
				stmt.executeUpdate("INSERT INTO project VALUES ('ProductF', 4, 'Narnia', 4)");

				// Save a save point
				savep = conn.setSavepoint("BeforeInventedDnum");

				// Insert statement (using invented Dnum value)
				stmt.executeUpdate("INSERT INTO works_on VALUES ('NautilusX', 5, 'Marioland', 6)");
			}

			if (student.equals("Zdravko Todorov")) {

				// Insert statement (correct)
				stmt.executeUpdate("INSERT INTO works_on VALUES ('123456789', 3, 20.0)");

				// Save a save point
				savep = conn.setSavepoint("BeforeInventedEssn");

				// Insert statement (using invented Essn value)
				stmt.executeUpdate("INSERT INTO works_on VALUES ('121212121', 2, 15.0)");
			}

			// Commit the transaction
			conn.commit();
			System.out.println("The transaction was successfully executed!");

			// Catch the SQL exception
		} catch (SQLException e) {

			// Try rolling back to last save point and commit
			try {
				conn.rollback(savep);
				System.err.println(e.getMessage());
				result += "Error detected!\n";
				System.err.println("\nThe transaction was rolled back to the las savepoint");
				result += "The transaction was rolled back to the las savepoint\n";
				conn.commit();
				System.out.println("The transaction commited succesfully after rollback!");
				result += "The transaction commited succesfully after rollback!\n";

				// Catch SQL exception
			} catch (SQLException e2) {

				System.err.println("There was an error making the rollback");
			}
		}
		return result;
	}

	public String updateTransaction(String student) {

		// Initialize the connection and save point to null
		Connection conn = null;
		Savepoint savep = null;
		String result = "";

		// Starting connection
		System.out.println("~ Starting MySQL JDBC Connection ~");

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
			
			// Disable foreign keys check
			Statement stmtAux = conn.createStatement();
			stmtAux.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtAux.close();

			if (student.equals("Aritz Plazaola")) {

				// Update statement
				stmt.executeUpdate("UPDATE person SET id = 20 WHERE nameId = 'Abigail'");
			}

			if (student.equals("Zdravko Todorov")) {

				// Insert statement
				stmt.executeUpdate("UPDATE department SET Mgr_ssn = 0 WHERE Dnumber = 4");
			}

			// Commit the transaction
			conn.commit();
			System.out.println("The transaction was successfully executed!");
			result += "The transaction was successfully executed!";
			
			// Enable foreign keys check
			Statement stmtAux2 = conn.createStatement();
			stmtAux2.execute("SET FOREIGN_KEY_CHECKS=1");
			stmtAux2.close();
			
			// Catch the SQL exception
		} catch (SQLException e) {

			System.err.println(e.getMessage());
			result += "Error detected!\n";
		}
		return result;
	}
}

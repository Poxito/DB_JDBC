package dataAccess;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Employee;
import domain.Queries;

public class Query {

	private Queries queries;
	private String SQL_SELECT;

	public Query() {

		queries = new Queries();
	}

	public ArrayList<String> executeQuery(String student, int queryN) {

		System.out.println("~ Starting MySQL JDBC Connection ~");

		///////////////////////////////////////////////////////
		// List for the result

		ArrayList<String> result = new ArrayList<String>();

		///////////////////////////////////////////////////////
		// Query strings

		// Aritz Plazaola's queries
		if (student.equals("Aritz Plazaola")) {

			// Depends on query number
			switch (queryN) {
			case 0:
				SQL_SELECT = queries.getAritzQueries().get(0);
				break;

			case 1:
				SQL_SELECT = queries.getAritzQueries().get(1);
				break;

			case 2:
				SQL_SELECT = queries.getAritzQueries().get(2);
				break;
			}
		}
		// Zdravko Todorov's queries
		if (student.equals("Zdravko Todorov")) {

			// Depends on query number
			switch (queryN) {
			case 0:
				SQL_SELECT = queries.getZdravkoQueries().get(0);
				break;

			case 1:
				SQL_SELECT = queries.getZdravkoQueries().get(1);
				break;

			case 2:
				SQL_SELECT = queries.getZdravkoQueries().get(2);
				break;
			}
		}

		///////////////////////////////////////////////////////
		// Execute if possible

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/dbdi20", "DBDI20",
				"notfound"); PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

			///////////////////////////////////////////////////////
			// Execute Query and store the result set

			ResultSet resultSet = preparedStatement.executeQuery();

			///////////////////////////////////////////////////////
			// Iterate over the result set

			while (resultSet.next()) {

				///////////////////////////////////////////////////////
				// Result depends on query type

				// Aritz Plazaola's queries
				if (student.equals("Aritz Plazaola")) {

					// Depends on query number
					switch (queryN) {
					case 0:
						String id = resultSet.getString("nameId");
						String cat = resultSet.getString("category");
						result.add(id + ", " + cat);
						break;

					case 1:
						String name = resultSet.getString("Fname");
						BigDecimal hours = resultSet.getBigDecimal("Hours");
						result.add(name + ", " + hours.toString());
						break;

					case 2:
						String id2 = resultSet.getString("nameId");
						result.add(id2);
						break;
					}
				}
				// Zdravko Todorov's queries
				if (student.equals("Zdravko Todorov")) {

					// Depends on query number
					switch (queryN) {
					case 0:
						String name = resultSet.getString("guidename");
						String tripto = resultSet.getString("TripTo");
						result.add(name + ", " + tripto);
						break;

					case 1:
						String id = resultSet.getString("CustomerId");
						String name2 = resultSet.getString("custname");
						String address = resultSet.getString("custaddress");
						int phone = resultSet.getInt("custphone");
						result.add(id + ", " + name2 + ", " + address + ", " + String.valueOf(phone));
						break;

					case 2:
						String name3 = resultSet.getString("Fname");
						String lname = resultSet.getString("Lname");
						result.add(name3 + ", " + lname);
						break;
					}
				}
			}

			///////////////////////////////////////////////////////
			// Catch exceptions

			// SQL related
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

			// Java exception
		} catch (Exception e) {
			e.printStackTrace();
		}

		///////////////////////////////////////////////////////
		// Return result

		return result;
	}

}
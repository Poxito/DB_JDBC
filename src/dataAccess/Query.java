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

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/dbdi24", "DBDI24",
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

					switch (queryN) {
					case 0:
						//TODO
						break;

					case 1:
						//TODO
						break;

					case 2:
						String id = resultSet.getString("nameId");
						result.add(id);
						break;
					}
				}
				// Zdravko Todorov's queries
				if (student.equals("Zdravko Todorov")) {

					switch (queryN) {
					case 0:
						//TODO
						break;

					case 1:
						//TODO
						break;

					case 2:
						//TODO
						break;
					}
				}

			/*	String id = resultSet.getString("Ssn");
				String name = resultSet.getString("Fname");
				BigDecimal salary = resultSet.getBigDecimal("Salary");

				Employee obj = new Employee();
				obj.setId(id);
				obj.setName(name);
				obj.setSalary(salary);

			result.add(obj);*/

			}

			///////////////////////////////////////////////////////
			// Print the result

//			result.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getSalary()));

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
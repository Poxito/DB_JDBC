package dataAccess;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Employee;

public class Query {

    public static void main(String[] args) {

        System.out.println("MySQL JDBC Connection Testing ~");
        
        ///////////////////////////////////////////////////////
        // List for the result
        
        List<Employee> result = new ArrayList<>();
        ///////////////////////////////////////////////////////
        // Query string
        
        String SQL_SELECT = "Select * from employee";

        ///////////////////////////////////////////////////////
        // Execute if possible
        
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://dif-mysql.ehu.es:3306/dbdi24", "DBDI24", "notfound");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
        	
            ///////////////////////////////////////////////////////
        	// Execute Query and store the result set
        	
            ResultSet resultSet = preparedStatement.executeQuery();

            ///////////////////////////////////////////////////////
            // Iterate over the result set
            
            while (resultSet.next()) {

                String id = resultSet.getString("Ssn");
                String name = resultSet.getString("Fname");
                BigDecimal salary = resultSet.getBigDecimal("Salary");

                Employee obj = new Employee();
                obj.setId(id);
                obj.setName(name);
                obj.setSalary(salary);

                result.add(obj);

            }
            
            ///////////////////////////////////////////////////////
            // Print the result
            
            result.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getSalary()));

          ///////////////////////////////////////////////////////
          // Catch exceptions
            
            // SQL related
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            
            //Java exception
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
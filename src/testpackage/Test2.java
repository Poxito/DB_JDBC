package testpackage;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Employee;

public class Test2 {

    public static void main(String[] args) {

        System.out.println("MySQL JDBC Connection Testing ~");

        List<Employee> result = new ArrayList<>();

        String SQL_SELECT = "Select * from employee";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://dif-mysql.ehu.es:3306/dbdi24", "DBDI24", "notfound");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

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
            result.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getSalary()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
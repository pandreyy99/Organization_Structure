package services;

import departmentsInformations.Department;
import departmentsInformations.Location;
import employeeType.Employee;
import salary.Salary;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScriereEmployee {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id,firstName,lastName,age,job,salariuNet,departmentId,departmentName";

    public static void writeCsvFile(String fileName) {
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        //Create new employees objects

        Location location = new Location("", "");

        Employee employee1 = new Employee("Ahmed", "Mohamed", 25,
                "Software Dev", new Salary(2000, 0.0),
                new Department(1, "IT", location));
        Employee employee2 = new Employee("Sara", "Said", 23,
                "Front-End Dev", new Salary(1800, 0.1),
                new Department(1, "IT", location));
        Employee employee3 = new Employee("Ali", "Hassan", 24,
                "Back-End Dev", new Salary(2500, 0.0),
                new Department(1, "IT", location));
        Employee employee4 = new Employee("Sama", "Karim", 20,
                "Full-stack Dev", new Salary(3000, 0.0),
                new Department(1, "IT", location));
        Employee employee5 = new Employee("Khaled", "Mohamed", 22,
                "Driver", new Salary(1200, 0.0),
                new Department(2, "Delivery", location));
        Employee employee6 = new Employee("Ghada", "Sarhan", 21,
                "Secretary", new Salary(2000, 0.0),
                new Department(3, "Administration", location));

        //Create a new list of employee objects
        List<Employee> employees = new ArrayList();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(String.valueOf(path));

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new employee object list to the CSV file
            int index = 1;
            for (Employee employee : employees) {
                try {
                    fileWriter.append(String.valueOf(index));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    fileWriter.append(COMMA_DELIMITER);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String firstName = employee.getEmployeeName().split(" ")[0];
                String lastName = employee.getEmployeeName().split(" ")[1];
                fileWriter.append(firstName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(lastName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(employee.getAge()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(employee.getJob());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(employee.getSalary().getSalariuNet()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(employee.getDepartment().getDepartmentId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(employee.getDepartment().getName());
                fileWriter.append(NEW_LINE_SEPARATOR);
                index += 1;
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }
}

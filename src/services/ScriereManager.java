package services;

import departmentsInformations.Department;
import departmentsInformations.Location;
import employeeType.Manager;
import salary.Salary;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScriereManager {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id,firstName,lastName,age,job,salariuNet,departmentId,departmentName";

    public static void writeCsvFile(String fileName) {
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        //Create new managers objects

        Location location = new Location("", "");

        Manager manager1 = new Manager("Ahmed", "Mohamed", 25,
                new Salary(2000, 0.0),
                new Department(1, "IT", location));
        Manager manager2 = new Manager("Sara", "Said", 23,
                new Salary(1800, 0.1),
                new Department(1, "IT", location));
        Manager manager3 = new Manager("Ali", "Hassan", 24,
                new Salary(2500, 0.0),
                new Department(1, "IT", location));
        Manager manager4 = new Manager("Sama", "Karim", 20,
                new Salary(3000, 0.0),
                new Department(1, "IT", location));
        Manager manager5 = new Manager("Khaled", "Mohamed", 22,
                new Salary(1200, 0.0),
                new Department(2, "Delivery", location));
        Manager manager6 = new Manager("Ghada", "Sarhan", 21,
                new Salary(2000, 0.0),
                new Department(3, "Administration", location));

        //Create a new list of manager objects
        List<Manager> managers = new ArrayList();
        managers.add(manager1);
        managers.add(manager2);
        managers.add(manager3);
        managers.add(manager4);
        managers.add(manager5);
        managers.add(manager6);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(String.valueOf(path));

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new manager object list to the CSV file
            int index = 1;
            for (Manager manager : managers) {
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
                String firstName = manager.getName().split(" ")[0];
                String lastName = manager.getName().split(" ")[1];
                fileWriter.append(firstName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(lastName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(manager.getAge()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(manager.getJob());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(manager.getSalary().getSalariuNet()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(manager.getDepartment().getDepartmentId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(manager.getDepartment().getName());
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

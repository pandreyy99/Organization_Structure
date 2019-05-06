package services;

import employeeType.Driver;
import salary.Salary;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScriereDriver {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id,firstName,lastName,age,job,salariuNet,driverLicense";

    public static void writeCsvFile(String fileName) {
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        //Create new drivers objects

        List<String> driverLicense = new ArrayList<>();
        driverLicense.add("A");
        driverLicense.add("B");
        driverLicense.add("DE");

        Driver driver1 = new Driver("Ahmed", "Mohamed", 25,
                new Salary(2000, 0.0), driverLicense);
        Driver driver2 = new Driver("Sara", "Said", 23,
                new Salary(1800, 0.1), driverLicense);
        Driver driver3 = new Driver("Ali", "Hassan", 24,
                new Salary(2500, 0.0), driverLicense);
        Driver driver4 = new Driver("Sama", "Karim", 20,
                new Salary(3000, 0.0), driverLicense);
        Driver driver5 = new Driver("Khaled", "Mohamed", 22,
                new Salary(1200, 0.0), driverLicense);
        Driver driver6 = new Driver("Ghada", "Sarhan", 21,
                new Salary(2000, 0.0), driverLicense);

        //Create a new list of driver objects
        List<Driver> drivers = new ArrayList();
        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);
        drivers.add(driver4);
        drivers.add(driver5);
        drivers.add(driver6);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(String.valueOf(path));

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new driver object list to the CSV file
            int index = 1;
            for (Driver driver : drivers) {
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
                String firstName = driver.getName().split(" ")[0];
                String lastName = driver.getName().split(" ")[1];
                fileWriter.append(firstName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(lastName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(driver.getAge()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(driver.getJob());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(driver.getSalary().getSalariuNet()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(driver.getAllDriverLicense());
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

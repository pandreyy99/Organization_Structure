package services;

import departmentsInformations.Department;
import departmentsInformations.Location;
import employeeType.Employee;
import salary.Salary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CitireEmployee {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Employee attributes index
    private static final int Employee_ID_IDX = 0;
    private static final int Employee_FNAME_IDX = 1;
    private static final int Employee_LNAME_IDX = 2;
    private static final int Employee_AGE = 3;
    private static final int Employee_JOB = 4;
    private static final int Employee_SALARY = 5;
    private static final int Employee_DPRT_INDEX = 6;
    private static final int Employee_DPRTNAME_INDEX = 7;

    public static void readCsvFile(String fileName) {
        // set Path
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        BufferedReader fileReader = null;

        try {

            //Create a new list of employees to be filled by CSV file data
            List<Employee> employees = new ArrayList();

            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(String.valueOf(path)));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new employee object and fill his  data
                    Employee employee = new Employee(tokens[Employee_FNAME_IDX], tokens[Employee_LNAME_IDX],
                            Integer.parseInt(tokens[Employee_AGE]), tokens[Employee_JOB],
                            new Salary(Double.parseDouble(tokens[Employee_SALARY]), 0.0),
                            new Department(Integer.parseInt(tokens[Employee_DPRT_INDEX]), tokens[Employee_DPRTNAME_INDEX],
                                    new Location("", "")));
                    employees.add(employee);
                }
            }

            //Print the new employee list
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

    }
}

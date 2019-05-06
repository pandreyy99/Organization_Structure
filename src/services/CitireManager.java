package services;

import departmentsInformations.Department;
import departmentsInformations.Location;
import employeeType.Manager;
import salary.Salary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CitireManager {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Manager attributes index
    private static final int Manager_ID_IDX = 0;
    private static final int Manager_FNAME_IDX = 1;
    private static final int Manager_LNAME_IDX = 2;
    private static final int Manager_AGE = 3;
    private static final int Manager_SALARY = 5;
    private static final int Manager_DPRT_INDEX = 6;
    private static final int Manager_DPRTNAME_INDEX = 7;

    public static void readCsvFile(String fileName) {
        // set Path
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        BufferedReader fileReader = null;

        try {

            //Create a new list of managers to be filled by CSV file data
            List<Manager> managers = new ArrayList();

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
                    //Create a new manager object and fill his  data
                    Manager manager = new Manager(tokens[Manager_FNAME_IDX], tokens[Manager_LNAME_IDX],
                            Integer.parseInt(tokens[Manager_AGE]),
                            new Salary(Double.parseDouble(tokens[Manager_SALARY]), 0.0),
                            new Department(Integer.parseInt(tokens[Manager_DPRT_INDEX]), tokens[Manager_DPRTNAME_INDEX],
                                    new Location("", "")));
                    managers.add(manager);
                }
            }

            //Print the new manager list
            for (Manager manager : managers) {
                System.out.println(manager.toString());
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

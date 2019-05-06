package services;

import employeeType.Driver;
import salary.Salary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CitireDriver {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Driver attributes index
    private static final int Driver_ID_IDX = 0;
    private static final int Driver_FNAME_IDX = 1;
    private static final int Driver_LNAME_IDX = 2;
    private static final int Driver_AGE = 3;
    private static final int Driver_SALARY = 5;
    private static final int Driver_LICENSE_IDX = 6;

    public static void readCsvFile(String fileName) {
        // set Path
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        BufferedReader fileReader = null;

        try {

            //Create a new list of drivers to be filled by CSV file data
            List<Driver> drivers = new ArrayList();

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
                    List<String> licenses = new LinkedList<>();
                    licenses.addAll(Arrays.asList(tokens[Driver_LICENSE_IDX].split(" ")));
                    //Create a new driver object and fill his  data
                    Driver driver = new Driver(tokens[Driver_FNAME_IDX], tokens[Driver_LNAME_IDX],
                            Integer.parseInt(tokens[Driver_AGE]),
                            new Salary(Double.parseDouble(tokens[Driver_SALARY]), 0.0), licenses);
                    drivers.add(driver);
                }
            }

            //Print the new driver list
            for (Driver driver : drivers) {
                System.out.println(driver.toString());
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

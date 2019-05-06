package services;

import employeeType.Secretary;
import salary.Salary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CitireSecretary {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Secretary attributes index
    private static final int Secretary_ID_IDX = 0;
    private static final int Secretary_FNAME_IDX = 1;
    private static final int Secretary_LNAME_IDX = 2;
    private static final int Secretary_AGE = 3;
    private static final int Secretary_SALARY = 5;
    private static final int Secretary_LooksPretty = 6;

    public static void readCsvFile(String fileName) {
        // set Path
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        BufferedReader fileReader = null;

        try {

            //Create a new list of secretaries to be filled by CSV file data
            List<Secretary> secretaries = new ArrayList();

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
                    //Create a new secretary object and fill his  data
                    Secretary secretary = new Secretary(tokens[Secretary_FNAME_IDX], tokens[Secretary_LNAME_IDX],
                            Integer.parseInt(tokens[Secretary_AGE]),
                            new Salary(Double.parseDouble(tokens[Secretary_SALARY]), 0.0),
                            Boolean.parseBoolean(tokens[Secretary_LooksPretty]));
                    secretaries.add(secretary);
                }
            }

            //Print the new secretary list
            for (Secretary secretary : secretaries) {
                System.out.println(secretary.toString());
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

package services;

import departmentsInformations.Location;
import employeeType.Secretary;
import salary.Salary;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScriereSecretary {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id,firstName,lastName,age,job,salariuNet,looksPretty";

    public static void writeCsvFile(String fileName) {
        Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");

        //Create new secretary objects

        Location location = new Location("", "");

        Secretary secretary1 = new Secretary("Maria", "Ioana", 25,
                new Salary(2000, 0.0),
                true);
        Secretary secretary2 = new Secretary("Sara", "Said", 23,
                new Salary(1800, 0.1),
                false);
        Secretary secretary3 = new Secretary("Anais", "Bostan", 22,
                new Salary(2500, 0.0),
                false);
        Secretary secretary4 = new Secretary("Sama", "Karim", 20,
                new Salary(3000, 0.0),
                true);
        Secretary secretary5 = new Secretary("Becky", "G", 21,
                new Salary(1200, 0.0),
                true);
        Secretary secretary6 = new Secretary("Tina", "Retina", 19,
                new Salary(2000, 0.0),
                true);

        //Create a new list of secretary objects
        List<Secretary> secretaries = new ArrayList();
        secretaries.add(secretary1);
        secretaries.add(secretary2);
        secretaries.add(secretary3);
        secretaries.add(secretary4);
        secretaries.add(secretary5);
        secretaries.add(secretary6);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(String.valueOf(path));

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new secretary object list to the CSV file
            int index = 1;
            for (Secretary secretary : secretaries) {
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
                String firstName = secretary.getName().split(" ")[0];
                String lastName = secretary.getName().split(" ")[1];
                fileWriter.append(firstName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(lastName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(secretary.getAge()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(secretary.getJob());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(secretary.getSalary().getSalariuNet()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(secretary.lookPretty()));
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

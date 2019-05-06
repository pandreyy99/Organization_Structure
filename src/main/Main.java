package main;

import departmentsInformations.Department;
import employeeType.Employee;
import services.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Services service = new Services();
        service.addAnDepartment("IT", "Bucharest", "Splaiul Independentei nr. 204");
        service.setDepartmentManager("IT", "P", "A", 19,
                5000, 0.25);
        service.setDepartmentSecretary("IT", "A", "O", 20,
                3500, 0.0, true);
        List<String> licenses = new LinkedList<>();
        licenses.add("B");
        licenses.add("A1");
        licenses.add("DE");
        licenses.add("A");
        Collections.sort(licenses);
        service.addDriver("N", "A", 30, 1500, 0.1, licenses);
        service.addDriver("M", "L", 30, 1500, 0.1, licenses);
        service.addDriver("Z", "A", 30, 1500, 0.1, licenses);
        service.removeDriver("M", "A", 30);
        service.addEmployee("IT", "A", "A", 20, "software Dev",
                3000, 0.0);
        service.addEmployee("IT", "B", "B", 20, "software Dev",
                2800, 0.0);
        service.addEmployee("IT", "C", "C", 19, "Front-End Dev",
                2500, 0.01);
        service.addEmployee("IT", "D", "D", 18, "Front-End Dev",
                1800, 0.0);
        service.increaseEmployeeSalary("IT", "C", "C", 20, 3000, 0.2);
        service.decreseEmployeeSalary("IT", "C", "C", 20, 0.15);
        service.fireEmployee("IT", "B", "B", 20);

        Vector<Department> departments = service.getDepartments();

        for (Department d : departments) {
            System.out.println("Departamentul " + d.getName());
            System.out.println("Manager : " + d.getManager());
            System.out.println("Secretary : " + d.getSecretary());
            List<Employee> employees;
            employees = d.getEmployees();
            for (Employee e : employees) {
                System.out.println(e.toString());
            }
        }

        service.promoteEmployee("IT", "D", "D", 18);
        departments = service.getDepartments();
        for (Department d : departments) {
            System.out.println("Departamentul " + d.getName());
            System.out.println("Manager : " + d.getManager());
        }

        service.changeDepartmentLocation("IT", "Braila", "Bd. Independentei nr. 101");
        departments = service.getDepartments();
        for (Department d : departments) {
            System.out.println(d.getName() + " has address : " + d.getLocation().toString());
        }

        ScriereEmployee employeeWriter = new ScriereEmployee();
        CitireEmployee employeeReader = new CitireEmployee();

        employeeWriter.writeCsvFile("employee");

        ScriereManager managerWriter = new ScriereManager();
        CitireManager managerReader = new CitireManager();

        managerWriter.writeCsvFile("manager");

        ScriereSecretary secretaryWriter = new ScriereSecretary();
        CitireSecretary secretaryReader = new CitireSecretary();

        secretaryWriter.writeCsvFile("secretary");

        ScriereDriver driverWriter = new ScriereDriver();
        CitireDriver driverReader = new CitireDriver();

        driverWriter.writeCsvFile("driver");
    }
}

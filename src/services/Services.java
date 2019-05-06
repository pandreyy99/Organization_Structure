package services;

import departmentsInformations.Department;
import departmentsInformations.Location;
import employeeType.Driver;
import employeeType.Employee;
import employeeType.Manager;
import employeeType.Secretary;
import salary.Salary;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Services {
    private Vector<Department> departments = new Vector<>();
    private List<Driver> drivers = new LinkedList<>();

    public Services() {
    }

    public Vector<Department> getDepartments() {
        return departments;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public boolean addAnDepartment(String name, String city, String address) {
        // printing the action
        auditServices.writeCsvFile("addAnDepartment");

        //adding an department
        int id = departments.size() + 1;
        Location location = new Location(city, address);
        Department department = new Department(id, name, location);
        departments.add(department);
        return true;
    }

    public void setDepartmentSecretary(String DepartmentName, String secretaryFirstName, String secretaryLastName,
                                       int age, double salary, double cmmpct, boolean looksPretty) {
        // printing the action
        auditServices.writeCsvFile("setDepartmentSecretary");

        Salary secretarySalary = new Salary(salary, cmmpct);
        for (Department d : departments) {
            if (d.getName().equals(DepartmentName)) {
                Secretary secretary = new Secretary(secretaryFirstName, secretaryLastName, age, secretarySalary, looksPretty);
                secretary.setDepartment(d);
                d.setSecretary(secretary);
            }
        }
    }

    public void setDepartmentManager(String DepartmentName, String managerFirstName, String managerLastName,
                                     int age, double salary, double cmmpct) {
        // printing the action
        auditServices.writeCsvFile("setDepartmentManager");

        Salary secretarySalary = new Salary(salary, cmmpct);
        for (Department d : departments) {
            if (d.getName().equals(DepartmentName)) {
                Manager manager = new Manager(managerFirstName, managerLastName, age, secretarySalary, d);
                d.setManager(manager);
            }
        }
    }

    public boolean addEmployee(String DepartmentName, String firstName, String lastName, int age,
                               String job, double salary, double cmmpct) {
        // printing the action
        auditServices.writeCsvFile("addEmployee");

        Salary employeeSalary = new Salary(salary, cmmpct);
        for (Department d : departments) {
            if (d.getName().equals(DepartmentName)) {
                Employee employee = new Employee(firstName, lastName, age, job, employeeSalary, d);
                d.addEmployee(employee);
                return true;
            }
        }
        return false;
    }

    public void fireEmployee(String DepartmentName, String firstName, String lastName, int age) {
        // printing the action
        auditServices.writeCsvFile("fireEmployee");

        Salary s = new Salary(0, 0.0);
        for (Department d : departments) {
            if (d.getName().equals(DepartmentName)) {
                Employee personToFire = new Employee(firstName, lastName, age, " ", s, d);
                if (d.removeEmployee(personToFire))
                    System.out.println(personToFire.getEmployeeName() + " was fired.");
                else
                    System.out.println("Something went wrong!");
            }
        }
    }

    public void promoteEmployee(String departmentName, String firstName, String lastName, int age) {
        // printing the action
        auditServices.writeCsvFile("promoteEmployee");

        for (Department d : departments) {
            if (d.getName().equals(departmentName)) {
                List<Employee> employees = d.getEmployees();
                Salary salary = new Salary(0, 0.0);
                Employee employee = new Employee(firstName, lastName, age, " ", salary, d);
                for (Employee e : employees) {
                    if (e.equals(employee)) {
                        Manager newManager = new Manager(firstName, lastName, age, e.getSalary(), d);
                        d.setManager(newManager);
                        d.removeEmployee(e);
                    }
                }
            }
        }
    }

    public boolean changeDepartmentLocation(String DepartmentName, String newCity, String newAdress) {
        // printing the action
        auditServices.writeCsvFile("changeDepartmentLocation");

        Location newLocation = new Location(newCity, newAdress);
        for (Department d : departments) {
            if (d.getName().equals(DepartmentName)) {
                d.setLocation(newLocation);
                return true;
            }
        }
        return false;
    }

    public void addSecretaryActivity(String departmentName, String activityName) {
        // printing the action
        auditServices.writeCsvFile("addSecretaryActivity");

        for (Department d : departments) {
            if (d.getName().equals(departmentName)) {
                Secretary secretary = d.getSecretary();
                secretary.addActivity(activityName);
                d.setSecretary(secretary);
            }
        }
    }

    public void completeActivity(String departmentName, String activityName) {
        // printing the action
        auditServices.writeCsvFile("completeActivity");

        for (Department d : departments) {
            if (d.getName().equals(departmentName)) {
                Secretary secretary = d.getSecretary();
                secretary.removeActivity(activityName);
                d.setSecretary(secretary);
            }
        }
    }

    public void increaseEmployeeSalary(String departmentName, String firstName, String lastName, int age,
                                       double newSalary, double newCMMPCT) {
        // printing the action
        auditServices.writeCsvFile("increaseEmployeeSalary");

        Salary salary = new Salary(newSalary, newCMMPCT);
        for (Department d : departments) {
            if (d.getName().equals(departmentName)) {
                Employee employeeToSearchFor = new Employee(firstName, lastName, age, " ", salary, d);
                List<Employee> employees = d.getEmployees();
                for (Employee e : employees) {
                    if (e.equals(employeeToSearchFor)) {
                        e.setSalary(salary);
                    }
                }
            }
        }
    }

    public void decreseEmployeeSalary(String departmentName, String firstName, String lastName, int age,
                                      double penalty) {
        // printing the action
        auditServices.writeCsvFile("decreaseEmployeeSalary");

        for (Department d : departments) {
            if (d.getName().equals(departmentName)) {
                Salary salary = new Salary(0, 0.0);
                Employee employeeToSearchFor = new Employee(firstName, lastName, age, " ", salary, d);
                List<Employee> employees = d.getEmployees();
                for (Employee e : employees) {
                    if (e.equals(employeeToSearchFor)) {
                        salary = e.getSalary();
                        Salary newSalary;
                        newSalary = new Salary(salary.getSalariuBrut() * (1 - penalty), salary.getCommissionPct());
                        e.setSalary(newSalary);
                    }
                }
            }
        }
    }

    public void addDriver(String firstName, String lastName, int age, double salary,
                          double cmmpct, List<String> driverLicenses) {
        // printing the action
        auditServices.writeCsvFile("addDriver");

        Salary driverSalary = new Salary(salary, cmmpct);
        Driver driver = new Driver(firstName, lastName, age, driverSalary, driverLicenses);
        drivers.add(driver);
    }

    public void removeDriver(String firstName, String lastName, int age) {
        // printing the action
        auditServices.writeCsvFile("removeDriver");

        Driver driverToSearchFor = new Driver(firstName, lastName, age, new Salary(0, 0.0),
                new LinkedList<>());
        for (Driver d : drivers) {
            if (d.equals(driverToSearchFor))
                drivers.remove(d);
        }
    }

    public void addLicenseCategoryToDriver(String firstName, String lastName, int age, String category) {
        // printing the action
        auditServices.writeCsvFile("addLicenseCategoryToDriver");

        Driver driverToSearchFor = new Driver(firstName, lastName, age, new Salary(0, 0.0),
                new LinkedList<>());
        for (Driver d : drivers) {
            if (d.equals(driverToSearchFor))
                d.addDriverLicenseCategory(category);
        }
    }
}

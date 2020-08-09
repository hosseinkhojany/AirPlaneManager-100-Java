package Models;

import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Views.EmployeePages;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class Employee extends Person   {
    private static int idMaker = 1;
    private double salary;
    private String address;

    public Employee(String name, String family, String phoneNumber, String email,
                   String username, String password, String address,
                   double salary) {
        super(name, family, phoneNumber, email, "2", username, password);
        this.salary = salary;
        this.address = address;
        id += (People.employeesIdMaker++);

//        id = new SimpleStringProperty(id.get() + People.employeesIdMaker++);
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public void show(Stage primaryStage, People people, Flights flights, Airplanes airplanes) {
        EmployeePages employeePages = new EmployeePages(primaryStage,this, people, flights, airplanes);
        try {
            employeePages.dashboardPage();
        } catch (FileNotFoundException e) {
            System.err.println("Image Files not fount, Check for ~\\Images\\employee.png");
        }
    }
}

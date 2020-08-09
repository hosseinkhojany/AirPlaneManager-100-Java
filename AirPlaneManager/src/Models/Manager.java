package Models;

import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Views.ManagerPages;
import Views.SuperAdminPages;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Manager extends Person   {
    private double salary;
    private String address;
    private boolean isSuperAdmin;
    ArrayList<Message> messages = new ArrayList<>();

    public Manager(String name, String family, String phoneNumber, String email,
                   String username, String password, String address,
                   double salary, boolean isSuperAdmin) {
        super(name, family, phoneNumber, email, "1", username, password);
        this.salary = salary;
        this.address = address;
        this.isSuperAdmin = isSuperAdmin;
        id += People.managersIdMaker++;
//        id = new SimpleStringProperty(id.get() + People.managersIdMaker++);
    }


    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public ArrayList<Message> getMessages() { return messages; }
    public void addMessages(Message message) { this.messages.add(message); }

    @Override
    public void show(Stage primaryStage, People people, Flights flights, Airplanes airplanes) {
        if(isSuperAdmin){
            SuperAdminPages superAdminPages = new SuperAdminPages(primaryStage,this, people, flights, airplanes);
            try {
                superAdminPages.dashboardPage();
            } catch (FileNotFoundException e) {
                System.err.println("Image Files not fount, Check for ~\\Images\\superAdmin.png");
            }
        }else {
            ManagerPages managerPages = new ManagerPages(primaryStage,this, people, flights, airplanes);
            try {
                managerPages.dashboardPage();
            } catch (FileNotFoundException e) {
                System.err.println("Image Files not fount, Check for ~\\Images\\manager.png");
            }
        }
    }

    @Override
    public String toString() {
        return getName() + " " + getFamily();
    }
}

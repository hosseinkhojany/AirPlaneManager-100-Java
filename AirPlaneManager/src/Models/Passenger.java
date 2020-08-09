package Models;

import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Views.PassengerPages;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Passenger extends Person {
    private double balance;
    private ArrayList<Flight> flights = new ArrayList<>();

    public Passenger(String name, String family, String phoneNumber, String email,
                    String username, String password,
                    double balance) {
        super(name, family, phoneNumber, email, "3", username, password);
        this.balance = balance;
        id += (People.passengersIdMaker++);
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public ArrayList<Flight> getFlights() { return flights; }
    public void addFlight(Flight flight) { flights.add(flight); }


    @Override
    public void show(Stage primaryStage, People people, Flights flights, Airplanes airplanes) {
        PassengerPages passengerPages = new PassengerPages(primaryStage, this, people, flights, airplanes);
        try {
            passengerPages.dashboardPage();
        } catch (FileNotFoundException e) {
            System.err.println("Image Files not fount, Check for ~\\Images\\passenger.png");
        }
    }
}

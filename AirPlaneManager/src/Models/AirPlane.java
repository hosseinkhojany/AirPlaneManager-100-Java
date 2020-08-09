package Models;

import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;

import java.io.Serializable;
import java.util.ArrayList;

public class AirPlane implements Serializable {
    private String id;
    private int seats;
    ArrayList<Flight> flights = new ArrayList<>();

    public AirPlane(int seats) {
        this.seats = seats;
        id = "5" + (Airplanes.ariPlaneIdMaker++);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public ArrayList<Flight> getFlights() { return flights; }
    public void addFlight(Flight flight) { flights.add(flight); }

    @Override
    public String toString() {
        return "#"+id+" seats:["+seats+"]";
    }
}

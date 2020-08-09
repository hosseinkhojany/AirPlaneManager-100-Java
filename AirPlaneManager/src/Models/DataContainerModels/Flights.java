package Models.DataContainerModels;

import Models.Flight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Flights implements Serializable {
    public static int ticketIdMaker = 1;
    public static int flightIdMaker = 1;

    public void init() {
        flightIdMaker = flights.size() + 1;
    }

    ArrayList<Flight> flights = new ArrayList<>();

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void delete(Flight flight) {
        flights.remove(flight);
    }
}

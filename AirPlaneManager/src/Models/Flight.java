package Models;

import Models.DataContainerModels.Flights;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;



public class Flight  implements Serializable {
    private String id;
    private AirPlane airPlane;
    private Ticket ticket;
    private String destination;
    private String origin;
    private String start;
    private String duration;
    FlightStatus status;
    ArrayList<Passenger> passengers = new ArrayList<>();

    public Flight(AirPlane airPlane, Ticket ticket, String destination, String origin, String start, String duration) {
        this.id = "6" + (Flights.flightIdMaker++);
        this.airPlane = airPlane;
        this.ticket = ticket;
        this.destination = destination;
        this.origin = origin;
        this.start = start;
        this.duration = duration;
    }

    public String getId() { return id; }
    public AirPlane getAirPlane() { return airPlane; }
    public Ticket getTicket() { return ticket; }
    public String getDestination() { return destination; }
    public String getOrigin() { return origin; }
    public String getStart() { return start; }
    public String getEnd() { return duration; }
    public String getDuration() { return duration; }

    public ArrayList<Passenger> getPassengers() { return passengers; }
    public void addPassengers(Passenger p) { passengers.add(p); }

    public FlightStatus getStatus(){
        LocalDateTime t1 = LocalDateTime.parse(start,  DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss"));
        LocalDateTime t2 = t1.plusHours(Integer.parseInt(duration.substring(0,duration.indexOf(':'))));
        t2 = t2.plusMinutes(Integer.parseInt(duration.substring(duration.indexOf(':')+1)));
        LocalDateTime now = LocalDateTime.now();
        if(t1.isAfter(now)){
            status = FlightStatus.undone;
        }else if(t2.isBefore(now)){
            status = FlightStatus.done;
        }else if(t1.isBefore(now) && t2.isAfter(now)){
            status = FlightStatus.flying;
        }
        return status;
    }

    public void setAirPlane(AirPlane airPlane) {
        this.airPlane = airPlane;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

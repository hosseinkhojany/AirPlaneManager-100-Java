package Models;

import Models.DataContainerModels.Flights;

import java.io.Serializable;

public class Ticket implements Serializable {
    private String id;
    private double cost;
    private double penaltyPercentage;

    public Ticket(double cost, double penaltyPercentage) {
        this.cost = cost;
        this.penaltyPercentage = penaltyPercentage;
        id = "4" + (Flights.ticketIdMaker++);
    }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public double getPenaltyPercentage() { return penaltyPercentage; }
    public void setPenaltyPercentage(double penaltyPercentage) { this.penaltyPercentage = penaltyPercentage; }

    @Override
    public String toString() {
        return "#"+id+" ["+cost+" $]";
    }
}

package Models.DataContainerModels;

import Models.AirPlane;

import java.io.Serializable;
import java.util.ArrayList;

public class Airplanes implements Serializable {
    public static int ariPlaneIdMaker = 1;
    public void init(){
        ariPlaneIdMaker = airplanes.size()+1;
    }
    ArrayList<AirPlane> airplanes = new ArrayList<>();

    public ArrayList<AirPlane> getAirplanes() { return airplanes; }
    public void addAirPlane(AirPlane airPlane){ airplanes.add(airPlane);}
}

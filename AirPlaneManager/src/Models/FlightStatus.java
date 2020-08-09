package Models;

import java.io.Serializable;

public enum FlightStatus implements Serializable {
    undone, done, flying;

    @Override
    public String toString() {
        if(this == undone)
            return "undone";
        else if(this == flying)
            return  "flying";
        else if(this == done)
            return "done";
        else
            return "unknown";
    }
}

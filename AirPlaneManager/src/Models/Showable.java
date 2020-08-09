package Models;

import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import javafx.stage.Stage;

public interface Showable {
    void show(Stage primaryStage, People people, Flights flights, Airplanes airplanes);
}


import Controls.DefaultPageButtons;
import Models.*;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Views.DefaultPages;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    People people;
    Flights flights;
    Airplanes airplanes;

    @Override
    public void init() throws Exception {
        super.init();
        new File("DataBase").mkdir();

        File file = new File("DataBase\\people.db");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            people = (People) ois.readObject();
            ois.close();

        } else
            people = new People();
        if(!people.contains("admin"))
            people.add(new Manager("-","-" , "09387836256", "admin@gmail.com","admin","pass","-",0 ,true));
        people.init();

        file = new File("DataBase\\flights.db");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            flights = (Flights) ois.readObject();
            ois.close();
        } else
            flights = new Flights();
        flights.init();

        file = new File("DataBase\\airplanes.db");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            airplanes = (Airplanes) ois.readObject();
            ois.close();
        } else
            airplanes = new Airplanes();
        airplanes.init();


        //dummy data:
        AirPlane airPlane = new AirPlane(15);
        AirPlane airPlane2 = new AirPlane(30);
        airplanes.addAirPlane(airPlane);
        airplanes.addAirPlane(airPlane2);

        people.add(new Passenger("name pass", "fam pass" , "09387258415" , "khv@khv.kh", "pas1", "123", 0));
        people.add(new Passenger("name pass", "fam pass" , "09387836645" , "khv@khv.kh", "pas2", "123", 0));
        people.add(new Passenger("name pass", "fam pass" , "09123456255" , "khv@khv.kh", "pas3", "123", 0));

        people.add(new Employee("name pass", "fam pass" , "09387836255" , "khv@khv.kh", "emp1", "123","ad1", 0));
        people.add(new Employee("name pass", "fam pass" , "09387836255" , "khv@khv.kh", "emp2", "123","ad2", 0));
        people.add(new Employee("name pass", "fam pass" , "09387836255" , "khv@khv.kh", "emp3", "123", "ad3" , 0));

        people.add(new Manager("man name", "man fam" , "09387836255" , "man@khv.kh", "man", "123","rhtjykul - dtgndnf" ,0, false));
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Airport Management System");
        DefaultPages.init(primaryStage, people, flights, airplanes);
        DefaultPageButtons.init(people);
        DefaultPages.loginPage();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        new File("DataBase").mkdir();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DataBase\\people.db"));
        oos.writeObject(people);

        oos = new ObjectOutputStream(new FileOutputStream("DataBase\\flights.db"));
        oos.writeObject(flights);

        oos = new ObjectOutputStream(new FileOutputStream("DataBase\\airplanes.db"));
        oos.writeObject(airplanes);

    }

    public static void main(String[] args) { launch(args); }
}

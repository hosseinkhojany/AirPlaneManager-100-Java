package Models;

import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import javafx.stage.Stage;

import java.io.Serializable;

abstract public class Person implements Showable, Serializable {
    protected String Name, Family, phoneNumber, email;
    protected String id;
    protected String username;
    protected String password;

    public Person(String name, String family, String phoneNumber, String email, String id, String username, String password) {
        this.Name = name;
        this.Family = family;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    abstract public void show(Stage primaryStage, People people, Flights flights, Airplanes airplanes);

    public final String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    public final String getFamily() { return Family; }
    public void setFamily(String family) { this.Family = family; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


}

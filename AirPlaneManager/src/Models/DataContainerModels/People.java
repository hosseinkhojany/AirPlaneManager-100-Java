package Models.DataContainerModels;

import Models.Employee;
import Models.Manager;
import Models.Passenger;
import Models.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;


public class People implements Serializable {
    public static int passengersIdMaker = 1;
    public static int managersIdMaker = 1;
    public static int employeesIdMaker = 1;

    private ArrayList<Person> people = new ArrayList<>();

    public void init() {
        passengersIdMaker = getPassengers().size() + 1;
        managersIdMaker = getManagers().size() + 1;
        employeesIdMaker = getEmployees().size() + 1;
    }

    public Person getUser(String username, String pass) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getUsername().equals(username))
                if (people.get(i).getPassword().equals(pass))
                    return people.get(i);
                else
                    return null;
        }
        return null;
    }

    public boolean contains(String username) {
        for (int i = 0; i < people.size(); i++)
            if (people.get(i).getUsername().equals(username))
                return true;
        return false;
    }

    public Person add(Person person) {
        people.add(person);
        return person;
    }

    public ArrayList<Manager> getManagers() {
        ArrayList<Manager> managers = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().startsWith("1")) {
                Manager manager = (Manager) people.get(i);
                if (!manager.isSuperAdmin())
                    managers.add((Manager) people.get(i));
            }
        }
        return managers;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().startsWith("2"))
                employees.add((Employee) people.get(i));
        }
        return employees;
    }

    public ArrayList<Passenger> getPassengers() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().startsWith("3"))
                passengers.add((Passenger) people.get(i));
        }
        return passengers;
    }

    public void delete(Person p) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getUsername().equals(p.getUsername()) && people.get(i).getId().equals(p.getId())) {
                people.remove(i);
                break;
            }
        }
    }
}
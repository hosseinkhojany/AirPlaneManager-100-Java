package Controls;

import Models.Employee;
import Models.Manager;
import Models.Passenger;
import Views.DefaultDialog;
import Views.DefaultPages;

import Views.ManagerPages;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import static Views.DefaultPages.createSignUpPage;

public class ManagerPageButtons {
    private static ManagerPages managerPages;
    static final InnerShadow innerShadow = new InnerShadow();
    static final DropShadow dropShadow = new DropShadow();
    public static void init(ManagerPages managerPages){
        ManagerPageButtons.managerPages = managerPages;
    }

    public static Button editInfoButton(){
        Button button = new Button("Edit Info.");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> managerPages.editInfoPage());
        return button;
    }

    public static Button logoutButton(){
        Button button = new Button("Logout");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#f44336"), null, null)));
        button.setOnMouseClicked(e -> DefaultPages.loginPage());
        return button;
    }

    public static Button messagesButton(){
        Button button = new Button("Messages");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(18));
        button.setMinSize(100,100);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> managerPages.messagesPage());
        return button;
    }

    public static Button flightsButton(){
        Button button = new Button("Flights");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(18));
        button.setMinSize(100,100);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> managerPages.flightsPage());
        return button;
    }
    public static Button airplanesButton(){
        Button button = new Button("Airplanes");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(18));
        button.setMinSize(100,100);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> managerPages.airplanesPage());
        return button;
    }
    public static Button passengersButton(){
        Button button = new Button("Passengers");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(18));
        button.setMinSize(100,100);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> managerPages.passengersPage());
        return button;
    }
    public static Button employeesButton(){
        Button button = new Button("Employees");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(18));
        button.setMinSize(100,100);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> managerPages.employeesPage());
        return button;
    }

    public static void submitEditsButtonMaker(Button button, Dialog dialog, Label errorLabel, TextField[]textFields){
        button.setText("Submit Edits");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setOnMouseClicked(e -> {
            int len = textFields.length;
            for (int i = 0; i < len; i++) {
                if (textFields[i].getText().equals("") || textFields[i].getText().equals(" ")) {
                    errorLabel.setText("Fill all blanks please");
                    return;
                }
            }
            if (textFields[7].getText().equals(textFields[6].getText())) {
                if (!textFields[4].getText().equals(managerPages.getManager().getUsername())  &&  managerPages.getPeople().contains(textFields[4].getText()))
                    errorLabel.setText("this username is in use of other user");
                else if (!Pattern.matches("09\\d{9}", textFields[2].getText()))
                    errorLabel.setText("phone number is not correct! (reg: 09XXXXXXXXX)");
                else if (!Pattern.matches("[a-z|A-Z|0-9|\\.|_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}", textFields[3].getText()))
                    errorLabel.setText("email address is not correct!");
                else {
                    managerPages.getManager().setName(textFields[0].getText());
                    managerPages.getManager().setFamily(textFields[1].getText());
                    managerPages.getManager().setPhoneNumber(textFields[2].getText());
                    managerPages.getManager().setEmail(textFields[3].getText());
                    managerPages.getManager().setUsername(textFields[4].getText());
                    managerPages.getManager().setAddress(textFields[5].getText());
                    managerPages.getManager().setPassword(textFields[6].getText());
                    dialog.close();
                    try {
                        managerPages.dashboardPage();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                errorLabel.setText("password and confirmation is not equal");
            }
        });
    }

    public static Button newPassengerButton(){
        Button button = new Button("New");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setOnMouseClicked(e ->{
            DialogPane dialogPane = new DialogPane();
            Dialog dialog = DefaultDialog.get("Add new Passenger", dialogPane);
            VBox vBox = createSignUpPage("New Passenger", false,"First Name", "Last Name", "Phone number", "Email", "Username" , "Password", "re-enter Pass");
            GridPane gridPane = (GridPane)vBox.getChildren().get(2);
            ObservableList<Node> nodes = gridPane.getChildren();
            TextField textFields[] = new TextField[7];
            for (int i = 0,j=1; i < 7; i++,j+=2)
                textFields[i] = ((TextField)nodes.get(j));

            addNewButtonMaker("Passenger",dialog, ((Button)nodes.get(14)), textFields, (Label) vBox.getChildren().get(4));

            dialogPane.setContent(vBox);
            dialog.showAndWait();
        });
        return button;
    }

    public static void addNewButtonMaker(String type, Dialog dialog, Button button, TextField [] textFields, Label errorLabel){
        button.setText("Add");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(event -> {
            int len = textFields.length;
            for (int i = 0; i < len; i++) {
                if (textFields[i].getText().equals("") || textFields[i].getText().equals(" ")) {
                    errorLabel.setText("Fill all blanks please");
                    return;
                }
            }
            if (textFields[len - 1].getText().equals(textFields[len - 2].getText())) {
                if (managerPages.getPeople().contains(textFields[4].getText()))
                    errorLabel.setText("this username is in use of other user");
                else if (!Pattern.matches("09\\d{9}", textFields[2].getText()))
                    errorLabel.setText("phone number is not correct! (reg: 09XXXXXXXXX)");
                else if (!Pattern.matches("[a-z|A-Z|0-9|\\.|_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}", textFields[3].getText()))
                    errorLabel.setText("email address is not correct!");
                else if(type.equals("Passenger")){
                    managerPages.getPeople().add(new Passenger(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText(), textFields[5].getText(), 0));
                    dialog.close();
                    managerPages.passengersPage();
                }
                else if(type.equals("Employee")){
                    managerPages.getPeople().add(new Employee(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText(), textFields[6].getText(), textFields[5].getText(), 0));
                    dialog.close();
                    managerPages.passengersPage();
                }
            } else {
                errorLabel.setText("password and confirmation are not equal");
            }
        });
    }

    public static Button newEmployeeButton() {
        Button button = new Button("New");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setOnMouseClicked(e ->{
            DialogPane dialogPane = new DialogPane();
            Dialog dialog = DefaultDialog.get("Add new Employee", dialogPane);
            VBox vBox = createSignUpPage("New Employee", false,"First Name", "Last Name", "Phone number", "Email", "Username" ,"Address", "Password", "re-enter Pass");
            GridPane gridPane = (GridPane)vBox.getChildren().get(2);
            ObservableList<Node> nodes = gridPane.getChildren();
            TextField textFields[] = new TextField[8];
            for (int i = 0,j=1; i < 8; i++,j+=2)
                textFields[i] = ((TextField)nodes.get(j));

            addNewButtonMaker("Employee",dialog, ((Button)nodes.get(16)), textFields, (Label) vBox.getChildren().get(4));

            dialogPane.setContent(vBox);
            dialog.showAndWait();
        });
        return button;
    }

    public static Button newFlightButton() {
        Button button = new Button("New");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setOnMouseClicked(e ->{
            DefaultDialog.create_edit_FlightDialog(null,managerPages.getFlights(), managerPages.getAirplanes(), true, managerPages, null).showAndWait();
        });
        return button;
    }

    public static Button newAirPlaneButton() {
        Button button = new Button("New");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setOnMouseClicked(e ->{
            DefaultDialog.create_edit_AirplaneDialog(null , managerPages.getAirplanes(), managerPages).showAndWait();
        });
        return button;
    }
}

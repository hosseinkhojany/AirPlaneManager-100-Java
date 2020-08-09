package Controls;

import Models.Manager;
import Models.Message;
import Models.Passenger;
import Views.DefaultDialog;
import Views.DefaultPages;
import Views.EmployeePages;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class EmployeePageButtons {
    private static EmployeePages employeePages;
    static final InnerShadow innerShadow = new InnerShadow();
    static final DropShadow dropShadow = new DropShadow();
    public static void init(EmployeePages employeePages){
        EmployeePageButtons.employeePages = employeePages;
    }

    public static Button editInfoButton(){
        Button button = new Button("Edit Info.");
        button.setEffect(dropShadow);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> employeePages.editInfoPage());
        return button;
    }

    public static Button logoutButton(){
        Button button = new Button("Logout");
        button.setEffect(dropShadow);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#f44336"), null, null)));
        button.setOnMouseClicked(e -> DefaultPages.loginPage());
        return button;
    }

    public static Button messagesButton(){
        Button button = new Button("Messages");
        button.setEffect(dropShadow);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(30));
        button.setMinSize(150,150);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> employeePages.messagesPage());
        return button;
    }

    public static Button flightsButton(){
        Button button = new Button("Flights");
        button.setEffect(dropShadow);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(30));
        button.setMinSize(150,150);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> employeePages.flightsPage());
        return button;
    }

    public static void submitEditsButtonMaker(Button button, Dialog dialog, Label errorLabel, TextField[] textFields) {
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
                if (!textFields[4].getText().equals(employeePages.getEmployee().getUsername())  &&  employeePages.getPeople().contains(textFields[4].getText()))
                    errorLabel.setText("this username is in use of other user");
                else if (!Pattern.matches("09\\d{9}", textFields[2].getText()))
                    errorLabel.setText("phone number is not correct! (reg: 09XXXXXXXXX)");
                else if (!Pattern.matches("[a-z|A-Z|0-9|\\.|_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}", textFields[3].getText()))
                    errorLabel.setText("email address is not correct!");
                else {
                    employeePages.getEmployee().setName(textFields[0].getText());
                    employeePages.getEmployee().setFamily(textFields[1].getText());
                    employeePages.getEmployee().setPhoneNumber(textFields[2].getText());
                    employeePages.getEmployee().setEmail(textFields[3].getText());
                    employeePages.getEmployee().setUsername(textFields[4].getText());
                    employeePages.getEmployee().setAddress(textFields[5].getText());
                    employeePages.getEmployee().setPassword(textFields[6].getText());
                    dialog.close();
                    try {
                        employeePages.dashboardPage();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                errorLabel.setText("password and confirmation is not equal");
            }
        });
    }

    public static Button sendMessageButton(Dialog dialog, Label errorLabel, TextArea messageBody, TextField titleTextField, ComboBox managersComboBox) {
        Button button = new Button("Send >");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.setOnMouseClicked(e -> {
            if(messageBody.getText().equals("") || titleTextField.getText().equals("")){
                errorLabel.setText("fill all blanks please.");
            }else {
                Manager manager = (Manager)managersComboBox.getValue();
                if(manager == null){
                    errorLabel.setText("select a manager to send to");
                }else{
                    manager.addMessages(new Message(employeePages.getEmployee(), manager, titleTextField.getText(), messageBody.getText()));
                    dialog.close();
                }
            }
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
            DefaultDialog.create_edit_FlightDialog(null,employeePages.getFlights(), employeePages.getAirplanes(), false, null, employeePages).showAndWait();
        });
        return button;
    }
}

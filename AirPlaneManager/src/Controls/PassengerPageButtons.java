package Controls;

import Models.Employee;
import Models.Manager;
import Models.Message;
import Models.Passenger;
import Views.DefaultPages;
import Views.PassengerPages;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;

public class PassengerPageButtons {
    private static PassengerPages passengerPages;
    static final InnerShadow innerShadow = new InnerShadow();
    static final DropShadow dropShadow = new DropShadow();
    public static void init(PassengerPages passengerPages){
        PassengerPageButtons.passengerPages = passengerPages;
    }

    public static Button increaseBalanceButton(){
        Button button = new Button("+");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.setOnMouseClicked(e -> passengerPages.increaseBalancePage());
        return button;
    }

    public static Button editInfoButton(){
        Button button = new Button("Edit Info.");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> passengerPages.editInfoPage());
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
        button.setFont(new Font(30));
        button.setMinSize(150,150);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> passengerPages.messagesPage());
        return button;
    }

    public static Button buyTicketButton(){
        Button button = new Button("Buy Ticket");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(30));
        button.setMinSize(150,150);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> passengerPages.buyTicketPage());
        return button;
    }

    public static Button myFlightsButton(){
        Button button = new Button("My Flights");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setFont(new Font(30));
        button.setMinSize(150,150);
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> passengerPages.myFlightsPage());
        return button;
    }

    public static Button payOnlineButton(TextField amount, Label errorLabel, Dialog dialog){
        Button button = new Button("Pay online");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setOnMouseClicked(e -> {
            if(!Pattern.matches("([0-9]+)|([0-9]+\\.[0-9]+)",amount.getText())){
                errorLabel.setText("incorrect input! please input valid amount of transaction.");
            }else if(Double.parseDouble(amount.getText())< 5000){
                errorLabel.setText("minimum value is 5000 Tomans.");
            }else{
                try {
                    Desktop.getDesktop().browse(new URL("https://shaparak.ir/").toURI());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e2) {
                    e2.printStackTrace();
                }
                if(true) {//check whether transaction was successful or not
                    passengerPages.getPassenger().setBalance(passengerPages.getPassenger().getBalance() + Double.parseDouble(amount.getText()));
                    dialog.close();
                    try {
                        passengerPages.dashboardPage();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    errorLabel.setText("transaction interrupted!");
                }
            }
        });
        return button;
    }

    public static void submitEditsButtonMaker(Button button,Dialog dialog, Label errorLabel, TextField []textFields){
        button.setText("Submit Edits");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.setOnMouseClicked(e -> {
            int len = textFields.length;
            for (int i = 0; i < len; i++) {
                if (textFields[i].getText().equals("") || textFields[i].getText().equals(" ")) {
                    errorLabel.setText("Fill all blanks please");
                    return;
                }
            }
            if (textFields[5].getText().equals(textFields[6].getText())) {
                if (!textFields[4].getText().equals(passengerPages.getPassenger().getUsername())  &&  passengerPages.getPeople().contains(textFields[4].getText()))
                    errorLabel.setText("this username is in use of other user");
                else if (!Pattern.matches("09\\d{9}", textFields[2].getText()))
                    errorLabel.setText("phone number is not correct! (reg: 09XXXXXXXXX)");
                else if (!Pattern.matches("[a-z|A-Z|0-9|\\.|_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}", textFields[3].getText()))
                    errorLabel.setText("email address is not correct!");
                else {
                    passengerPages.getPassenger().setName(textFields[0].getText());
                    passengerPages.getPassenger().setFamily(textFields[1].getText());
                    passengerPages.getPassenger().setPhoneNumber(textFields[2].getText());
                    passengerPages.getPassenger().setEmail(textFields[3].getText());
                    passengerPages.getPassenger().setUsername(textFields[4].getText());
                    passengerPages.getPassenger().setPassword(textFields[5].getText());
                    dialog.close();
                    try {
                        passengerPages.dashboardPage();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                errorLabel.setText("password and confirmation is not equal");
            }
        });
    }

    public static Button sendMessageButton (Dialog dialog,Label errorLabel, TextArea messageBody, TextField title, ComboBox comboBox){
        Button button = new Button("Send >");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.setOnMouseClicked(e -> {
            if(messageBody.getText().equals("") || title.getText().equals("")){
                errorLabel.setText("fill all blanks please.");
            }else {
                Manager manager = (Manager)comboBox.getValue();
                if(manager == null){
                    errorLabel.setText("select a manager to send to");
                }else{
                    manager.addMessages(new Message(passengerPages.getPassenger(), manager, title.getText(), messageBody.getText()));
                    dialog.close();
                }
            }
        });
        return button;
    }
}

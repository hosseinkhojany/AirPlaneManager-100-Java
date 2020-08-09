package Controls;

import Models.DataContainerModels.People;
import Models.Employee;
import Models.Manager;
import Models.Passenger;
import Views.DefaultPages;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.util.regex.Pattern;


public class DefaultPageButtons {
    static People people;

    static final InnerShadow shadow = new InnerShadow();
    static final DropShadow dshadow = new DropShadow();

    public static void init(People people) {
        DefaultPageButtons.people = people;
    }

    public static Button loginButton(TextField usernameTextField, TextField passwordTextField, Label errorLabel) {
        Button button = new Button("Login");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(shadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dshadow));
        button.setEffect(dshadow);

        button.setOnMouseClicked(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            errorLabel.setVisible(true);

            if (username.equals("") || password.equals("")) {
                errorLabel.setText("Fill all blanks please");
            } else {
                errorLabel.setText("");
                DefaultPages.currentUser = people.getUser(username, password);
                if (DefaultPages.currentUser == null) {
                    errorLabel.setText("Wrong Username or Password");
                } else {
                    DefaultPages.dashboardPage();
                }
            }
        });
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        return button;
    }

    public static Button toSignUpPageButton() {
        Button button = new Button("Sign Up");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(shadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dshadow));
        button.setEffect(dshadow);
        button.setOnMouseClicked(event -> DefaultPages.singUpPage());
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        return button;
    }

    public static Button toLoginPageButton() {
        Button button = new Button("back to Login Page");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(shadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dshadow));
        button.setEffect(dshadow);
        button.setOnMouseClicked(event -> DefaultPages.loginPage());
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        return button;
    }

    public static Button signUpButton(String type, TextField textFields[], Label errorLabel, boolean redirect) {
        Button signUpButton = new Button("SignUp");
        signUpButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> signUpButton.setEffect(shadow));
        signUpButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> signUpButton.setEffect(dshadow));
        signUpButton.setEffect(dshadow);
        signUpButton.setTextFill(Color.web("#212121"));
        signUpButton.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        signUpButton.setOnMouseClicked(event -> {
            int len = textFields.length;
            for (int i = 0; i < len; i++) {
                if (textFields[i].getText().equals("") || textFields[i].getText().equals(" ")) {
                    errorLabel.setText("Fill all blanks please");
                    return;
                }
            }
            if (textFields[len - 1].getText().equals(textFields[len - 2].getText())) {
                if (people.contains(textFields[4].getText()))
                    errorLabel.setText("this username is in use of other user");
                else if (!Pattern.matches("09\\d{9}", textFields[2].getText()))
                    errorLabel.setText("phone number is not correct! (reg: 09XXXXXXXXX)");
                else if (!Pattern.matches("[a-z|A-Z|0-9|\\.|_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}", textFields[3].getText()))
                    errorLabel.setText("email address is not correct!");
                else if (type.equals("SignUp Passenger")) {
                    DefaultPages.currentUser = people.add(new Passenger(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText(), textFields[5].getText(), 0));
                    if (redirect)
                        DefaultPages.dashboardPage();
                } else if (type.equals("SignUp Manager")) {
                    DefaultPages.currentUser = people.add(new Manager(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText(), textFields[6].getText(), textFields[5].getText(), 0, false));
                    if (redirect)
                        DefaultPages.dashboardPage();
                } else if (type.equals("SignUp Employee")) {
                    DefaultPages.currentUser = people.add(new Employee(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText(), textFields[6].getText(), textFields[5].getText(), 0));
                    if (redirect)
                        DefaultPages.dashboardPage();
                }

            } else {
                errorLabel.setText("password and confirmation are not equal");
            }
        });
        return signUpButton;
    }
}

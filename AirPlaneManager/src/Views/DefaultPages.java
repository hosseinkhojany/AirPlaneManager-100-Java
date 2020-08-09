package Views;

import Controls.DefaultPageButtons;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Models.Person;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DefaultPages {
    static Stage primaryStage; //singleton
    public static Person currentUser; //singleton
    static People people;
    static Flights flights;
    static Airplanes airplanes;

    public static void init(Stage primaryStage, People people, Flights flights, Airplanes airplanes) {
        DefaultPages.primaryStage = primaryStage;
        DefaultPages.people = people;
        DefaultPages.flights = flights;
        DefaultPages.airplanes = airplanes;
    }

    public static void loginPage() {
        GridPane gridPane = new GridPane();

        Label loginLabel = new Label("Login");
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        Label errorLabel = new Label("");

        usernameLabel.setTextFill(Color.web("#EEEEEE"));
        loginLabel.setTextFill(Color.web("#EEEEEE"));
        passwordLabel.setTextFill(Color.web("#EEEEEE"));
        loginLabel.setFont(new Font(45));
        errorLabel.setTextFill(Color.web("#ef5350"));

        TextField usernameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();

        Button loginButton = DefaultPageButtons.loginButton(usernameTextField, passwordTextField, errorLabel);
        Button signUpButton = DefaultPageButtons.toSignUpPageButton();
        loginButton.setMinWidth(100);
        signUpButton.setMinWidth(100);

        gridPane.add(usernameLabel, 0, 0, 1, 1);
        gridPane.add(usernameTextField, 1, 0, 2, 1);
        gridPane.add(passwordLabel, 0, 1, 1, 1);
        gridPane.add(passwordTextField, 1, 1, 2, 1);
        gridPane.add(signUpButton, 0, 2);
        gridPane.add(loginButton, 2, 2);

        gridPane.setHalignment(loginButton, HPos.RIGHT);
        gridPane.setVgap(15);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(loginLabel, new Separator(), gridPane, new Separator(), errorLabel);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));

        Scene scene = new Scene(vBox, 700, 700);
        primaryStage.setScene(scene);

        usernameTextField.setText("emp1");
        passwordTextField.setText("123");
    }

    public static void singUpPage() {
        Tab passengerSignUpTab = new Tab("Passengers", createSignUpPage("SignUp Passenger",
                true,"First Name", "Last Name", "Phone number", "Email", "Username" , "Password", "re-enter Pass"));
        Tab employeeSignUpTab = new Tab("Employees", createSignUpPage("SignUp Employee",
                true,"First Name", "Last Name", "Phone number", "Email",  "Username" , "address", "Password", "re-enter Pass"));
        Tab managerSignUpTab = new Tab("Managers", createSignUpPage("SignUp Manager",
                true,"First Name", "Last Name", "Phone number", "Email",  "Username" , "address", "Password", "re-enter Pass"));
        passengerSignUpTab.setStyle("-fx-background-color:#263238 ; -fx-text-base-color:#EEEEEE ;");
        employeeSignUpTab.setStyle("-fx-background-color:#263238 ; -fx-text-base-color:#EEEEEE ;");
        managerSignUpTab.setStyle("-fx-background-color:#263238 ; -fx-text-base-color:#EEEEEE ;");

        passengerSignUpTab.setClosable(false);
        employeeSignUpTab.setClosable(false);
        managerSignUpTab.setClosable(false);
        TabPane tabPane = new TabPane(passengerSignUpTab, employeeSignUpTab, managerSignUpTab);

        Button backButton = DefaultPageButtons.toLoginPageButton();
        backButton.setMinWidth(100);
        StackPane stackPane = new StackPane(backButton);
        stackPane.setAlignment(backButton, Pos.TOP_LEFT);
        stackPane.setMargin(backButton, new Insets(0,10,0,10));

        VBox vBox = new VBox(tabPane, stackPane);
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 700, 700));
    }

    public static VBox createSignUpPage(String header,boolean redirectAfterSignUp, String... fields) {
        GridPane gridPane = new GridPane();
        Label labels[] = new Label[fields.length];
        TextField textFields[] = new TextField[fields.length];
        int i = 0;
        for (; i < fields.length; i++) {
            labels[i] = new Label(fields[i] + ":");
            labels[i].setTextFill(Color.web("#EEEEEE"));
            if (i + 2 < fields.length)
                textFields[i] = new TextField();
            else
                textFields[i] = new PasswordField();

            gridPane.add(labels[i], 0, i, 1, 1);
            gridPane.add(textFields[i], 1, i, 2, 1);
        }
        gridPane.setVgap(15);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label signUpLabel = new Label(header);
        Label errorLabel = new Label("");
        signUpLabel.setTextFill(Color.web("#EEEEEE"));
        signUpLabel.setFont(new Font(45));
        errorLabel.setTextFill(Color.web("#ef5350"));

        Button signUpButton = DefaultPageButtons.signUpButton(header, textFields, errorLabel, redirectAfterSignUp);
        signUpButton.setMinWidth(100);

        gridPane.add(signUpButton, 2, i, 1, 1);
        gridPane.setHalignment(signUpButton, HPos.RIGHT);

        VBox vBox = new VBox(signUpLabel, new Separator(), gridPane, new Separator(), errorLabel);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));

        return vBox;
    }

    public static void dashboardPage() {
        currentUser.show(primaryStage, people, flights, airplanes);
    }

}

package Views;

import Controls.ManagerPageButtons;
import Controls.PassengerPageButtons;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Models.Flight;
import Models.FlightStatus;
import Models.Manager;
import Models.Passenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class PassengerPages {
    Stage primaryStage;
    People people;
    Flights flights;
    Airplanes airplanes;
    Passenger passenger;

    public PassengerPages(Stage primaryStage, Passenger passenger, People people, Flights flights, Airplanes airplanes) {
        this.primaryStage = primaryStage;
        this.people = people;
        this.flights = flights;
        this.airplanes = airplanes;
        this.passenger = passenger;
        PassengerPageButtons.init(this);
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public People getPeople() {
        return people;
    }

    public Flights getFlights() {
        return flights;
    }

    public Airplanes getAirplanes() {
        return airplanes;
    }

    public void dashboardPage() throws FileNotFoundException {
//        ImageView passengerImage = new ImageView(new Image(new FileInputStream("Images\\passenger.png"), 300, 300, false, false));
        Label userFullNameLabel = new Label(passenger.getName() + " " + passenger.getFamily());
        Label usernameLabel = new Label(passenger.getUsername());
        Label balanceLabel = new Label("balance");
        Label balanceValueLabel = new Label(passenger.getBalance() + "");
        Button increaseBalanceButton = PassengerPageButtons.increaseBalanceButton();
        Button editInfoButton = PassengerPageButtons.editInfoButton();
        Button logoutButton = PassengerPageButtons.logoutButton();
        Button messagesButton = PassengerPageButtons.messagesButton();
        Button buyTicketButton = PassengerPageButtons.buyTicketButton();
        Button myFlightsButton = PassengerPageButtons.myFlightsButton();

        userFullNameLabel.setTextFill(Color.web("#EEEEEE"));
        userFullNameLabel.setFont(new Font(40));
        usernameLabel.setTextFill(Color.web("#9E9E9E"));
        usernameLabel.setFont(new Font(30));
        balanceLabel.setTextFill(Color.web("#ECEFF1"));
        balanceLabel.setFont(new Font(25));
        balanceValueLabel.setTextFill(Color.web("#9E9E9E"));
        balanceValueLabel.setFont(new Font(25));

        VBox vBox0 = new VBox(new ImageView(new Image("Images//passenger.jpg" , 300, 300, false, false )), userFullNameLabel, usernameLabel);
        vBox0.setAlignment(Pos.CENTER);
        HBox hBox0 = new HBox(balanceValueLabel, increaseBalanceButton);
        hBox0.setAlignment(Pos.TOP_RIGHT);
        hBox0.setSpacing(4);
        Separator separator0 = new Separator();
        VBox vBox1 = new VBox(balanceLabel, separator0, hBox0);
        vBox1.setAlignment(Pos.TOP_RIGHT);
        vBox1.setSpacing(5);
        separator0.setMaxWidth(balanceValueLabel.getMaxWidth() + 90);
        StackPane stackPane0 = new StackPane(vBox0, vBox1, editInfoButton, logoutButton);
        stackPane0.setAlignment(Pos.TOP_CENTER);
        stackPane0.setAlignment(editInfoButton, Pos.BOTTOM_LEFT);
        stackPane0.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        stackPane0.setMargin(editInfoButton, new Insets(0, 0, 0, 15));
        stackPane0.setMargin(logoutButton, new Insets(0, 15, 0, 0));
        stackPane0.setMargin(vBox0, new Insets(15, 0, 0, 0));
        stackPane0.setMargin(vBox1, new Insets(15, 15, 0, 0));

        HBox hBox1 = new HBox(messagesButton, buyTicketButton, myFlightsButton);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(25);
        VBox vBox2 = new VBox(stackPane0, new Separator(), hBox1, new Separator());
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setSpacing(20);
        vBox2.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox2, 700, 700));
    }

    public void increaseBalancePage() {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("increase your balance online", dialogPane);

        Label infoLabel = new Label("Input amount, how much do you want to increase your balance?");
        Label errorLabel = new Label("");
        infoLabel.setTextFill(Color.web("#EEEEEE"));
        errorLabel.setTextFill(Color.web("#ef5350"));
        TextField amount = new TextField();
        amount.setPromptText("Tomans$");
        Button payOnline = PassengerPageButtons.payOnlineButton(amount, errorLabel, dialog);
        HBox hBox = new HBox(amount, payOnline);
        VBox vBox = new VBox(infoLabel, hBox, errorLabel);
        hBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(15);
        vBox.setSpacing(30);

        dialogPane.setContent(vBox);
        dialog.showAndWait();
    }

    public void editInfoPage() {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("Edit information", dialogPane);
        VBox vBox = DefaultPages.createSignUpPage("Edit your information",
                false, "First Name", "Last Name", "Phone number", "Email", "Username", "Password", "re-enter Pass");
        GridPane gridPane = (GridPane) vBox.getChildren().get(2);
        ObservableList<Node> nodes = gridPane.getChildren();
        TextField textFields[] = new TextField[7];
        for (int i = 0, j = 1; i < 7; i++, j += 2)
            textFields[i] = ((TextField) nodes.get(j));
        textFields[0].setText(passenger.getName());
        textFields[1].setText(passenger.getFamily());
        textFields[2].setText(passenger.getPhoneNumber());
        textFields[3].setText(passenger.getEmail());
        textFields[4].setText(passenger.getUsername());
        textFields[5].setText(passenger.getPassword());
        textFields[6].setText(passenger.getPassword());
        Button submitEdits = ((Button) nodes.get(14));

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ef5350"));
        PassengerPageButtons.submitEditsButtonMaker(submitEdits, dialog, errorLabel, textFields);

        vBox.getChildren().addAll(errorLabel);
        dialogPane.setContent(vBox);
        dialog.showAndWait();
    }

    public void messagesPage() {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("New Message", dialogPane);

        Label errorLabel = new Label("");
        Label toLabel = new Label("To :");
        Label titleLabel = new Label("title:");
        errorLabel.setTextFill(Color.web("#ef5350"));
        toLabel.setTextFill(Color.web("#EEEEEE"));
        titleLabel.setTextFill(Color.web("#EEEEEE"));

        TextField titleTextField = new TextField();
        titleTextField.setMinWidth(400);
        TextArea messageBody = new TextArea();
        messageBody.setMinWidth(400);
        messageBody.setPromptText("write your message");


        final ComboBox managersComboBox = new ComboBox();
        managersComboBox.setPromptText("Choose manager to send to");
        ArrayList<Manager> managers = people.getManagers();
        for (Manager m : managers)
            managersComboBox.getItems().add(m);

        Button sendMessageButton = PassengerPageButtons.sendMessageButton(dialog, errorLabel, messageBody, titleTextField, managersComboBox);

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        grid.add(toLabel, 0, 0);
        grid.add(managersComboBox, 1, 0);
        grid.add(titleLabel, 0, 1);
        grid.add(titleTextField, 1, 1);
        grid.add(messageBody, 0, 2, 2, 1);
        grid.add(sendMessageButton, 0, 3);
        grid.add(errorLabel, 1, 3);

        dialogPane.setContent(grid);
        dialog.showAndWait();
    }

    public void buyTicketPage() {
        Label flightsListLabel = new Label("Select Flight to buy:");
        flightsListLabel.setTextFill(Color.web("#EEEEEE"));
        flightsListLabel.setFont(new Font(45));

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e -> {
            try {
                dashboardPage();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        StackPane stackPane = new StackPane(backButton );
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);

        TableView tableView = new TableView();
        tableView.setEditable(true);

        TableColumn<Flight, String> column0 = new TableColumn<>("ID");
        column0.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Flight, String> column1 = new TableColumn<>("Ticket");
        column1.setCellValueFactory(new PropertyValueFactory<>("ticket"));

        TableColumn<Flight, String> column2 = new TableColumn<>("Destination");
        column2.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<Flight, String> column3 = new TableColumn<>("Origin");
        column3.setCellValueFactory(new PropertyValueFactory<>("origin"));

        TableColumn<Flight, String> column4 = new TableColumn<>("Flight Time");
        column4.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Flight, String> column5 = new TableColumn<>("duration");
        column5.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Flight, String> column6 = new TableColumn<>("AirPlane");
        column6.setCellValueFactory(new PropertyValueFactory<>("airPlane"));

        TableColumn<Flight, String> column7 = new TableColumn<>("Status");
        column7.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Flight, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>> cellFactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(final TableColumn<Flight, Void> param) {
                final TableCell<Flight, Void> cell = new TableCell<Flight, Void>() {

                    private final Button btn = new Button("Buy");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            buyDialog(getTableView().getItems().get(getIndex()));
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setMinWidth(50);
                            StackPane stackPane1 = new StackPane(btn);
                            stackPane.setAlignment(btn, Pos.CENTER);
                            stackPane1.setMinWidth(80);
                            setGraphic(stackPane1);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);

        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(flights.getFlights().toArray());
        tableView.setItems(observableList);

        tableView.getColumns().addAll(column0, column7, column1, column2, column3, column4, column5, column6, colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(flightsListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 1200, 700));
    }

    private void buyDialog(Flight flight) {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("buy new flight", dialogPane);
        Label msg = new Label();
        msg.setTextFill(Color.web("#EEEEEE"));
        msg.setFont(new Font(30));

        boolean err = false;
        if (flight.getStatus() == FlightStatus.undone) {
            if (flight.getAirPlane().getSeats() > flight.getPassengers().size()) {
                if (passenger.getBalance() >= flight.getTicket().getCost()) {
                    msg.setText("are you sure you want to by this ticket?\npay " + flight.getTicket().getCost() + " for flight from " + flight.getOrigin() + " to " + flight.getDestination() + ".");
                } else {
                    msg.setText("Not enough balance to buy ticket.");
                    err = true;
                }
            } else {
                msg.setText("No seats available to buy ticket.");
                err = true;
            }
        } else {
            msg.setText("Time to buy ticket is over.");
            err = true;
        }
        if (err) {
            Button ok = new Button("OK");
            ok.setMinWidth(100);
            ok.setOnMouseClicked(e -> {
                dialog.close();
                buyTicketPage();
            });
            StackPane stackPane = new StackPane(ok);
            stackPane.setAlignment(ok, Pos.CENTER_RIGHT);

            VBox vBox = new VBox(msg, stackPane);
            vBox.setSpacing(20);
            dialogPane.setContent(vBox);
        } else {
            Button ok = new Button("OK");
            ok.setMinWidth(100);
            ok.setOnMouseClicked(e -> {
                passenger.setBalance(passenger.getBalance() - flight.getTicket().getCost());
                passenger.addFlight(flight);
                flight.addPassengers(passenger);
                dialog.close();
                myFlightsPage();
            });
            Button cancel = new Button("Cancel");
            cancel.setMinWidth(100);
            cancel.setOnMouseClicked(e -> {
                dialog.close();
                buyTicketPage();
            });

            StackPane stackPane = new StackPane(cancel, ok);
            stackPane.setAlignment(cancel, Pos.CENTER_LEFT);
            stackPane.setAlignment(ok, Pos.CENTER_RIGHT);

            VBox vBox = new VBox(msg, stackPane);
            vBox.setSpacing(20);
            dialogPane.setContent(vBox);
        }
//        dialogPane.setMinHeight(300);
        dialog.showAndWait();
    }

    public void myFlightsPage() {
        Label flightsListLabel = new Label("Your Flights List:");
        flightsListLabel.setTextFill(Color.web("#EEEEEE"));
        flightsListLabel.setFont(new Font(45));

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e -> {
            try {
                dashboardPage();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        StackPane stackPane = new StackPane(backButton );
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);

        TableView tableView = new TableView();
        tableView.setEditable(true);

        TableColumn<Flight, String> column0 = new TableColumn<>("ID");
        column0.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Flight, String> column1 = new TableColumn<>("Ticket");
        column1.setCellValueFactory(new PropertyValueFactory<>("ticket"));

        TableColumn<Flight, String> column2 = new TableColumn<>("Destination");
        column2.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<Flight, String> column3 = new TableColumn<>("Origin");
        column3.setCellValueFactory(new PropertyValueFactory<>("origin"));

        TableColumn<Flight, String> column4 = new TableColumn<>("Flight Time");
        column4.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Flight, String> column5 = new TableColumn<>("duration");
        column5.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Flight, String> column6 = new TableColumn<>("AirPlane");
        column6.setCellValueFactory(new PropertyValueFactory<>("airPlane"));

        TableColumn<Flight, String> column7 = new TableColumn<>("Status");
        column7.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(passenger.getFlights().toArray());
        tableView.setItems(observableList);

        tableView.getColumns().addAll(column0, column7, column1, column2, column3, column4, column5, column6);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(flightsListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 1200, 700));
    }
}

package Views;

import Controls.EmployeePageButtons;
import Controls.ManagerPageButtons;
import Controls.PassengerPageButtons;
import Models.*;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ManagerPages {
    Stage primaryStage;
    People people;
    Flights flights;
    Airplanes airplanes;
    Manager manager;

    public ManagerPages(Stage primaryStage, Manager manager, People people, Flights flights, Airplanes airplanes) {
        this.primaryStage = primaryStage;
        this.people = people;
        this.flights = flights;
        this.airplanes = airplanes;
        this.manager = manager;
        ManagerPageButtons.init(this);
    }

    public void dashboardPage() throws FileNotFoundException {
//        ImageView managerImage = new ImageView(new Image(new FileInputStream("Images\\manager.jpg"), 300, 300, false, false));
        Label userFullNameLabel = new Label(manager.getName() + " " + manager.getFamily());
        Label usernameLabel = new Label(manager.getUsername());
        Label salaryLabel = new Label("Salary");
        Label salaryValueLabel = new Label(manager.getSalary() + "");
        Button editInfoButton = ManagerPageButtons.editInfoButton();
        Button logoutButton = ManagerPageButtons.logoutButton();
        Button messagesButton = ManagerPageButtons.messagesButton();
        Button flightsButton = ManagerPageButtons.flightsButton();
        Button passengersButton = ManagerPageButtons.passengersButton();
        Button employeesButton = ManagerPageButtons.employeesButton();
        Button airplanesButton = ManagerPageButtons.airplanesButton();

        userFullNameLabel.setTextFill(Color.web("#EEEEEE"));
        userFullNameLabel.setFont(new Font(40));
        usernameLabel.setTextFill(Color.web("#9E9E9E"));
        usernameLabel.setFont(new Font(30));
        salaryLabel.setTextFill(Color.web("#EEEEEE"));
        salaryLabel.setFont(new Font(25));
        salaryValueLabel.setTextFill(Color.web("#558B2F"));
        salaryValueLabel.setFont(new Font(30));

        VBox vBox0 = new VBox(new ImageView(new Image("Images//manager.jpg" , 300, 300, false, false )), userFullNameLabel, usernameLabel);
        vBox0.setAlignment(Pos.CENTER);
        HBox hBox0 = new HBox(salaryValueLabel);
        hBox0.setAlignment(Pos.TOP_RIGHT);
        hBox0.setMargin(salaryValueLabel, new Insets(5, 0, 0, 0));
        VBox vBox1 = new VBox(salaryLabel, hBox0);
        vBox1.setAlignment(Pos.TOP_RIGHT);

        StackPane stackPane0 = new StackPane(vBox0, vBox1, editInfoButton, logoutButton);
        stackPane0.setAlignment(Pos.TOP_CENTER);
        stackPane0.setAlignment(editInfoButton, Pos.BOTTOM_LEFT);
        stackPane0.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        stackPane0.setMargin(editInfoButton, new Insets(0, 0, 0, 15));
        stackPane0.setMargin(logoutButton, new Insets(0, 15, 0, 0));
        stackPane0.setMargin(vBox0, new Insets(15, 0, 0, 0));
        stackPane0.setMargin(vBox1, new Insets(15, 15, 0, 0));

        HBox hBox1 = new HBox(passengersButton, employeesButton, flightsButton, airplanesButton, messagesButton);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(25);
        VBox vBox2 = new VBox(stackPane0, new Separator(), hBox1, new Separator());
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setSpacing(20);
        vBox2.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox2, 700, 700));
    }

    public void editInfoPage() {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("Edit information",dialogPane);
        VBox vBox = DefaultPages.createSignUpPage("Edit your information",
                false,"First Name", "Last Name", "Phone number", "Email", "Username" , "address", "Password", "re-enter Pass");
        GridPane gridPane = (GridPane)vBox.getChildren().get(2);
        ObservableList<Node> nodes = gridPane.getChildren();
        TextField textFields[] = new TextField[8];
        for (int i = 0,j=1; i < 8; i++,j+=2)
            textFields[i] = ((TextField)nodes.get(j));
        textFields[0].setText(manager.getName());
        textFields[1].setText(manager.getFamily());
        textFields[2].setText(manager.getPhoneNumber());
        textFields[3].setText(manager.getEmail());
        textFields[4].setText(manager.getUsername());
        textFields[5].setText(manager.getAddress());
        textFields[6].setText(manager.getPassword());
        textFields[7].setText(manager.getPassword());
        Button submitEdits = ((Button)nodes.get(16));

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ef5350"));
        ManagerPageButtons.submitEditsButtonMaker(submitEdits,dialog,errorLabel,textFields);

        vBox.getChildren().addAll(errorLabel);
        dialogPane.setContent(vBox);
        dialog.showAndWait();
    }

    public void messagesPage() {
        Label passengersListLabel = new Label("Messages List:");
        passengersListLabel.setTextFill(Color.web("#EEEEEE"));
        passengersListLabel.setFont(new Font(45));

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e -> {
            try {
                dashboardPage();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        StackPane stackPane = new StackPane(backButton);
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);

        TableView tableView = new TableView();
        tableView.setEditable(true);
        TableColumn<Message, String> column0 = new TableColumn<>("Title");
        column0.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Message, String> column1 = new TableColumn<>("From");
        column1.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<Message, String> column2 = new TableColumn<>("Message");
        column2.setCellValueFactory(new PropertyValueFactory<>("text"));

        TableColumn<Message, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<Message, Void>, TableCell<Message, Void>> cellFactory = new Callback<TableColumn<Message, Void>, TableCell<Message, Void>>() {
            @Override
            public TableCell<Message, Void> call(final TableColumn<Message, Void> param) {
                final TableCell<Message, Void> cell = new TableCell<Message, Void>() {

                    private final Button btn = new Button("delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Message Passenger = getTableView().getItems().get(getIndex());
                            manager.getMessages().remove(Passenger);
                            messagesPage();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(manager.getMessages().toArray());
        tableView.setItems(observableList);

        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().addAll(column0,column1, column2,colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(passengersListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 800, 700));
    }

    public void flightsPage() {
        Label flightsListLabel = new Label("Flights List:");
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
        Button addNewFlightButton = ManagerPageButtons.newFlightButton();
        StackPane stackPane = new StackPane(backButton, addNewFlightButton);
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        stackPane.setAlignment(addNewFlightButton, Pos.CENTER_RIGHT);

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

                    private final Button btn = new Button("edit");
                    private final Button btn2 = new Button("delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            DefaultDialog.create_edit_FlightDialog(getTableView().getItems().get(getIndex()),flights,airplanes, true, ManagerPages.this, null).showAndWait();
                        });
                        btn2.setOnAction((ActionEvent event) -> {
                            Flight flight = getTableView().getItems().get(getIndex());
                            flights.delete(flight);
                            flightsPage();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setMinWidth(50);
                            btn2.setMinWidth(50);
                            StackPane stackPane1 = new StackPane(btn , btn2);
                            stackPane.setAlignment(btn, Pos.CENTER_LEFT);
                            stackPane.setAlignment(btn2, Pos.CENTER_RIGHT);
                            stackPane1.setMinWidth(100);
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

        tableView.getColumns().addAll(column0,column7, column1, column2, column3,column4, column5,column6, colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(flightsListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 1200, 700));
    }

    public void airplanesPage() {
        Label flightsListLabel = new Label("Airplanes List:");
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
        Button addNewFlightButton = ManagerPageButtons.newAirPlaneButton();
        StackPane stackPane = new StackPane(backButton, addNewFlightButton);
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        stackPane.setAlignment(addNewFlightButton, Pos.CENTER_RIGHT);

        TableView<AirPlane> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<AirPlane, String> column0 = new TableColumn<>("ID");
        column0.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<AirPlane, Number> column1 = new TableColumn<>("Seats");
        column1.setCellValueFactory(new PropertyValueFactory<>("seats"));
        column1.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        column1.setOnEditCommit(t -> ((AirPlane)t.getTableView().getItems().get(t.getTablePosition().getRow())).setSeats((Integer) t.getNewValue()));

        TableColumn<AirPlane, Void> colBtn = new TableColumn<AirPlane, Void>("Action");
        Callback<TableColumn<AirPlane, Void>, TableCell<AirPlane, Void>> cellFactory = new Callback<TableColumn<AirPlane, Void>, TableCell<AirPlane, Void>>() {
            @Override
            public TableCell<AirPlane, Void> call(final TableColumn<AirPlane, Void> param) {
                final TableCell<AirPlane, Void> cell = new TableCell<AirPlane, Void>() {

                    private final Button btn1 = new Button("edit");
                    private final Button btn2 = new Button("flights");
                    private final Button btn3 = new Button("delete");

                    {
                        btn1.setOnAction((ActionEvent event) -> {
                            DefaultDialog.create_edit_AirplaneDialog(getTableView().getItems().get(getIndex()), airplanes, ManagerPages.this).showAndWait();
                        });
                        btn2.setOnAction((ActionEvent event) -> {
//                            Flight flight = getTableView().getItems().get(getIndex());
//                            people.delete(Flight);
//                            passengersPage();
                        });
                        btn3.setOnMouseClicked(e ->{
                            airplanes.getAirplanes().remove(getTableView().getItems().get(getIndex()));
                            airplanesPage();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn1.setMinWidth(50);
                            btn2.setMinWidth(50);
                            btn3.setMinWidth(50);
                            StackPane stackPane1 = new StackPane(btn1 , btn2 , btn3);
                            stackPane.setAlignment(btn1, Pos.CENTER_LEFT);
                            stackPane.setAlignment(btn2, Pos.CENTER);
                            stackPane.setAlignment(btn3, Pos.CENTER_RIGHT);
                            stackPane1.setMinWidth(150);
                            setGraphic(stackPane1);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);

        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(airplanes.getAirplanes().toArray());
        tableView.setItems(observableList);

        tableView.getColumns().addAll(column0, column1, colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(flightsListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 800, 700));
    }

    public void passengersPage() {
        Label passengersListLabel = new Label("Passengers List:");
        passengersListLabel.setTextFill(Color.web("#EEEEEE"));
        passengersListLabel.setFont(new Font(45));

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e -> {
            try {
                dashboardPage();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Button addNewPassengerButton = ManagerPageButtons.newPassengerButton();
        StackPane stackPane = new StackPane(backButton, addNewPassengerButton);
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        stackPane.setAlignment(addNewPassengerButton, Pos.CENTER_RIGHT);

        TableView tableView = new TableView();
        tableView.setEditable(true);

        TableColumn<Passenger, String> column0 = new TableColumn<>("First Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("name"));
        column0.setCellFactory(TextFieldTableCell.forTableColumn());
        column0.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Passenger, String> column1 = new TableColumn<>("Last Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("family"));
        column1.setCellFactory(TextFieldTableCell.forTableColumn());
        column1.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setFamily(t.getNewValue()));

        TableColumn<Passenger, String> column2 = new TableColumn<>("Email");
        column2.setCellValueFactory(new PropertyValueFactory<>("email"));
        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue()));

        TableColumn<Passenger, String> column3 = new TableColumn<>("Telephone");
        column3.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        column3.setCellFactory(TextFieldTableCell.forTableColumn());
        column3.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue()));

        TableColumn<Passenger, String> column4 = new TableColumn<>("ID");
        column4.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Passenger, String> column5 = new TableColumn<>("Username");
        column5.setCellValueFactory(new PropertyValueFactory<>("username"));
        column5.setCellFactory(TextFieldTableCell.forTableColumn());
        column5.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setUsername(t.getNewValue()));

        TableColumn<Passenger, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<Passenger, Void>, TableCell<Passenger, Void>> cellFactory = new Callback<TableColumn<Passenger, Void>, TableCell<Passenger, Void>>() {
            @Override
            public TableCell<Passenger, Void> call(final TableColumn<Passenger, Void> param) {
                final TableCell<Passenger, Void> cell = new TableCell<Passenger, Void>() {

                    private final Button btn = new Button("delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Passenger Passenger = getTableView().getItems().get(getIndex());
                            people.delete(Passenger);
                            passengersPage();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(people.getPassengers().toArray());
        tableView.setItems(observableList);

        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().addAll(column4, column5, column0, column1, column2, column3,colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(passengersListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 800, 700));
    }

    public void employeesPage() {
        Label passengersListLabel = new Label("Employee List:");
        passengersListLabel.setTextFill(Color.web("#EEEEEE"));
        passengersListLabel.setFont(new Font(45));

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e -> {
            try {
                dashboardPage();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Button addNewPassengerButton = ManagerPageButtons.newEmployeeButton();
        StackPane stackPane = new StackPane(backButton, addNewPassengerButton);
        stackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        stackPane.setAlignment(addNewPassengerButton, Pos.CENTER_RIGHT);

        TableView tableView = new TableView();
        tableView.setEditable(true);

        TableColumn<Employee, String> column0 = new TableColumn<>("First Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("name"));
        column0.setCellFactory(TextFieldTableCell.forTableColumn());
        column0.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Employee, String> column1 = new TableColumn<>("Last Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("family"));
        column1.setCellFactory(TextFieldTableCell.forTableColumn());
        column1.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setFamily(t.getNewValue()));

        TableColumn<Employee, String> column2 = new TableColumn<>("Email");
        column2.setCellValueFactory(new PropertyValueFactory<>("email"));
        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue()));

        TableColumn<Employee, String> column3 = new TableColumn<>("Telephone");
        column3.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        column3.setCellFactory(TextFieldTableCell.forTableColumn());
        column3.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue()));

        TableColumn<Employee, String> column4 = new TableColumn<>("ID");
        column4.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> column5 = new TableColumn<>("Username");
        column5.setCellValueFactory(new PropertyValueFactory<>("username"));
        column5.setCellFactory(TextFieldTableCell.forTableColumn());
        column5.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setUsername(t.getNewValue()));

        TableColumn<Employee, String> column6 = new TableColumn<>("Address");
        column6.setCellValueFactory(new PropertyValueFactory<>("address"));
        column6.setCellFactory(TextFieldTableCell.forTableColumn());
        column6.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAddress(t.getNewValue()));

        TableColumn<Employee, Double> column7= new TableColumn<>("Salary");
        column7.setCellValueFactory(new PropertyValueFactory<>("salary"));
        column7.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        column7.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSalary(t.getNewValue()));

        TableColumn<Employee, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {

                    private final Button btn = new Button("delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            people.delete(employee);
                            employeesPage();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(people.getEmployees().toArray());
        tableView.setItems(observableList);

        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().addAll(column4, column5, column0, column1, column2, column3, column6,column7,colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(passengersListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 1000, 700));
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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

    public Manager getManager() {
        return manager;
    }
}

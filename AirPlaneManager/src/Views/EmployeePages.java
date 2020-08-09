package Views;

import Controls.EmployeePageButtons;
import Controls.ManagerPageButtons;
import Controls.PassengerPageButtons;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Models.Employee;
import Models.Flight;
import Models.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class EmployeePages {
    Stage primaryStage;
    People people;
    Flights flights;
    Airplanes airplanes;
    Employee employee;

    public EmployeePages(Stage primaryStage,Employee employee, People people, Flights flights, Airplanes airplanes) {
        this.primaryStage = primaryStage;
        this.people = people;
        this.flights = flights;
        this.airplanes = airplanes;
        this.employee = employee;
        EmployeePageButtons.init(this);
    }

    public void dashboardPage() throws FileNotFoundException {
//        ImageView employeeImage = new ImageView(new Image(new FileInputStream("Images\\employee.png"),300,300,false,false));
        Label userFullNameLabel = new Label(employee.getName() + " " + employee.getFamily());
        Label usernameLabel = new Label(employee.getUsername());
        Label salaryLabel = new Label("Salary");
        Label salaryValueLabel = new Label(employee.getSalary() + "");
        Button editInfoButton = EmployeePageButtons.editInfoButton();
        Button logoutButton = EmployeePageButtons.logoutButton();
        Button messagesButton = EmployeePageButtons.messagesButton();
        Button flightsButton = EmployeePageButtons.flightsButton();

        userFullNameLabel.setTextFill(Color.web("#EEEEEE"));
        userFullNameLabel.setFont(new Font(40));
        usernameLabel.setTextFill(Color.web("#9E9E9E"));
        usernameLabel.setFont(new Font(30));
        salaryLabel.setTextFill(Color.web("#EEEEEE"));
        salaryLabel.setFont(new Font(25));
        salaryValueLabel.setTextFill(Color.web("#558B2F"));
        salaryValueLabel.setFont(new Font(30));

        VBox vBox0 = new VBox(new ImageView(new Image("Images//employee.jpg" , 300, 300, false, false )), userFullNameLabel, usernameLabel);
        vBox0.setAlignment(Pos.CENTER);
        HBox hBox0 = new HBox(salaryValueLabel);
        hBox0.setAlignment(Pos.TOP_RIGHT);
        hBox0.setMargin(salaryValueLabel,new Insets(5,0,0,0));
        VBox vBox1 = new VBox(salaryLabel,hBox0);
        vBox1.setAlignment(Pos.TOP_RIGHT);

        StackPane stackPane0 = new StackPane(vBox0, vBox1, editInfoButton,logoutButton);
        stackPane0.setAlignment(Pos.TOP_CENTER);
        stackPane0.setAlignment(editInfoButton, Pos.BOTTOM_LEFT);
        stackPane0.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        stackPane0.setMargin(editInfoButton, new Insets(0,0,0,15));
        stackPane0.setMargin(logoutButton, new Insets(0,15,0,0));
        stackPane0.setMargin(vBox0, new Insets(15,0,0,0));
        stackPane0.setMargin(vBox1, new Insets(15,15,0,0));

        HBox hBox1 = new HBox(messagesButton,flightsButton);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(25);
        VBox vBox2 = new VBox(stackPane0, new Separator(), hBox1, new Separator());
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setSpacing(20);
        vBox2.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox2, 700,700));
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
        textFields[0].setText(employee.getName());
        textFields[1].setText(employee.getFamily());
        textFields[2].setText(employee.getPhoneNumber());
        textFields[3].setText(employee.getEmail());
        textFields[4].setText(employee.getUsername());
        textFields[5].setText(employee.getAddress());
        textFields[6].setText(employee.getPassword());
        textFields[7].setText(employee.getPassword());
        Button submitEdits = ((Button)nodes.get(16));

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ef5350"));
        EmployeePageButtons.submitEditsButtonMaker(submitEdits,dialog,errorLabel,textFields);

        vBox.getChildren().addAll(errorLabel);
        dialogPane.setContent(vBox);
        dialog.showAndWait();
    }

    public void messagesPage() {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("New Message",dialogPane);

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

        Button sendMessageButton = EmployeePageButtons.sendMessageButton(dialog,errorLabel,messageBody,titleTextField,managersComboBox);

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        grid.add(toLabel, 0, 0);
        grid.add(managersComboBox, 1, 0);
        grid.add(titleLabel, 0, 1);
        grid.add(titleTextField, 1, 1 );
        grid.add(messageBody, 0, 2, 2,1);
        grid.add(sendMessageButton, 0, 3);
        grid.add (errorLabel, 1, 3);

        dialogPane.setContent(grid);
        dialog.showAndWait();
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
        Button addNewFlightButton = EmployeePageButtons.newFlightButton();
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
                            DefaultDialog.create_edit_FlightDialog(getTableView().getItems().get(getIndex()),flights,airplanes, false, null, EmployeePages.this).showAndWait();
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

        tableView.getColumns().addAll(column0, column7,column1, column2, column3,column4, column5,column6, colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox = new VBox(flightsListLabel, tableView, stackPane);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox, 1200, 700));
    }

    public Stage getPrimaryStage() { return primaryStage; }
    public People getPeople() { return people; }
    public Flights getFlights() { return flights; }
    public Airplanes getAirplanes() { return airplanes; }
    public Employee getEmployee() { return employee; }
}

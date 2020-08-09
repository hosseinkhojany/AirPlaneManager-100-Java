package Views;
import Controls.SuperAdminPageButtons;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.DataContainerModels.People;
import Models.Employee;
import Models.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SuperAdminPages {
    Stage primaryStage;
    People people;
    Flights flights;
    Airplanes airplanes;
    Manager manager;

    public SuperAdminPages(Stage primaryStage, Manager manager, People people, Flights flights, Airplanes airplanes) {
        this.primaryStage = primaryStage;
        this.people = people;
        this.flights = flights;
        this.airplanes = airplanes;
        this.manager = manager;
        SuperAdminPageButtons.init(this);
    }

    public void dashboardPage() throws FileNotFoundException {
        ImageView passengerImage = new ImageView(new Image(new FileInputStream("Images\\superAdmin.png"),280,300,false,false));
        Label usernameLabel = new Label(manager.getUsername());
        Button editInfoButton = SuperAdminPageButtons.editInfoButton();
        Button logoutButton = SuperAdminPageButtons.logoutButton();

        usernameLabel.setTextFill(Color.web("#9CCC65"));
        usernameLabel.setFont(new Font(40));

        VBox vBox0 = new VBox(passengerImage, usernameLabel);
        vBox0.setAlignment(Pos.CENTER);
        StackPane stackPane0 = new StackPane(vBox0, editInfoButton,logoutButton);
        stackPane0.setAlignment(Pos.TOP_CENTER);
        stackPane0.setAlignment(editInfoButton, Pos.BOTTOM_LEFT);
        stackPane0.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        stackPane0.setMargin(editInfoButton, new Insets(0,0,0,15));
        stackPane0.setMargin(logoutButton, new Insets(0,15,0,0));
        stackPane0.setMargin(vBox0, new Insets(15,0,0,0));


        Label managersListLabel = new Label("Manager List:");
        managersListLabel.setTextFill(Color.web("#EEEEEE"));
        managersListLabel.setFont(new Font(45));

        Button addNewManagerButton = SuperAdminPageButtons.newManagerPage();
        StackPane stackPane = new StackPane(addNewManagerButton);
        stackPane.setAlignment(addNewManagerButton, Pos.CENTER_RIGHT);
        stackPane.setMargin(addNewManagerButton, new Insets(20));

        TableView tableView = new TableView();
        tableView.setEditable(true);

        TableColumn<Manager, String> column0 = new TableColumn<>("First Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("name"));
        column0.setCellFactory(TextFieldTableCell.forTableColumn());
        column0.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        TableColumn<Manager, String> column1 = new TableColumn<>("Last Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("family"));
        column1.setCellFactory(TextFieldTableCell.forTableColumn());
        column1.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setFamily(t.getNewValue()));

        TableColumn<Manager, String> column2 = new TableColumn<>("Email");
        column2.setCellValueFactory(new PropertyValueFactory<>("email"));
        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue()));

        TableColumn<Manager, String> column3 = new TableColumn<>("Telephone");
        column3.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        column3.setCellFactory(TextFieldTableCell.forTableColumn());
        column3.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue()));

        TableColumn<Manager, String> column4 = new TableColumn<>("ID");
        column4.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Manager, String> column5 = new TableColumn<>("Username");
        column5.setCellValueFactory(new PropertyValueFactory<>("username"));
        column5.setCellFactory(TextFieldTableCell.forTableColumn());
        column5.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setUsername(t.getNewValue()));


        TableColumn<Manager, String> column6 = new TableColumn<>("Address");
        column6.setCellValueFactory(new PropertyValueFactory<>("address"));
        column6.setCellFactory(TextFieldTableCell.forTableColumn());
        column6.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAddress(t.getNewValue()));

        TableColumn<Manager, Double> column7= new TableColumn<>("Salary");
        column7.setCellValueFactory(new PropertyValueFactory<>("salary"));
        column7.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        column7.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSalary(t.getNewValue()));

        TableColumn<Manager, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<Manager, Void>, TableCell<Manager, Void>> cellFactory = new Callback<TableColumn<Manager, Void>, TableCell<Manager, Void>>() {
            @Override
            public TableCell<Manager, Void> call(final TableColumn<Manager, Void> param) {
                final TableCell<Manager, Void> cell = new TableCell<Manager, Void>() {

                    private final Button btn = new Button("delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Manager Manager = getTableView().getItems().get(getIndex());
                            people.delete(Manager);
                            try {
                                dashboardPage();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
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
        observableList.addAll(people.getManagers().toArray());
        tableView.setItems(observableList);

        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().addAll(column4, column5, column0, column1, column2, column3, column6,column7,colBtn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(600);

        VBox vBox2 = new VBox(stackPane0, new Separator(), managersListLabel , tableView , stackPane);
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setSpacing(20);
        vBox2.setMargin(tableView, new Insets(0,20,0,20));
        vBox2.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        primaryStage.setScene(new Scene(vBox2, 1000,700));
    }

    public void editInfoPage() {
        DialogPane dialogPane = new DialogPane();
        Dialog dialog = DefaultDialog.get("Edit information", dialogPane);
        Label headerLabel = new Label("Edit Information");
        headerLabel.setTextFill(Color.web("#EEEEEE"));
        headerLabel.setFont(new Font(45));
        Label username = new Label("username");
        Label password = new Label("password");
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ef5350"));
        TextField usernameTF = new TextField(manager.getUsername());
        PasswordField passwordTF = new PasswordField();
        passwordTF.setText(manager.getPassword());
        username.setTextFill(Color.web("#EEEEEE"));
        password.setTextFill(Color.web("#EEEEEE"));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.add(username, 0,0);
        gridPane.add(usernameTF, 1,0,2,1);

        gridPane.add(password, 0,1);
        gridPane.add(passwordTF, 1,1,2,1);

        Button submit = new Button("Submit");
        submit.setOnMouseClicked(e->{
            if(usernameTF.getText().trim().equals("") || passwordTF.getText().trim().equals("")){
                errorLabel.setText("please fill all blanks");
            }else if(people.contains(usernameTF.getText())){
                errorLabel.setText("this username is in use of other user.");
            }else{
                manager.setUsername(usernameTF.getText());
                manager.setPassword(passwordTF.getText());
                dialog.close();
                try {
                    dashboardPage();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        VBox vBox = new VBox(headerLabel, new Separator(), gridPane , new Separator() , submit, errorLabel);
        vBox.setSpacing(20);
        dialogPane.setContent(vBox);
        dialog.showAndWait();
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

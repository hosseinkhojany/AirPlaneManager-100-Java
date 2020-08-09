package Views;

import Controls.DateTimePicker;
import Models.AirPlane;
import Models.DataContainerModels.Airplanes;
import Models.DataContainerModels.Flights;
import Models.Flight;
import Models.Ticket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DefaultDialog {
    public static Dialog get(String header, DialogPane dialogPane) {
        Dialog dialog = new Dialog();
        dialog.setTitle(header);
        dialogPane.setBackground(new Background(new BackgroundFill(Color.web("#263238"), null, null)));
        dialog.setDialogPane(dialogPane);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        return dialog;
    }

    public static Dialog create_edit_FlightDialog(Flight flight, Flights flights, Airplanes airplanes, boolean isManager, ManagerPages managerPages, EmployeePages employeePages) {
        Dialog dialog;
        DialogPane dialogPane = new DialogPane();
        if (flight == null) {
            dialog = get("New Flight", dialogPane);
        } else {
            dialog = get("Edit Flight", dialogPane);
        }

        Label labelHeader = new Label();
        labelHeader.setTextFill(Color.web("#EEEEEE"));
        labelHeader.setFont(new Font(45));
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ef5350"));

        Label airplaneLabel = new Label("AirPlane");
        Label ticketLabel = new Label("ticket price");
        Label destinationLabel = new Label("Destination");
        Label originLabel = new Label("Origin");
        Label startLabel = new Label("Flight time");
        Label durationLabel = new Label("Duration");

        airplaneLabel.setTextFill(Color.web("#EEEEEE"));
        ticketLabel.setTextFill(Color.web("#EEEEEE"));
        destinationLabel.setTextFill(Color.web("#EEEEEE"));
        originLabel.setTextFill(Color.web("#EEEEEE"));
        startLabel.setTextFill(Color.web("#EEEEEE"));
        durationLabel.setTextFill(Color.web("#EEEEEE"));

        ComboBox<AirPlane> comboBox = new ComboBox();
        comboBox.getItems().addAll(airplanes.getAirplanes());

        TextField ticketTF = new TextField();
        TextField destinationTF = new TextField();
        TextField originTF = new TextField();
        DateTimePicker startTF = new DateTimePicker();
        TextField durationTF = new TextField();

        Button submit = new Button("Submit");
        submit.setOnMouseClicked(e -> {
            String ticket = ticketTF.getText();
            String dest = destinationTF.getText();
            String origin = originTF.getText();
            String start = startTF.getDateTimeValue().format(DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss"));
            String duration = durationTF.getText();
            AirPlane airPlane = comboBox.getValue();

            if (ticket.trim().equals("") || dest.trim().equals("") || origin.trim().equals("")
                    || start.trim().equals("") || duration.trim().equals("")) {
                errorLabel.setText("please fill al blanks");
            } else if (airPlane == null) {
                errorLabel.setText("please select an airplane");
            } else if (!Pattern.matches("([0-9]+)|([0-9]+\\.[0-9]+)", ticket)) {
                errorLabel.setText("ticket price must be valid floating point number");
            }else if (!Pattern.matches("[0-9][0-9]\\:[0-9][0-9]", duration)) {
                errorLabel.setText("duration must be in HH:MM format");
            } else {
                if(flight == null) {
                    Flight newFlight = new Flight(airPlane, new Ticket(Double.parseDouble(ticket), 0),
                            dest, origin, start, duration);
                    airPlane.addFlight(newFlight);
                    flights.addFlight(newFlight);
                }else{
                    flight.setAirPlane(airPlane);
                    flight.setTicket(new Ticket(Double.parseDouble(ticket), 0));
                    flight.setDestination(dest);
                    flight.setDuration(duration);
                    flight.setOrigin(origin);
                    flight.setStart(start);
                }
                dialog.close();
                if(isManager)
                    managerPages.flightsPage();
                else
                    employeePages.flightsPage();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(30);
        gridPane.add(airplaneLabel, 0, 0);
        gridPane.add(comboBox, 1, 0, 2, 1);

        gridPane.add(ticketLabel, 0, 1);
        gridPane.add(ticketTF, 1, 1, 2, 1);

        gridPane.add(destinationLabel, 0, 2);
        gridPane.add(destinationTF, 1, 2, 2, 1);

        gridPane.add(originLabel, 0, 3);
        gridPane.add(originTF, 1, 3, 2, 1);

        gridPane.add(startLabel, 0, 4);
        gridPane.add(startTF, 1, 4, 2, 1);

        gridPane.add(durationLabel, 0, 5);
        gridPane.add(durationTF, 1, 5, 2, 1);

        VBox vBox = new VBox(labelHeader, new Separator(), gridPane, new Separator(), submit, errorLabel);
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.TOP_CENTER);
        dialogPane.setContent(vBox);
        dialogPane.setMinWidth(300);
        if (flight == null) {
            labelHeader.setText("New Flight");
        } else {
            labelHeader.setText("Edit Flight");
            comboBox.setValue(flight.getAirPlane());
            ticketTF.setText(flight.getTicket().getCost() + "");
            destinationTF.setText(flight.getDestination());
            originTF.setText(flight.getOrigin());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf.parse(flight.getStart()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            startTF.setDateTimeValue(LocalDateTime.of(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE)));
            durationTF.setText(flight.getDuration());
        }
        return dialog;
    }

    public static Dialog create_edit_AirplaneDialog(AirPlane airPlane, Airplanes airplanes, ManagerPages managerPages) {
        Dialog dialog;
        DialogPane dialogPane = new DialogPane();
        if (airPlane == null) {
            dialog = get("new airplane", dialogPane);
        } else {
            dialog = get("edit airplane", dialogPane);
        }

        Label labelHeader = new Label();
        labelHeader.setTextFill(Color.web("#EEEEEE"));
        labelHeader.setFont(new Font(45));
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ef5350"));
        Label seatsLabel = new Label("seats");
        seatsLabel.setTextFill(Color.web("#EEEEEE"));
        TextField seatsTF = new TextField();

        Button submit = new Button("submit");
        submit.setOnMouseClicked(e -> {
            if (seatsTF.getText().equals("")) {
                errorLabel.setText("please set number of seats");
            } else if (!Pattern.matches("[0-9]+", seatsTF.getText())) {
                errorLabel.setText("incorrect input, value must be an integer");
            } else {
                if(airPlane == null) {
                    airplanes.addAirPlane(new AirPlane(Integer.parseInt(seatsTF.getText())));
                    dialog.close();
                }else{
                    airPlane.setSeats(Integer.parseInt(seatsTF.getText()));
                }
                dialog.close();
                managerPages.airplanesPage();
            }
        });

        HBox hBox = new HBox(seatsLabel, seatsTF);
        VBox vBox = new VBox(labelHeader, new Separator(), hBox, new Separator(), submit, errorLabel);
        vBox.setSpacing(30);
        hBox.setSpacing(15);
        dialogPane.setContent(vBox);
        dialogPane.setMinWidth(300);
        if (airPlane == null) {
            labelHeader.setText("New Airplane");
        } else {
            labelHeader.setText("Edit Airplane");
            seatsTF.setText(airPlane.getSeats() + "");
        }
        return dialog;
    }

}

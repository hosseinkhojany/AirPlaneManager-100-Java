package Controls;

import Models.Manager;
import Views.*;
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

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import static Views.DefaultPages.createSignUpPage;

public class SuperAdminPageButtons {
    private static SuperAdminPages superAdminPages;
    static final InnerShadow innerShadow = new InnerShadow();
    static final DropShadow dropShadow = new DropShadow();
    public static void init(SuperAdminPages managerPages){
        SuperAdminPageButtons.superAdminPages = managerPages;
    }

    public static Button editInfoButton(){
        Button button = new Button("Edit Info.");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setTextFill(Color.web("#212121"));
        button.setBackground(new Background(new BackgroundFill(Color.web("#B0BEC5"), null, null)));
        button.setOnMouseClicked(e -> superAdminPages.editInfoPage());
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

    public static Button newManagerPage(){
        Button button = new Button("New");
        button.setBackground(new Background(new BackgroundFill(Color.web("#C5E1A5"), null, null)));
        button.setTextFill(Color.web("#1B5E20"));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> button.setEffect(innerShadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setEffect(dropShadow));
        button.setEffect(dropShadow);
        button.setOnMouseClicked(e ->{
            DialogPane dialogPane = new DialogPane();
            Dialog dialog = DefaultDialog.get("Add new Manager", dialogPane);
            VBox vBox = createSignUpPage("New Manager", false,"First Name", "Last Name", "Phone number", "Email", "Username" ,"Address", "Password", "re-enter Pass");
            GridPane gridPane = (GridPane)vBox.getChildren().get(2);
            ObservableList<Node> nodes = gridPane.getChildren();
            TextField textFields[] = new TextField[8];
            for (int i = 0,j=1; i < 8; i++,j+=2)
                textFields[i] = ((TextField)nodes.get(j));

            addNewButtonMaker(dialog, ((Button)nodes.get(16)), textFields, (Label) vBox.getChildren().get(4));

            dialogPane.setContent(vBox);
            dialog.showAndWait();
        });
        return button;
    }

    public static void addNewButtonMaker(Dialog dialog, Button button, TextField [] textFields, Label errorLabel){
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
                if (superAdminPages.getPeople().contains(textFields[4].getText()))
                    errorLabel.setText("this username is in use of other user");
                else if (!Pattern.matches("09\\d{9}", textFields[2].getText()))
                    errorLabel.setText("phone number is not correct! (reg: 09XXXXXXXXX)");
                else if (!Pattern.matches("[a-z|A-Z|0-9|\\.|_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}", textFields[3].getText()))
                    errorLabel.setText("email address is not correct!");
                else{
                    superAdminPages.getPeople().add(new Manager(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText(), textFields[6].getText(), textFields[5].getText(), 0, false));
                    dialog.close();
                    try {
                        superAdminPages.dashboardPage();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                errorLabel.setText("password and confirmation are not equal");
            }
        });
    }
}

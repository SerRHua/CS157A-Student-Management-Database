package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * creates a basic username and password to log into the student management system
 * username: admin
 * password: admin
 *
 */
public class LoginController {
	// create a window for login information
	private Stage stage;

	/*
	 * Constructor for main to show login window
	 */
    public LoginController(Stage stage) {
        this.stage = stage;
    }

    /**
     * show the window for Login
     */
	public void show() {
        Label label = new Label("Log In to the Student Management System!");

        Label user = new Label("Enter username:");
        TextField username = new TextField();
        username.setPromptText("username");
        HBox inputUser = new HBox(15, user, username);

        Label pass = new Label("Enter password:");
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        HBox inputPass = new HBox(15, pass, password);

        Button loginBtn = new Button("Log In");

        // check to see if username or password is correct
        // if either are incorrect, show an error popup
        // if both are correct, let the user into the management system
        loginBtn.setOnAction(e -> {
        	if (username.getText().equals("admin") && password.getText().equals("admin")) {
        		new MainMenuController(stage).show();
        	} else {
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password.");
                alert.showAndWait();
        	}
        });

        VBox layout = new VBox(15, label, inputUser, inputPass, loginBtn);
        layout.setStyle("-fx-padding: 20px");

        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Log In");
        stage.show();
	}

}



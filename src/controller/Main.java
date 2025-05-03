package controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
*
* main method to run the project
*
*/
public class Main extends Application {
	/**
	 * start the method to login to the database
	 */
    @Override
    public void start(Stage primaryStage) {
        LoginController login = new LoginController(primaryStage);
        login.show();
    }

    /**
     * launch the program
     * @param args - none used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
package controller;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * GUI to make Main Menu and choose 4 buttons: Enrollment, Course, Grade, Student
 *
 */
public class MainMenuController {
	// primary stage used in Main Menu
    private Stage stage;

    /**
     * constructor for Main Menu GUI
     * @param stage - primary stage
     */
    public MainMenuController(Stage stage) {
        this.stage = stage;
    }

    /**
     * show the window for main menu: 4 buttons to the tables 
     */
    public void show() {
        Label label = new Label("Welcome to the Student Management System!");

        Button studentBtn = new Button("Students");
        studentBtn.setOnAction(e -> new StudentController(stage).show());

        Button courseBtn = new Button("Courses");
        courseBtn.setOnAction(e -> new CourseController(stage).show());

        Button enrollBtn = new Button("Enrollments");
        enrollBtn.setOnAction(e -> new EnrollmentController(stage).show());

        Button gradesBtn = new Button("Grades");
        gradesBtn.setOnAction(e -> new GradesController(stage).show());

        VBox layout = new VBox(15, label, studentBtn, courseBtn, enrollBtn, gradesBtn);
        layout.setStyle("-fx-padding: 20px");

        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Main Menu");
        stage.show();
    }
}
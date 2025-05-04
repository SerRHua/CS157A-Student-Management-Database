package controller;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import data_access_objects.CourseDAO;
import data_structures.Course;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.*;
import javafx.geometry.Orientation;
/**
 * GUI for the Course database
 *
 */
public class CourseController {
	// primary stage used in course
    private Stage stage;
    
    // list of all courses made in database, used for GUI table
    private ObservableList<Course> courses = FXCollections.observableArrayList(CourseDAO.fetchAllCourses());
    
    // create the data access object for course
    private CourseDAO courseDAO = new CourseDAO();
    
    /**
     * constructor for course GUI
     * @param stage - primary stage
     */
    public CourseController(Stage stage) {
        this.stage = stage;
    }

    /**
     * show the window for course 
     */
    public void show() {
        // update tables for all users if multiple users access at same time
        // updates every 5 seconds
        Timeline timeline = new Timeline(
        	    new KeyFrame(Duration.seconds(5), e -> {
        	        courses.setAll(CourseDAO.fetchAllCourses());
        	    })
        	);
        	timeline.setCycleCount(Animation.INDEFINITE);
        	timeline.play();
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20px");
        
        // make the table for courses
        TableView<Course> table = new TableView<>(courses);
        table.setPrefHeight(150);
        
        TableColumn<Course, Integer> idCourse = new TableColumn<>("Course ID");
        idCourse.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Course, String> courseNameCol = new TableColumn<>("Course Name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        TableColumn<Course, String> teacherNameCol = new TableColumn<>("Teacher Name");
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        TableColumn<Course, Integer> semesterYear = new TableColumn<>("Semester");
        semesterYear.setCellValueFactory(new PropertyValueFactory<>("semester"));
        table.getColumns().addAll(idCourse, courseNameCol, teacherNameCol, semesterYear);

        // --- ADD COURSE ---
        TextField name = new TextField();
        name.setPromptText("Course Name");
        TextField teacher = new TextField();
        teacher.setPromptText("Teacher Name");
        TextField semester = new TextField();
        semester.setPromptText("Semester");

        Button addBtn = new Button("Add Course");
        
        VBox addBox = new VBox(10, new Label("Add Course"), name, teacher, semester, addBtn);
        
        // --- DELETE COURSE ---
        TextField deleteByID = new TextField();
        deleteByID.setPromptText("CourseID to delete Course");

        Button deleteBtn = new Button("Delete Course");
        

        VBox deleteBox = new VBox(10, new Label("Delete Course"), deleteByID, deleteBtn);
        
        // --- UPDATE COURSE ---
        TextField updateByID = new TextField();
        updateByID.setPromptText("CourseID (required)");
        TextField updateFirst = new TextField();
        updateFirst.setPromptText("Updated Course Name (if needed)");
        TextField updateLast = new TextField();
        updateLast.setPromptText("Updated Teacher Name (if needed)");
        TextField updateSemester = new TextField();
        updateSemester.setPromptText("Updated Semester (if needed)");
        
        Button updateBtn = new Button("Update Course Records");

        VBox updateBox = new VBox(10, new Label("Update Course"), updateByID, updateFirst, updateLast, updateSemester, updateBtn);
        
        // --- SEARCH/FILTER Courses ---
        Label columnLabel = new Label("Select Columns:");
        CheckBox colCourseID = new CheckBox("courseId");
        CheckBox colCourseName = new CheckBox("Course Name");
        CheckBox colTeacherName = new CheckBox("Teacher Name");
        CheckBox colSemesterYear = new CheckBox("Semester and year");

        VBox columnSelectionBox = new VBox(5, columnLabel, colCourseID, colCourseName, colTeacherName, colSemesterYear);
        
        // separate cols and rows for filtering - looks better
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        // Conditions - filter through rows
        
        Label rowLabel = new Label("Select Rows:");
        // filter for courseID
        ComboBox<String> courseIDOperatorCombo = new ComboBox<>();
        courseIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        courseIDOperatorCombo.setValue("Select"); // placeholder-style default
        TextField courseIDChoose = new TextField();
        courseIDChoose.setPromptText("courseID to filter");
        HBox courseIDRow = new HBox(5, courseIDOperatorCombo, courseIDChoose);
        
        // filter for course name
        ComboBox<String> courseNameOperatorCombo = new ComboBox<>();
        courseNameOperatorCombo.getItems().addAll("Select", "="); // course name is string, so can only be operator only = for now
        courseNameOperatorCombo.setValue("Select"); // placeholder-style default
        TextField courseNameChoose = new TextField();
        courseNameChoose.setPromptText("Course Name to filter");
        HBox courseNameRow = new HBox(5, courseNameOperatorCombo, courseNameChoose);
        
        // filter for teacher name
        ComboBox<String> teacherNameOperatorCombo = new ComboBox<>(); // teacher name is string, so can only be operator only = for now
        teacherNameOperatorCombo.getItems().addAll("Select", "=");
        teacherNameOperatorCombo.setValue("Select"); // placeholder-style default
        TextField teacherNameChoose = new TextField();
        teacherNameChoose.setPromptText("Teacher Name to filter");
        HBox teacherRow = new HBox(5, teacherNameOperatorCombo, teacherNameChoose);
        
        // filter for semester year
        ComboBox<String> semesterOperatorCombo = new ComboBox<>();
        semesterOperatorCombo.getItems().addAll("Select", "="); // teacher name is string, so can only be operator only = for now
        semesterOperatorCombo.setValue("Select"); // placeholder-style default
        TextField semesterChoose = new TextField();
        semesterChoose.setPromptText("Semester year (e.g. Spring 2025) to filter");
        HBox semesterRow = new HBox(5, semesterOperatorCombo, semesterChoose);
        
        VBox rowSelectionBox = new VBox(5, rowLabel, courseIDRow, courseNameRow, teacherRow, semesterRow);
        
        HBox selectionsBox = new HBox(5, columnSelectionBox, separator, rowSelectionBox);


        Button searchBtn = new Button("Filter Courses");
        
        VBox searchBox = new VBox(10, new Label("Search/Filter Grade With Conditions"), selectionsBox, searchBtn);
        
        // --- Place Add and Delete and Update and Filter Side by Side ---
        HBox formSection = new HBox(30, addBox, deleteBox, updateBox, searchBox);
        
        // add actions to add, delete, update, search buttons
        addBtn.setOnAction(e -> {
            String courseName = name.getText().trim();
            String teacherName = teacher.getText().trim();
            String semesterText = semester.getText().trim();

            if (courseName.isEmpty() || teacherName.isEmpty() || semesterText.isEmpty()) {
                showAlert("Please fill in all fields: Course Name, Teacher, and Semester.");
                return;
            }

            courseDAO.createCourseRecord(courseName, teacherName, semesterText);
            courses.setAll(CourseDAO.fetchAllCourses());

            idCourse.setVisible(true);
            courseNameCol.setVisible(true);
            teacherNameCol.setVisible(true);
            semesterYear.setVisible(true);
            //make sure table refreshes for all users looking at tables
            timeline.play();
        });

        
        deleteBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(deleteByID.getText());
                
                // check to see if courseID exists
                Course existing = courses.stream()
                        .filter(c -> c.getId() == id)
                        .findFirst()
                        .orElse(null);
                    if (existing == null) {
                    	Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Course ID Not Found");
                        alert.setHeaderText(null);
                        alert.setContentText("Course with ID " + id + " not found.");
                        alert.showAndWait();
                        return;
                    }
                
                courseDAO.deleteCourseRecord(id);
                courses.setAll(CourseDAO.fetchAllCourses());
                
             // make sure all columns visible in case filter button was used
                idCourse.setVisible(true);
                courseNameCol.setVisible(true);
                teacherNameCol.setVisible(true);
                semesterYear.setVisible(true);
              //make sure table refreshes for all users looking at tables
                timeline.play();
            } catch (NumberFormatException ex) {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Course ID Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Invalid ID format.");
                alert.showAndWait();
                System.out.println("Invalid ID format.");
            }
        });
        
        updateBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(updateByID.getText());
                // check to see if courseID exists
                Course existing = courses.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);
                if (existing == null) {
                	Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Course ID Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Course with ID " + id + " not found.");
                    alert.showAndWait();
                    return;
                }

                String courseName  = updateFirst.getText().isEmpty()  ? existing.getCourseName() : updateFirst.getText();
                String teacherName = updateLast.getText().isEmpty()   ? existing.getInstructor(): updateLast.getText();
                String semesterVal = updateSemester.getText().isEmpty() ? existing.getSemester()   : updateSemester.getText();

                courseDAO.updateCourseRecord(id, courseName, teacherName, semesterVal);
                
                // make sure all columns visible in case filter button was used
                idCourse.setVisible(true);
                courseNameCol.setVisible(true);
                teacherNameCol.setVisible(true);
                semesterYear.setVisible(true);
              //make sure table refreshes for all users looking at tables
                timeline.play();

            } catch (NumberFormatException ex) {
                //showAlert("Invalid ID format.");
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid ID");
                alert.setHeaderText(null);
                alert.setContentText("Invalid ID .");
                alert.showAndWait();
            }
        });
        
        searchBtn.setOnAction(e -> {
        	List<String> selectedCols = new ArrayList<>();
            if (colCourseID.isSelected()) selectedCols.add("courseID");
            if (colCourseName.isSelected()) selectedCols.add("course_name");
            if (colTeacherName.isSelected()) selectedCols.add("instructor");
            if (colSemesterYear.isSelected()) selectedCols.add("semester");
            String colsString = selectedCols.isEmpty() ? "*" : String.join(", ", selectedCols);
            if (!selectedCols.contains("courseID")) {
                colsString = "courseID, " + colsString;  // ensure it's always included for object mapping
            }
            
            if (selectedCols.isEmpty()) {
                showAlert("Please select at least one column to display.");
                return;
            }
            
            // Convert selected columns to string for search

            List<String> conditions = new ArrayList<>();
            
            // Create conditions for each filter
            String eidOp = courseIDOperatorCombo.getValue();
            String eidVal = courseIDChoose.getText().trim();
            if (!"Select".equals(eidOp) && !eidVal.isEmpty()) {
                conditions.add("courseID " + convertOperator(eidOp) + " " + eidVal);
            }

            String sidOp = courseNameOperatorCombo.getValue();
            String sidVal = courseNameChoose.getText().trim();
            if (!"Select".equals(sidOp) && !sidVal.isEmpty()) {
                conditions.add("course_name " + convertOperator(sidOp) + " " + sidVal);
            }

            String cidOp = teacherNameOperatorCombo.getValue();
            String cidVal = teacherNameChoose.getText().trim();
            if (!"Select".equals(cidOp) && !cidVal.isEmpty()) {
                conditions.add("instructor " + convertOperator(cidOp) + " " + cidVal);
            }

            String yearOp = semesterOperatorCombo.getValue();
            String yearVal = semesterChoose.getText().trim();
            if (!"Select".equals(yearOp) && !yearVal.isEmpty()) {
                conditions.add("semester " + convertOperator(yearOp) + " " + yearVal);
            }
            
            // Build condition string for query
            String conditionString = String.join(" AND ", conditions);
            
            // Query the DAO to get the filtered courses
            List<Course> validCourses = courseDAO.searchCourseRecord(colsString, conditionString);
            validCourses.removeIf(course -> course.getId() == -1); // Exclude invalid records
            
            // Set the filtered list to the ObservableList
            courses.setAll(validCourses);
            idCourse.setVisible(colCourseID.isSelected());
            courseNameCol.setVisible(colCourseName.isSelected());
            teacherNameCol.setVisible(colTeacherName.isSelected());
            semesterYear.setVisible(colSemesterYear.isSelected());
            //pause the table auto updates so it doesn't show whole table while user is searching/filtering
            timeline.pause();
        });

        Button back = new Button("Back");
        back.setOnAction(e -> new MainMenuController(stage).show());

        layout.getChildren().addAll(formSection, table, back);
        stage.setScene(new Scene(layout, 1000, 400));
        
    }
    
    /**
     * convert ≤ and ≥ into <= or => for Java to easily use operator
     * @param op - operator that user picked in GUI
     * @return operators for Java to use
     */
    private String convertOperator(String op) {
        return switch (op) {
            case "≤" -> "<=";
            case "≥" -> ">=";
            default -> op;
        };
    }
    
    /**
     * shows the error to the user
     * @param message - message to tell user
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
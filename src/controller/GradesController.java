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
import data_access_objects.GradesDAO;
import data_access_objects.StudentDAO;
import data_structures.Course;
import data_structures.Enrollment;
import data_structures.Grade;
import data_structures.Student;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.*;
import javafx.geometry.Orientation;

/**
 * GUI to make Grades from database
 *
 */
public class GradesController {
	// primary stage for gui
    private Stage stage;
    // list of all Grades made in database, used for GUI table
    private ObservableList<Grade> grades = FXCollections.observableArrayList(GradesDAO.fetchAllGrades());
    // get the database for Grades
    private GradesDAO gradesDAO = new GradesDAO();

    /**
     * constructor for Grades GUI
     * @param stage - primary stage
     */
    public GradesController(Stage stage) {
        this.stage = stage;
    }

    /**
     * show the window for grades GUI 
     */
    public void show() {
        // update tables for all users if multiple users access at same time
        // updates every 5 seconds
        Timeline timeline = new Timeline(
        	    new KeyFrame(Duration.seconds(5), e -> {
        	        grades.setAll(GradesDAO.fetchAllGrades());
        	    })
        	);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play(); //make sure table updates every 5 seconds
        	
    	// make table for Grades
        TableView<Grade> table = new TableView<>(grades);
        table.setPrefHeight(150);

        TableColumn<Grade, Integer> gradeIDCol = new TableColumn<>("Grade ID");
        gradeIDCol.setCellValueFactory(new PropertyValueFactory<>("gradeid"));

        TableColumn<Grade, Integer> studentIDCol = new TableColumn<>("Student ID");
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Grade, Integer> courseIDCol = new TableColumn<>("Course ID");
        courseIDCol.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        
        TableColumn<Grade, Integer> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Grade, String> semesterYearCol = new TableColumn<>("Semester Year");
        semesterYearCol.setCellValueFactory(new PropertyValueFactory<>("semesteryear"));

        table.getColumns().addAll(gradeIDCol, courseIDCol, studentIDCol, gradeCol, semesterYearCol);
    	
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20px");

        // --- ADD Grade ---
        TextField studentID = new TextField();
        studentID.setPromptText("Student ID");
        TextField courseID = new TextField();
        courseID.setPromptText("Course ID");
        TextField grade = new TextField();
        grade.setPromptText("Grade (e.g. 98.3)");
        TextField semester = new TextField();
        semester.setPromptText("Semester Year");

        Button addBtn = new Button("Add Grade");
        
        VBox addBox = new VBox(10, new Label("Add Grade"), courseID, studentID, grade, semester, addBtn);
        
        // --- DELETE GRADE ---
        TextField gradeID = new TextField();
        gradeID.setPromptText("Required GradeID");
        Button deleteBtn = new Button("Delete Grade");
        
        
        VBox deleteBox = new VBox(10, new Label("Delete Grade"), gradeID, deleteBtn);
        
        // -- UPDATE GRADE ---
        TextField requiredGradeID = new TextField();
        requiredGradeID.setPromptText("gradeID (required)");
        TextField updatedStudentID = new TextField();
        updatedStudentID.setPromptText("StudentID (optional)");
        TextField updatedCourseID = new TextField();
        updatedCourseID.setPromptText("CourseID (optional)");
        TextField changeGrade = new TextField();
        changeGrade.setPromptText("Change Grade e.g. 92.3 (optional)");
        TextField changeSemester = new TextField();
        changeSemester.setPromptText("Change Semester e.g. Fall 2025 (optional)");
        
        Button updateBtn = new Button("Update Grade");
        
        
        VBox updateBox = new VBox(10, new Label("Update Grade"), requiredGradeID, updatedCourseID, updatedStudentID, changeGrade, changeSemester, updateBtn);
        
        // --- SEARCH/FILTER STUDENTS/GRADES ---
        
        // conditions - filter through columns
        Label columnLabel = new Label("Select Columns:");
        CheckBox colGradeID = new CheckBox("GradeID");
        CheckBox colStudentID = new CheckBox("StudentID");
        CheckBox colCourseID = new CheckBox("CourseID");
        CheckBox colGrade = new CheckBox("Grade");
        CheckBox colSemester = new CheckBox("Semester");

        VBox columnSelectionBox = new VBox(5, columnLabel, colGradeID, colCourseID, colStudentID, colGrade, colSemester);
        
        // separate cols and rows for filtering - looks better
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        // Conditions - filter through rows
        
        Label rowLabel = new Label("Select Rows:");
        
        // filter for gradeID
        ComboBox<String> gradeIDOperatorCombo = new ComboBox<>();
        gradeIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        gradeIDOperatorCombo.setValue("Select"); // placeholder-style default
        
        TextField gradeIDChoose = new TextField();
        gradeIDChoose.setPromptText("gradeID to filter");
        HBox gradeIDRow = new HBox(5, gradeIDOperatorCombo, gradeIDChoose);
        
        // filter for studentID
        ComboBox<String> studentIDOperatorCombo = new ComboBox<>();
        studentIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        studentIDOperatorCombo.setValue("Select"); // placeholder-style default
        
        TextField studentIDChoose = new TextField();
        studentIDChoose.setPromptText("studentID to filter");
        HBox studentIDRow = new HBox(5, studentIDOperatorCombo, studentIDChoose);
        
        // filter for courseID
        ComboBox<String> courseIDOperatorCombo = new ComboBox<>();
        courseIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        courseIDOperatorCombo.setValue("Select"); // placeholder-style default
        
        TextField courseIDChoose = new TextField();
        courseIDChoose.setPromptText("courseID to filter");
        HBox courseIDRow = new HBox(5, courseIDOperatorCombo, courseIDChoose);
        
        // filter for grade (e.g. 93.5)
        ComboBox<String> gradeOperatorCombo = new ComboBox<>();
        gradeOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        gradeOperatorCombo.setValue("Select"); // placeholder-style default
        
        TextField gradeChoose = new TextField();
        gradeChoose.setPromptText("grade (e.g. 93.5) to filter");
        HBox gradeRow = new HBox(5, gradeOperatorCombo, gradeChoose);
        
        // filter for semester (e.g. Spring 2025)
        ComboBox<String> semesterOperatorCombo = new ComboBox<>();
        semesterOperatorCombo.getItems().addAll("Select", "="); // semester name is string, so can only be operator only = for now
        semesterOperatorCombo.setValue("Select"); // placeholder-style default
        
        TextField semesterChoose = new TextField();
        semesterChoose.setPromptText("semester (e.g. Spring 2025) to filter");
        HBox semesterRow = new HBox(5, semesterOperatorCombo, semesterChoose);

        
        
        VBox rowSelectionBox = new VBox(5, rowLabel, gradeIDRow, courseIDRow, studentIDRow, gradeRow, semesterRow);
        
        HBox selectionsBox = new HBox(5, columnSelectionBox, separator, rowSelectionBox);


        Button searchBtn = new Button("Search/Filter Grade");
        
        VBox searchBox = new VBox(10, new Label("Search/Filter Grade With Conditions"), selectionsBox, searchBtn);
        
        // -- give actions to Add, Delete, Update, Search Buttons ---
        addBtn.setOnAction(e -> {
            try {
                String sText = studentID.getText().trim();
                String cText = courseID.getText().trim();
                String gText = grade.getText().trim();
                String sem = semester.getText().trim();

                // Basic empty field check
                if (sText.isEmpty() || cText.isEmpty() || gText.isEmpty() || sem.isEmpty()) {
                    showAlert("All fields must be filled out.");
                    return;
                }

                // Numeric checks
                if (!sText.matches("\\d+") || !cText.matches("\\d+") || !gText.matches("^-?\\d+(\\.\\d+)?$")) {
                    showAlert("Student ID, Course ID, and Grade must be valid numbers.");
                    return;
                }

                int sID = Integer.parseInt(sText);
                int cID = Integer.parseInt(cText);
                double g = Double.parseDouble(gText);

                // Check if student and course exist
                List<Integer> validStudentIDs = StudentDAO.fetchAllStudents()
                    .stream().map(Student::getId).toList();
                List<Integer> validCourseIDs = CourseDAO.fetchAllCourses()
                    .stream().map(Course::getId).toList();

                if (!validStudentIDs.contains(sID)) {
                    showAlert("Student ID " + sID + " does not exist.");
                    return;
                }
                if (!validCourseIDs.contains(cID)) {
                    showAlert("Course ID " + cID + " does not exist.");
                    return;
                }

                // Create grade record
                gradesDAO.createGradeRecord(sID, cID, g, sem);
                grades.setAll(GradesDAO.fetchAllGrades());
                
                timeline.play();

                gradeIDCol.setVisible(true);
                studentIDCol.setVisible(true);
                courseIDCol.setVisible(true);
                semesterYearCol.setVisible(true);
                gradeCol.setVisible(true);

            } catch (Exception ex) {
                showAlert("Unexpected error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        
        deleteBtn.setOnAction(e -> {
            int id = Integer.parseInt(gradeID.getText());
            Grade existing = grades.stream()
                    .filter(c -> c.getGradeid() == id)
                    .findFirst()
                    .orElse(null);
                if (existing == null) {
                	showAlert("Grade with ID " + id + " not found.");
                    return;
                }
            gradesDAO.deleteGradeRecord(id);
            grades.setAll(GradesDAO.fetchAllGrades());
            
            gradeIDCol.setVisible(true);
            studentIDCol.setVisible(true);
            courseIDCol.setVisible(true);
            semesterYearCol.setVisible(true);
            gradeCol.setVisible(true);
            timeline.play();
        });
        
        updateBtn.setOnAction(e -> {
            String gradeIDText = requiredGradeID.getText().trim();

            // Check if the gradeID is empty or contains letters
            if (gradeIDText.isEmpty()) {
                showAlert("Grade ID cannot be empty.");
                return;
            }

            // Check if the gradeID contains any non-numeric characters
            if (!gradeIDText.matches("\\d+")) {
                showAlert("Grade ID must be a number.");
                return;
            }
        	int id = Integer.parseInt(requiredGradeID.getText());

            Grade existingGrade = grades.stream()
                .filter(s -> s.getGradeid() == id)
                .findFirst()
                .orElse(null);

            if (existingGrade == null) {
            	showAlert("Grade with ID " + id + " not found.");
                return;
            }
            
            //optional - if 
            int newStudentID = updatedStudentID.getText().isEmpty() ? existingGrade.getId() : Integer.parseInt(updatedStudentID.getText());
            int newCourseID = updatedCourseID.getText().isEmpty() ? existingGrade.getCourseid() : Integer.parseInt(updatedCourseID.getText());
            String newYear = changeSemester.getText().isEmpty() ? existingGrade.getSemesteryear() : String.valueOf(changeSemester.getText());
            double newGrade = changeGrade.getText().isEmpty() ? existingGrade.getGrade() : Double.parseDouble(changeGrade.getText());
            
            // Fetch valid IDs
            List<Integer> validStudentIDs = StudentDAO.fetchAllStudents()
                .stream().map(Student::getId).toList();
            List<Integer> validCourseIDs = CourseDAO.fetchAllCourses()
                .stream().map(Course::getId).toList();

            // Validate
            if (!validStudentIDs.contains(newStudentID)) {
                showAlert("Student ID " + newStudentID + " does not exist.");
                return;
            }
            if (!validCourseIDs.contains(newCourseID)) {
                showAlert("Course ID " + newCourseID + " does not exist.");
                return;
            }

            gradesDAO.updateGradeRecord(id, newStudentID, newCourseID, newGrade, newYear);

            grades.setAll(GradesDAO.fetchAllGrades());
            
            gradeIDCol.setVisible(true);
            studentIDCol.setVisible(true);
            courseIDCol.setVisible(true);
            semesterYearCol.setVisible(true);
            gradeCol.setVisible(true);
            timeline.play();
        });
        
        
        searchBtn.setOnAction(e -> {
        	// List of selected columns
            List<String> selectedCols = new ArrayList<>();
            if (colGradeID.isSelected()) selectedCols.add("gradeID");
            if (colStudentID.isSelected()) selectedCols.add("studentID");
            if (colCourseID.isSelected()) selectedCols.add("courseID");
            if (colGrade.isSelected()) selectedCols.add("grade");
            if (colSemester.isSelected()) selectedCols.add("semester");
            
            if (selectedCols.isEmpty()) {
                showAlert("Please select at least one column to display.");
                return;
            }
            
            
            String colsString = selectedCols.isEmpty() ? "*" : String.join(", ", selectedCols);
            if (!selectedCols.contains("gradeID")) {
                colsString = "gradeID, " + colsString;  // ensure it's always included for object mapping
            }
            if (!selectedCols.contains("grade")) {
                colsString = "grade, " + colsString;  // ensure it's always included for object mapping
            }


            List<String> conditions = new ArrayList<>();
            
            // Create conditions for each filter
            String eidOp = gradeIDOperatorCombo.getValue();
            String eidVal = gradeIDChoose.getText().trim();
            if (!"Select".equals(eidOp) && !eidVal.isEmpty()) {
                conditions.add("gradeID " + convertOperator(eidOp) + " " + eidVal);
            }

            String sidOp = studentIDOperatorCombo.getValue();
            String sidVal = studentIDChoose.getText().trim();
            if (!"Select".equals(sidOp) && !sidVal.isEmpty()) {
                conditions.add("studentID " + convertOperator(sidOp) + " " + sidVal);
            }

            String cidOp = courseIDOperatorCombo.getValue();
            String cidVal = courseIDChoose.getText().trim();
            if (!"Select".equals(cidOp) && !cidVal.isEmpty()) {
                conditions.add("courseID " + convertOperator(cidOp) + " " + cidVal);
            }
            
            String gradeOp = gradeOperatorCombo.getValue();
            String gradeVal = gradeChoose.getText().trim();
            if (!"Select".equals(gradeOp) && !gradeVal.isEmpty()) {
                conditions.add("grade " + convertOperator(gradeOp) + " " + gradeVal);
            }

            String yearOp = semesterOperatorCombo.getValue();
            String yearVal = semesterChoose.getText().trim();
            if (!"Select".equals(yearOp) && !yearVal.isEmpty()) {
                conditions.add("semester " + convertOperator(yearOp) + " " + yearVal);
            }
            
            // Build condition string for query
            String conditionString = String.join(" AND ", conditions);
            
            // Query the DAO to get the filtered Grades
            List<Grade> validGrades = gradesDAO.searchGradeRecord(colsString, conditionString);
            validGrades.removeIf(enrollment -> enrollment.getGradeid() == -1); // Exclude invalid records
            
            // Set the filtered list to the ObservableList
            grades.setAll(validGrades);
            gradeIDCol.setVisible(colGradeID.isSelected());
            studentIDCol.setVisible(colStudentID.isSelected());
            courseIDCol.setVisible(colCourseID.isSelected());
            gradeCol.setVisible(colGrade.isSelected());
            semesterYearCol.setVisible(colSemester.isSelected());
            timeline.pause();
        });
        
        // --- Place Add, Delete, Update, Search Side by Side ---
        HBox formSection = new HBox(30, addBox, deleteBox, updateBox, searchBox);

        Button back = new Button("Back");
        back.setOnAction(e -> new MainMenuController(stage).show());

        layout.getChildren().addAll(formSection, table, back);
        stage.setScene(new Scene(layout, 1000, 400));
        
    }
    
    /**
     * shows the error to the user
     * @param message - message to tell user
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
}
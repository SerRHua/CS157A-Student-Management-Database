# Student Management System
This Student Management System manages students, courses, enrollments, and grades.
## Project Overview
A Student Management System tracks students, their enrollments, grades, and courses offered. By using a database, all the information will be organized into one place.
The purpose of this project is to improve administrative efficiency and reduce time and tasks needed to create the student information otherwise.
## Instructions for setting up project
Download the MySQL Installer:

### Go to the MySQL
Downloads page and download the MySQL Installer for Windows (choose the "MySQL Installer - Community" version).

### Run the Installer

Choose the Developer Default setup to install MySQL Server, Workbench, Shell, and other tools.

Click Next and proceed through the dependency check (the installer will fetch missing components if needed).

### Configure MySQL Server
Choose the default server configuration.
Set a root password (remember this—you’ll need it to log in).
Optionally, add additional MySQL user accounts. Set MySQL to run as a Windows service.

### Complete Installation
Click Execute to install and configure all selected components.
Step 2: Download MySQL JDBC Driver
Download the MySQL Connector/J from: https://www.mysql.com/products/connector/ Extract the .zip and locate the mysql-connector-java-<version>.jar file.

### If using terminal and have home-brew installed
Use the command “brew install mysql”
Then, use “mysql -u root -p” and press enter, because there is no password yet. For MySQL version < 8.0, use the command “UPDATE mysql.user SET Password = PASSWORD(‘password’) WHERE User = 'root'; FLUSH PRIVILEGES;” to set the password. If the version is >= 8.0, use the command “"FLUSH PRIVILEGES; ALTER USER 'root'@'localhost' IDENTIFIED BY ‘password’; FLUSH PRIVILEGES;”.

### For IntelliJ IDEA
File → Project Structure → Libraries
Click + → Java → Add the mysql-connector-java-xx.jar
Apply and OK
###For Eclipse
Right-click project → Build Path → Configure Build Path
Under Libraries, click Add External JARs
Choose the mysql-connector-java-xx.jar
1. The JavaFX used is 21.0.7, and it’s in sdk. It’s downloaded from https://openjfx.io/. In the project, go to “Build Path” > “Configure Build Path”, and click on “Module Path”. Then, click on “Add External Jars.”  Add all the JavaFX JARS, such as javafx.base.jar, javafx.controls.jar, javafx.fxml.jar, javafx.graphics.jar, javaafx.media.jar, javafx.swing.jar, javafx.web.jar, and javafx-swt.jar. In Classpath, add the mysql-connector-j-9.2.0.jar. You may need to remove any previously added JavaFX and MySQL connector JARs to avoid conflicts and ensure your project uses the correct versions you have.
2. In the controller package, click on Main.java, and then click on “Run As” > “Run Configurations.” Under the Arguments tab, input “--module-path /Users/serena/javafx-sdk-21.0.7/lib --add-modules javafx.controls,javafx.fxml” under the VM arguments. The “/Users/serena/javafx-sdk-21.0.7/lib” will have to be changed to your path to the lib folder of your installed JavaFX SDK on your system.
3. In the same Arguments tab, make sure to uncheck “Use the -XstartOnFirstThread argument when launching with SWT” and “Use the -XX:+ShowCodeDetailsInExceptionMessages argument when launching” so that the program will run.

4. Open the SQL file in MySQLWorkplace or a similar IDE and set username and password for the database
5. Use the initialize_data.sql file to add entries to the tables

6. In the /src/data_access_objects/MySQLConnection.java file, replace “root” and “password” from lines 13 and 15:
13	private static final String USER = "root";
15	private static final String PASSWORD = "password";
With own username and password
Make sure that the database name is correct "private static final String URL = "jdbc:mysql://localhost:3306/university_db";"
Change "university_db" to the name of your database.
7. Run the Main file in the controller package to begin using the application

## Dependencies and required software
MySQL and JavaFX was used. MySQL is used for making the database and the backend of the project. JavaFX is used for the frontend and graphical user interface. The JDBC is used to connect the frontend
and backend of the project.

## Any additional configuration steps needed to connect to the database
There are no additional steps. 

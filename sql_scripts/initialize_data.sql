-- Insert Students
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Jazmin", "Pitts", "2002-01-04"); -- studentID will be 1
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Braylen", "Connor", "2000-02-08"); -- studentID will be 2
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Jaden", "Molina", "1999-03-12"); -- studentID will be 3
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Aspyn", "Griffith", "2004-04-16"); -- studentID will be 4
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Henrik", "Hudson", "2006-05-20"); -- studentID will be 5
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Dalary", "Avila", "2000-06-24"); -- studentID will be 6
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Frank", "Carpenter", "2002-07-28"); -- studentID will be 7
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Davis", "Hendricks", "2002-08-04"); -- studentID will be 8
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Vera", "Johns", "2003-09-08"); -- studentID will be 9
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Reed", "Reeves", "2004-10-12"); -- studentID will be 10
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Ryan", "Wilson", "2002-11-16"); -- studentID will be 11
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Troy", "Hunt", "2002-12-20"); -- studentID will be 12
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Milena", "Parrish", "2002-01-24"); -- studentID will be 13
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Nehemiah", "Vo", "2002-02-04"); -- studentID will be 14
INSERT INTO Students (first_name, last_name, birthdate)
VALUES ("Saoirse", "Farrell", "2002-03-08"); -- studentID will be 15

-- Insert Courses
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Python", "Smith", "Spring"); -- courseID will be 1
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Java", "Smith", "Spring"); -- courseID will be 2
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("C++", "Lee", "Fall"); -- courseID will be 3
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("C", "Langston", "Fall"); -- courseID will be 4
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Discrete Math", "Brown", "Fall"); -- courseID will be 5
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Discrete Math", "Lee", "Spring"); -- courseID will be 6
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Database Management", "Langston", "Spring"); -- courseID will be 7
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Java", "Lee", "Fall"); -- courseID will be 8
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Discrete Math", "Brown", "Spring"); -- courseID will be 9
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Creative Writing", "Davis", "Fall"); -- courseID will be 10
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Creative Writing", "Davis", "Spring"); -- courseID will be 11
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("C", "Smith", "Spring"); -- courseID will be 12
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Linear Algebra", "Smith", "Spring"); -- courseID will be 13
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Science Fiction", "Davis", "Winter"); -- courseID will be 14
INSERT INTO Courses (course_name, instructor, semester)
VALUES ("Linear Algebra", "Smith", "Summer"); -- courseID will be 15

-- Insert Enrollments
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (5, 5, 2022); -- courseID = 5, studentID = 5
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (5, 6, 2020); -- courseID = 5, studentID = 6
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (6, 6, 2018); -- courseID = 6, studentID = 6
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (7, 10, 2019); -- courseID = 7, studentID = 10
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (8, 10, 2018); -- courseID = 8, studentID = 10
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (11, 12, 2013); -- courseID = 11, studentID = 12
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (6, 12, 2016); -- courseID = 6, studentID = 12
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (6, 13, 2018); -- courseID = 6, studentID = 13
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (7, 10, 2015); -- courseID = 7, studentID = 10
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (7, 11, 2017); -- courseID = 7, studentID = 11
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (11, 11, 2018); -- courseID = 11, studentID = 11
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (12, 11, 2018); -- courseID = 12, studentID = 11
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (13, 5, 2019); -- courseID = 13, studentID = 5
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (13, 6, 2015); -- courseID = 13, studentID = 6
INSERT INTO Enrollments (courseID, studentID, year)
VALUES (13, 7, 2020); -- courseID = 13, studentID = 7

-- Insert Grades
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (5, 5, 90, "Fall"); -- studentID = 5, courseID = 5
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (6, 5, 100, "Fall"); -- studentID = 6, courseID = 5
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (6, 6, 80, "Fall"); -- studentID = 6, courseID = 6
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (10, 7, 65, "Fall"); -- studentID = 10, courseID = 7
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (10, 8, 89, "Spring"); -- studentID = 10, courseID = 8
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (12, 11, 75, "Spring"); -- studentID = 12, courseID = 11
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (12, 6, 73, "Fall"); -- studentID = 12, courseID = 6
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (13, 6, 27, "Fall"); -- studentID = 13, courseID = 6
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (10, 7, 45, "Fall"); -- studentID = 10, courseID = 7
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (11, 7, 56, "Fall"); -- studentID = 11, courseID = 7
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (11, 11, 98, "Spring"); -- studentID = 11, courseID = 11
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (11, 12, 85, "Fall"); -- studentID = 11, courseID = 12
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (5, 13, 66, "Fall"); -- studentID = 5, courseID = 13
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (6, 13, 100, "Fall");
INSERT INTO Grades (studentID, courseID, grade, semester)
VALUES (7, 13, 95, "Fall");

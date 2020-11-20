# StudentAttendanceTracker
		Student Attendance Tracker

contributors:
 - Kamalraj D
 - Dharma durai V
 - Tharun viswa
 - Pawan Kumar
 - Harish J
 - Nikhil 

Source code:
 The source code is given in the form of IntelliJ project file. To read the code, download the file, and open it using an IDE (IntelliJ is preferred)
 or notepad. All the classes can be found in studentAttendaceTracker > src > com.company 

 (note: the opencsv module is used in the project, in case of using an IDE add it as a dependancy. In case of IntelliJ goto 
  project structure > modules and add the opencsv-**.jar in the module)

 Run the programme:
 1. call the main method in the Main class (studentAttendanceTracker>src>com.company>Main.java)
  	(or)
 2. open the studentAttendanceTracker.jar (in studentAttendanceTracker>out>artifacts>studentAttendanceTracker_jar>studentAttendanceTracker.jar)

-----------------------------------------------------------------------------------------------------------------------------------------------------

Code details:

Class FacultyFrame :

 UI details:

 - FacultyFrame creates a JFrame that contains a table to display the attendance details for the particular subject of the faculty in the center
 - In the west pane of the screen, it displays details of the user and text fields to edit the selected row in table and on clicking the 
   update button, the edited details will be updated to the data handler and the table
 - the button increase attendance adds one attendace to everyone, which reduced time
 - There are two menus in the menu bar
    - In profile log out option will terminate the window
    - In attedance additional operation on the attendance data on table will be provided viz. decrease attendance, increase late by 1,
      and decrease late by 1

 Code details:

 - Constructor FacultyFrame() takes String argument name to display the name in the west pane of the screen
 - readyFrame() calls the necessary functions to create the frame
 - setNorthPanel() sets the necessary JComboBox in northPane of the root panel
 - getFrameItems() returns a JPanel with necessary components for the west pane of the root panel
 - addMenu() sets the menu for the frame
 - initTable() takes a JTable parameteter and populates the table with necessary contents
 - refreshTable() repaints the table

-----------------------------------------------------------------------------------------------------------------------------------------------------

Class TutorFrame inherits FacultyFrame :
 
 UI details:
 
 - It contains all the components available for a faculty, and in addition gives a subject selector so that the user with tutor access
   can edit data of any subjects

 Code details:

 - setNorthPanel() is overridden to add a JComboBox which helps the user to select the subject

----------------------------------------------------------------------------------------------------------------------------------------------------

STUDENT PORTAL

Exceptions:
	
	UsernameAlreadyExistsException - thrown during signup when the user tries to register a username that already exists

	UsernameNotFoundException - thrown during login when the  user tries to login with a username that does not exist

	TypeMismatchException - thrown when the category of user(Student,Faculty,Tutor) does not match with the category registered

Classes:
	
(1) SignupControl :

	Constructor - takes username,password,rollnumber and category(student or faculty or tutor) as input

	setSubject(subject) - if the user is a faculty,this method gets the course handled by the faculty as input.If not, it assigns 'null' to the instance variable 'subject'

	storedata() - store the signup details in a csv file 'SignupDetails.csv'

(2) LoginControl:

	Constructor - takes username,password and category(student or faculty or tutor) as input

	signin() - *If the username and password match ,it returns rollnumber(if it's a student) or email(if it's a faculty or tutor).The roll number returned is used to create a student object and proceed to the student portal.

	*If the username and password doesn't match,it returns null.
	
	*If the category doesn't match, TypeMismatchException is thrown
	
	*If the username is not found,UsernameNotFoundException is thrown

----------------------------------------------------------------------------------------------------------------------------------------------

STUDENT PORTAL

Exceptions:
	
	RollNumberNotFoundException - thrown when the specified roll number is not found in the file
	
	SubjectNotFoundException - thrown when the specified subject is not found in the file

Classes:
	
(1)StudentAttendance:
	(For retrieveing data from the attendance file)
	
	Constructor - takes roll number of student as input
	
	getAttendance() - returns an integer array of attendance values of the student in all subjects
	
	getSubjects() - returns an array of all the subjects

	
(2)AttendanceData:
	(Used as an object by the Student class to store information regarding the attendance in a subject of a student)

	Constructor - takes the  number of 'present','late','excused' and 'unexcused' of the student(Student class)	in a subject(subject is also a parameter of the constructor)

	getPercentage() - returns percentage of attendance in the subject

	getData() - returns number of 'present' and 'absent' of the student in the subject

	getSubject() - returns the subject


(3)Student(subclass of StudentAttendance):
	
	(For processing the data retrieved from the file using the superclass methods,returns the attendnace details as AttendanceData objects)

	Constructor - takes roll number of student as input

	getName() - returns name of the student

	getAttendance(subject)- takes subject as input  and returns an AttendanceData object of attendance of the student in the subject
	(Uses getAttendance() to get the integer values)

	getTotalAttendance() - returns an array of AttendanceData objects consisiting of the attendance details of the student in all subjects
	(Uses getSubjects() to get all the subjects)


----------------------------------------------------------------------------------------------------------------------------------------------

Class Professor :

 - Professor() constructor takes two string s and fna for subject and filename and initializes the subjects needed
 - getAttendance() takes subject, filename and data and returns a AttendanceData
 - copy() is an utility function that helps to copy content from one file to another during updation
 - getsubjectattendance() returns a linkedlist of Student for a subject
 - modifyall() takes a int, subch, and choice and updates all the attendance with the int
 - modify() takes a int, choice and roll no and updates the value of that rollno

-----------------------------------------------------------------------------------------------------------------------------------------------



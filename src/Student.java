import java.util.ArrayList;
import java.io.Serializable;
import java.util.Scanner;

public class Student extends User implements IsStudent, Serializable{
	private String firstName;
	private String lastName;
	//The student username is their student ID as well, and will function as an ID
	private String userName;
	private String password;

	String letter;
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Student(String letter, String first_name, String last_name, ArrayList<Student> studentList, ArrayList<Course> courseList) {
		super(studentList, courseList);

		firstName = first_name;
		lastName = last_name;
		this.letter = letter;
		userName ="Student"+AdminStudList.size();
		password = "Password"+AdminStudList.size();
		if(!letter.equals("")) {
			displayStudMenu(letter, first_name, last_name);
		}
	}
	
	
	public void viewAllCoursesNotFull() {
		int i;
		int j;
		for(i=0;i<AdminCourseList.size();i++) {
			//check to see if the current number of students is the max
			if(Integer.parseInt(AdminCourseList.get(i).getNumStu()) < Integer.parseInt(AdminCourseList.get(i).getMaxStu())) {
				//print out the info of the courses that are not full
				System.out.println("");
				System.out.println("Course Name: "+AdminCourseList.get(i).getCourseName());
				//course ID
				System.out.println("Course ID: "+AdminCourseList.get(i).getCourseID());
				//max number of students allowed
				System.out.println("Maximum number of students allowed to register in the course: "+AdminCourseList.get(i).getMaxStu());
				//number of students currently registered 
				System.out.println("Number of students registered in the course: "+AdminCourseList.get(i).getNumStu());
				//course instructor
				System.out.println("Course Instructor: "+AdminCourseList.get(i).getCourseInstructor());
				//course section
				System.out.println("Course Section: "+AdminCourseList.get(i).getCourseSection());
				//course location
				System.out.println("Course Location: "+AdminCourseList.get(i).getCourseLoc());
			}
		}
		
	}
	
	public void viewAllCourses() {
		//students can't see other students in the class
		int i;
		for(i=0;i<AdminCourseList.size();i++) {
			System.out.println("");
			System.out.println("Course Name: "+AdminCourseList.get(i).getCourseName());
			System.out.println("");
			System.out.println("Number of students currently registered in the course: "+AdminCourseList.get(i).getNumStu());
			System.out.println("Maximum number of students allowed in the course: "+AdminCourseList.get(i).getMaxStu());
			System.out.println("Course Location: "+AdminCourseList.get(i).getCourseLoc());
		} 
	}
	
	
	public void viewCoursesForAStud(String first_name, String last_name) {
		boolean check = false;
		int i;
		int j;
		for(i=0;i<AdminCourseList.size();i++) {
			ArrayList<Student> regName = AdminCourseList.get(i).getListOfNames();
			for(j=0;j<regName.size();j++) {
				String fName = regName.get(j).getFirstName();
				String lName = regName.get(j).getLastName();
				if(fName.equalsIgnoreCase(first_name)) {
					if(lName.equals(last_name)) {
						//the student doesn't need the same info as the admin does in the user method because they don't care what the maximum number or what the current number of students is because they are already in the course
						System.out.println(AdminCourseList.get(i).getCourseName());
						System.out.println("Course Section: "+AdminCourseList.get(i).getCourseSection());
						System.out.println("Location of the course: "+AdminCourseList.get(i).getCourseLoc());
						check = true;
					}
				}
			}				
			
		}
		if(check == false) {
			System.out.println("");
			System.out.println("You are not registered for any courses.");
		}

		
	}
	
	//create the student menu
	public void displayStudMenu(String stuLetter, String stuFirstName, String stuLastName) {
		//create a scanner
		Scanner input10 = new Scanner(System.in);
		switch(stuLetter) {
		case("V"):
			//view all the courses
			viewAllCourses();
			return;
		case("F"):
			//view all the courses that are still open
			viewAllCoursesNotFull();
		case("R"):
			//register for a course
			System.out.println("");
			System.out.println("What is the name of the course would you like to register for?");
			String courseName = input10.nextLine();
			System.out.println("What is the section number of the course would you like to register for?");
			String courseSection = input10.nextLine();
			int studentCourse = searchCourse(courseName, courseSection);
			if(studentCourse == -1) {
				System.out.println("Error. This is not a course.");
			}
			else {
			addStudentToCourse(stuFirstName, stuLastName, studentCourse);
			}
			return;
		case("W"):
			//withdraw from a course
			System.out.println("What is the name of the course would you like to withdraw from?");
			String dCourseName = input10.nextLine();
			System.out.println("What is the section number of the course would you like to withdraw from?");
			String dCourseSection = input10.nextLine();
			int dStudentCourse = searchCourse(dCourseName, dCourseSection);
			deleteStudentFromCourse(stuFirstName, stuLastName, dStudentCourse);
			return;
		case("C"):
			//view the courses you are registered in
			viewCoursesForAStud(stuFirstName, stuLastName);
			return;
		case("E"):
			//exit
			break;
		default:
			System.out.println("Error, invalid input. Enter: V, R, W, C, or E");
			return;
		}
		
	}
	
	

	
	
	
	
	
	
	
}

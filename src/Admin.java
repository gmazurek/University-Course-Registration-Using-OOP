import java.io.BufferedWriter;
import java.io.File;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Admin extends User implements IsAdmin, Serializable{
	
	
	//create Admin Username and Password
	String Username;
	String Password;
	
	
	public Admin(String adUsername, String adPassword) {
		Username = adUsername;
		Password = adPassword;
	}
	
	public Admin(ArrayList<Student> studentList, ArrayList<Course> courseList) {
		super(studentList, courseList);
		
	}
	
	
	
	public void createCourse() {
		//Create a scanner
		Scanner input2 = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter the course name: ");
		String adCourseName = input2.nextLine();
		System.out.println("Enter the course ID: ");
		String adCourseID= input2.nextLine();
		//You trust the administrator to enter a number and not a string
		System.out.println("Enter the maximum number of students: ");
		String adMaxStu= input2.nextLine();
		//didn't ask for the current number because they are creating the class, so it it assumed to be 0
		String adNumStu= "0";
		//didn't ask for the list of names because there are currently 0 people in this course
		System.out.println("Enter the course instructor: ");
		String adCoIn= input2.nextLine();
		System.out.println("Enter the course section: ");
		String adCoSec= input2.nextLine();
		System.out.println("Enter the course location: ");
		String adCoLoc= input2.nextLine();
		//Don't need to check if that course exists because administrators are very cautious about creating new courses and sections. It's very expensive.
		Course newObj = new Course(adCourseName, adCourseID, adMaxStu, adNumStu, new ArrayList<Student>(), adCoIn, adCoSec, adCoLoc);	
		AdminCourseList.add(newObj);
		

	}
	public void deleteCourse(){
		//Create a scanner
		Scanner input3 = new Scanner(System.in);
		//get input of which class to delete
		System.out.println("");
		System.out.println("The name of the course you would like to delete: ");
		String delCoName = input3.nextLine();
		System.out.println("The section of the course you would like to delete: ");
		String delCoSec = input3.nextLine();
		//search for the class to delete in the course list array
		int delCo = searchCourse(delCoName, delCoSec);
		if(delCo > -1) {
			AdminCourseList.remove(delCo);
		}
		else {
			System.out.println("");
			System.out.println("Error. Must enter a valid course name and section.");
		}
		
		
	}
	
	
	
	public void editCourse(){
		//Create a scanner
		Scanner input4 = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter the name of the course you would like to edit: ");
		String edCoName = input4.nextLine();
		System.out.println("Enter the section of the course you would like to edit: ");
		String edCoSec = input4.nextLine();
		//search for the class to edit the course
		int edCo = searchCourse(edCoName, edCoSec);
		if(edCo > -1) {
			//create the variable for the User's choice as to how they want to edit the course
			String userChoice="";
			while(!userChoice.equals("Q")) {
				System.out.println("If you would like to change the maximum number of students, press M.");
				System.out.println("If you would like to take a student out of the course, press D.");
				System.out.println("If you would like to add a student to this course, press A.");
				System.out.println("If you would like to change the course instructor, press C.");
				System.out.println("If you would like to change the course section, press S.");
				System.out.println("If you would like to change the course location, press L.");
				System.out.println("If you would like to stop editing this class, press Q.");
				userChoice = input4.nextLine().toUpperCase();
				
				String yonum;
				String yoDelStuf;
				String yoDelStul;
				String yoAddStuf;
				String yoAddStul;
				String yoInstr;
				String yoSec;
				String yoLoc;
				
				switch(userChoice) {
				case("M"):
					//get the user input for the max number
					System.out.println("");
					System.out.println("Enter the number that you like to set as the maximum number of students for this course? ");
					yonum = input4.nextLine();
					changeMax(edCo, yonum);
					continue;
				case("D"):
					System.out.println("");
					//get the name of the student you'de like to delete from  a course
					System.out.println("Enter the first name of the student you would like to delete from this course: ");
					yoDelStuf = input4.nextLine();
					System.out.println("Enter the last name of the student you would like to delete from this course: ");
					yoDelStul = input4.nextLine();
					//need to search student list
					deleteStudentFromCourse(yoDelStuf, yoDelStul,edCo);
					continue;
				case("A"):
					//get the name of the student they would like to add
					System.out.println("");
					System.out.println("Enter the first name of the student you would like to add to this course: ");
					yoAddStuf = input4.nextLine();
					System.out.println("Enter the last name of the student you would like to add to this course: ");
					yoAddStul = input4.nextLine();
					addStudentToCourse(yoAddStuf, yoAddStul, edCo);
					continue;
				case("C"):
					//get the name of the new instructor
					System.out.println("");
					System.out.println("Enter the name of the person you would like to set as the instructor for this course: ");
					yoInstr = input4.nextLine();
					AdminCourseList.get(edCo).setCourseInstructor(yoInstr);
					continue;
				case("S"):
					//get the new section number
					System.out.println("");
					System.out.println("Enter section you would like to set as the section for this course: ");
					yoSec = input4.nextLine();
					AdminCourseList.get(edCo).setCourseSection(yoSec);
					continue;
				case("L"):
					//get the new location
					System.out.println("");
					System.out.println("Enter the location you would like to set for for this course: ");
					yoLoc = input4.nextLine();
					AdminCourseList.get(edCo).setCourseLoc(yoLoc);
					continue;
				case("Q"):
					//quit
					return;
				default:
					System.out.println("");
					System.out.println("Error, invalid input. Enter: M, D, A, C,S, L, or Q");
					continue;
				
			
				}
			}
		}
		else {
			System.out.println("");
			System.out.println("Error. The Course name or section number were incorrect.");
		}
		
			
		
	}
	
	
	
	public void registerStudent(){
		//Create a scanner
		Scanner input5 = new Scanner(System.in);
		//I specifically chose not to data validate to see if their name came up again because I believe that the administrator is not careless with who they enroll in school after acception, opposed to in class where people change around schedules frequently
		System.out.println("");
		System.out.println("Please enter student first name");
		String first_name = input5.nextLine();
		System.out.println("Please enter student last name");
		String last_name = input5.nextLine();
		String letter = "";
		Student a = new Student(letter, first_name, last_name, AdminStudList, AdminCourseList);
		AdminStudList.add(a);
		
	}
	
	public void displayInforForACourse() {
		int i;
		int j;
		//Create a scanner
		Scanner input6 = new Scanner(System.in);
		System.out.println("");
		System.out.print("Enter the course ID to see the course's information: ");
		String courseID = input6.nextLine();
		for(i =0; i<AdminCourseList.size(); i++) {
			String id = AdminCourseList.get(i).getCourseID();
			if(id.equals(courseID)) {	
				System.out.println("");
				System.out.println("Course Name:"+AdminCourseList.get(i).getCourseName());
				System.out.println("Course ID:"+AdminCourseList.get(i).getCourseID());
				System.out.println("Maximum number of students:"+AdminCourseList.get(i).getMaxStu());
				System.out.println("Number of students in the course:"+AdminCourseList.get(i).getNumStu());
				System.out.println("Course Instructor:"+AdminCourseList.get(i).getCourseInstructor());
				System.out.println("Course section:"+AdminCourseList.get(i).getCourseSection());
				System.out.println("Course Location:"+AdminCourseList.get(i).getCourseLoc());
				ArrayList<Student>tName = AdminCourseList.get(i).getListOfNames();
				if(tName.size() > 0) {
					System.out.println("Student names and IDs:");
					for(j=0;j<tName.size();j++) {
						System.out.println(tName.get(j).getFirstName()+" "+tName.get(j).getLastName()+" "+tName.get(j).getUserName());
					}
				}
				else {
					System.out.println("");
					System.out.println("No students are registered in this class.");
				}
				break;
				
			}
			
		}
		
	}
	

	
	
	

	
	public static void writeToFileFullCourses() {
		String fiName = "FullCourses.txt";
		Scanner input7 = new Scanner(System.in);
		
		try{
			File f = new File(fiName);
			//f.delete();
			FileWriter fileWriter = new FileWriter(f);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			//String text = input7.nextLine();
			//bufferedWriter.write(text);
			
			
			//write once full course's information per line
			bufferedWriter.write(viewAllFullCourses());
			bufferedWriter.newLine();
				
			
			System.out.println("");
			System.out.println("A file has been written containing all of the full courses.");
			//Always close writer
			bufferedWriter.close();
		}

		
		catch (IOException exk) {
			System.out.println("");
			System.out.println( "Error writing file '" + fiName + "'");
			exk.printStackTrace();
		}
		
	

		
}


	public void viewStudsRegForACourse() {
		int i;
		//Create a scanner
		Scanner input8 = new Scanner(System.in);
		System.out.println("");
		System.out.print("Enter the name of the course: ");
		String CoName = input8.nextLine();
		System.out.print("Enter the section of the course: ");
		String CoSec = input8.nextLine();
		//search for the class to edit the course
		int Co = searchCourse(CoName, CoSec);
		if(Co> -1) {
			ArrayList<Student> cName = AdminCourseList.get(Co).getListOfNames();
			if(cName.size() > 0) {
				System.out.println("");
				System.out.println("The Students registered for this course:");
				for(i=0;i<cName.size();i++) {
					System.out.println(cName.get(i).getFirstName()+" "+cName.get(i).getLastName());
				}
			}
			else {
				System.out.println("");
				System.out.println("There are no students registered for this course");
			}
		}
		else {
			System.out.println("");
			System.out.println("Error. The name or section of this course entered was invalid.");
		}
	}
	
	
	public void sort() {
		int j;
		//create an arrayList of sorted courses
		ArrayList<String> SortedCoursesString = new ArrayList<>();
		ArrayList<String>SortedNames = new ArrayList<>();
		
		for(j=0;j<AdminCourseList.size();j++) {
			SortedCoursesString.add(AdminCourseList.get(j).getNumStu());
			SortedNames.add(AdminCourseList.get(j).getCourseName());
		}
		//sort it in ascending order
		Collections.sort(SortedCoursesString);
		int a;
		int i;
		for(i=0;i<SortedCoursesString.size();i++) {
			for(j=0;j<SortedCoursesString.size();j++) {
				if(SortedCoursesString.get(i).equals(AdminCourseList.get(j).getNumStu())) {
					for(a=0;a<SortedNames.size();a++) {
						if(AdminCourseList.get(j).getCourseName().equals(SortedNames.get(a))) {
							System.out.println(SortedNames.get(a)+": "+SortedCoursesString.get(i));	
							SortedNames.remove(a);
						}
							
					}
						
				}
				
			}	
		}
	}


	public void displayAdMenu(String letter) {
		//Create a scanner
		Scanner input9 = new Scanner(System.in);
		//create the admin menu
		switch(letter) {
		case("C"):
			createCourse();
			return;
		case("D"):
			deleteCourse();
			return;
		case("T"):
			editCourse();
			return;
		case("I"):
			displayInforForACourse();
			return;
		case("R"):
			registerStudent();
			return;
		case("V"):
			viewAllCourses();
			return;
		case("F"):
			System.out.println(viewAllFullCourses());
			return;
		case("W"):
			writeToFileFullCourses();
			return;
		case("N"):
			viewStudsRegForACourse();
			return;
		case("L"):
			System.out.println("");
			System.out.println("Enter the student's first name. ");
			String first_name = input9.nextLine();
			System.out.println("Enter the student's last name. ");
			String last_name =input9.nextLine();
			viewCoursesForAStud(first_name, last_name);
			return;
		case("S"):
			sort();
			return;
		case("Q"):
			break;
		default:
			System.out.println("");
			System.out.println("Error, invalid input. Enter: C, D, T, I, R, V, F, W, N, L, S, or Q");
			return;
		
		}
		
	}
	


}

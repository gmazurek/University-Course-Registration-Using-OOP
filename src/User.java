import java.util.ArrayList;
import java.io.Serializable;

public class User extends HW1 implements Serializable{
	
	static ArrayList<Student> AdminStudList = new ArrayList<Student>();
	static ArrayList<Course> AdminCourseList = new ArrayList<Course>();
	
	
	
	public User(){
		
	}
	

	
	public User(ArrayList<Student> studentList, ArrayList<Course> courseList) {
		AdminStudList = studentList;
		AdminCourseList = courseList;
		
	}
	
	

	

	public int searchCourse(String courseName, String courseSection) {
		//search courses
		int dele = -1;
		for(int i =0; i<AdminCourseList.size(); i++) {
			String sec = AdminCourseList.get(i).getCourseSection();
			String name = AdminCourseList.get(i).getCourseName();
			if(sec.equals(courseSection)) {
				if(name.equals(courseName)) {
				dele = i;	
				}	
			}
		}
		return dele;
	}
	
	
	
	
	//check if a course is at maximum
	public void changeMax(int edCo, String yonum) {
		String currnum = AdminCourseList.get(edCo).getNumStu();
		//see if the class is at max already
		if(Integer.parseInt(yonum) > Integer.parseInt(currnum)) {
			AdminCourseList.get(edCo).setMaxStu(yonum);
		}
		else {
			System.out.println("You cannot make the maximum less because the class is full.");
		}
	}
	
	
	
	public static String viewAllFullCourses() {
		Boolean fullCourse = false;
		int i;
		int j;
		
		String result = "";
		for(i=0;i<AdminCourseList.size();i++) {
			
			//check to see if the current number of students is the max
			if(AdminCourseList.get(i).getNumStu().equals(AdminCourseList.get(i).getMaxStu())) {
				//add the course to the full course ArrayList
				fullCourse = true;
				//get out the info of the full course
				result+="Course Name: "+AdminCourseList.get(i).getCourseName()+", ";
				result+="Student names and IDs: ";
				ArrayList<Student> tName = AdminCourseList.get(i).getListOfNames();
				for(j=0;j<tName.size();j++) {
					result+=tName.get(j).getFirstName()+" "+tName.get(j).getLastName()+" "+tName.get(j).getUserName();
					if(tName.size() > 1) {
						result+=" ** ";
					}
				}
				result+=" Number of students registered in the course: "+AdminCourseList.get(i).getNumStu()+"\n";
				
			}

		}
		if(!fullCourse) {
			result = "There are no full courses.";
			
		}
		return result;
	}
	
	
	//delete a student from a course
		public void deleteStudentFromCourse(String yoDelStuf, String yoDelStul, int edCo) {
			int i;
			boolean check = false;
			ArrayList<Student> tName = AdminCourseList.get(edCo).getListOfNames();
			
			for(i=0; i<tName.size(); i++) {
				String fName = tName.get(i).getFirstName();
				String lName = tName.get(i).getLastName();
				if(fName.equals(yoDelStuf)) {
					if(lName.equals(yoDelStul)) {
						tName.remove(i);
						//lower the number of students in the class
						int tempNum = Integer.parseInt(AdminCourseList.get(edCo).getNumStu()) ;
						int tempNum2 = tempNum -1;
						String newNum= Integer.toString(tempNum2);
						AdminCourseList.get(edCo).setNumStu(newNum);
						check = true;
						break;
					}	
				}
			}
			if(check == false) {
				System.out.println(yoDelStuf+" "+yoDelStul+" is not in this class.");
			}
		}	
		
		//add a student to a course

		public void addStudentToCourse(String yoAddStuf, String yoAddStul, int edCo) {
			int j;
			//I'm not checking if the student is already registered in the school, because I assume that the administrator would know if this person is a registered student or not
			//But, the administrator might not know if the student has registered for this course or not, so its plausible that there needs to be a checker here to make sure that they are not already in the course
			ArrayList<Student> tName = AdminCourseList.get(edCo).getListOfNames();
			//Check if they are already registered in this course
			for(j=0; j< tName.size(); j++) {
				String fName = tName.get(j).getFirstName();
				String lName = tName.get(j).getLastName();
				if(fName.equals(yoAddStuf)) {
					if(lName.equals(yoAddStul)) {
					System.out.println("This person is already registered in this course.");
					break;
					}
				}
			}
			//check if the class is full
			String maxnum = AdminCourseList.get(edCo).getMaxStu();
			if(tName.size() == Integer.parseInt(maxnum)){
				System.out.println("This class is full. Another student cannot be added.");
			}
			else {
				//Actually add the student to the course
				for(j=0;j<AdminStudList.size();j++) {
					String fNom = AdminStudList.get(j).getFirstName();
					String lNom = AdminStudList.get(j).getLastName();
					if(fNom.equals(yoAddStuf)) {
						if(lNom.equals(yoAddStul)) {
							Student G = AdminStudList.get(j);
							//add their name to the list of students
							tName.add(G);
							//add to the number of students
							int tempNum = Integer.parseInt(AdminCourseList.get(edCo).getNumStu()) ;
							int tempNum2 = tempNum +1;
							String newNum= Integer.toString(tempNum2);
							AdminCourseList.get(edCo).setNumStu(newNum);
						}
					}	
				}
					
			}
		}	
		
		public void viewAllCourses() {
			int i;
			int j;
			for(i=0;i<AdminCourseList.size();i++) {
				System.out.println("");
				System.out.println("Course Name: "+AdminCourseList.get(i).getCourseName());
				System.out.println("");
				ArrayList<Student> tName = AdminCourseList.get(i).getListOfNames();
				//check to see if there are any people registered
				if(AdminCourseList.get(i).getNumStu().equals("0")) {
	
				}
				else {
					System.out.println("Student names and IDs:");
					for(j=0;j<tName.size();j++) {
						System.out.println(tName.get(j).getFirstName()+" "+tName.get(j).getLastName()+" "+tName.get(j).getUserName());
					}
				}
				
				System.out.println("Number of students currently registered in the course: "+AdminCourseList.get(i).getNumStu());
				System.out.println("Maximum number of students allowed in the course: "+AdminCourseList.get(i).getMaxStu());
				System.out.println("Course Location: "+AdminCourseList.get(i).getCourseLoc());
			} 
		}
	
		public void viewCoursesForAStud(String first_name, String last_name) {
			boolean check = false;
			int i;
			int j;
			System.out.print(first_name+""+last_name+"'s courses: ");
			for(i=0;i<AdminCourseList.size();i++) {
				ArrayList<Student> regName = AdminCourseList.get(i).getListOfNames();
				for(j=0;j<regName.size();j++) {
					String fName = regName.get(j).getFirstName();
					String lName = regName.get(j).getLastName();
					if(fName.equalsIgnoreCase(first_name)) {
						if(lName.equals(last_name)) {
							//The admin wants to see the course name and course section so they can referance it if they's like, and would like to see the max number and current number to gage different info regarding adding and deleting students
							System.out.println(AdminCourseList.get(i).getCourseName()+" Section"+AdminCourseList.get(i).getCourseSection());
							System.out.println("Number of students currently registered in the course: "+AdminCourseList.get(i).getNumStu());
							System.out.println("Maximum number of students allowed in the course: "+AdminCourseList.get(i).getMaxStu());
							check = true;
						}
					}
				}				
				
			}
			if(check == false) {
				System.out.println("Student is not registered for any courses.");
			}

			
		}
		
		
		
}

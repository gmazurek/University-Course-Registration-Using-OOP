import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable{
	private String courseName;
	private String courseID;
	private String courseLoc;
	private String maxStu;
	private String numStu;
	private ArrayList<Student> listOfNames;
	private String courseInstructor;
	private String courseSection;
	
	
	//constructor that takes 8 parameters
	public Course(String courseName, String courseID, String maxStu, String numStu, ArrayList<Student> listOfNames, String courseInstructor, String courseSection, String courseLoc) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.maxStu = maxStu;
		this.numStu = numStu;
		this.listOfNames = listOfNames;
		this.courseInstructor = courseInstructor;
		this.courseSection = courseSection;
		this.courseLoc = courseLoc;
	}
	
	//getters and setters for the course variables
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseLoc() {
		return courseLoc;
	}
	public void setCourseLoc(String courseLoc) {
		this.courseLoc = courseLoc;
	}
	public String getMaxStu() {
		return maxStu;
	}
	public void setMaxStu(String maxStu) {
		this.maxStu = maxStu;
	}
	public String getNumStu() {
		return numStu;
	}
	public void setNumStu(String numStu) {
		this.numStu = numStu;
	}
	public ArrayList<Student> getListOfNames() {
		return listOfNames;
	}
	public void setListOfNames(ArrayList<Student> listOfNames) {
		this.listOfNames = listOfNames;
	}
	public String getCourseInstructor() {
		return courseInstructor;
	}
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	public String getCourseSection() {
		return courseSection;
	}
	public void setCourseSection(String courseSection) {
		this.courseSection = courseSection;
	}
	
		
}
	


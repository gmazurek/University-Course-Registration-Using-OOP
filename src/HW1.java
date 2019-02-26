import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class HW1 implements Serializable{

	public static void main(String[] args) throws IOException{
		
		//create an ArrayList to hold the courses
		ArrayList<Course>courseList;
		ArrayList<Student>studentList;
		
		
		//deserialize
		
		File File1 = new File("Admin1.ser");
		
		File File2 = new File("Admin2.ser");
		
		
		
		
		courseList = new ArrayList<Course>();
		
		studentList = new ArrayList<Student>();
		
		
		
		if(File1.exists() & File2.exists()) {
			
			
			courseList = new ArrayList<Course>();
			
			studentList = new ArrayList<Student>();
			
			
			
			
			//for file 1
			 try{
				  //FileInputSystem recieves bytes from a file
			      FileInputStream fis1 = new FileInputStream("Admin1.ser");
			      
			      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
			      ObjectInputStream ois1 = new ObjectInputStream(fis1);
			      
			      //Cast as a course. readObject will take the object from ObjectInputStream
			      courseList = (ArrayList<Course>)ois1.readObject();
			      ois1.close();
			      fis1.close();
			    }
			    catch(IOException ioe) {
			       ioe.printStackTrace();
			       return;
			    }
			 catch(ClassNotFoundException cnfe) {
			       cnfe.printStackTrace();
			       return;
			     }
			 //for file 2
			 try{
			      FileInputStream fis2 = new FileInputStream("Admin2.ser");
			      
			      ObjectInputStream ois2 = new ObjectInputStream(fis2);

			      studentList = (ArrayList<Student>)ois2.readObject();
			      ois2.close();
			      fis2.close();
			    }
			    catch(IOException ioe) {
			       ioe.printStackTrace();
			       return;
			    }
			 catch(ClassNotFoundException cnfe) {
			       cnfe.printStackTrace();
			       return;
			     }
			 
		}	
		else {
			//read universitycourses into file
			String fileName = "MyUniversityCourses.csv";

			//References one line at a time
			String line = null;
			try{

				//instantiate the file reader class
				FileReader fileReader = new FileReader(fileName);

				//create bufferedReader to wrap fileReader
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				//create counter for splitting the info
				int counter = 0;
				
				//read an d print out lines of text from file
				while((line = bufferedReader.readLine()) != null) {
					String sepFile[] = line.split(",");
					if(counter > 0) {
						//create new object in Array list course list
						Course newObj = new Course(sepFile[0],sepFile[1], sepFile[2], sepFile[3], new ArrayList<Student>(), sepFile[5], sepFile[6], sepFile[7]);	
						courseList.add(newObj);
					}
					counter++;
				}

				//close the file
				bufferedReader.close();
			}
			//create a catch block in case the file is not found
			catch(FileNotFoundException ex){
				System.out.println( "Unable to open file '" + fileName + "'");
				//the printStackTrace method will print out an error output stream ("What went wrong?" report);

				ex.printStackTrace();
			}

			catch (IOException ex) {
				System.out.println( "Error reading file '" + fileName + "'");
				ex.printStackTrace();
			}
			
			
		}
		
		
		
					
			

		
		
		

		//create the Admin
		Admin TheAdmin = new Admin(studentList, courseList);
		//need to login
		String ok = "no";
		//create scanner
		Scanner input = new Scanner(System.in);
		while(ok == "no") {
			System.out.println("");
			System.out.println("Login");
			System.out.print("Enter A if you are an Administrator. Enter S if you are a Student.");
			String identifier = input.nextLine().toUpperCase();
			if(identifier.equals("A")) {
				System.out.print("Admin Username: ");
				String adUsername = input.nextLine();
				if(!adUsername.equals("Admin")) {
					System.out.println("Incorrect Username.");
					
				}
				else {
					System.out.print("Admin Password: ");
					String adPassword = input.nextLine();
					if(!adPassword.equals("Admin001")) {
						System.out.println("Incorrect Password.");
					}
					else {

						//display the admin menu
						String letter = "";
						while(!letter.equalsIgnoreCase("Q")){
							System.out.println("");
							System.out.println("Admin Menu:");
							System.out.println("Course Management");
							System.out.println("Enter C if you would like to create a new course. ");
							System.out.println("Enter D if you would like to delete a course. ");
							System.out.println("Enter T if you would like to edit a course. ");
							System.out.println("Enter I if you would like to display information for a course. ");
							System.out.println("Enter R if you would like to register a student. ");
							System.out.println("");
							System.out.println("Reports");
							System.out.println("Enter V if you would like view all courses. ");
							System.out.println("Enter F if you would like view all courses that are full. ");
							System.out.println("Enter W if you would like to write to a file the list of courses that are full. ");
							System.out.println("Enter N if you would like to view the names of the students that are registered in a specific course. ");
							System.out.println("Enter L if you would like view the list of courses that a given student is registered in. ");
							System.out.println("Enter S if you would like to sort the list of courses based on the number of students registered. ");
							System.out.print("Enter Q if you would like to Exit this menu. ");
							letter =input.nextLine().toUpperCase();
							//create an instance of Admin and pass in displayAdminMenu
							TheAdmin.displayAdMenu(letter);
							

						
						}	
					
						//serialization
						//for file 1
						try {
							//FileOutput Stream writes data to a file
							FileOutputStream fos1 = new FileOutputStream("Admin1.ser");
							
							//ObjectOutputStream writes objects to a stream (A sequence of data)
							ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
							
							//Writes the specific object to the OOS
							oos1.writeObject(courseList);
							
							//Close both streams
							oos1.close();
							fos1.close();

						} 
						catch (IOException ioe) {
							ioe.printStackTrace();
						}
						
						//for file 2
						try {							
							FileOutputStream fos2 = new FileOutputStream("Admin2.ser");															
							ObjectOutputStream oos2 = new ObjectOutputStream(fos2);																
							oos2.writeObject(studentList);															
							oos2.close();
							fos2.close();
						} 
						catch (IOException ioe) {
							ioe.printStackTrace();
						}
						
						
						ok ="yes";
								
					}
				}
			}
			//Display menu for student
			if(identifier.equals("S")) {
				if(!studentList.equals(null)) {
					System.out.print("Student Username: ");
					String studentUsername = input.nextLine();
					System.out.print("Student Password: ");
					String studentPassword = input.nextLine();
					int j;
					//check to see that this student is already registered in the school system
					//Check if it finds the student or not
					boolean findStud = false;
					for(j=0;j<studentList.size();j++) {
						String uNom = studentList.get(j).getUserName();
						String pNom = studentList.get(j).getPassword();
						if(uNom.equals(studentUsername)) {
							if(pNom.equals(studentPassword)) {
								findStud = true;
								String stuFirstName = studentList.get(j).getFirstName();
								String stuLastName = studentList.get(j).getLastName();
								String stuLetter= "";
								while(!stuLetter.equals("E")){
									System.out.println("");
									System.out.println("Student Menu: ");
									System.out.println("Enter V to view all courses. ");
									System.out.println("Enter F to view all courses that are not full.");
									System.out.println("Enter R to register in a course. ");
									System.out.println("Enter W to withdraw from a course. ");
									System.out.println("Enter C to view all the courses that the current student is registered in. ");
									System.out.print("Enter E to exit the program. ");
									stuLetter = input.nextLine().toUpperCase();
									//create an instance of Student and pass in displayStudentMenu
									
									studentList.get(j).displayStudMenu(stuLetter, stuFirstName, stuLastName);
									
								}
								//serialization
								//for file 1
								try {
									//FileOutput Stream writes data to a file
									FileOutputStream fos1 = new FileOutputStream("Admin1.ser");
									
									//ObjectOutputStream writes objects to a stream (A sequence of data)
									ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
									
									//Writes the specific object to the OOS
									oos1.writeObject(courseList);
									
									//Close both streams
									oos1.close();
									fos1.close();
		
								} 
								catch (IOException ioe) {
									ioe.printStackTrace();
								}
								
								//for file 2
								try {							
									FileOutputStream fos2 = new FileOutputStream("Admin2.ser");															
									ObjectOutputStream oos2 = new ObjectOutputStream(fos2);																
									oos2.writeObject(studentList);															
									oos2.close();
									fos2.close();
								} 
								catch (IOException ioe) {
									ioe.printStackTrace();
								}
								
								
								//look back on this
								ok = "yes";
								
							}
							else {
								System.out.println("Incorrect password.");
							}
						}
						
					}
					if(!findStud) {
						System.out.println("You are not a registered Student");
					}
				
				}
				else {
					System.out.println("You are not a registered student.");
				}
				
			}
			
				
			
			
		}	
		
		
		
		
		
		
		
		
	}
	
		
		

}



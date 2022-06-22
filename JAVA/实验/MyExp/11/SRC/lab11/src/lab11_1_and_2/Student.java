package lab11_1_and_2;

//Fig. 15.9: Account.java
//Account class for storing records as objects.
public class Student 
{
	private String studentNumber;
	private String studentName;
	private String studentPhoneNumber;
	private String studentEmail;
	
	// initializes an Account with default values
	public Student() 
	{
		this("", "", "", "");
	} 
	
	// initializes an Account with provided values
	public Student(String studentNumber, String studentName, String studentPhoneNumber, String studentEmail) 
	{
	   this.studentNumber = studentNumber;
	   this.studentName = studentName;
	   this.studentPhoneNumber = studentPhoneNumber;
	   this.studentEmail = studentEmail;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	
} 

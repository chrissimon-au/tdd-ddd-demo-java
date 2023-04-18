package au.chrissimon.universityapi.Students;

public class RegisterStudentRequest	{
	private String name;
	
	RegisterStudentRequest() {}

	public RegisterStudentRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
	}
}
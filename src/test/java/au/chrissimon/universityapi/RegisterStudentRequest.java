package au.chrissimon.universityapi;

public class RegisterStudentRequest	{
	private String name;
	
	RegisterStudentRequest() {}

	RegisterStudentRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
	}
}
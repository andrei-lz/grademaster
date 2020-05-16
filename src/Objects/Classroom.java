package Objects;

import java.util.LinkedList;

public class Classroom {
	
	private String name;
	private LinkedList<Student> students;
	
	public Classroom(String name, LinkedList<Student> students) {
		super();
		this.name = name;
		this.students = students;
	}
	
	public void addStudent(Student s) {
		students.add(s);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Student> getStudents() {
		return students;
	}

	public void setStudents(LinkedList<Student> students) {
		this.students = students;
	}
	
}

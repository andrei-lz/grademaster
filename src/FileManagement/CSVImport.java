package FileManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.sound.midi.Synthesizer;

import GUI.ClassCardLayoutPane;
import GUI.GradeBoundariesPane;
import Objects.Classroom;
import Objects.Student;

public class CSVImport {

	private static String[] headings;
	private static LinkedList<Classroom> classes;
	private LinkedList<Student> students;
	private static String filename;

	public CSVImport(String filename) {
		this.filename = filename;
		classes = new LinkedList<Classroom>();
		students = new LinkedList<Student>();

		try {
			BufferedReader br = new BufferedReader(new FileReader("res/" + filename));

			String line = br.readLine();

			String[] temp = line.split(",");
			headings = new String[temp.length-1];
			for (int i = 0; i < headings.length; i++) {
				headings[i] = temp[i+1];
			}

			//headings = line.split(",");

			while (line != null) {
				line = br.readLine();
				if(line != null) {
					String[] temparr = line.split(",");
					
					students.add(new Student(temparr[0], temparr[1], temparr[2], Integer.parseInt(temparr[3]), Integer.parseInt(temparr[4]), Integer.parseInt(temparr[5]), Integer.parseInt(temparr[6]), Integer.parseInt(temparr[7]), Integer.parseInt(temparr[8]), Integer.parseInt(temparr[9]), Integer.parseInt(temparr[10]), Integer.parseInt(temparr[11]), Double.parseDouble(temparr[12]), Double.parseDouble(temparr[13]), Double.parseDouble(temparr[14]), Double.parseDouble(temparr[15]), Double.parseDouble(temparr[16]), Integer.parseInt(temparr[17]), Integer.parseInt(temparr[18])));
				}
			}

			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Student s : students) {

			//Automatically Calculate and Fill In The Gaps

			s.setTerm1((s.getTest1()*.6)+(s.getJava1()*.4));
			s.setTerm2((s.getTest2()*.6)+(s.getJava2()*.4));
			s.setTerm3((s.getTest3()*.6)+(s.getJava3()*.4));
			s.setTerm4((s.getTest4()*.6)+(s.getJava4()*.4));
			s.setOverallPercent((s.getTerm1()+s.getTerm2()+s.getTerm3()+s.getTerm4())/4);
			String[][] boundaries = ClassCardLayoutPane.getBoundaries();
			String[] bounds = new String[7];
			
			for (int i = 0; i < boundaries.length; i++) {

				bounds[i] = boundaries[i][1];

			}
			double overall = s.getOverallPercent();
			int grade = 0;
			if (overall >= Integer.parseInt(bounds[0])) {
				grade = 7;
			} else if (overall >= Double.parseDouble(bounds[1])) {
				grade = 6;
			} else if (overall >= Double.parseDouble(bounds[2])) {
				grade = 5;
			} else if (overall >= Double.parseDouble(bounds[3])) {
				grade = 4;
			} else if (overall >= Double.parseDouble(bounds[4])) {
				grade = 3;
			} else if (overall >= Double.parseDouble(bounds[5])) {
				grade = 2;
			} else {
				grade = 1;
			}
			s.setOverallGrade(grade);
			s.setResidual((s.getOverallGrade())-(s.getTargetGrade()));

			//SORT ALL THE STUDENTS INTO INDIVIDUAL CLASSES

			if (classes.size() > 0) {

				boolean flag = false;
				for (Classroom c : classes) {

					if (c.getName().equalsIgnoreCase(s.getClassName())) {
						flag = true;
						c.addStudent(s);
						break;
					}

				}
				if (!flag) {
					Classroom c = new Classroom(s.getClassName(), new LinkedList<Student>());
					classes.add(c);
					c.addStudent(s);
				}

				flag = false;
			} else {

				Classroom c = new Classroom(s.getClassName(), new LinkedList<Student>());
				classes.add(c);
				c.addStudent(s);

			}


		}

	}


	public static void writeRecordsToFile() {

		if (classes.size() > 0) {

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("res/" + filename, false));
				
				//Write the headings
				String headingString = "Class,";
				for (int i = 0; i < headings.length; i ++) {
					headingString+=headings[i]+",";
				}
				headingString+="\n";
				
				writer.write(headingString);
				
				//Re-write the database
				for (Classroom c : classes) {

					for (Student s : c.getStudents()) {
						
						String line = s.getClassName()+","+s.getFirstName()+","+s.getLastName()+","+s.getTargetGrade()+","+s.getTest1()+","+s.getTest2()+","+s.getTest3()+","+s.getTest4()+","+s.getJava1()+","+s.getJava2()+","+s.getJava3()+","+s.getJava4()+","+s.getTerm1()+","+s.getTerm2()+","+s.getTerm3()+","+s.getTerm4()+","+s.getOverallPercent()+","+s.getOverallGrade()+","+s.getResidual()+"\n";
						
						writer.write(line);
						
					}

				}

				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}

	public String[] getHeadings() {
		return headings;
	}

	public void setHeadings(String[] headings) {
		this.headings = headings;
	}

	public static LinkedList<Classroom> getClasses() {
		return classes;
	}

	public void setClasses(LinkedList<Classroom> classes) {
		this.classes = classes;
	}



}

package Objects;

public class Student {
	
	private String className;
	private String firstName;
	private String lastName;
	private int targetGrade;
	private int test1, test2, test3, test4;
	private int java1, java2, java3, java4;
	private double term1, term2, term3, term4;
	private double overallPercent;
	private int overallGrade;
	private int residual;
	
	public Student(String className, String firstName, String lastName, int targetGrade, int test1, int test2, int test3, int test4,
			int java1, int java2, int java3, int java4, double term1, double term2, double term3, double term4, double overallPercent,
			int overallGrade, int residual) {
		this.className = className;
		this.firstName = firstName;
		this.lastName = lastName;
		this.targetGrade = targetGrade;
		this.test1 = test1;
		this.test2 = test2;
		this.test3 = test3;
		this.test4 = test4;
		this.java1 = java1;
		this.java2 = java2;
		this.java3 = java3;
		this.java4 = java4;
		this.term1 = term1;
		this.term2 = term2;
		this.term3 = term3;
		this.term4 = term4;
		this.overallPercent = overallPercent;
		this.overallGrade = overallGrade;
		this.residual = residual;
	}	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

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

	public int getTargetGrade() {
		return targetGrade;
	}

	public void setTargetGrade(int targetGrade) {
		this.targetGrade = targetGrade;
	}

	public int getTest1() {
		return test1;
	}

	public void setTest1(int test1) {
		this.test1 = test1;
	}

	public int getTest2() {
		return test2;
	}

	public void setTest2(int test2) {
		this.test2 = test2;
	}

	public int getTest3() {
		return test3;
	}

	public void setTest3(int test3) {
		this.test3 = test3;
	}

	public int getTest4() {
		return test4;
	}

	public void setTest4(int test4) {
		this.test4 = test4;
	}

	public int getJava1() {
		return java1;
	}

	public void setJava1(int java1) {
		this.java1 = java1;
	}

	public int getJava2() {
		return java2;
	}

	public void setJava2(int java2) {
		this.java2 = java2;
	}

	public int getJava3() {
		return java3;
	}

	public void setJava3(int java3) {
		this.java3 = java3;
	}

	public int getJava4() {
		return java4;
	}

	public void setJava4(int java4) {
		this.java4 = java4;
	}

	public double getTerm1() {
		return term1;
	}

	public void setTerm1(double term1) {
		this.term1 = term1;
	}

	public double getTerm2() {
		return term2;
	}

	public void setTerm2(double term2) {
		this.term2 = term2;
	}

	public double getTerm3() {
		return term3;
	}

	public void setTerm3(double term3) {
		this.term3 = term3;
	}

	public double getTerm4() {
		return term4;
	}

	public void setTerm4(double term4) {
		this.term4 = term4;
	}

	public double getOverallPercent() {
		return overallPercent;
	}

	public void setOverallPercent(double overallPercent) {
		this.overallPercent = overallPercent;
	}

	public int getOverallGrade() {
		return overallGrade;
	}

	public void setOverallGrade(int overallGrade) {
		this.overallGrade = overallGrade;
	}

	public int getResidual() {
		return residual;
	}

	public void setResidual(int residual) {
		this.residual = residual;
	}

	@Override
	public String toString() {
		return "Student [className=" + className + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", targetGrade=" + targetGrade + ", test1=" + test1 + ", test2=" + test2 + ", test3=" + test3
				+ ", test4=" + test4 + ", java1=" + java1 + ", java2=" + java2 + ", java3=" + java3 + ", java4=" + java4
				+ ", term1=" + term1 + ", term2=" + term2 + ", term3=" + term3 + ", term4=" + term4
				+ ", overallPercent=" + overallPercent + ", overallGrade=" + overallGrade + ", residual=" + residual
				+ "]";
	}
	
}

package GUI;

import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import FileManagement.CSVImport;
import Objects.Classroom;

public class MainTabbedPane extends JTabbedPane {	

	private static String teacherName;
	private static boolean admin;
	private static GradeBoundariesPane gbp;
	private static LinkedList<IndividualClassPane> classTabs = new LinkedList<IndividualClassPane>();
	private static ClassStatisticsPanel classesOverview;
	private static ClassCardLayoutPane dbView;
	
	public MainTabbedPane(String teacherName, boolean admin) {
		this.teacherName = teacherName;
		this.admin = admin;
		initialiseTabs(teacherName);

	}

	private void initialiseTabs(String teacherName) {

		dbView = new ClassCardLayoutPane(teacherName);
		addTab("Database", null, dbView, "Shows Database View");
		
		gbp = new GradeBoundariesPane();
		
		CSVImport ci = ClassCardLayoutPane.getCi();
		LinkedList<Classroom> classes = CSVImport.getClasses();
		
		classesOverview = new ClassStatisticsPanel();
		addTab("Class Statistics", null, classesOverview, "Shows Class Statistics View");
		
		for (Classroom c : classes) {
			IndividualClassPane classTab = new IndividualClassPane(c);
			classTabs.add(classTab);
			addTab(c.getName(), null, classTab, "Shows "+ c.getName() +" View");
		}

		//Class Stats

		if(admin) {
			JComponent gradeBoundaryTab = gbp;
			addTab("Grade Boundaries", null, gradeBoundaryTab, "Shows the grade boundaries");
		}
	}

	public static boolean isAdmin() {
		return admin;
	}

	public static GradeBoundariesPane getGbp() {
		return gbp;
	}

	public static LinkedList<IndividualClassPane> getClassTabs() {
		return classTabs;
	}

	public static ClassStatisticsPanel getClassesOverview() {
		return classesOverview;
	}

	public static ClassCardLayoutPane getDbView() {
		return dbView;
	}

}

package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FileManagement.CSVImport;
import Graphs.PercentageOutOfClassChart;
import Graphs.StudentPercentageLineChart;
import Graphs.TermJavaBarChart;
import Objects.Classroom;
import Objects.Student;

public class IndividualClassPane extends JPanel {

	private Classroom classroom;
	private LinkedList<Student> students;
	LinkedList<Classroom> classes;
	JPanel cards;
	public static CSVImport ci = ClassCardLayoutPane.getCi();


	LinkedList<JButton> studentGraphUpdateTriggers = new LinkedList<JButton>();

	public IndividualClassPane(Classroom c) {
		classroom = c;
		students = c.getStudents();
		initialiseGUI();
	}

	private void initialiseGUI() {
		super.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		refreshTheDatabases();
		synchroniseInstancedArgumentWithStatic(classes);
		String[] tempList = new String[students.size()];
		for (int i = 0; i < students.size(); i++) {
			tempList[i] = students.get(i).getFirstName() + " " + students.get(i).getLastName();
		}

		String[] listElements = tempList;
		cards = new JPanel(new CardLayout());

		JPanel sidePanel = new JPanel(new BorderLayout());
		// sidePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		sidePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel classroomName = new JLabel(classroom.getName());
		classroomName.setFont(new Font("Arial", Font.PLAIN, 20));
		classroomName.setBorder(new EmptyBorder(10, 2, 10, 50));
		sidePanel.add(classroomName, BorderLayout.NORTH);

		JLabel label = new JLabel("Update");
		sidePanel.add(label);

		JList selectionList = new JList(listElements);
		selectionList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent evt) {

				if (!evt.getValueIsAdjusting()) {
					label.setText(selectionList.getSelectedValue().toString());
					CardLayout cl = (CardLayout) (cards.getLayout());
					cl.show(cards, label.getText());
				}
			}

		});

		selectionList.setBorder(new EmptyBorder(2, 5, 5, 20));
		selectionList.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
		JScrollPane selectionListPane = new JScrollPane(selectionList);

		// Adding default card before anything is selected

		JPanel defaultPane = new JPanel();
		defaultPane.setLayout(new BoxLayout(defaultPane, BoxLayout.X_AXIS));
		JLabel som = new JLabel("Please select one of the students from the list in order to display information about their performance");
		defaultPane.add(Box.createHorizontalGlue());
		defaultPane.add(som, BorderLayout.CENTER);
		defaultPane.add(Box.createHorizontalGlue());
		cards.add(defaultPane);

		// Adding a card for each of the students
		for (int i = 0; i < listElements.length; i++) {
			/*
			 * ClassJTablePanel c1 = new ClassJTablePanel(classes.get(i).getStudents());
			 * JScrollPane temp = new JScrollPane(c1.getMainTable(),
			 * JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			 * JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			 */
			//studentGraphs = new StudentGraphs(students.get(i));

			JPanel temp = new JPanel();
			Student s = students.get(i);

			TermJavaBarChart sg = new TermJavaBarChart(s);
			StudentPercentageLineChart lc = new StudentPercentageLineChart(s);
			PercentageOutOfClassChart loct = new PercentageOutOfClassChart(s);

			temp.add(sg.getChartPanel());
			temp.add(lc.getChartPanel());
			temp.add(loct.getChartPanel());
			cards.add(temp);

			setPreferredSize(new Dimension(500,500));

			JButton check = new JButton("Update");

			check.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					sg.refreshData(s);
					lc.refreshData(s);
					loct.refreshData(s);
					refreshTheDatabases();
					synchroniseInstancedArgumentWithStatic(classes);

				}

			});

			studentGraphUpdateTriggers.add(check);







			cards.add(temp, listElements[i]);

		}

		sidePanel.add(selectionListPane, BorderLayout.CENTER);
		add(cards, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.WEST);

	}

	public void refreshTheDatabases() {
		classes = ci.getClasses();
	}

	private int synchroniseInstancedArgumentWithStatic(LinkedList<Classroom> classes) {
		int counter = -1;

		for (Classroom c : classes) {
			counter ++;
			if (c.getName().equals(classroom.getName())) {
				classroom = c;
				students = c.getStudents();
				break;
			}
		}

		final int dbClassIndex = counter;
		return dbClassIndex;
	}

	public void repaintCards() {
		cards.repaint();
	}

	public LinkedList<JButton> getStudentGraphUpdateTriggers() {
		return studentGraphUpdateTriggers;
	}



}

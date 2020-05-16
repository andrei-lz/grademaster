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

import ClassGraphs.ClassIndividualStudentPerformance;
import ClassGraphs.LevelsAchievedGraph;
import ClassGraphs.TermAverageGraph;
import FileManagement.CSVImport;
import Objects.Classroom;

public class ClassStatisticsPanel extends JPanel {
	
	JPanel cards;
	private static LinkedList<Classroom> classes = CSVImport.getClasses();
	public static CSVImport ci = ClassCardLayoutPane.getCi();


	LinkedList<JButton> studentGraphUpdateTriggers = new LinkedList<JButton>();

	public ClassStatisticsPanel() {
		initialiseGUI();
	}

	private void initialiseGUI() {
		super.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		refreshTheDatabases();
		
		String[] tempList = new String[classes.size()];
		for (int i = 0; i < classes.size(); i++) {
			tempList[i] = classes.get(i).getName();
		}

		String[] listElements = tempList;
		cards = new JPanel(new CardLayout());

		//Create Side Panel
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		// sidePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		sidePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel spacing = new JLabel("Classes        ");
		spacing.setFont(new Font("Arial", Font.PLAIN, 20));
		spacing.setBorder(new EmptyBorder(10, 0, 10, 50));
		sidePanel.add(spacing);
		
		//Label Used for connection between list and cards
		JLabel label = new JLabel("Update");
		
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
		JLabel som = new JLabel("Please select one of the classes from the list in order to display information about their performance");
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
			
			
			
			cards.add(temp);

			setPreferredSize(new Dimension(500,500));

			JButton check = new JButton(listElements[i]);
			
			Classroom c = findClassroomByName(listElements[i]);
			
			ClassIndividualStudentPerformance cisp = new ClassIndividualStudentPerformance(c);
			LevelsAchievedGraph lag = new LevelsAchievedGraph(c);
			TermAverageGraph tag = new TermAverageGraph(c);
			
			
			temp.add(cisp.getChartPanel());
			temp.add(lag.getChartPanel());
			temp.add(tag.getChartPanel());
			//temp.add(check);
			
			check.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					tag.refreshData(c);
					lag.refreshData(c);
					cisp.refreshData(c);
					refreshTheDatabases();

				}

			});

			studentGraphUpdateTriggers.add(check);







			cards.add(temp, listElements[i]);

		}

		sidePanel.add(selectionListPane, BorderLayout.CENTER);
		add(cards, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.WEST);

	}
	
	private Classroom findClassroomByName (String className) {
		
		for (Classroom c: classes) {
			if (c.getName() == className) {
				return c;
			}
		}
		return null;
	}
	
	public void refreshTheDatabases() {
		classes = ci.getClasses();
	}

	public void repaintCards() {
		cards.repaint();
	}

	public LinkedList<JButton> getStudentGraphUpdateTriggers() {
		return studentGraphUpdateTriggers;
	}
	
}

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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import FileManagement.CSVImport;
import FileManagement.ImportGradeBoundaries;
import Objects.Classroom;

@SuppressWarnings("serial")
public class ClassCardLayoutPane extends JPanel {


	JPanel cards;
	public static CSVImport ci;
	public static LinkedList<Classroom> classes;
	private String[] headings = {"Grade  ", "Boundary (%)  "};
	private static String[][] boundaries;

	private LinkedList<ClassJTablePanel> userTablesForLocking;
	boolean locked;

	public ClassCardLayoutPane(String teacherName) {
		initialiseGUI(teacherName);
	}

	private void initialiseGUI(String teacherNamePlate) {
		super.setLayout(new BorderLayout());
		

		
		ImportGradeBoundaries im = new ImportGradeBoundaries("GradeBoundaries.csv");
		boundaries = ImportGradeBoundaries.getGradeBoundaries();
		
		ci = new CSVImport("StudentDB.csv");
		classes = ci.getClasses();
		
		userTablesForLocking = new LinkedList<ClassJTablePanel>();
		
		
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

		//Add Current Teacher Name
		JLabel teacherName = new JLabel(teacherNamePlate);
		teacherName.setFont(new Font("Arial", Font.PLAIN, 20));
		teacherName.setBorder(new EmptyBorder(10, 0, 10, 50));

		sidePanel.add(teacherName, BorderLayout.NORTH);

		JLabel label = new JLabel("Update");
		label.setVisible(false);
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

		selectionList.setBorder(new EmptyBorder(2, 5, 5, 10));
		//selectionList.setFont(selectionList.getFont().deriveFont(13.0f));
		selectionList.setFont(new Font("DialogInput", Font.BOLD, 13));
		JScrollPane selectionListPane = new JScrollPane(selectionList);
		sidePanel.add(selectionListPane);

		//Adding the Grade Boundaries Table
		JScrollPane gradeBoundariesJTable = new JScrollPane(new JTable(boundaries,headings) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
		gradeBoundariesJTable.setPreferredSize(new Dimension(100, 100));
		sidePanel.add(gradeBoundariesJTable);


		//if (MainTabbedPane.isAdmin()) {
		//Adding the Write to File Button

		JButton updateRecords = new JButton("Update File Records");
		updateRecords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CSVImport.writeRecordsToFile();

			}

		});


		//Adding the lock switch

		locked = false;
		JButton lockRecords = new JButton("Lock Records");

		lockRecords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(!locked) {
					lockRecords.setText("Unlock Records");

					for (ClassJTablePanel i : userTablesForLocking) {
						i.getMainTable().setModel(i.getLockedTableModel());
					}
					locked = true;
				} else {
					lockRecords.setText("Lock Records");

					for (ClassJTablePanel i : userTablesForLocking) {
						i.getMainTable().setModel(i.getDefaultTableModel());
					}
					locked = false;
				}
			}

		});
		sidePanel.add(lockRecords);
		sidePanel.add(updateRecords);
		//}
		// Adding default card before anything is selected

		JPanel defaultPane = new JPanel();
		defaultPane.setLayout(new BoxLayout(defaultPane, BoxLayout.X_AXIS));
		JLabel som = new JLabel("Please select one of the classes from the list in order to display information about the students");
		defaultPane.add(Box.createHorizontalGlue());
		defaultPane.add(som, BorderLayout.CENTER);
		defaultPane.add(Box.createHorizontalGlue());
		cards.add(defaultPane);

		// Adding cards corresponding to classes
		for (int i = 0; i < listElements.length; i++) {

			ClassJTablePanel c1 = new ClassJTablePanel(classes.get(i));// using passive class
			// ClassJTablePanel, just gets the table FROM
			// the object and creates the JScrollPane
			// outside the class
			JScrollPane temp = new JScrollPane(c1.getMainTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			userTablesForLocking.add(c1);

			cards.add(temp, listElements[i]);

		}


		add(cards, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.WEST);

	}

	public static String[][] getBoundaries() {
		return boundaries;
	}

	public static CSVImport getCi() {
		return ci;
	}

	public static void setCi(CSVImport ci) {
		ClassCardLayoutPane.ci = ci;
	}

	public LinkedList<ClassJTablePanel> getUserTablesForLocking() {
		return userTablesForLocking;
	}

}

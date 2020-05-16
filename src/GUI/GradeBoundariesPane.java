package GUI;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import FileManagement.ImportGradeBoundaries;
import Objects.Classroom;

public class GradeBoundariesPane extends JPanel {

	private static JTable gradeBoundariesJTable;
	private static String[] headings = {"Grade  ", "Boundary (%)  "};
	private static String[][] boundaries;
	private LinkedList<ClassJTablePanel> userTables;

	public GradeBoundariesPane() {

		userTables = MainTabbedPane.getDbView().getUserTablesForLocking();

		boundaries = ClassCardLayoutPane.getBoundaries();

		gradeBoundariesJTable = new JTable(boundaries, headings) {
			/**
			 *
			 */
			private static final long serialVersionUID = -39903918383196211L;

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					if (!MainTabbedPane.isAdmin()) {
						return false;
					} else {
						return true;
					}
				}
			}
		};

		gradeBoundariesJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		gradeBoundariesJTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				int col = e.getColumn();
				int r = e.getFirstRow();

				if (r >= 0 && r < 8) {

					int dbClassIndex = 0;

					for (ClassJTablePanel table: userTables) {

						TableModel model = table.getMainTable().getModel();


						for (int row = 0; row < model.getRowCount(); row ++) {

							String[] bounds = new String[7];

							for (int i = 0; i < boundaries.length; i++) {

								bounds[i] = boundaries[i][1];

							}
							double overall = Double.parseDouble(model.getValueAt(row, 15) + "");

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

							if (!(grade+"").equalsIgnoreCase(model.getValueAt(row, 16)+"")) {
								System.out.println("Grade is changed");
								model.setValueAt(grade + "", row, 16);
								LinkedList<Classroom> classes = ClassCardLayoutPane.ci.getClasses();
								classes.get(dbClassIndex).getStudents().get(row).setOverallGrade(grade);


							}

						}
						
						MainTabbedPane.getClassesOverview().getStudentGraphUpdateTriggers().get(dbClassIndex).doClick();;
						
						dbClassIndex++;
					}

				}

			}

		});


		JScrollPane mainScrollPane = new JScrollPane(gradeBoundariesJTable);

		add(mainScrollPane, BorderLayout.CENTER);
	}

	public static JTable getGradeBoundariesJTable() {
		return gradeBoundariesJTable;
	}

	public static String[][] getBoundaries() {
		return boundaries;
	}

	public static String[] getHeadings() {
		return headings;
	}

}

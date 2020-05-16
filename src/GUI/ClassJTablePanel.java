package GUI;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Objects.Classroom;
import Objects.Student;

public class ClassJTablePanel {

	private Object[][] values;
	private JTable mainTable;
	private Classroom instancedClassObject;
	private LinkedList<Student> classStudentList;

	DefaultTableModel defaultTableModel;
	DefaultTableModel lockedTableModel;

	public ClassJTablePanel(Classroom classObject) {
		instancedClassObject = classObject;
		classStudentList = classObject.getStudents();
		initialiseTableData(classStudentList);
	}

	private void initialiseTableData(LinkedList<Student> classStudentList) {

		// Reference the CSVImport
		LinkedList<Classroom> classes = ClassCardLayoutPane.ci.getClasses();

		//Put the values into a 2D array
		values = convertStudentLinkedListToValues(classStudentList);

		//match up the class in the main static database with the instanced argument class
		int dbClassIndex = synchroniseInstancedArgumentWithStatic(classes);

		String[] headings = ClassCardLayoutPane.ci.getHeadings();

		defaultTableModel = new DefaultTableModel(values, headings) {
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

			private static final long serialVersionUID = 7703687010225837726L;

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col > 10 || col < 3) {
					return false;
				} else {
					return true;
				}
			}
		};

		lockedTableModel = new DefaultTableModel(values, headings) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7947691437657376363L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		mainTable = new JTable(defaultTableModel);

		mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		mainTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub



						TableModel model = (TableModel) e.getSource();
						int row = e.getFirstRow();
						int col = e.getColumn();

						// Calculate Term Averages ( With Weighings )
						if (col == 3 || col == 7) {//corresponding pairs of statistics (test and java for the same term)
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 3) + "")) && valid(Integer.parseInt(model.getValueAt(row, 7) + ""))) {
									double value = Integer.parseInt(model.getValueAt(row, 3) + "");
									double value2 = Integer.parseInt(model.getValueAt(row, 7) + "");
									double term = (value * 0.6) + (value2 * 0.4);// Average calculated with weightings (test worth 60% whereas Java is worth 40%)
									model.setValueAt(term + "", row, 11);

									classes.get(dbClassIndex).getStudents().get(row).setTerm1(term);// Update the corresponding value in the main LinkedList
								} else { // If there is an invalid input, such as letters, then 
									double term = 0;
									model.setValueAt(term + "", row, 11);
									classes.get(dbClassIndex).getStudents().get(row).setTerm1(term);// Update the corresponding value in the main LinkedList
								}
							} catch (Exception e) {

								double term = 0;
								model.setValueAt(term + "", row, 11);
								classes.get(dbClassIndex).getStudents().get(row).setTerm1(term);// Update the corresponding value in the main LinkedList

							}

						} else if (col == 4 || col == 8) {//corresponding pairs of statistics (test and java for the same term)

							try {
								if(valid(Integer.parseInt(model.getValueAt(row, 4) + "")) && valid(Integer.parseInt(model.getValueAt(row, 8) + ""))) {// Check if there is an invalid input, such as letters

									double value = Integer.parseInt(model.getValueAt(row, 4) + "");
									double value2 = Integer.parseInt(model.getValueAt(row, 8) + "");
									double term = (value * 0.6) + (value2 * 0.4);// Average calculated with weightings (test worth 60% whereas Java is worth 40%)
									model.setValueAt(term + "", row, 12);

									classes.get(dbClassIndex).getStudents().get(row).setTerm2(term);// Update the corresponding value in the main LinkedList
								}
							} catch (Exception e) { // If there is an invalid input, such as letters, then 

								double term = 0;
								model.setValueAt(term + "", row, 12);

								classes.get(dbClassIndex).getStudents().get(row).setTerm2(term);// Update the corresponding value in the main LinkedList
							}

						} else if (col == 5 || col == 9) {//corresponding pairs of statistics (test and java for the same term)

							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 5) + "")) && valid(Integer.parseInt(model.getValueAt(row, 9) + ""))) {// Check if there is an invalid input, such as letters
									double value = Integer.parseInt(model.getValueAt(row, 5) + "");
									double value2 = Integer.parseInt(model.getValueAt(row, 9) + "");
									double term = (value * 0.6) + (value2 * 0.4);// Average calculated with weightings (test worth 60% whereas Java is worth 40%)
									model.setValueAt(term + "", row, 13);

									classes.get(dbClassIndex).getStudents().get(row).setTerm3(term);// Update the corresponding value in the main LinkedList
								}
							} catch (Exception e) {// If there is an invalid input, such as letters, then 
								double term = 0;
								model.setValueAt(term + "", row, 13);//Set the field value to 0

								classes.get(dbClassIndex).getStudents().get(row).setTerm3(term);// Update the corresponding value in the main LinkedList
							}
						} else if (col == 6 || col == 10) {//corresponding pairs of statistics (test and java for the same term)
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 6) + "")) && valid(Integer.parseInt(model.getValueAt(row, 10) + ""))) {// Check if there is an invalid input, such as letters
									double value = Integer.parseInt(model.getValueAt(row, 6) + "");
									double value2 = Integer.parseInt(model.getValueAt(row, 10) + "");
									double term = (value * 0.6) + (value2 * 0.4);// Average calculated with weightings (test worth 60% whereas Java is worth 40%)
									model.setValueAt(term + "", row, 14);

									classes.get(dbClassIndex).getStudents().get(row).setTerm4(term);// Update the corresponding value in the main LinkedList
								}
							} catch (Exception e) {// If there is an invalid input, such as letters, then 
								double term = 0;
								model.setValueAt(term + "", row, 14);

								classes.get(dbClassIndex).getStudents().get(row).setTerm4(term);// Update the corresponding value in the main LinkedList
							}
						}
						if (col > 2 && col < 11) {



							//Set Test and Java Values
							//System.out.println(classes.get(dbClassIndex));
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 3) + ""))) {
									classes.get(dbClassIndex).getStudents().get(row).setTest1(Integer.parseInt(model.getValueAt(row, 3)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setTest1(0);
									model.setValueAt("0", row, 3);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setTest1(0);
								model.setValueAt("0", row, 3);
							}
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 4) + ""))) {
									classes.get(dbClassIndex).getStudents().get(row).setTest2(Integer.parseInt(model.getValueAt(row, 4)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setTest2(0);
									model.setValueAt("0", row, 4);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setTest2(0);
								model.setValueAt("0", row, 4);
							}
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 5) + ""))) {
									classes.get(dbClassIndex).getStudents().get(row).setTest3(Integer.parseInt(model.getValueAt(row, 5)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setTest3(0);
									model.setValueAt("0", row, 5);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setTest3(0);
								model.setValueAt("0", row, 5);
							}
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 6) + ""))) {

									classes.get(dbClassIndex).getStudents().get(row).setTest4(Integer.parseInt(model.getValueAt(row, 6)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setTest4(0);
									model.setValueAt("0", row, 6);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setTest4(0);
								model.setValueAt("0", row, 6);
							}

							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 7) + ""))) {

									classes.get(dbClassIndex).getStudents().get(row).setJava1(Integer.parseInt(model.getValueAt(row, 7)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setJava1(0);
									model.setValueAt("0", row, 7);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setJava1(0);
								model.setValueAt("0", row, 7);
							}
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 8) + ""))) {

									classes.get(dbClassIndex).getStudents().get(row).setJava2(Integer.parseInt(model.getValueAt(row, 8)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setJava2(0);
									model.setValueAt("0", row, 8);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setJava2(0);
								model.setValueAt("0", row, 8);
							}
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 9) + ""))) {
									classes.get(dbClassIndex).getStudents().get(row).setJava3(Integer.parseInt(model.getValueAt(row, 9)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setJava3(0);
									model.setValueAt("0", row, 9);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setJava3(0);
								model.setValueAt("0", row, 9);
							}
							try {
								if (valid(Integer.parseInt(model.getValueAt(row, 10) + ""))) {

									classes.get(dbClassIndex).getStudents().get(row).setJava4(Integer.parseInt(model.getValueAt(row, 10)+""));
								} else {
									classes.get(dbClassIndex).getStudents().get(row).setJava4(0);
									model.setValueAt("0", row, 10);
								}
							} catch (Exception e) {
								classes.get(dbClassIndex).getStudents().get(row).setJava4(0);
								model.setValueAt("0", row, 10);
							}


							// Calculate Overall Percentage
							double value1 = Double.parseDouble(model.getValueAt(row, 11) + "");
							double value2 = Double.parseDouble(model.getValueAt(row, 12) + "");
							double value3 = Double.parseDouble(model.getValueAt(row, 13) + "");
							double value4 = Double.parseDouble(model.getValueAt(row, 14) + "");
							double overall = (value1 + value2 + value3 + value4) / 4;
							model.setValueAt(overall + "", row, 15);
							classes.get(dbClassIndex).getStudents().get(row).setOverallPercent(overall);

							// Calculate Overall Grade


							String[][] boundaries = GradeBoundariesPane.getBoundaries();
							String[] bounds = new String[7];

							for (int i = 0; i < boundaries.length; i++) {

								bounds[i] = boundaries[i][1];
								//System.out.println(bounds[i]);

							}

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
							model.setValueAt(grade + "", row, 16);
							classes.get(dbClassIndex).getStudents().get(row).setOverallGrade(grade);



							//Calculate Residual

							int residual = Integer.parseInt(model.getValueAt(row, 16)+"") - Integer.parseInt(model.getValueAt(row, 2)+"");
							classes.get(dbClassIndex).getStudents().get(row).setResidual(residual);
							model.setValueAt(residual + "", row, 17);

							//Update Graphs
							//! IMPORTANT !//

							MainTabbedPane.getClassTabs().get(dbClassIndex).getStudentGraphUpdateTriggers().get(row).doClick();
							MainTabbedPane.getClassesOverview().getStudentGraphUpdateTriggers().get(dbClassIndex).doClick();;

						}
					}

				});
			}

		});

	}

	private int synchroniseInstancedArgumentWithStatic(LinkedList<Classroom> classes) {
		int counter = -1;

		for (Classroom c : classes) {
			counter ++;
			if (c.getName().equals(instancedClassObject.getName())) {
				instancedClassObject = c;
				classStudentList = c.getStudents();
				break;
			}
		}

		final int dbClassIndex = counter;
		return dbClassIndex;
	}

	private void calculateOverallGrade() {

	}

	private Object[][] convertStudentLinkedListToValues(LinkedList<Student> classStudentList) {
		Object[][] v = new String[classStudentList.size()][18];

		int i = 0;
		for (Student s : classStudentList) {

			v[i][0] = s.getFirstName();
			v[i][1] = s.getLastName();
			v[i][2] = s.getTargetGrade() + "";
			v[i][3] = s.getTest1() + "";
			v[i][4] = s.getTest2() + "";
			v[i][5] = s.getTest3() + "";
			v[i][6] = s.getTest4() + "";
			v[i][7] = s.getJava1() + "";
			v[i][8] = s.getJava2() + "";
			v[i][9] = s.getJava3() + "";
			v[i][10] = s.getJava4() + "";
			v[i][11] = s.getTerm1() + "";
			v[i][12] = s.getTerm2() + "";
			v[i][13] = s.getTerm3() + "";
			v[i][14] = s.getTerm4() + "";
			v[i][15] = s.getOverallPercent() + "";
			v[i][16] = s.getOverallGrade() + "";
			v[i][17] = s.getResidual() + "";

			i++;
		}

		return v;
	}

	private boolean valid(int input) {

		if (input >= 0 && input <= 100) {

			return true;

		}

		return false;
	}


	public JTable getMainTable() {
		return mainTable;
	}

	public void setMainTable(JTable mainTable) {
		this.mainTable = mainTable;
	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public DefaultTableModel getLockedTableModel() {
		return lockedTableModel;
	}

}

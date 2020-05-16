package Graphs;

import java.awt.Color;
import java.awt.Paint;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import FileManagement.CSVImport;
import GUI.MainTabbedPane;
import Objects.Classroom;
import Objects.Student;

public class PercentageOutOfClassChart {

	private Student student;
	private DefaultCategoryDataset dataset;
	private JFreeChart barChart;
	private ChartPanel chartPanel;

	public PercentageOutOfClassChart(Student s) {

		this.chartPanel = generateGraph(s);
		
		if (!MainTabbedPane.isAdmin()) {
			chartPanel.setPopupMenu(null);
		}
	}

	private ChartPanel generateGraph(Student student) {

		//JPanel graph = new JPanel();
		this.dataset = createDataset1(student);
		this.barChart = ChartFactory.createBarChart(student.getFirstName()+"'s average percentage vs the class", "Category", "Level", dataset, PlotOrientation.VERTICAL, true, true, false);

		formatGraphAppearance(barChart); // applying visual styles to the chart

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

		return chartPanel;
	}

	//Create Dataset of the Graph

	public DefaultCategoryDataset createDataset1(Student s) {
		final String termTestLabel = "Students";
		final String term1 = s.getFirstName();
		final String term2 = "Class Average";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(s.getTest1(), termTestLabel, term1);
		dataset.addValue(calculateClassAverageLevel(s.getClassName()), termTestLabel, term2);

		return dataset;
	}

	//REFRESH DATA OF THE GRAPH

	public void refreshData(Student s) {
		student = s;

		dataset.clear();

		final String termTestLabel = "Students";
		final String term1 = s.getFirstName();
		final String term2 = "Class Average";

		dataset.addValue(s.getTest1(), termTestLabel, term1);
		dataset.addValue(calculateClassAverageLevel(s.getClassName()), termTestLabel, term2);

		chartPanel.repaint();
		System.out.println(student.toString());
	}

	private int calculateClassAverageLevel(String className) {

		Classroom studentClass = null;

		for (Classroom c : CSVImport.getClasses()) {
			if (c.getName().equalsIgnoreCase(className)) {
				studentClass = c;
			}
		}

		if (studentClass != null) {

			LinkedList<Student> studs =  studentClass.getStudents();
			int avg = 0;
			for (Student s : studs) {

				avg += s.getOverallPercent();

			}
			avg/=studs.size();
			return avg;
		}
		return 0;
	}

	private void formatGraphAppearance(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();

		// Lock the Y scale from 0 to 100
		NumberAxis range = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();
		range.setRange(0.0, 100.0);
		//

		plot.setDomainGridlinePaint(Color.darkGray);
		plot.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.setDomainGridlinePaint(Color.black);
		plot.setOutlineVisible(false);

		plot.setBackgroundPaint(null);
		chart.setBackgroundPaint(null);

		Paint[] colors = {
				new Color(79, 100, 121),      // blue
				new Color(183, 86, 87),      // red
				new Color(214, 234, 223),      // green
		};

		// change the default colors
		for (int i = 0; i < 4; i++) {
			plot.getRenderer().setSeriesPaint(i, colors[i % colors.length]);
		}
		
	}

	public Student getStudent() {
		return student;
	}

	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public JFreeChart getBarChart() {
		return barChart;
	}

	public ChartPanel getChartPanel() {
		return this.chartPanel;
	}


}

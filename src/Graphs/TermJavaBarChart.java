package Graphs;

import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import GUI.MainTabbedPane;
import Objects.Student;

public class TermJavaBarChart {

	private Student student;
	private DefaultCategoryDataset dataset;
	private JFreeChart barChart;
	private ChartPanel chartPanel;

	public TermJavaBarChart(Student s) {

		this.chartPanel = generateGraph(s);
		
		if (!MainTabbedPane.isAdmin()) {
			chartPanel.setPopupMenu(null);
		}
	}

	private ChartPanel generateGraph(Student student) {

		//JPanel graph = new JPanel();
		this.dataset = createDataset(student);
		this.barChart = ChartFactory.createBarChart(student.getFirstName()+"'s individual components", "Category", "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);

		formatGraphAppearance(barChart); // applying visual styles to the chart

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

		return chartPanel;
	}

	//Create Dataset of the Graph

	public DefaultCategoryDataset createDataset(Student s) {
		final String termTestLabel = "Term Theory Test";
		final String javaTaskLabel = "Java Task";
		final String term1 = "Term 1";
		final String term2 = "Term 2";
		final String term3 = "Term 3";
		final String term4 = "Term 4";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(s.getTest1(), termTestLabel, term1);
		dataset.addValue(s.getTest2(), termTestLabel, term2);
		dataset.addValue(s.getTest3(), termTestLabel, term3);
		dataset.addValue(s.getTest4(), termTestLabel, term4);

		dataset.addValue(s.getJava1(), javaTaskLabel, term1);
		dataset.addValue(s.getJava2(), javaTaskLabel, term2);
		dataset.addValue(s.getJava3(), javaTaskLabel, term3);
		dataset.addValue(s.getJava4(), javaTaskLabel, term4);

		return dataset;
	}

	//REFRESH DATA OF THE GRAPH

	public void refreshData(Student s) {
		student = s;

		dataset.clear();

		final String termTestLabel = "Term Theory Test";
		final String javaTaskLabel = "Java Task";
		final String term1 = "Term 1";
		final String term2 = "Term 2";
		final String term3 = "Term 3";
		final String term4 = "Term 4";

		dataset.addValue(s.getTest1(), termTestLabel, term1);
		dataset.addValue(s.getTest2(), termTestLabel, term2);
		dataset.addValue(s.getTest3(), termTestLabel, term3);
		dataset.addValue(s.getTest4(), termTestLabel, term4);

		dataset.addValue(s.getJava1(), javaTaskLabel, term1);
		dataset.addValue(s.getJava2(), javaTaskLabel, term2);
		dataset.addValue(s.getJava3(), javaTaskLabel, term3);
		dataset.addValue(s.getJava4(), javaTaskLabel, term4);

		chartPanel.repaint();
		//System.out.println(student.toString());
	}

	private void formatGraphAppearance(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
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

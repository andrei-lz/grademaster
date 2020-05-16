package Graphs;

import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import GUI.MainTabbedPane;
import Objects.Student;

public class StudentPercentageLineChart {

	private Student student;
	private DefaultCategoryDataset dataset;
	private JFreeChart lineChart = null;
	private ChartPanel chartPanel;

	public StudentPercentageLineChart(Student s) {

		this.chartPanel = generateGraph(s);
		
		if (!MainTabbedPane.isAdmin()) {
			chartPanel.setPopupMenu(null);
		}
	}

	private ChartPanel generateGraph(Student s) {

		this.dataset = createDataset(s);
		this.lineChart = ChartFactory.createLineChart(s.getFirstName()+"'s progress across the year", "Term", "Percentage", dataset);

		formatGraphAppearance(lineChart); // applying visual styles to the chart

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

		return chartPanel;
	}

	private DefaultCategoryDataset createDataset(Student s) {
		final String name = "Term Percentage";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(s.getTerm1(), name, "1");
		dataset.addValue(s.getTerm2(), name, "2");
		dataset.addValue(s.getTerm3(), name, "3");
		dataset.addValue(s.getTerm4(), name, "4");

		return dataset;
	}

	public void refreshData(Student s) {
		final String name = "Term Percentage";

		student = s;

		dataset.clear();

		dataset.addValue(s.getTerm1(), name, "1");
		dataset.addValue(s.getTerm2(), name, "2");
		dataset.addValue(s.getTerm3(), name, "3");
		dataset.addValue(s.getTerm4(), name, "4");

		chartPanel.repaint();
		System.out.println(student.toString());
	}


	private void formatGraphAppearance(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();

		// Lock the Y scale from 0 to 100
		NumberAxis range = (NumberAxis) lineChart.getCategoryPlot().getRangeAxis();
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

	public JFreeChart getLineChart() {
		return lineChart;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}



}

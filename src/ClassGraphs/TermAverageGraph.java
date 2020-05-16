package ClassGraphs;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.Comparator;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import GUI.MainTabbedPane;
import Objects.Classroom;
import Objects.Student;

public class TermAverageGraph {

	private Classroom classroom;
	private DefaultCategoryDataset dataset;
	private JFreeChart barChart;
	private ChartPanel chartPanel;

	public TermAverageGraph(Classroom c) {
		this.classroom = c;
		this.chartPanel = generateGraph(c);

		if (!MainTabbedPane.isAdmin()) {
			chartPanel.setPopupMenu(null);
		}
	}

	private ChartPanel generateGraph(Classroom classroom) {
		
		this.dataset = createDataset(classroom);
		this.barChart = ChartFactory.createBarChart(classroom.getName() + "'s overall student average for each term",
				"Terms", "Overall Average Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);

		formatGraphAppearance(barChart); // applying visual styles to the chart

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

		return chartPanel;
	}

	// Create Dataset of the Graph

	public DefaultCategoryDataset createDataset(Classroom classrooom) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		double term1avg = 0;
		double term2avg = 0;
		double term3avg = 0;
		double term4avg = 0;

		for (Student s : classroom.getStudents()) {

			term1avg += s.getTerm1();
			term2avg += s.getTerm2();
			term3avg += s.getTerm3();
			term4avg += s.getTerm4();

		}

		term1avg /= classroom.getStudents().size();
		term2avg /= classroom.getStudents().size();
		term3avg /= classroom.getStudents().size();
		term4avg /= classroom.getStudents().size();

		dataset.addValue(term1avg, "", "Term 1");
		dataset.addValue(term2avg, "", "Term 2");
		dataset.addValue(term3avg, "", "Term 3");
		dataset.addValue(term4avg, "", "Term 4");

		return dataset;
	}

	// REFRESH DATA OF THE GRAPH

	public void refreshData(Classroom classroom) {
		this.classroom = classroom;
		
		dataset.clear();
		
		double term1avg = 0;
		double term2avg = 0;
		double term3avg = 0;
		double term4avg = 0;

		for (Student s : classroom.getStudents()) {

			term1avg += s.getTerm1();
			term2avg += s.getTerm2();
			term3avg += s.getTerm3();
			term4avg += s.getTerm4();

		}

		term1avg /= classroom.getStudents().size();
		term2avg /= classroom.getStudents().size();
		term3avg /= classroom.getStudents().size();
		term4avg /= classroom.getStudents().size();

		dataset.addValue(term1avg, "", "Term 1");
		dataset.addValue(term2avg, "", "Term 2");
		dataset.addValue(term3avg, "", "Term 3");
		dataset.addValue(term4avg, "", "Term 4");

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

		Paint[] colors = { new Color(79, 100, 121), // blue
				new Color(183, 86, 87), // red
				new Color(214, 234, 223), // green
		};

		final GradientPaint gp = new GradientPaint(0.0f, 0.0f, new Color(162, 195, 164), 0.0f, 0.0f,
				new Color(134, 157, 150));

		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setBarPainter(new StandardBarPainter());

		// change the default colors
		for (int i = 0; i < 4; i++) {
			renderer.setSeriesPaint(i, colors[i % colors.length]);
		}

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		chart.removeLegend();
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

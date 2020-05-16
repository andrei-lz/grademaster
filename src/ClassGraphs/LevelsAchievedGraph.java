package ClassGraphs;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import GUI.MainTabbedPane;
import Objects.Classroom;
import Objects.Student;

public class LevelsAchievedGraph {

	private Classroom classroom;
	private DefaultCategoryDataset dataset;
	private JFreeChart barChart;
	private ChartPanel chartPanel;

	public LevelsAchievedGraph(Classroom c) {
		this.classroom = c;
		this.chartPanel = generateGraph(c);
		
		if (!MainTabbedPane.isAdmin()) {
			chartPanel.setPopupMenu(null);
		}
	}

	private ChartPanel generateGraph(Classroom classroom) {

		//JPanel graph = new JPanel();
		this.dataset = createDataset(classroom);
		this.barChart = ChartFactory.createBarChart(classroom.getName()+"'s students of each grade", "Student Grade", "Number of Students", dataset, PlotOrientation.HORIZONTAL, true, true, false);

		formatGraphAppearance(barChart); // applying visual styles to the chart

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

		return chartPanel;
	}

	//Create Dataset of the Graph

	public DefaultCategoryDataset createDataset(Classroom classrooom) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int[] cumulativeGrades = {0,0,0,0,0,0,0};

		for (Student s : classroom.getStudents()) {

			cumulativeGrades[s.getOverallGrade()-1] += 1;

		}

		for (int i = cumulativeGrades.length-1 ; i >= 0; i--) {

			dataset.addValue(cumulativeGrades[i], "", i+1+"");

		}

		return dataset;
	}

	//REFRESH DATA OF THE GRAPH

	public void refreshData(Classroom classroom) {
		this.classroom = classroom;

		dataset.clear();

		int[] cumulativeGrades = {0,0,0,0,0,0,0};

		for (Student s : classroom.getStudents()) {

			cumulativeGrades[s.getOverallGrade()-1] += 1;

		}

		for (int i = cumulativeGrades.length-1 ; i >= 0; i--) {

			dataset.addValue(cumulativeGrades[i], "", i+1+"");

		}
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
		
		final GradientPaint gp = new GradientPaint(
	            0.0f, 0.0f, new Color(191, 200, 173), 
	            0.0f, 0.0f, new Color(144, 180, 148)
	        );

		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setBarPainter(new StandardBarPainter());
		
		// change the default colors
		for (int i = 0; i < 4; i++) {
			renderer.setSeriesPaint(i, gp);
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

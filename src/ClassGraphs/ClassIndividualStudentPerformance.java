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

public class ClassIndividualStudentPerformance {

	private Classroom classroom;
	private DefaultCategoryDataset dataset;
	private JFreeChart barChart;
	private ChartPanel chartPanel;
	
	public ClassIndividualStudentPerformance(Classroom c) {
		this.classroom = c;
		this.chartPanel = generateGraph(c);
		
		if (!MainTabbedPane.isAdmin()) {
			chartPanel.setPopupMenu(null);
		}
	}

	private ChartPanel generateGraph(Classroom classroom) {

		//JPanel graph = new JPanel();
		this.dataset = createDataset(classroom);
		this.barChart = ChartFactory.createBarChart(classroom.getName()+"'s individual student performance ranking", "Student Name", "Overall Average Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);

		formatGraphAppearance(barChart); // applying visual styles to the chart

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1050, 350));

		return chartPanel;
	}

	//Create Dataset of the Graph

	public DefaultCategoryDataset createDataset(Classroom classrooom) {
		String nameLabel = "";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		LinkedList<Student> orderedStudents = new LinkedList<Student>();

		for (Student s : classroom.getStudents()) {
			
			orderedStudents.add(s);

		}
		orderedStudents.sort( new Comparator<Student>(){
			@Override
			public int compare(Student o1, Student o2){
				return (int) (o2.getOverallPercent() - o1.getOverallPercent());
			}
		});

		for (Student s : orderedStudents) {

			nameLabel = s.getFirstName();
			dataset.addValue(s.getOverallPercent(), "", nameLabel);

		}

		return dataset;
	}

	//REFRESH DATA OF THE GRAPH

	public void refreshData(Classroom classroom) {
		this.classroom = classroom;

		String nameLabel = "";
		dataset.clear();

		LinkedList<Student> orderedStudents = new LinkedList<Student>();

		for (Student s : classroom.getStudents()) {
			
			orderedStudents.add(s);

		}
		orderedStudents.sort( new Comparator<Student>(){
			@Override
			public int compare(Student o1, Student o2){
				return (int) (o2.getOverallPercent() - o1.getOverallPercent());
			}
		});

		for (Student s : orderedStudents) {

			nameLabel = s.getFirstName();
			dataset.addValue(s.getOverallPercent(), "", nameLabel);

		}
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
		
		final GradientPaint gp = new GradientPaint(
	            0.0f, 0.0f, new Color(213, 176, 172), 
	            0.0f, 0.0f, new Color(250, 207, 173)
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

package view;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Plotting extends ApplicationFrame {
	public Plotting(long[][] selection, long[][] merge, long[][] quick, long[][] heap) {
		super("Sorting Algorithms");
		super.setBounds(20, 20,800, 500);	
		super.setResizable(false);
		JFreeChart xylineChart = ChartFactory.createXYLineChart("", "Array Size", "Running Time",
				createDataset(selection, merge, quick, heap), PlotOrientation.VERTICAL, true, true,
				false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);
		renderer.setSeriesPaint(3, Color.blue);
		
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		renderer.setSeriesStroke(3, new BasicStroke(2.0f));
		
		plot.setRenderer(renderer);
		setContentPane(chartPanel);
	}

	private XYDataset createDataset(long[][] selection, long[][] merge, long[][] quick,
			long[][] heap) {
		final XYSeries selectionSort = get(selection, "Selection sort");
		final XYSeries mergeSort = get(merge, "Merge sort");
		final XYSeries quickSort = get(quick, "Quick sort");
		final XYSeries heapSort = get(heap, "Heap sort");
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(selectionSort);
		dataset.addSeries(mergeSort);
		dataset.addSeries(quickSort);
		dataset.addSeries(heapSort);
		return dataset;
	}

	private XYSeries get(long[][] array, String name) {
		final XYSeries temp = new XYSeries(name);
		for (int i = 0; i < array.length; i++) {
			temp.add((double) array[i][0], (double) array[i][1]);
		}
		return temp;
	}
}
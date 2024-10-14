package Gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.HashMap;

//used to generate a panel with the corresponding pie chart
public class PieChart{

    private ChartPanel chartPanel;

    public PieChart(String title, HashMap map)
    {
        DefaultPieDataset dataset = createDataset(map);
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, false, true, false);
        chartPanel = new ChartPanel(chart);
    }

    public ChartPanel getPanel()
    {
        return chartPanel;
    }

    //Fill dataset with corresponding value
    private DefaultPieDataset createDataset(HashMap map)
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        map.forEach( (k, v) -> {
            dataset.setValue(k.toString(), Double.valueOf(v.toString()));
        });
        return dataset;
    }
}

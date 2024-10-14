package Gui;

import MainPackage.BasicStats;
import MainPackage.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUI {

    private JFrame frame;
    private BasicStats stats;
    private HashMap<Coordinate, Integer> coords;

    public GUI(BasicStats stats, HashMap<Coordinate, Integer> coords)
    {
        this.stats = stats;
        this.coords = coords;
        frame = new JFrame("LogAnalyzer");
        init();
    }

    private void init()
    {
        frame.setSize(new Dimension(800, 700));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        ChartsPanel chartsPanel = new ChartsPanel(stats);
        PointsPanel pointsPanel = new PointsPanel(coords);

        tabbedPane.addTab("Charts", chartsPanel);
        tabbedPane.addTab("Map", pointsPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}

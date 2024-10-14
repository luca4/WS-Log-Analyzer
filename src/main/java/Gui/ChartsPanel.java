package Gui;

import MainPackage.BasicStats;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

//JPanel that show pie charts
public class ChartsPanel extends JPanel {

    private GridBagConstraints gbc;

    public ChartsPanel(BasicStats stats)
    {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        createPieChart("Requests rate", stats.getRequestsRateMap(), 0, 0);
        createPieChart("Status rate", stats.getStatusRateMap(), 1, 0);
        createPieChart("User agent rate", stats.getAgentRateMap(), 0, 1);
        createPieChart("Used protocol rate", stats.getUsedProtocolRateMap(), 1, 1);
    }

    private void createPieChart(String title, HashMap map, int xPos, int yPos)
    {
        PieChart pieChart = new PieChart(title, map);
        gbc.gridx = xPos;
        gbc.gridy = yPos;
        add(pieChart.getPanel(), gbc);
    }
}

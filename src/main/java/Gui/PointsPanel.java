package Gui;

import MainPackage.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

//JPanel that shows the OpenStreetMap map, with clustered points
public class PointsPanel extends JPanel {

    //Setup a user agent so you can get data from OpenStreetMap
    static {
        System.setProperty("http.agent", "Mozilla/5.0");
    }

    public PointsPanel(HashMap<Coordinate, Integer> coords)
    {
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        JMapViewer map = new JMapViewer();

        HashMap<Integer, Color> colorMap = new HashMap<>();
        colorMap.put(0, Color.ORANGE);
        colorMap.put(1, Color.BLACK);
        colorMap.put(2, Color.BLUE);
        colorMap.put(3, Color.CYAN);
        colorMap.put(4, Color.GRAY);
        colorMap.put(5, Color.GREEN);

        //Add all points to the map and set color based on cluster
        coords.forEach( (coordinate, clusterId) -> {
            MapMarkerDot marker = new MapMarkerDot(coordinate.getLatitude(), coordinate.getLongitude());
            marker.setBackColor(colorMap.get(clusterId));
            map.addMapMarker(marker);
        });

        map.setDisplayToFitMapMarkers();
        map.setZoom(2);
        add(map, BorderLayout.CENTER);
    }
}

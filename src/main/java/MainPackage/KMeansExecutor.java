package MainPackage;

import Request.Request;
import ca.pjer.ekmeans.EKmeans;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//Allow execution of k-means algorithm using external library
public class KMeansExecutor {

    private final int clustersNumber = 6;
    private ArrayList<Request> requests;
    private File db;

    public KMeansExecutor(String dbPathFile)
    {
        requests = Data.getInstance().getAllData();
        db = new File(dbPathFile);
    }

    //Start the DBScan algorithm
    public HashMap<Coordinate, Integer> calculate()
    {
        ArrayList<Coordinate> coordinates;
        try {
            coordinates = getCoordinates();
        } catch (IOException e) {
            System.err.println("Problem with reading database file");
            e.printStackTrace();
            return null ;
        }

        //Number of data to cluster
        int n = coordinates.size();
        Random random = new Random(System.currentTimeMillis());

        //Create data structure with lat, lon for the algorithm
        double[][] data = new double[n][2];
        for(int i = 0; i < n; i++) {
            data[i][0] = coordinates.get(i).getLatitude();
            data[i][1] = coordinates.get(i).getLongitude();
        }

        //Create random centroids
        double[][] centroids = new double[clustersNumber][2];
        for (int i = 0; i < clustersNumber; i++) {
            centroids[i][0] = Math.abs(random.nextInt() % 100);
            centroids[i][1] = Math.abs(random.nextInt() % 100);
        }

        EKmeans eKmeans = new EKmeans(centroids, data);
        eKmeans.setDistanceFunction(EKmeans.EUCLIDEAN_DISTANCE_FUNCTION);
        eKmeans.run();

        //Get clustered data
        int[] assignments = eKmeans.getAssignments();

        HashMap<Coordinate, Integer> clusteredCoords = new HashMap<>();
        for(int i=0; i<n; i++)
        {
            clusteredCoords.put(coordinates.get(i), assignments[i]);
        }

        return clusteredCoords;
    }

    //Retrieve coordinates from ip address
    private ArrayList<Coordinate> getCoordinates() throws IOException
    {
        DatabaseReader dbReader = new DatabaseReader.Builder(db).build();
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for(Request r : requests) {
            CityResponse resp;
            try {
                resp = dbReader.city(InetAddress.getByName(r.getIpAddr()));
                coordinates.add(new Coordinate(resp.getLocation().getLatitude(), resp.getLocation().getLongitude()));
            } catch (GeoIp2Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return coordinates;
    }
}

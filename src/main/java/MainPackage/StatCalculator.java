package MainPackage;

import Request.Request;

import java.util.ArrayList;
import java.util.HashMap;

//Calculate stats for pie charts
public class StatCalculator {

    private Data data;
    private BasicStats stats;
    private int totalElements;
    private ArrayList<Request> allRequests;

    public StatCalculator()
    {
        data = Data.getInstance();
        stats = new BasicStats();
        totalElements = data.getTotalRequests();
        allRequests = data.getAllData();
    }

    public BasicStats calculate()
    {
        calculateRequestsTypeRate();
        calculateStatusCodeRate();
        calculateAgentRate();
        calculateUsedProtocolRate();

        return stats;
    }

    private void calculateRequestsTypeRate()
    {
        HashMap<String, Double> requestsRateMap = new HashMap<>();
        requestsRateMap.put("Get", data.getGetArray().size() / (double)totalElements);
        requestsRateMap.put("Post", data.getPostArray().size() / (double)totalElements);
        requestsRateMap.put("Put", data.getPutArray().size() / (double)totalElements);
        requestsRateMap.put("Delete", data.getDeleteArray().size() / (double)totalElements);
        requestsRateMap.put("Head", data.getHeadArray().size() / (double)totalElements);
        stats.setRequestsRateMap(requestsRateMap);
    }

    private void calculateStatusCodeRate()
    {
        HashMap<Integer, Long> statusRateMap = new HashMap<Integer, Long>();
        for(Request req : allRequests)
        {
            int statusCode = req.getStatusCode();
            if(statusRateMap.containsKey(statusCode))
                statusRateMap.put(statusCode, statusRateMap.get(statusCode)+1);

            else{ statusRateMap.put(statusCode, 1L); }
        }
        stats.setStatusRateMap(statusRateMap);
    }

    private void calculateAgentRate()
    {
        HashMap<String, Long> agentRateMap = new HashMap<String, Long>();
        for(Request req : allRequests)
        {
            String agent = req.getUserAgent();
            if(agentRateMap.containsKey(agent))
                agentRateMap.put(agent, agentRateMap.get(agent)+1);

            else{ agentRateMap.put(agent, 1L); }
        }
        stats.setAgentRateMap(agentRateMap);
    }

    private void calculateUsedProtocolRate()
    {
        HashMap<String, Long> usedProtocolRateMap = new HashMap<String, Long>();
        for(Request req : allRequests)
        {
            String protocol = req.getProtocol();
            if(usedProtocolRateMap.containsKey(protocol))
                usedProtocolRateMap.put(protocol, usedProtocolRateMap.get(protocol)+1);

            else{ usedProtocolRateMap.put(protocol, 1L); }
        }
        stats.setUsedProtocolRateMap(usedProtocolRateMap);
    }
}

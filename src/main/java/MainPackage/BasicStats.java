package MainPackage;

import java.util.HashMap;

// Save data for pie charts
public class BasicStats {

    private HashMap<String, Double> requestsRateMap;
    private HashMap<Integer, Long> statusRateMap;
    private HashMap<String, Long> agentRateMap;
    private HashMap<String, Long> usedProtocolRateMap;

    public HashMap<Integer, Long> getStatusRateMap() {
        return statusRateMap;
    }

    public void setStatusRateMap(HashMap<Integer, Long> statusRateMap) {
        this.statusRateMap = statusRateMap;
    }

    public HashMap<String, Long> getAgentRateMap() {
        return agentRateMap;
    }

    public void setAgentRateMap(HashMap<String, Long> agentRateMap) {
        this.agentRateMap = agentRateMap;
    }

    public HashMap<String, Long> getUsedProtocolRateMap() {
        return usedProtocolRateMap;
    }

    public void setUsedProtocolRateMap(HashMap<String, Long> usedProtocolRateMap) {
        this.usedProtocolRateMap = usedProtocolRateMap;
    }

    public HashMap<String, Double> getRequestsRateMap() {
        return requestsRateMap;
    }

    public void setRequestsRateMap(HashMap<String, Double> requestsRateMap) {
        this.requestsRateMap = requestsRateMap;
    }
}

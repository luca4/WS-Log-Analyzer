package Request;

import nl.basjes.parse.useragent.UserAgentAnalyzer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//Save single request data
public abstract class Request {

    private String ipAddr, request, protocol, refererHTTPRequest, userAgent;
    private int statusCode, objectSize;
    protected MethodType methodType;
    private Date processFinishTime;

    public enum MethodType{
        GET,
        POST,
        PUT,
        DELETE,
        HEAD
    }

    public Request(String data){

        String[] dataArr = data.split("==");

        ipAddr = dataArr[0];

        //Parse data value
        try {
            processFinishTime = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss_Z", Locale.US).parse(dataArr[1]);
        } catch (ParseException e) {
            System.err.println("Error while parsing data");
            e.printStackTrace();
            processFinishTime = null;
            return;
        }

        //Specified in child classes
        methodType = null;

        request = dataArr[3];
        protocol = dataArr[4];
        statusCode = Integer.parseInt(dataArr[5]);
        objectSize = Integer.parseInt(dataArr[6]);
        refererHTTPRequest = dataArr[7];

        //Get user agent from log data
        UserAgentAnalyzer analyzer = MainPackage.UserAgentAnalyzer.getInstance();
        userAgent = analyzer.parse(dataArr[8]).getValue("AgentName");
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getRequest() {
        return request;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRefererHTTPRequest() {
        return refererHTTPRequest;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getObjectSize() {
        return objectSize;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public Date getProcessFinishTime() {
        return processFinishTime;
    }

    @Override
    public String toString() {
        return ipAddr+" "+processFinishTime.toString()+" Method_None "+request+" "+protocol+" "+statusCode+" "
                +objectSize+" "+refererHTTPRequest+" "+userAgent;
    }
}

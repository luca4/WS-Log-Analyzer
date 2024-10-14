package MainPackage;

import Request.*;

import java.util.ArrayList;

//Singleton class that saves requests read from the sanitized file
public class Data {

    private static Data instance = null;
    private RequestArray<GetRequest> getArray;
    private RequestArray<PostRequest> postArray;
    private RequestArray<PutRequest> putArray;
    private RequestArray<DeleteRequest> deleteArray;
    private RequestArray<HeadRequest> headArray;

    private Data()
    {
        getArray = new RequestArray<GetRequest>();
        postArray = new RequestArray<PostRequest>();
        putArray = new RequestArray<PutRequest>();
        deleteArray = new RequestArray<DeleteRequest>();
        headArray = new RequestArray<HeadRequest>();
    }

    static Data getInstance()
    {
        if(instance==null)
            instance = new Data();
        return instance;
    }

    public synchronized void add(String row)
    {
        String method = row.split("==")[2];
        switch (method)
        {
            case "GET": {
                getArray.add(new GetRequest(row));
                break;
            }
            case "POST":{
                postArray.add(new PostRequest(row));
                break;
            }
            case "PUT":{
                putArray.add(new PutRequest(row));
                break;
            }
            case "DELETE":{
                deleteArray.add(new DeleteRequest(row));
                break;
            }
            case "HEAD": {
                headArray.add(new HeadRequest(row));
                break;
            }
        }
    }

    //Return total number of requests
    public int getTotalRequests()
    {
        return getArray.size() + postArray.size() + putArray.size() + deleteArray.size() + headArray.size();
    }

    //Return unified data
    public ArrayList<Request> getAllData()
    {
        ArrayList<Request> combined = new ArrayList<Request>();
        combined.addAll(getArray.getArrayList());
        combined.addAll(postArray.getArrayList());
        combined.addAll(putArray.getArrayList());
        combined.addAll(deleteArray.getArrayList());
        combined.addAll(headArray.getArrayList());
        return combined;
    }

    public RequestArray<GetRequest> getGetArray() {
        return getArray;
    }

    public RequestArray<PostRequest> getPostArray() {
        return postArray;
    }

    public RequestArray<PutRequest> getPutArray() {
        return putArray;
    }

    public RequestArray<DeleteRequest> getDeleteArray() {
        return deleteArray;
    }

    public RequestArray<HeadRequest> getHeadArray() {
        return headArray;
    }
}

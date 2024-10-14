package Request;

public class GetRequest extends Request {

    public GetRequest(String data) {
        super(data);
        methodType = MethodType.GET;
    }
}

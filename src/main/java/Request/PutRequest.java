package Request;

public class PutRequest extends Request {

    public PutRequest(String data) {
        super(data);
        methodType = MethodType.PUT;
    }
}
package Request;

public class HeadRequest extends Request {
    public HeadRequest(String data) {
        super(data);
        methodType = MethodType.HEAD;
    }
}

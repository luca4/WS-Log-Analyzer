package Request;

public class PostRequest extends Request {
    public PostRequest(String data) {
        super(data);
        methodType = MethodType.POST;
    }
}

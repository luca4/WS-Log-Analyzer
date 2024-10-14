package Request;

public class DeleteRequest extends Request {

    public DeleteRequest(String data) {
        super(data);
        methodType = MethodType.DELETE;
    }
}

package Request;

import java.util.ArrayList;

//Allows saving different array types with Generics
public class RequestArray<T extends Request> {

    private ArrayList<T> list = new ArrayList<T>();

    public T get(int i)
    {
        return list.get(i);
    }

    public void add(T element)
    {
        list.add(element);
    }

    public int size() { return list.size(); }

    public ArrayList<T> getArrayList()
    {
        return list;
    }
}

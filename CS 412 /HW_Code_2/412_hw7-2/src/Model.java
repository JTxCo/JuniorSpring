import java.util.ArrayList;

public class Model {
    private ArrayList<String> data;

    public Model(){
         data = new ArrayList<String>();
    }
    public ArrayList<String> getData(){
        return data;
    }
    public void addData(String item){
        System.out.println("data: " + data);
        data.add(item);
    }
    public void removeData(String item){
        System.out.println("data stuff: " + data);
        data.remove(item);
        System.out.println("data stuff removed: " + item +" all : " + data);
    }
}

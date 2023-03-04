import java.util.ArrayList;
public class Model {//holding data
    private ArrayList<String> worddata;

    public Model() {
        worddata = new ArrayList<String>();
    }

    public ArrayList<String> getdata() {
        return worddata;
    }

    public void adddata (String input){
        worddata.add(input);
    }
     public void deletinginput(String Item){
        worddata.remove(Item);
    }


}

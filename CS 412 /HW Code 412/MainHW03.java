import java.util.ArrayList;
import java.util.Random;

public class MainHW03 {

    public static void main(String[] args){
        ArrayList<Sort> sortAlgorithms = new ArrayList<Sort>();
        sortAlgorithms.add(new BubbleSort());
        sortAlgorithms.add(new SelectionSort());
        sortAlgorithms.add(new InsertionSort());
        sortAlgorithms.add(new JavaSort());

        int[] originalData = makeNewRandomData(10000);

        for(Sort algorithm : sortAlgorithms){
            algorithm.setData(originalData.clone());
            algorithm.sortData();
            boolean ok = algorithm.isSorted();
            long runtime = algorithm.getRuntime();
            String name = algorithm.getName();

            String msg = String.format("algorithm %15s, runtime = %10d ns, isSorted = %b", name, runtime, ok );
            System.out.println(msg);
        }
    }

    private static int[] makeNewRandomData(int size){
        int[] data = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            data[i] = random.nextInt(size*5);
        }
        return data;
    }
}

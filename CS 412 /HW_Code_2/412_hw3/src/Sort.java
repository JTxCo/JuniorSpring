import java.util.Date;
public abstract class Sort {
   private int [] data;
    String name;
   private long startTime;
   private long stopTime;

   public abstract void sortData();
   void printData(){
       for(int i : data){
           System.out.println(i);
       }
   }
   boolean isSorted(){
       for(int i =0; i<data.length-1; i++){
           if(data[i+1] < data[i]) {
               System.out.format("j :%d is greater than i: %d " , data[i+1], data[i]);
               return  false;
           }
       }
       return true;
   }
   void swap(int i, int j){
       int temp = data[i];
       data[i] = data[j];
       data[j] = temp;
   }
   long getRuntime(){
       return stopTime - startTime;
   }
   void startTimer(){
    startTime = System.nanoTime();
   }
   void stopTimer(){
     stopTime= System.nanoTime();
   }
   int[] getData(){
       return data;
   }
   String getName(){
       return name;
   }
   void setData(int [] stuff){
       this.data = stuff;
   }
}

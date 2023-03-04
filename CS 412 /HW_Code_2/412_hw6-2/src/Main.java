import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner reading = new Scanner(System.in);
        System.out.print("enter input file: ");
        String inputFile = reading.nextLine();
        System.out.println("input file: " + inputFile);
        System.out.print("enter output file: ");
        String outputFile = reading.nextLine();
        System.out.println("output file is: " + outputFile);
        File readingInput = new File(inputFile);
        if(!(readingInput.exists())){
            try {
                throw new Exception("File does not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(1);
        }
        Map<String, Integer> words = new TreeMap<>();
        int wordCount =0;
        try {
            Scanner fileScanner = new Scanner(readingInput);
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] separated = line.toLowerCase(Locale.ROOT).replaceAll("[^a-zA-Z\\s]","").split("\\s+");
                for(String i : separated){
                    if(words.containsKey(i)){
                        words.replace(i, words.get(i)+1);
                        wordCount+=1;
                    }
                    else if(i ==""){}
                    else {
                        words.put(i, 1);
                        wordCount+=1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("word count: "+ wordCount);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            for(Map.Entry<String,Integer> entry : words.entrySet()){
                bw.write(entry.getKey() +" : " + entry.getValue());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int max =  Collections.max(words.values());
        String maxKey = null;
        for(Map.Entry<String,Integer> entry : words.entrySet()) {
            if (entry.getValue() == max) {
                maxKey = entry.getKey();
            }
        }
        System.out.println("word: " + maxKey + " with a count: " + max);
    }
}

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;

public class Main {


    // ()

    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> words = new HashMap<>();
        System.out.print("Enter input file: ");
        Scanner input = new Scanner(System.in);
        String inputfile= input.nextLine();
        File existingtest = new File(inputfile);
        boolean existmaybe;
        if(!(existmaybe = existingtest.exists())){
            System.out.println("the file inputed does not exist so nothing to do here");
            System.exit(1);
        }
        System.out.print("Emter output file: " );
        String outputfile = input.nextLine();
        Path filepth = Path.of(inputfile);
        String text = Files.readString(filepth);
        String lower = text.toLowerCase();
        lower = lower.replaceAll( "[^a-zA-Z]", "");
        String []wordhldr = lower.split(" ");
        int reading =0;
        for ( String i : wordhldr){
            if(words.containsKey(i)){
                int counter= words.get(i);
                counter++;
                words.put(i, counter);
            }

            else{
                words.put(i,1);
            }
            reading++;
        }
        File output = new File(outputfile);
        output.createNewFile();
        Path outputpath = Path.of(outputfile);
        StringBuilder maptostring = new StringBuilder();
        TreeMap<String, Integer> sortedwords = new TreeMap<>();
        sortedwords.putAll(words);
        for(String key : sortedwords.keySet()){
            maptostring.append(key+ ": "+ sortedwords.get(key)+"\n" );
            Files.writeString(outputpath, maptostring);
        }
        int readmax = Collections.max(words.values());
        String readname = Collections.max(words.keySet());
        System.out.println(reading + " words were read");
        System.out.println("The word: "+ readname  + " was read "+ readmax+ " times");
    }
}

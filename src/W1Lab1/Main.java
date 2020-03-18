package W1Lab1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String data = Main.readFromFile("src/W1Lab1/textFile.txt");
        String[] arr = data.split(" ");
        List<KeyValue<String, Integer>> listString = new ArrayList<>();

        for(String s : arr){
            s = s.toLowerCase();
            s.split("-");
            if(s.matches("^[a-zA-Z']+.?/?"))
                listString.add(new KeyValue<String, Integer>(s.replaceAll("[^a-zA-Z']", ""), 1));
        }

        Collections.sort(listString, new Comparator<KeyValue<String, Integer>>() {
            @Override
            public int compare(KeyValue<String, Integer> p1, KeyValue<String, Integer> p2) {
            	String s1 = (String)p1.getKey();
        		String s2 = (String)p2.getKey();
        		return s1.compareTo(s2);
            }
        });
        System.out.println(listString.size());
        listString.forEach(e-> System.out.println("(" + e.getKey() + ", " +e.getValue() +")"));
    }

    private static String readFromFile(String path){
        String words = "";
        try {
            words = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

}


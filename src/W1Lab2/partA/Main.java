package W1Lab2.partA;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javafx.util.Pair;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String data = Main.readFromFile("src/textFile.txt");
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
            public int compare(KeyValue p1, KeyValue p2) {
            	String s1 = (String)p1.getKey();
        		String s2 = (String)p2.getKey();
        		return s1.compareTo(s2);
            }
        });
        System.out.println(listString.size());
        listString.forEach(e-> System.out.println("(" + e.getKey() + ", " +e.getValue() +")"));
        
       
        Map<String, List<Integer>> groupBp = new HashMap<>();
        List<GroupByPair<String, Integer>> list1 = new ArrayList<GroupByPair<String,Integer>>();
        
        for(KeyValue<String, Integer> pair : listString) {
        	GroupByPair<String, Integer> check = new GroupByPair<String, Integer>((String) pair.getKey());
        	if(!list1.contains(check)) {
        		check.getValues().add(1);
        		list1.add(check);
 
        	}
        	else {
        		for(GroupByPair<String, Integer> gp : list1) {
        			if(gp.getKey().equals(pair.getKey())) {
        				gp.getValues().add((Integer) pair.getValue());
        			}
        		}
        		
        		
        	}
        }
        System.out.println(list1);
        
        List<KeyValue> sumPairs = new ArrayList<KeyValue>();
        for( GroupByPair<String, Integer> groupByPair : list1) {
        	int sum = 0;
        	for(int x : groupByPair.getValues()) {
        		sum += x;
        	}
        	KeyValue k = new KeyValue<>(groupByPair.getKey(), sum);
        	System.out.println(k);
        	sumPairs.add(k);
        	
        	
        }
//        System.out.println(sumPairs);
        
        
        
        System.out.println("-------------using hash map------------");
        
        for(KeyValue pair : listString) {
        	if(!groupBp.containsKey(pair.getKey())) {
        		
	        	groupBp.put((String) pair.getKey(), new ArrayList<>());
	        	groupBp.get(pair.getKey()).add((Integer) pair.getValue());
        	}
        	else {
        		groupBp.get(pair.getKey()).add((Integer) pair.getValue());
        	}
        }
        System.out.println("---------------------------------try----------------------------");
        System.out.println(groupBp);
        List<GroupByPair> list = new ArrayList<>();
        
        System.out.println("------------------sum------------------------");
        for(Map.Entry<String, List<Integer>> groupByPair : groupBp.entrySet()) {
        	int sum = 0;
        	for(int x : groupByPair.getValue()) {
        		sum += x;
        	}
        	System.out.println(groupByPair.getKey() + " " + sum);
        	
        }
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


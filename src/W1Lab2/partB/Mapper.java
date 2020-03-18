package W1Lab2.partB;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import W1Lab2.partA.KeyValue;


public class Mapper {
	
	private String path;
	private List<KeyValue<String, Integer>> listString;
	
	
	public List<KeyValue<String, Integer>> getListString() {
		return listString;
	}

	public Mapper(String path) {
		this.path = path;
		this.listString = new ArrayList<>();
	}

	public List<KeyValue<String, Integer>> map() {
		String data = readFromFile(path);
	    String[] arr = data.split(" ");
	    

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
	    return listString;
	}
	
	public void printMapperOutput() {
		listString.forEach(e-> System.out.println("< " + e.getKey() + ", " +e.getValue() +" >"));
	}
    
    public String readFromFile(String path){
        String words = "";
        try {
            words = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

}

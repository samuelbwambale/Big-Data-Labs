package W1Lab3.prob1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

	private String path;
	private List<KeyValue<String, Integer>> extractedWords;
	private List<KeyValue<String, Integer>> keySumPairs;

	public List<KeyValue<String, Integer>> getListString() {
		return keySumPairs;
	}

	public Mapper(String path) {
		this.path = path;
		this.extractedWords = new ArrayList<>();
		this.keySumPairs = new ArrayList<>();
	}

		public void map() {
		String data = readFromFile(path);
		String[] arr = data.split(" ");

		for (String s : arr) {
			s = s.toLowerCase();
			s.split("-");
			if (s.matches("^[a-zA-Z']+.?/?"))
				extractedWords.add(new KeyValue<String, Integer>(s.replaceAll("[^a-zA-Z']", ""), 1));
		}

		Collections.sort(extractedWords, new Comparator<KeyValue<String, Integer>>() {
			@Override
			public int compare(KeyValue<String, Integer> p1, KeyValue<String, Integer> p2) {
				String s1 = (String) p1.getKey();
				String s2 = (String) p2.getKey();
				return s1.compareTo(s2);
			}
		});

		Map<String, List<Integer>> groupBp = new HashMap<>();

		for (KeyValue<String, Integer> pair : extractedWords) {
			if (!groupBp.containsKey(pair.getKey())) {

				groupBp.put((String) pair.getKey(), new ArrayList<>());
				groupBp.get(pair.getKey()).add((Integer) pair.getValue());
			} else {
				groupBp.get(pair.getKey()).add((Integer) pair.getValue());
			}
		}
		
        for(Map.Entry<String, List<Integer>> groupByPair : groupBp.entrySet()) {
        	int sum = 0;
        	for(int x : groupByPair.getValue()) {
        		sum += x;
        	}
        	KeyValue<String, Integer> kv = new KeyValue<String, Integer>(groupByPair.getKey(), sum);
        	keySumPairs.add(kv);
        	
        }
	}

	public void printMapperOutput() {
		keySumPairs.forEach(e -> System.out.println("< " + e.getKey() + ", " + e.getValue() + " >"));
	}

	public String readFromFile(String path) {
		String words = "";
		try {
			words = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}

}

package W1Lab3.prob2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

	private String path;
	private List<KeyValue<String, Integer>> extractedWords;
	private List<KeyValue<String, List<Integer>>> mapperOutput;

	public List<KeyValue<String, List<Integer>>> getListString() {
		return mapperOutput;
	}

	public Mapper(String path) {
		this.path = path;
		this.extractedWords = new ArrayList<>();
		this.mapperOutput = new ArrayList<>();
	}

		public void map() {
		String data = readFromFile(path);
		System.out.println(data);
		String[] arr = data.split(" ");

		for (String s : arr) {
			s = s.toLowerCase();
			s.split("-");
			if (s.matches("^[a-zA-Z']+.?/?"))
				extractedWords.add(new KeyValue<String, Integer>(s.replaceAll("[^a-zA-Z']", ""), s.length()));
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
		int count = 1;
		for (KeyValue<String, Integer> pair : extractedWords) {
			String firstLetter = pair.getKey().charAt(0)+"";
			if (!groupBp.containsKey(pair.getKey().charAt(0)+"")) {

				groupBp.put(firstLetter, new ArrayList<>());
				groupBp.get(firstLetter).add(0, (Integer) pair.getValue());
				groupBp.get(firstLetter).add(1, count);
			} else {
				int newLength = groupBp.get(firstLetter).get(0) + pair.getValue();
				int newCount = groupBp.get(firstLetter).get(1) + 1;
				List<Integer> newLenghtCountList = Arrays.asList(newLength, newCount);
				groupBp.put(firstLetter, newLenghtCountList);
			}
		}
				
        for(Map.Entry<String, List<Integer>> groupByPair : groupBp.entrySet()) {
        	
        	KeyValue<String, List<Integer>> kv = new KeyValue<String, List<Integer>>(groupByPair.getKey(), groupByPair.getValue());
        	mapperOutput.add(kv);
        	
        }
	}
	

	public void printMapperOutput() {
		mapperOutput.forEach(e -> System.out.println("< " + e.getKey() + ", " + e.getValue() + " >"));
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

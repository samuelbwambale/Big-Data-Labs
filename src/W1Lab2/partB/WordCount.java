package W1Lab2.partB;

import java.util.ArrayList;
import java.util.List;

import W1Lab2.partA.KeyValue;


public class WordCount {

	private List<Mapper> mappers;
	private List<Reducer> reducers;
	private int r;
	private List<List<KeyValue<String, Integer>>> reducerKeyValues;

	public WordCount(int r) {
		this.r = r;
		this.reducerKeyValues = new ArrayList<>();
		this.mappers = new ArrayList<>();
		this.reducers = new ArrayList<>();
	}

	public static void main(String[] args) {
		WordCount wordCount = new WordCount(4);
		wordCount.createMappers();
		wordCount.shuffle();
		wordCount.printReducerInput();
	}
	
	public void printReducerInput() {
		for(int c = 0; c < reducers.size(); c++) {
			System.out.println("Reducer "+ c +" input");
			reducers.get(c).reduce();
			reducers.get(c).printReducerInput();
			
			
		}
		printReducerOutput();
	}
	
	public void printReducerOutput() {
		for(int c = 0; c < reducers.size(); c++) {
			System.out.println("Reducer "+ c +" output");
			reducers.get(c).printReducerOutput();
		}
	}

	public void createMappers() {
		Mapper mapper1 = new Mapper("src/W1Lab2/textFile.txt");
		Mapper mapper2 = new Mapper("src/W1Lab2/textFile2.txt");
		Mapper mapper3 = new Mapper("src/W1Lab2/textFile3.txt");
		mappers.add(mapper1);
		mappers.add(mapper2);
		mappers.add(mapper3);

		for (int i = 0; i < mappers.size(); i++) {
			mappers.get(i).map();
			System.out.println("Mapper " + i + " Output");
			mappers.get(i).printMapperOutput();
		}
	}

	public void shuffle() {
		
		int i = 0;
		while (i < r) {
			reducers.add(new Reducer());
			i++;
		}

		for (int j = 0; j < mappers.size(); j++) {
			System.out.println("Mapper " + j + " Output");
			mappers.get(j).printMapperOutput();
			createKeysPerMapper();
			for (KeyValue<String, Integer> kv : mappers.get(j).getListString()) {
				int reducerId = Math.abs(getPartition(kv.getKey()));
				
				reducers.get(reducerId).addKeyValue(kv); //keyValues in reducer
				reducerKeyValues.get(reducerId).add(kv);
				
			}
			printKeysPerMapper(j);
		}
	}
	
	public void createKeysPerMapper() {
		this.reducerKeyValues = new ArrayList<>();
		int i = 0;
		while (i < r) {
			reducerKeyValues.add(new ArrayList<KeyValue<String, Integer>>());
			i++;
		}
	}
	
	public void printKeysPerMapper(int y) {
		for(int j = 0; j < reducerKeyValues.size(); j++) {
			System.out.println("Pairs sent from Mapper " + y + " Reducer " + j);
			for(int i = 0; i<reducerKeyValues.get(j).size(); i++) {
				System.out.println(reducerKeyValues.get(j).get(i));
			}
			
		}
	}

	public int getPartition(String key) {
		return (int) key.hashCode() % r;
	}
	
	

}

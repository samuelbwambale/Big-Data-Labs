package W1Lab3.prob2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reducer {
	private List<KeyValue<String, List<Integer>>> reducerInput;
	private List<KeyValue<String, List<Integer>>> reducerOutput;
	private List<KeyValue<String, Double>> reducerAverageOutput;
	private List<GroupByPair<String, Integer>> groupPairs;

	public Reducer() {
		this.reducerInput = new ArrayList<>();
		this.reducerOutput = new ArrayList<>();
		this.reducerAverageOutput = new ArrayList<>();
	}

	public void addKeyValue(KeyValue<String, List<Integer>> kv) {
		reducerInput.add(kv);
	}

	public void reduce() {
		HashMap<String, List<Integer>> keyWithSums = new HashMap<String, List<Integer>>();

		for (KeyValue<String, List<Integer>> pair : reducerInput) {
			if (!keyWithSums.containsKey(pair.getKey())) {
				List<Integer> pairValue = pair.getValue();
				keyWithSums.put(pair.getKey(), pairValue);

			} else {
				int newLength = keyWithSums.get(pair.getKey()).get(0) + pair.getValue().get(0);
				int newCount = keyWithSums.get(pair.getKey()).get(1) + pair.getValue().get(1);
				List<Integer> newLenghtCountList = Arrays.asList(newLength, newCount);
				keyWithSums.put(pair.getKey(), newLenghtCountList);

			}
		}

		

		for (Map.Entry<String, List<Integer>> keyWithAggregatedSum : keyWithSums.entrySet()) {

			KeyValue<String, List<Integer>> kv = new KeyValue<String, List<Integer>>(keyWithAggregatedSum.getKey(),
					keyWithAggregatedSum.getValue());
			reducerOutput.add(kv);

		}
		
		for(KeyValue<String, List<Integer>> kv : reducerOutput) {
			String key = kv.getKey();
			double average = kv.getValue().get(0) / kv.getValue().get(1);
			KeyValue<String, Double> newKey = new KeyValue<String, Double>(key, average);
			reducerAverageOutput.add(newKey);
			
		}

	}
	
	
	public void printReducerInput() {
		reducerInput.forEach(e -> System.out.println("< " + e.getKey() + ", " + e.getValue() + " >"));
	}

	public void printReducerOutput() {
		reducerAverageOutput.forEach(e -> System.out.println("< " + e.getKey() + ", " + e.getValue() + " >"));
	}

}

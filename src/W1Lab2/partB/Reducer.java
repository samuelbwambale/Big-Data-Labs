package W1Lab2.partB;

import java.util.ArrayList;
import java.util.List;

import W1Lab2.partA.GroupByPair;
import W1Lab2.partA.KeyValue;


public class Reducer {
	private List<KeyValue<String, Integer>> reducerInput;
	private List<KeyValue<String, Integer>> reducerOutput;
	private List<GroupByPair<String, Integer>> groupPairs;

	public Reducer() {
		this.reducerInput = new ArrayList<>();
		this.reducerOutput = new ArrayList<>();
	}

	public void addKeyValue(KeyValue<String, Integer> kv) {
		reducerInput.add(kv);
	}

	public void reduce() {
		groupPairs = new ArrayList<GroupByPair<String, Integer>>();

		for (KeyValue<String, Integer> pair : reducerInput) {
			GroupByPair<String, Integer> check = new GroupByPair<String, Integer>((String) pair.getKey());
			if (!groupPairs.contains(check)) {
				check.getValues().add(1);
				groupPairs.add(check);

			} else {
				for (GroupByPair<String, Integer> gp : groupPairs) {
					if (gp.getKey().equals(pair.getKey())) {
						gp.getValues().add((Integer) pair.getValue());
					}
				}

			}
		}

		for (GroupByPair<String, Integer> groupByPair : groupPairs) {
			int sum = 0;
			for (int x : groupByPair.getValues()) {
				sum += x;
			}
			KeyValue<String, Integer> k = new KeyValue<>(groupByPair.getKey(), sum);
			reducerOutput.add(k);

		}

	}

	public void printReducerInput() {
		groupPairs.forEach(e -> System.out.println("< " + e.getKey() + ", " + e.getValues() + " >"));
	}

	public void printReducerOutput() {
		reducerOutput.forEach(e -> System.out.println("< " + e.getKey() + ", " + e.getValue() + " >"));
	}

}

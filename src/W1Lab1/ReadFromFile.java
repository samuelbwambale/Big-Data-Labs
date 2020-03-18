package W1Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromFile {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<KeyValue> listOfWords = new ArrayList<>();
		String data = "";
		try {
			File myObj = new File("src/textFile.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = data + " " + myReader.next();

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m1 = p.matcher(data);
		while (m1.find()) {
			listOfWords.add(new KeyValue<String, Integer>(m1.group().toLowerCase(), 1));
		}

//		Collections.sort(listOfWords, (a, b) -> {
//			return a.compareTo(b);
//		});
		System.out.println(listOfWords.size());
		listOfWords.forEach(e -> System.out.println("(" + e.getKey() + ", " + e.getValue() + ")"));

	}
}

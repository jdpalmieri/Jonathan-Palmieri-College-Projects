import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/* CS2223 Algorithms  
 * @author Jonathan Palmieri
 * Assignment 3
 * Q1 HashTables, and Machine Learning
 */

public class Document {
	public LinearProbingHashST<String, Term> Terms = new LinearProbingHashST<String, Term>();
	private static Scanner SCAN = new Scanner(System.in);
	private String finalPath;
	private String test = "He is quiet and small, he is black";
	public Document(String path, String name) {	
		this.finalPath = path + name;
		try {
			File file = new File(finalPath);
			Scanner input = new Scanner(file);
			while (input.hasNext()){
				String word = input.next();
				word.replaceAll("\\p{Punct}","");
				word.toLowerCase();
				if(word.length() == 0) {
					continue;
				}
				if(Terms.get(word) == null) {
					Terms.put(word, new Term(word, name));
				}else {
					Terms.get(word).increase();
				}
			}
			input.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(extractTerm(test));
	}

	public String printPath(){
		return finalPath;
	}
	
	public String extractTerm(String line) {
		System.out.println(line);
		//System.out.println(line.indexOf(' '));
		return line.substring(line.indexOf(' '));
	}
}

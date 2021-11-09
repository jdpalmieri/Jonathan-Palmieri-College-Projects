import java.util.LinkedList;

/* CS2223 Algorithms  
 * @author Jonathan Palmieri
 * Assignment 3
 * Q1 HashTables, and Machine Learning
 */

public class Data {
	public LinkedList<String> DocsList;
	public LinkedList<Integer> freq;
	public LinkedList<Double> TFIDF;
	
	public Data(LinkedList<String> DocsList, LinkedList<Integer> freq, LinkedList<Double> TFIDF) {
		this.DocsList = DocsList;
		this.freq = freq;
		this.TFIDF = TFIDF;
	}
	
	//Prints Data into console in readable formate
	public void printData() {
		//System.out.println("Document: Freq: TFIDF:");
		System.out.printf("Document: %31s Freq: %2s TFIDF: \n"," "," ");
		for(int i = 0; i < DocsList.size(); i++) {
			System.out.printf(" %40s Freq: %2d TFIDF: %10f \n", DocsList.get(i), freq.get(i), TFIDF.get(i));
			//System.out.println(DocsList.get(i) + " Freq: " + freq.get(i) + " TFIDF: " + TFIDF.get(i));
		}
	}
	
}

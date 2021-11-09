/* CS2223 Algorithms  
 * @author Jonathan Palmieri
 * Assignment 3
 * Q1 HashTables, and Machine Learning
 */

public class Term {

	private String termName;
	private String doc;
	private int frequency;
	private double TFIDF;
	
	public Term(String name, String doc) {
		this.TFIDF = 0;
		this.termName = name;
		this.doc = doc;
		this.frequency = 1;
	}
	
	public String getDoc() {
		return this.doc;
	}
	
	public String getName() {
		return this.termName;
	}
	
	public void increase() {
		this.frequency++;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
	
	public double getTFIDF() {
		return this.TFIDF;
	}
	
	public void calcTFIDF(int n, int c) {
		this.TFIDF = this.frequency * Math.log(1 + ((double) n / (double) c));
	}
	
	
}

import java.util.LinkedList;

/* CS2223 Algorithms  
 * @author Jonathan Palmieri
 * Assignment 3
 * Q1 HashTables, and Machine Learning
 */

public class BigTerm {

	private String name;
	private LinkedList<Term> Terms = new LinkedList<Term>();
	public BigTerm(Term t) {
		this.name = t.getName();
		Terms.add(t);
	}
	
	public void addTerm(Term n) {
		Terms.add(n);
	}
	
	public LinkedList<Term> getTerms() {
		return Terms;
	}
	
	public int calcNumDocs() {
		return Terms.size();
	}
	
	//count-of-word-across-all-docs
	public int calcCOWAAD() {
		int count = 0;
		for(Term t : Terms) {
			count += t.getFrequency();
		}
		return count;
	}
	
	
}

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/* CS2223 Algorithms  
 * @author Jonathan Palmieri
 * Assignment 3
 * Q1 HashTables, and Machine Learning
 */

/*
 * Instruction for How to use Program.
 * Make Sure to change the file Path labeled as the string variable PATH to the correct path for your computer.
 *   
 */

public class Main {

	//Changes this String to the proper file path for the program to run. 
	public static final String PATH = "data\\";
	public static LinkedList<Document> Documents = new LinkedList<Document>();
	public static LinkedList<String> SearchTest = new LinkedList<String>();
	public static LinearProbingHashST<String, BigTerm> DocFreq = new LinearProbingHashST<String, BigTerm>();
	public static void main(String[] args) {
		Main m = new Main();

		File folder = new File(PATH);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				Documents.add(new Document(PATH ,file.getName()));
				//System.out.println(file.getName());
			}
		}

		for(Document d : Documents) {
			for(Term t : d.Terms.getValues()) {
				if(DocFreq.get(t.getName()) != null) {
					DocFreq.get(t.getName()).addTerm(t);
				}else {
					DocFreq.put(t.getName(), new BigTerm(t));
				}
			}
		}


		for(BigTerm bt : DocFreq.getValues()) {
			for(Term t : bt.getTerms()) {
				//System.out.println(t.getFrequency());
				t.calcTFIDF(bt.calcNumDocs(), bt.calcCOWAAD());
				//System.out.println(t.getTFIDF());
			}
		}

		//Test for Search 
		m.search("it").printData();
		
		//Test for Top 10
		System.out.println("\n Documents: " + Documents.get(0).printPath());
		for(Term t : m.top10(Documents.get(0))) {
			System.out.printf("%40s TFIDF: %f \n",t.getName(), t.getTFIDF());
		}
		Random rand = new Random();
		for(int i = 0; i < DocFreq.getValues().size()/10; i++) {
			SearchTest.add(DocFreq.getValues().get(rand.nextInt(DocFreq.getValues().size())).getTerms().get(0).getName());
		}
		
		for(String s: SearchTest) {
			m.search(s);
		}
		
		Stopwatch timer = new Stopwatch();
		System.out.print("\n Time: " + timer.elaspedTine() +" seconds");
		
	}

	public Data search(String key) {
		LinkedList<String> DocsList = new LinkedList<String>();
		LinkedList<Integer> freq = new LinkedList<Integer>();
		LinkedList<Double> TFIDF = new LinkedList<Double>();
		for(Term t : DocFreq.get(key).getTerms()) {
			DocsList.add(t.getDoc());
			freq.add(t.getFrequency());
			TFIDF.add(t.getTFIDF());
		}
		return new Data(DocsList, freq, TFIDF);
	}

	public LinkedList<Term> top10(Document d){
		LinkedList<Term> DocTerms = d.Terms.getValues();
		LinkedList<Term> top10 = new LinkedList<Term>();
		double max = 0;
		Term currentTerm = null;
		for(int i = 0; i < 9; i++) {
			for(Term t: DocTerms) {
				if(t.getTFIDF() > max) {
					max = t.getTFIDF();
					currentTerm = t;
				}
			}
			top10.add(currentTerm);
			DocTerms.remove(currentTerm);
			max = 0;
			currentTerm = null;
		}
		return  top10;
	}



}

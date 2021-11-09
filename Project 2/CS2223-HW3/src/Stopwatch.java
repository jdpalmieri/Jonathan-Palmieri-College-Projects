
/* CS2223 Algorithms 
 * Assignment 2 
 * @author Jonathan Palmieri
 * Question 2 HeapSort Class and Evaluation
 */
public class Stopwatch {
	private final long start; 
	
	public Stopwatch() {
		start = System.currentTimeMillis();
	}
	
	public double elaspedTine() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
}

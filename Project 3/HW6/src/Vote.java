
public class Vote {

	private String candidate;
	private int choiceNum;
	/**
	 * Vote is an Object that holds a candidates name and a Natural
	 * @param candidate String for the name of the candidate being voted for.
	 * @param choiceNum int for the rank in which the voter prefers the candidate Ex. First choice is 1
	 */
	public Vote(String candidate, int choiceNum) {
		this.candidate = candidate;
		this.choiceNum = choiceNum;
	}
	public String getCandidate() {
		return candidate;
	}
	public int getChoiceNum() {
		return choiceNum;
	}
	
}

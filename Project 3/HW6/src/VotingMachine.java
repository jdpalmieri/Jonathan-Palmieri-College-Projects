import java.util.Scanner;

public class VotingMachine {

	ElectionData ED = new ElectionData();
	Scanner keyboard = new Scanner(System.in);
	
	public VotingMachine(){
		
	}
	/**
	 * adds a Write in candidate to the ballot in Election Data 
	 * @param name String for the name of the candidate being added.
	 * @throws CandidateExistsException Exception is thrown if the candidate is already on the ballot.
	 */
	public void addWriteIn(String name) throws CandidateExistsException {
		try {
			ED.addCandidate(name);
		}catch (CandidateExistsException e) {
			System.out.println("Candidate All ready Exists");
		}
	}
	/**
	 * Prints candidates on the ballot
	 */
	public void printBallot() {
		System.out.println("The candidates are ");
		for (String s : ED.getBallot()) {
			System.out.println(s);
		}
	}
	
	/**
	 * Handles the User Interface in the console and gives the user instruction on how to vote
	 * @throws UnknownCandidateException Exception for an Unknown Candidate being voted for. 
	 * @throws DuplicateVotesException Exception for an Duplicate Candidate being voted for in the same vote.
	 * @throws CandidateExistsException Exception for an Existing Candidate trying to be added to the ballot.
	 */
	public void screen() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException {
		this.printBallot();
		System.out.println("Who do you want to vote for?");
		String candidate = keyboard.next();
		System.out.println("You voted for " + candidate);
		System.out.println("Who do you want to vote for 2nd?");
		String candidate2 = keyboard.next();
		System.out.println("You voted for " + candidate2);
		System.out.println("Who do you want to vote for 3nd?");
		String candidate3 = keyboard.next();
		System.out.println("You voted for " + candidate3);
		try {
			ED.processVote(candidate, candidate2, candidate3);
			System.out.println("Thank You for Voting");
		}catch (UnknownCandidateException e) {
			System.out.println("Unknown Candidate");
			System.out.println("Would you like to add this candidate to the ballot?");
			String input = keyboard.next();
			if(input.equalsIgnoreCase("y")) {
				addWriteIn(e.getUnfoundName());
				screen();
			}else {
				screen();
			}
		}catch (DuplicateVotesException e) {
			System.out.println("Can't vote for the same candidate twice");
			screen();
		}
	}

	//main method
	public static void main(String args[]) throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException {
		VotingMachine v = new VotingMachine();
		v.screen();
	}
}

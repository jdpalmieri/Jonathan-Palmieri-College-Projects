import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
	
	private LinkedList<String> ballot = new LinkedList<String>();
	private HashMap<Integer,LinkedList<Vote>> votes = new HashMap<Integer ,LinkedList<Vote>>();
	public ElectionData() {
		this.ballot.add("Gompei");
		this.ballot.add("Husky");
		for(String s: ballot) {
			this.votes.put(s.hashCode(), new LinkedList<Vote>());
		}
	}
	
	/**
	 * Getter for ballot
	 * @return LinkedList<String> people on the ballot
	 */
	public LinkedList<String> getBallot(){
		return this.ballot;
	}
	/**
	 * getter for Votes returns the LinkedList associated with a given hashCode value of a string.
	 * @param voteNum int hashValue used to find the LinkListed<Vote> Ex. hashValue of a candidates name
	 * @return LinkedList<Votes> for a given Candidate 
	 */
	public LinkedList<Vote> getVotes(int voteNum){
		return this.votes.get(voteNum);
	}

	/**
	 * Helper function that adds a vote and the vote rank to a candidate in hashTable
	 * @param voteNum int for priority rank of the vote Ex. First choice would be value 1
	 * @param vote Vote object that stores the candidate and the rank. Ex. new Vote("Husky", 2)
	 */
	public void addVote(int voteNum, Vote vote){
		this.votes.get(voteNum).addFirst(vote);
	}

	/**
	 * Processes an incoming vote and will add the vote to the hashMap with corresponding ranking of vote choice
	 * @param vote1 Name of First choice candidate.
	 * @param vote2 Name of Second choice candidate. 
	 * @param vote3	Name of Third choice candidate. 
	 * @throws UnknownCandidateException Exception for a candidate who is not on the ballot
	 * @throws DuplicateVotesException Exception for a candidate who was voted for twice
	 */
	public void processVote(String vote1, String vote2, String vote3) throws UnknownCandidateException, DuplicateVotesException {
		LinkedList<String> temp = new LinkedList<String>();
		temp.add(vote2);
		temp.add(vote3);
		if(!ballot.contains(vote1)) {
			throw new UnknownCandidateException(vote1);
		}else if(!ballot.contains(vote2)) {
			throw new UnknownCandidateException(vote1);
		}else if(!ballot.contains(vote3)) {
			throw new UnknownCandidateException(vote1);
		}else if(temp.contains(vote1)) {
			throw new DuplicateVotesException(vote1);
		}else if(vote2.equals(vote3)) {
			throw new DuplicateVotesException(vote2);
		}else {
			addVote(vote1.hashCode(), new Vote(vote1 , 1));
			addVote(vote2.hashCode(), new Vote(vote2 , 2));
			addVote(vote3.hashCode(), new Vote(vote3 , 3));
		}
	}

	/**
	 * adds a Candidate to the ballot 
	 * @param name takes a String and adds it to the ballot  
	 * @throws CandidateExistsException Exception for if the candidate provided already exists 
	 */
	public void addCandidate(String name) throws CandidateExistsException {
		if(ballot.contains(name)) {
			throw new CandidateExistsException(name);
		}else {
			this.ballot.add(name);
			this.votes.put(name.hashCode(), new LinkedList<Vote>());
		}
	}
	/**
	 * Finds the Winner with the Most First ranked votes
	 * @return String with the winners name of "Runoff required"
	 */
	public String findWinnerMostFirstVotes() {
		LinkedList<Integer> totals = new LinkedList<Integer>();
		for(String s: ballot) {
			int total = 0;
			for(Vote v: this.getVotes(s.hashCode())){
				if(v.getChoiceNum() == 1) {
					total++;
				}
			}
			totals.add(total);
		}
		int totalVotes = 0;
		for(Integer i: totals) {
			totalVotes += i;
		}
		int winnerIndex = -1;
		int mostVote = -1;
		for(int i = 0; i < totals.size(); i++) {
			if(mostVote < totals.get(i)) {
				mostVote = totals.get(i);
				winnerIndex = i;
			}
		}
		if(winnerIndex > -1 && mostVote > -1 && ((double) mostVote > ((double) totalVotes/2.0))) {
			return ballot.get(winnerIndex);
		}
		return "Runoff required";
	}
	
	/**
	 * Finds the winner with the most points
	 * @return the Winner with the most points or if there is a tie the first of those winner to appear in the ballot.
	 */
	public String findWinnerMostPoints()
	{
		LinkedList<Integer> totals = new LinkedList<Integer>();
		for(String s: ballot) {
			int total = 0;
			for(Vote v: this.getVotes(s.hashCode())){
				if(v.getChoiceNum() == 1) {
					total += 3;
				}if(v.getChoiceNum() == 2) {
					total += 2;
				}if(v.getChoiceNum() == 3) {
					total += 1;
				}
			}
			totals.add(total);
		}
		int winnerIndex = -1;
		int mostVote = -1;
		for(int i = 0; i < totals.size(); i++) {
			if(mostVote < totals.get(i)) {
				mostVote = totals.get(i);
				winnerIndex = i;
			}
		}
		if(winnerIndex > -1 && mostVote > -1){
			return ballot.get(winnerIndex);
		}
		return null;
	}
	
}

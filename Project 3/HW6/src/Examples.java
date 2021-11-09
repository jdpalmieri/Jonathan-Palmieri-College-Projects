import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Examples {

	public Examples() {

	}
	
	ElectionData Setup1 () {
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {

			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes

		try {

			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("gompei", "ziggy", "husky");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	ElectionData Setup2 () {
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {

			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes

		try {

			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("gompei", "ziggy", "husky");
			ED.processVote("husky", "gompei", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	ElectionData Setup3 () {
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {

			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes

		try {

			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	ElectionData Setup4 () {
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes

		try {

			ED.processVote("Gompei", "husky", "ziggy");
			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	
	// Runs a test on Election Setup1
	@Test
	public void testMostFirstWinner () {
		assertEquals("gompei", Setup1().findWinnerMostFirstVotes());
	}
	@Test
	public void testMostPointsWinner () {
		assertEquals("gompei", Setup1().findWinnerMostPoints());
	}
	// Runs a test on Election Setup2
	@Test
	public void testMostFirstWinner2 () {
		assertEquals("Runoff required", Setup2().findWinnerMostFirstVotes());
	}
	@Test
	public void testMostPointsWinner2 () {
		assertEquals("gompei", Setup2().findWinnerMostPoints());
	}
	// Runs a test on Election Setup3
	@Test
	public void testMostFirstWinner3 () {
		assertEquals("Runoff required", Setup3().findWinnerMostFirstVotes());
	}
	@Test
	public void testMostPointsWinner3 () {
		assertEquals("gompei", Setup3().findWinnerMostPoints());
	}
	
	
	@Test(expected = UnknownCandidateException.class)
	public void testException1() throws UnknownCandidateException, DuplicateVotesException {
		this.Setup4().processVote("gompei", "husky", "ziggy");
	}
	@Test(expected = UnknownCandidateException.class)
	public void testException2() throws UnknownCandidateException, DuplicateVotesException {
		this.Setup1().processVote("gompei", "bob", "ziggy");
	}
	
	@Test(expected = DuplicateVotesException.class)
	public void testException3() throws UnknownCandidateException, DuplicateVotesException {
		this.Setup1().processVote("gompei", "gompei", "ziggy");
	}
	
	@Test(expected = DuplicateVotesException.class)
	public void testException4() throws UnknownCandidateException, DuplicateVotesException {
		this.Setup1().processVote("husky", "husky", "ziggy");
	}
	
	@Test(expected = CandidateExistsException.class)
	public void testException5() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException {
		this.Setup1().addCandidate("husky");
	}
	@Test(expected = CandidateExistsException.class)
	public void testException6() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException {
		this.Setup1().addCandidate("ziggy");
	}
	
}

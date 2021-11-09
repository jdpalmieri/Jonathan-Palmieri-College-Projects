
public class DuplicateVotesException extends Exception {
	String unfoundName;
	public DuplicateVotesException(String name) {
      this.unfoundName = name;
    }
}

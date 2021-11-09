
public class UnknownCandidateException extends Exception {
	
	private String unfoundName;
	public UnknownCandidateException(String name) {
		this.unfoundName = name;
    }
	
	public String getUnfoundName() {
		return unfoundName;
	}
	
}

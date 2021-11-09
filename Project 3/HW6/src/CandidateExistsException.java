
public class CandidateExistsException extends Exception {
	private String unfoundName;
	public CandidateExistsException(String name) {
		this.unfoundName = name;
    }
	
	public String getDuplicateName() {
		return unfoundName;
	}
	
}

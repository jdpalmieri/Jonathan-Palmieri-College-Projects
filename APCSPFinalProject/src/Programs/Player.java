package Programs;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * @author Jonathan Palmieri
 * This Class creates and stores a list of valid credentials
 * It is also responsible for checking if credentials inputed are valid 
 */
public class Player {
	private ArrayList<String> Password = new ArrayList<String>();
	private ArrayList<String> Username = new ArrayList<String>();
	private ArrayList<Array> Stats = new ArrayList<Array>();
	private String un;
	private String pass;
	private boolean login;
	//Player Constructor
	public Player(String un, String pass) {
		this.un = un;
		this.pass = pass;
		loadIndex();
	}
	
	//Loads Test User Credentials
	public void loadIndex(){
		for(int i = 0; i < 50; i++) {
			Username.add(i,"user" + i);
			Password.add(i,"pass" + i);
		}
	}
	
	//Tests to see if Inputed Credentials are Valid
	public void login() {
		for(int i = 0; i < Username.size(); i++) {
			if(un.equals(Username.get(i))) {
				if(pass.equals(Password.get(i))) {
					System.out.println("You're In! Welcome " + un);
					login = true;
				}
				else {
					System.out.println("Wrong");
				}
			}
		}
	}
	
	public void getStats() {
		
	}
	//Returns the User's login state
	public boolean d() {
		return login;
	}
}

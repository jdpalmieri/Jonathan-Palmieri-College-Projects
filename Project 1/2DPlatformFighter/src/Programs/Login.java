package Programs;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import acm.program.GraphicsProgram;

/*
 * @author Jonathan Palmieri
 * This Class creates and launches a Login Application.
 * It asks for a username and password and prints a console output if it matches a pair of stored credentials. 
 */
public class Login extends GraphicsProgram {
	private static final int APP_X = 800;
	private static final int APP_Y = 800;
	String userName, passWord;
	JTextField passbox = new JPasswordField(10);
	JTextField unbox = new JTextField(10);
	JButton login = new JButton("login");
	Player p6;
	static String[] arg;
	private static boolean l = false;
	
	//Main Method 
	public static void main(String[] args) {
		if(!l)
			new Login().start(args);
		arg = args;
	}
	//Initializes all Listeners 
	public void init(){
		setSize(APP_X, APP_Y);
		login();
		getGCanvas().setFocusable(true);
		addKeyListeners();
		addActionListeners();
	}
	//Creates Swing Objects Required for Application
	public void login(){
		add(new JLabel("Username: "),SOUTH);
		add(unbox, SOUTH);
		add(new JLabel("Password: "),SOUTH);
		add(passbox, SOUTH);
		add(login, SOUTH);
	}
	//Action Listener for Login Button
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("login")) {
			userName = unbox.getText(); 
			passWord = passbox.getText(); 
			p6 = new Player(userName, passWord); 
			p6.login();
			l = p6.d();
			if(l)
				ACMTest.main(arg);
	
		}
	}
}

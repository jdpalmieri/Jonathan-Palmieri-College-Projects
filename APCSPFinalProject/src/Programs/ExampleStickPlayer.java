package Programs;

import java.awt.Color;
import java.awt.Graphics;

/*
 * @author Jonathan Palmieri
 * This Class is responsible for handling all variables associated with a given ExampleStickPlayer
 * This includes: 
 * - A Player's x and y cords 
 * - Direction a Player is facing
 * - If the player is Crouched
 * - A Player's HitBox
 * - A Player's Stats
 */
public class ExampleStickPlayer {
	private int x;
	private int y;
	private int playerDir;
	boolean Crouch;
	SolidObject hitbox;
	Stats stat;
	// ExampleStickPlayer Constructor
	public ExampleStickPlayer(int x, int y, int d){
		this.x = x;
		this.y = y;
		this.playerDir = d;
		stat = new Stats(100, 5, 5);
		hitbox = new SolidObject();
	}
	//Gets Player's X position
	public int getX() {
		return this.x;
	}
	//Gets Player's Y position
	public int getY() {
		return this.y;
	}
	
	//Sets Player's X position
	public int setX(int i) {
		x = x - i;
		return this.getX();
	}
	
	//Sets Player's Y position 
	public int setY(int i) {
		y = y - i;
		return this.getY();
	}
	//Gets Player's HitBox as a SolidObject
	public SolidObject getHitbox() {
		return this.hitbox;
	}
	//Gets Player's direction
	public int getPlayerDir() {
		return this.playerDir;
	}
	
	//Sets Player's Direction
	public void setPlayerDir(int d) {
		this.playerDir = d;
	}
	
	//Updates Players Crouch State
	public void Crouch(boolean n) {
		Crouch = n;
	}
	
	//Resets Players Stats and position to it's starting state 
	public void refresh(int x, int y) {
		this.stat.refresh();
		this.x = x;
		this.y = y;
	}
}
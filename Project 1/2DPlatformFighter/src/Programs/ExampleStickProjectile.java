package Programs;

/*
 * @author Jonathan Palmieri
 * This Class is responsible for handling all variables associated with a given ExampleStickProjectile
 * This includes: 
 * - A Projectile's x and y cords 
 * - Direction a Projectile is facing
 * - A Projectile's HitBox
 * - A Player's X and Y cord as an Origin point.
 */
public class ExampleStickProjectile {
	private int x;
	private int y;
	private int d;
	private SolidObject hitbox;
	// ExampleStickProjectile Constructor
	public ExampleStickProjectile(int sx, int sy, int d){
		this.x = sx;
		this.y = sy;
		this.d = d;
		hitbox = new SolidObject();
	}
	//Gets Projectile's X position
	public int getPX() {
		return this.x;
	}
	//Gets Projectile's Y position
	public int getPY() {
		return this.y;
	}
	//Gets Projectile's HitBox as a SolidObject
	public SolidObject getHitbox() {
		return this.hitbox;
	}
	//Sets Projectile's X position
	public void setPX(int x){
		this.x = x;
	}
	//Sets Projectile's Y position
	public void setPY(int y){
		this.y = y;
	}
	//Gets Projectile's direction
	public int getDir() {
		return this.d;
	}
	//Sets Projectile's direction
	public void setDir(int d) {
		this.d = d;
	}
	
}

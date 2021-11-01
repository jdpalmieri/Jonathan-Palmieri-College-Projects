package Programs;

/*
 * @author Jonathan Palmieri
 */
public abstract class ExampleStickGun {
	SolidObject hitbox;
	public ExampleStickGun(int x, int y, int d) {
		hitbox = new SolidObject();
	}
	public abstract void clipSize();{}

	public abstract void fireRate(); {}



}


package Programs;

/*
 * @author Jonathan Palmieri
 * This Class is Responsible for holding Stat values for the ExampleStickPlayer
 * This Includes:
 * - Health Points or hp of a Player
 * - Damage or dmg each attack does
 * - Speed or spd of a Player
 */
public class Stats {
	int hp;
	int dmg;
	int spd;
	
	// Stats Constructor
	public Stats(int hp, int dmg, int spd) {
		this.hp = hp;
		this.dmg = dmg;
		this.spd = spd;
	}
	//Gets Hp aka Health Points
	public int getHp() {
		return this.hp;
	}
	//Gets Dmg aka Damage
	public int getDmg() {
		return this.dmg;
	}
	//Gets spd aka Speed
	public int getSpd() {
		return this.spd;
	}
	//Sets Hp aka Health Points
	public void setHp(int i) {
		this.hp = this.hp-i;
	}
	//Sets Hp back to 100 (Default Case)
	public void refresh() {
		this.hp = 100;
	}
}

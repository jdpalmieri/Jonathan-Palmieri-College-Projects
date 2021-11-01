package Programs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/*
 * @author Jonathan Palmieri
 * 
 * Project Description:
 * ExampleStickFighter is a simple 2D platform fighting game that utilizes Stanford's ACM graphics library. 
 * 
 */

//This Class is the Main Class for this program and is responsible for building the game itself.
public class ExampleStickFighter extends JoeApplet  implements KeyListener, MouseListener, MouseMotionListener{
	
	ArrayList<SolidObject> floor = new ArrayList<SolidObject>();
	ArrayList<SolidObject> wallL = new ArrayList<SolidObject>();
	ArrayList<SolidObject> wallR = new ArrayList<SolidObject>();
	ArrayList<ArrayList<SolidObject>> Border = new ArrayList<ArrayList<SolidObject>>();
	ArrayList<ExampleStickProjectile> Proj = new ArrayList<ExampleStickProjectile>();
	ArrayList<ExampleStickProjectile> Proj2 = new ArrayList<ExampleStickProjectile>();
	private boolean[] moves = new boolean[8];
	private Color[] color = {Color.YELLOW.darker(), Color.RED, Color.GREEN}; 
	ExampleStickPlayer James, Jon;
	private static final String path ="C:\\Users\\Jonathan\\Desktop\\Player Sprite\\";
	private long timer = System.currentTimeMillis();
	//private boolean start = true;
	private int x = 50, x2 = 600;
	private int y = 50;
	private int ammo = 30, ammo2 = 30;
	int cooldown = (int) System.currentTimeMillis() + 2;
	boolean delay = false, delay2 = false;
	Font font = new Font("Times New Roman", Font.BOLD,45);
	private int grav1 = -2, grav2 = -2; 
	//Image TitleBack = new Image(path+"//TitleScreenBackground"); 
	//----------------Test Varriables---------
	boolean blade = false;
	boolean ground1 = false , ground2 = false;
	ArrayList<ExampleStickProjectile> bladeList = new ArrayList<ExampleStickProjectile>();
	//----------------------------------------
	
	
	/*Runs when program is initialized
	 *Adds all Key and Mouse Listeners 
	 *Creates Player Object and Loads the SolidObjects (Collision Objects) for Boarder Walls 
	 */
	public void init()
	{
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		James = new ExampleStickPlayer(50, 50, 1); 
		Jon = new ExampleStickPlayer(600, 50, -1); 
		loadBorder();
	}
	
	/*Paints all objects in the Applet. 
	 *Functions as the main method for ACM Graphics Library
	 */
	public void paint(Graphics art) {
		//menu(art);
		setSize(1000,900);
		setBackground(Color.BLACK);
		makeTimer(art);
		Collide();
		gravity();
		booMoves(); 	
		makePlayer(art, James);
		makePlayer(art, Jon);
		makeProjectile(art);
		if((int) System.currentTimeMillis() == cooldown) {
			System.out.print((int) System.currentTimeMillis() + 2);
			delay = false;
		}
		if(blade)
		makeSlash(art);	
		makeFloor(art);
		makeWalls(art);
		makeHpBar(art, James, Jon);
		makeHud(art);
		checkWin(art);
	}
	
	//Checks if either player has won the game
	public void checkWin(Graphics art) {
		if(James.stat.getHp() <= 0) {
			art.setColor(Color.YELLOW);
			art.setFont(font);
			art.drawString("Player 2 Wins", 1000/2 - 250, 450);
		}
		if(Jon.stat.getHp() <= 0) {
			art.setColor(Color.YELLOW);
			art.setFont(font);
			art.drawString("Player 1 Wins", 1000/2 - 250, 450);
		}
	}
	
	//Creates the Count Down Timer for in game overlay
	public void makeTimer(Graphics art) {
		long pastedTime = -1 * (timer - System.currentTimeMillis())/1000;
		long totalTime = 90;
		//System.out.println(totalTime - pastedTime);
		long finalTime = totalTime - pastedTime;
		art.setColor(Color.GREEN);
		art.setFont(font);
		art.drawString("Time: "+ finalTime, 400, 80);
		if(finalTime <= 0){
			James.stat.refresh();
			Jon.stat.refresh();
			pastedTime = 0;
			timer = System.currentTimeMillis();
		}
	}
	
	//Creates Health Bars for in game overlay 
	public void makeHpBar(Graphics art, ExampleStickPlayer p, ExampleStickPlayer p2) {
		int hp = p.stat.getHp();
		int ct = 2, ct2 = 2;
		if(hp > 50)ct = 2;
		if(hp <= 50)ct = 0;
		if(hp < 20)ct = 1;
		art.setColor(Color.CYAN);
		art.drawRect(50, 30, 2*hp, 40);
		art.setColor(color[ct]);
		art.fillRect(50, 30, 2*hp, 40);
		int hp2 = p2.stat.getHp();
		if(hp2 > 50)ct2 = 2;
		if(hp2 <= 50)ct2 = 0;
		if(hp2 < 20)ct2 = 1;
		art.setColor(Color.CYAN);
		art.drawRect(1000-(2*hp2)-50, 30, 2*hp2, 40);
		art.setColor(color[ct2]);
		art.fillRect(1000-(2*hp2)-50, 30, 2*hp2, 40);
	}
	
	//Creates Ammo Count and Other Overlay Stats
	public void makeHud(Graphics art) {
		if(ammo > 0) {art.setColor(color[0]);}
		else{art.setColor(color[1]);}
		art.setFont(font);
		art.drawString(ammo +"/30", 50, 120);
		if(ammo2 > 0) {art.setColor(color[0]);}
		else{art.setColor(color[1]);}
		art.drawString(ammo2 +"/30", 840, 120);
	}
	//Creates Player
	public void makePlayer(Graphics art, ExampleStickPlayer p) {
		int i = 0;
		if(p.Crouch == true)
			i = 30;
		p.getHitbox().makeSolidObject(p.getX(), p.getY()+i, 50, 80-i);
		art.setColor(Color.BLUE);
		art.fillRect(p.getX(), p.getY()+i, 50, 80-i);
	}
	
	//Applies Gravity if not touching the floor
	public void gravity() {
		if(!ground1)
			James.setY(grav1);
	 	if(!ground2)
			Jon.setY(grav2);
	}

	//-------------------------Attacks and Abilities-----------------
	//Makes A Projectile
	public void makeProjectile(Graphics art) {
		for(ExampleStickProjectile p : Proj	) {
			p.getHitbox().makeSolidObject(p.getPX(), p.getPY(), 20, 20);
			art.setColor(Color.GREEN);
			art.fillRect(p.getPX(), p.getPY(), 20, 20);
			//System.out.println("LLLLL");
			p.setPX(p.getPX()+(5*p.getDir()));
		}
		for(ExampleStickProjectile p : Proj2) {
			p.getHitbox().makeSolidObject(p.getPX(), p.getPY(), 20, 20);
			art.setColor(Color.GREEN);
			art.fillRect(p.getPX(), p.getPY(), 20, 20);
			//System.out.println("LLLLL");
			p.setPX(p.getPX()+(5*p.getDir()));
		}
	}
	//Controls the dash Ability for each player
		public void makeDash(ExampleStickPlayer p) {
			for(int i = 0; i < 10; i++) {
				p.setX(p.getPlayerDir()*-10);
			}
		}
	//Slash Attack
	public void makeSlash(Graphics art) {
		art.setColor(Color.GREEN);
		art.fillRect(bladeList.get(0).getPX(), bladeList.get(0).getPY()-10, 20, 20);
		bladeList.get(0).getHitbox().makeSolidObject(bladeList.get(0).getPX(), bladeList.get(0).getPY(), 20, 20);
		if(bladeList.get(0).getPX() < James.getX()+70) {
			bladeList.get(0).setPX(bladeList.get(0).getPX()+2);
		}
		if(bladeList.get(0).getPY() > James.getY()) {
			bladeList.get(0).setPY(bladeList.get(0).getPY()-2);
		}
		if(bladeList.get(0).getPY() <= James.getY()) {
			bladeList.remove(bladeList.get(0));
			blade = false;
		}
	}
	//-------------------Borders------------------------------------
	
	//Loads all SolidObjects from Floors and Walls
	public void loadBorder() {
		for(int i = 1; i < 15; i++) {
			floor.add(new SolidObject());
		}
		for(int i = 1; i < 10; i++) {
			wallL.add(new SolidObject());
			wallR.add(new SolidObject());
		}
	}
	
	//Makes The Floor for the level
	public void makeFloor(Graphics art) {
		
		for(int i = 0; i < 10; i++) {
			floor.get(i).makeSolidObject(100 * i,700, 100, 50);
			art.setColor(Color.RED);
			art.fillRect(100 * i, 700, 100, 50);
		}
		for(int i = 10; i < floor.size(); i++) {
			floor.get(i).makeSolidObject(50 * i,500, 50, 50);
			art.setColor(Color.RED);
			art.fillRect(50 * i, 500, 50, 50);
		}
	}
	//Makes Right and Left Walls
	public void makeWalls(Graphics art) {
		for(int i = 0; i < wallL.size(); i++) {
			wallL.get(i).makeSolidObject(0 ,100 * i, 50, 100);
			wallR.get(i).makeSolidObject(1000 - 50, 100 * i, 100, 100);
			art.setColor(Color.RED);
			art.fillRect(0 ,100 * i, 50, 100);
			art.fillRect(1000 - 50, 100 * i, 50, 100);
		}
	}
	//Boolean Switches For Fluent Movement 
	public void booMoves()
	{
		if(moves[0] == true)
			James.setX(5);
		if(moves[1] == true)
			James.setX(-5);
		if(moves[2] == true) 
			James.setY(10); 
		if(moves[3] == true)
			James.setY(0);//set -5
		if(moves[4] == true)
			Jon.setX(5);
		if(moves[5] == true)
			Jon.setX(-5);
		if(moves[6] == true)
			Jon.setY(60); moves[6] = false;
		if(moves[7] == true)
			Jon.setY(0);// set -5
	}

	//All Collision Detection 
	public void Collide(){
		if(James.getHitbox().isColliding(floor))
			ground1 = true; 
		if(!James.getHitbox().isColliding(floor)) 
			ground1 = false;
		if(James.getHitbox().isColliding(wallL))
			James.setX(-5);
		if(James.getHitbox().isColliding(wallR))
			James.setX(5);
		if(Jon.getHitbox().isColliding(floor))
			ground2 = true; 
		if(!Jon.getHitbox().isColliding(floor))
			ground2 = false;
		if(Jon.getHitbox().isColliding(wallL))
			Jon.setX(-5);
		if(Jon.getHitbox().isColliding(wallR))
			Jon.setX(5);
		if(Proj.size() > 0) {
			ArrayList<ExampleStickProjectile> temp = new ArrayList<ExampleStickProjectile>();
				for(ExampleStickProjectile p : Proj) {
					if(p.getHitbox().isColliding(wallR) || p.getHitbox().isColliding(wallL)) {
							temp.add(p);
							}
							if(p.getHitbox().isCollidingWith(Jon.getHitbox())) {
								temp.add(p);
								Jon.setX(p.getDir()*(-25));
								Jon.stat.setHp(James.stat.getDmg());
								System.out.println("Proj Hit");
							}
						}
					for(int i = 0; i < temp.size(); i++) {
							Proj.remove(temp.get(i));
						}
		}
		if(Proj2.size() > 0) {
			ArrayList<ExampleStickProjectile> temp = new ArrayList<ExampleStickProjectile>();
				for(ExampleStickProjectile p : Proj2) {
					if(p.getHitbox().isColliding(wallR) || p.getHitbox().isColliding(wallL)) {
							temp.add(p);
							}
							if(p.getHitbox().isCollidingWith(James.getHitbox())) {
								temp.add(p);
								James.setX(p.getDir()*(-25));
								James.stat.setHp(Jon.stat.getDmg());
								System.out.println("Proj Hit");
							}
						}
					for(int i = 0; i < temp.size(); i++) {
							Proj2.remove(temp.get(i));
						}
		}
		if(bladeList.size() > 0 && bladeList.get(0).getHitbox().isCollidingWith(Jon.getHitbox())) {
			blade = false;
			System.out.println("Hit");
			Jon.setX(-25);
			Jon.setY(40);
			Jon.stat.setHp(James.stat.getDmg()*2);
			bladeList.remove(0);
		}
	}
	
	//---------------Inputs---------------------------------------------------------
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		//---------------------------Player One Movement-------------------
		if(key == arg0.VK_A)
		{
			//James.setX(5);
			James.setPlayerDir(-1);
			moves[0] = true;
			blade = false;
		}
		if(key == arg0.VK_D)
		{
			//James.setX(-5);
			James.setPlayerDir(1);
			blade = false;
			moves[1] = true;
		}
		if(key == arg0.VK_W && ground1)
		{
			//James.setY(5);
			blade = false;
			moves[2] = true;
			
		}
		if(key == arg0.VK_S && !ground1)
		{
			//James.setY(-5);
			blade = false;
			moves[3] = true;
			grav1 = -8;
			//James.Crouch(true);
		}
		if(key == arg0.VK_S && ground1)
			James.Crouch(true);	
		if(key == arg0.VK_E && !James.Crouch) {//Make Proj
			if(delay == false && ammo > 0) {
				Proj.add(new ExampleStickProjectile(James.getX(), James.getY(), James.getPlayerDir()));
				cooldown = (int) System.currentTimeMillis() + 2;
				ammo--;
			}
			delay = true;
		}
		if(key == arg0.VK_Q) {
			int count = 0;
			for(ExampleStickProjectile p: bladeList) {
				count++;
			}
			for(int i = 0; i < count; i++)
				bladeList.remove(i);
			bladeList.add(new ExampleStickProjectile(James.getX()+40, James.getY()+50, James.getPlayerDir()));
			blade = true;
		}
		if(key == arg0.VK_F)
		{
			makeDash(James);
		}
		if(key == arg0.VK_ESCAPE) 
		{
//			try {
//				//TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			timer = System.currentTimeMillis();
			ammo = 30;
			ammo2 = 30;
			James.refresh(x,y);
			Jon.refresh(x2, y);
		}
		
		//--------------------------------Player Two movement-----------------------
		if(key == arg0.VK_LEFT)
		{
			Jon.setPlayerDir(-1);
			//Jon.setX(5);
			moves[4] = true;
		}
		if(key == arg0.VK_RIGHT)
		{
			Jon.setPlayerDir(1);
			//Jon.setX(-5);
			moves[5] = true;
		}
		if(key == arg0.VK_UP && ground2)
		{
			//Jon.setY(5);
			moves[6] = true;
		}
		if(key == arg0.VK_DOWN && !ground2)
		{
			moves[7] = true;
			//Jon.setY(-5);
			//Jon.Crouch(true);
			grav2 = -8;
		}
		if(key == arg0.VK_DOWN && ground2)
			Jon.Crouch(true);
		if(key == arg0.VK_SLASH && !Jon.Crouch)
		{
			if(delay2 == false && ammo2 > 0) {
				Proj2.add(new ExampleStickProjectile(Jon.getX(), Jon.getY(), Jon.getPlayerDir()));
				ammo2--;
			}
			delay2 = true;
				
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyR = arg0.getKeyCode();
		if(keyR == arg0.VK_A)
		{
			moves[0] = false;
		}
		if(keyR == arg0.VK_D)
		{
			moves[1] = false;
		}
		if(keyR == arg0.VK_W)
		{
			moves[2] = false;
		}
		if(keyR == arg0.VK_S)
		{
			moves[3] = false;
			James.Crouch(false);
			grav1 = -2;
		}
		if(keyR == arg0.VK_E)
		{
			delay = false;
		}
		if(keyR == arg0.VK_Q)
		{

		}

		if(keyR == arg0.VK_LEFT)
		{
			moves[4] = false;
		}
		if(keyR == arg0.VK_RIGHT)
		{
			moves[5] = false;
		}
		if(keyR == arg0.VK_UP)
		{
			moves[6] = false;
		}
		if(keyR == arg0.VK_DOWN)
		{
			moves[7] = false;
			Jon.Crouch(false);
			grav2 = -2;
		}
		if(keyR == arg0.VK_SLASH) {
			delay2 = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



}

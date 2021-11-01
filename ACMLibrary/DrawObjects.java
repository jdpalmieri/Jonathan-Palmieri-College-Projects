import java.awt.Color;
import acm.graphics.*;
import acm.program.GraphicsProgram;


@SuppressWarnings("serial")
public class DrawObjects extends GraphicsProgram {

	public static void main(String[] args) {
		(new DrawObjects()).start(args);
	}
	
	public void init() {
		setBackground(Color.LIGHT_GRAY);
		setSize(600, 400);
		GRect square = new GRect(100, 100, 150, 150);
		square.setColor(Color.RED);
		square.setFilled(true);
		square.setFillColor(Color.RED);
		add(square);
		
		GOval circle = new GOval(350, 100, 150, 150);
		circle.setColor(Color.GREEN);
		circle.setFilled(true);
		circle.setFillColor(Color.GREEN);
		add(circle);	
	}

}

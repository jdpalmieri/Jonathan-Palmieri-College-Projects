/*
 * File: DrawStopLight.java
 * -----------------------
 * This program draws a traditional stoplight with a red, yellow,
 * and green light in a gray rectangular frame.
 */
 
import acm.graphics.*;
import acm.program.*;
import java.awt.*;
 
public class DrawStopLight extends GraphicsProgram {
	private static final long serialVersionUID = 1L;
	
	private static final double FRAME_WIDTH = 50;
	private static final double FRAME_HEIGHT = 100;
	private static final double LAMP_RADIUS = 10;

	public void run() {
		double cx = getWidth() / 2;
		double cy = getHeight() / 2;
		double fx = cx - FRAME_WIDTH / 2;
		double fy = cx - FRAME_HEIGHT / 2;
		double dy = FRAME_HEIGHT / 4 + LAMP_RADIUS / 2;
		GRect frame = new GRect(fx, fy, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setFilled(true);
		frame.setColor(Color.GRAY);
		add(frame);
		add(createFilledCircle(cx, cy-dy, LAMP_RADIUS, Color.RED));
		add(createFilledCircle(cx, cy, LAMP_RADIUS, Color.YELLOW));
		add(createFilledCircle(cx, cy+dy, LAMP_RADIUS, Color.GREEN));
	}
	
	private GOval createFilledCircle(double x, double y, double r,
			Color color) {
		GOval circle = new GOval(x-r, y-r, 2*r, 2*r);
		circle.setColor(color);
		circle.setFilled(true);
		return circle;
	}
	
} 

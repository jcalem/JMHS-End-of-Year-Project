package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HexTile {
	
	public static final int RADIUS = 64;
	private double x;
	private double y;
	
	public HexTile(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		double sin30 = RADIUS * Math.sin(Math.PI/6);
		double sin60 = RADIUS * Math.sin(Math.PI/3);
		int[] xcoords = {(int)Math.round(x + RADIUS), (int)Math.round(sin30 + x), (int)Math.round(x - sin30), (int)Math.round(x - RADIUS), (int)Math.round(x - sin30), (int)Math.round(sin30 + x)};
		int[] ycoords = {(int)Math.round(y), (int)Math.round(sin60 + y), (int)Math.round(sin60 + y), (int)Math.round(y), (int)Math.round(y - sin60), (int)Math.round(y - sin60)};
		Polygon p = new Polygon(xcoords, ycoords, 6);
		g.drawPolygon(p);
	}
	public static int[] getBottomRight(double x, double y){
		double sin30 = RADIUS * Math.sin(Math.PI/6);
		double sin60 = RADIUS * Math.sin(Math.PI/3);
		int[] coord = {(int)Math.round(sin30 + x), (int)Math.round(y - sin60)};
		return coord;
	}
}

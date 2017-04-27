package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HexTileTest{
	
	public static final int r = 64;
	public static double RADIUS = r;
	private double x;
	private double y;
	private double mapx;
	private double mapy;
	
	public HexTileTest(double mapx, double mapy){
		this.mapx = mapx;
		this.mapy = mapy;
	}
	public double getX(){
		return this.mapx;
	}
	public double getY(){
		return this.mapy;
	}
	public void setCoords(double x, double y){
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		double sin30 = RADIUS * Math.sin(Math.PI/6);
		double sin60 = RADIUS * Math.sin(Math.PI/3);
		int[] ycoords = {(int)Math.round(y + RADIUS), (int)Math.round(sin30 + y), (int)Math.round(y - sin30), (int)Math.round(y - RADIUS), (int)Math.round(y - sin30), (int)Math.round(sin30 + y)};
		int[] xcoords = {(int)Math.round(x), (int)Math.round(sin60 + x), (int)Math.round(sin60 + x), (int)Math.round(x), (int)Math.round(x - sin60), (int)Math.round(x - sin60)};
		Polygon p = new Polygon(xcoords, ycoords, 6);
		
		g.drawPolygon(p);
	}
	public void draw(Graphics g, int num){
		g.setColor(Color.BLACK);
		double sin30 = RADIUS * Math.sin(Math.PI/6);
		double sin60 = RADIUS * Math.sin(Math.PI/3);
		int[] ycoords = {(int)Math.round(y + RADIUS), (int)Math.round(sin30 + y), (int)Math.round(y - sin30), (int)Math.round(y - RADIUS), (int)Math.round(y - sin30), (int)Math.round(sin30 + y)};
		int[] xcoords = {(int)Math.round(x), (int)Math.round(sin60 + x), (int)Math.round(sin60 + x), (int)Math.round(x), (int)Math.round(x - sin60), (int)Math.round(x - sin60)};
		Polygon p = new Polygon(xcoords, ycoords, 6);
		
		g.fillPolygon(p);
	}
}
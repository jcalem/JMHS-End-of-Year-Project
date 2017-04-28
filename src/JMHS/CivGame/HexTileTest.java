package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HexTileTest{
	
	public static final int r = 60;
	public static int RADIUS = r;
	public static int lengthToSide = 52;
	private int x;
	private int y;
	private int mapx;
	private int mapy;
	
	public HexTileTest(int mapx, int mapy){
		this.mapx = mapx;
		this.mapy = mapy;
	}
	public int getX(){
		return this.mapx;
	}
	public int getY(){
		return this.mapy;
	}
	public int getXx(){
		return this.x;
	}
	public void setCoords(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		//double sin30 = RADIUS * Math.sin(Math.PI/6);
		//double sin60 = RADIUS * Math.sin(Math.PI/3);
		//int[] ycoords = {(int)Math.round(y + RADIUS), (int)Math.round(sin30 + y), (int)Math.round(y - sin30), (int)Math.round(y - RADIUS), (int)Math.round(y - sin30), (int)Math.round(sin30 + y)};
		//int[] xcoords = {(int)Math.round(x), (int)Math.round(sin60 + x), (int)Math.round(sin60 + x), (int)Math.round(x), (int)Math.round(x - sin60), (int)Math.round(x - sin60)};
		int[] xcoords = {x + lengthToSide, x, x - lengthToSide, x - lengthToSide, x, x + lengthToSide};
		int[] ycoords = {(int)Math.round(y + .5 * RADIUS), y + RADIUS, (int)Math.round(y + .5 * RADIUS), (int)Math.round(y - .5 * RADIUS), y - RADIUS, (int)Math.round(y - .5 * RADIUS)};
		Polygon p = new Polygon(xcoords, ycoords, 6);
		
		g.drawPolygon(p);
	}
	public void draw(Graphics g, int num){
		g.setColor(Color.BLACK);
		int[] xcoords = {x + lengthToSide, x, x - lengthToSide, x - lengthToSide, x, x + lengthToSide};
		int[] ycoords = {(int)Math.round(y + .5 * RADIUS), y + RADIUS, (int)Math.round(y + .5 * RADIUS), (int)Math.round(y - .5 * RADIUS), y - RADIUS, (int)Math.round(y - .5 * RADIUS)};
		Polygon p = new Polygon(xcoords, ycoords, 6);
		
		g.fillPolygon(p);
	}
}
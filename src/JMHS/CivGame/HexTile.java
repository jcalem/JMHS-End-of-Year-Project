package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HexTile {
	
	public static final int r = 64;
	public static double RADIUS = r;
	private double x;
	private double y;
	int i;
	int j;
	private double mapx;
	private double mapy;
	private String type;
	private float color;
	private float moisture;
	
	
	public HexTile(double mapx, double mapy, float color, float moisture, int i, int j){
		this.i = i;
		this.j = j;
		this.mapx = mapx;
		this.mapy = mapy;
		this.color = color;
		this.moisture = moisture;
		type = getType();
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
	public String getType(){
		if(color <= .55) return "sea";
		else return "land";
	}
	public String getLandType(){
		if(moisture < .3) return "desert";
		if(moisture > .83) return "mountain";
		else return "land";
	}
	public void draw(Graphics g){
		if(this.type.equals("land"))
			g.setColor(Color.GREEN);
		else if(this.type.equals("mountain"))
			g.setColor(Color.GRAY);
		else if(this.type.equals("desert"))
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.BLUE);
		
		if(Main.test.area.contains(this))
			g.setColor(Color.RED);
		double sin30 = RADIUS * Math.sin(Math.PI/6);
		double sin60 = RADIUS * Math.sin(Math.PI/3);
		int[] ycoords = {(int)Math.round(y + RADIUS), (int)Math.round(sin30 + y), (int)Math.round(y - sin30), (int)Math.round(y - RADIUS), (int)Math.round(y - sin30), (int)Math.round(sin30 + y)};
		int[] xcoords = {(int)Math.round(x), (int)Math.round(sin60 + x), (int)Math.round(sin60 + x), (int)Math.round(x), (int)Math.round(x - sin60), (int)Math.round(x - sin60)};
		Polygon p = new Polygon(xcoords, ycoords, 6);
		//g.setColor(new Color(color, color, color));
		g.fillPolygon(p);
		g.setColor(Color.BLACK);
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

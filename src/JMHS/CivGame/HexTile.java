package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;

import javax.swing.ImageIcon;

public class HexTile {

	public static final int r = 64;
	public static double RADIUS = r;
	public double x;
	public double y;
	int i;
	int j;
	private double mapx;
	private double mapy;
	public String type;
	private float color;
	private float moisture;
	Color c;
	Graphics g;

	public HexTile(double mapx, double mapy, float color, float moisture, int i, int j) {
		this.i = i;
		this.j = j;
		this.mapx = mapx;
		this.mapy = mapy;
		this.color = color;
		this.moisture = moisture;
		type = getType();
		if (this.type.equals("land"))
			setColor(new Color(1, 255, 1));
		else if (this.type.equals("mountain"))
			setColor(Color.GRAY);
		else if (this.type.equals("desert"))
			setColor(Color.YELLOW);
		else{
			setColor(new Color(1, 1, 255));
		}
			
	}
	public void setColor(Color c){
		this.c = c;
	}
	public double getX() {
		return this.mapx;
	}

	public double getY() {
		return this.mapy;
	}

	public void setCoords(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getBoardX() {
		return x;
	}

	public double getBoardY() {
		return y;
	}

	public String getType() {
		if (color <= .55)
			return "sea";
		else
			return "land";
	}

	public String getLandType() {
		if (moisture < .3)
			return "desert";
		if (moisture > .83)
			return "mountain";
		else
			return "land";
	}
	public Color getColor(){
		return c;
	}
	public void draw(Graphics g) {
		g.setColor(c);
		double tint_factor = .5;
		if (Main.playerCiv.hasCity()) {
			if (Main.playerCiv.getCity(0).area.contains(this)) {
				int newR = (int) (g.getColor().getRed() + (255 - g.getColor().getRed())/* tint_factor */);
				int newG = g.getColor().getGreen();// (int)(g.getColor().getGreen()
													// + (255 -
													// g.getColor().getGreen())
													// * tint_factor);
				int newB = g.getColor().getBlue();// (int)(g.getColor().getBlue()
													// + (255 -
													// g.getColor().getBlue()) *
													// tint_factor);
				g.setColor(new Color(newR, newG, newB));
			}
		}
		double sin30 = RADIUS * Math.sin(Math.PI / 6);
		double sin60 = RADIUS * Math.sin(Math.PI / 3);
		int[] ycoords = { (int) Math.round(y + RADIUS), (int) Math.round(sin30 + y), (int) Math.round(y - sin30),
				(int) Math.round(y - RADIUS), (int) Math.round(y - sin30), (int) Math.round(sin30 + y) };
		int[] xcoords = { (int) Math.round(x), (int) Math.round(sin60 + x), (int) Math.round(sin60 + x),
				(int) Math.round(x), (int) Math.round(x - sin60), (int) Math.round(x - sin60) };
		Polygon p = new Polygon(xcoords, ycoords, 6);
		// g.setColor(new Color(color, color, color));
		
		g.fillPolygon(p);
		if(getType().equals("sea"))
			draw(g,1);
		

		if(Main.grid){
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			
			
		}
		
		
	}

	public void draw(Graphics g, int num) {
		double sin60 = RADIUS * Math.sin(Math.PI / 3);
		/*g.setColor(Color.BLACK);
		double sin30 = RADIUS * Math.sin(Math.PI / 6);
		double sin60 = RADIUS * Math.sin(Math.PI / 3);
		int[] ycoords = { (int) Math.round(y + RADIUS), (int) Math.round(sin30 + y), (int) Math.round(y - sin30),
				(int) Math.round(y - RADIUS), (int) Math.round(y - sin30), (int) Math.round(sin30 + y) };
		int[] xcoords = { (int) Math.round(x), (int) Math.round(sin60 + x), (int) Math.round(sin60 + x),
				(int) Math.round(x), (int) Math.round(x - sin60), (int) Math.round(x - sin60) };
		Polygon p = new Polygon(xcoords, ycoords, 6);

		g.fillPolygon(p);*/
		Image img = new ImageIcon("sea.gif").getImage();
		//g.setClip(getShape());
		//g.drawImage(img, 0, 0, null);
		g.drawImage(img, (int)Math.round(x - sin60), (int)Math.round(y - RADIUS), (int)Math.round(sin60 * 2), (int)Math.round(2 * RADIUS), null);
	}

	public Polygon getShape() {
		double sin30 = RADIUS * Math.sin(Math.PI / 6);
		double sin60 = RADIUS * Math.sin(Math.PI / 3);
		int[] ycoords = { (int) Math.round(y + RADIUS), (int) Math.round(sin30 + y), (int) Math.round(y - sin30),
				(int) Math.round(y - RADIUS), (int) Math.round(y - sin30), (int) Math.round(sin30 + y) };
		int[] xcoords = { (int) Math.round(x), (int) Math.round(sin60 + x), (int) Math.round(sin60 + x),
				(int) Math.round(x), (int) Math.round(x - sin60), (int) Math.round(x - sin60) };
		Polygon p = new Polygon(xcoords, ycoords, 6);
		return p;
	}

	public boolean hasUnit() {
		for (Civilization civ : Main.civs) {
			for (Unit unit : civ.units) {
				if (unit.locx == this.i && unit.locy == this.j)
					return true;
			}
		}
		return false;
	}

	public boolean hasCity() {
		for (Civilization civ : Main.civs) {
			for (Unit city : civ.units) {
				if (city.locx == this.i && city.locy == this.j)
					return true;
			}
		}
		return false;
	}
	public Unit getUnit(){
		for (Civilization civ : Main.civs) {
			for (Unit unit : civ.units) {
				if (unit.locx == this.i && unit.locy == this.j)
					return unit;
			}
		}
		return null;
	}
}

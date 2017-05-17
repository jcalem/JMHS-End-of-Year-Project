package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HexTile {

	public static final int r = 64;
	public static double RADIUS = r;
	public double x;
	public double y;
	int i;
	int j;
	private double mapx;
	private double mapy;
	private String type;
	private float color;
	private float moisture;

	public HexTile(double mapx, double mapy, float color, float moisture, int i, int j) {
		this.i = i;
		this.j = j;
		this.mapx = mapx;
		this.mapy = mapy;
		this.color = color;
		this.moisture = moisture;
		type = getType();
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

	public void draw(Graphics g) {
		if (this.type.equals("land"))
			g.setColor(Color.GREEN);
		else if (this.type.equals("mountain"))
			g.setColor(Color.GRAY);
		else if (this.type.equals("desert"))
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.BLUE);
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
		if(Main.grid){
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
		}
		
	}

	public void draw(Graphics g, int num) {
		g.setColor(Color.BLACK);
		double sin30 = RADIUS * Math.sin(Math.PI / 6);
		double sin60 = RADIUS * Math.sin(Math.PI / 3);
		int[] ycoords = { (int) Math.round(y + RADIUS), (int) Math.round(sin30 + y), (int) Math.round(y - sin30),
				(int) Math.round(y - RADIUS), (int) Math.round(y - sin30), (int) Math.round(sin30 + y) };
		int[] xcoords = { (int) Math.round(x), (int) Math.round(sin60 + x), (int) Math.round(sin60 + x),
				(int) Math.round(x), (int) Math.round(x - sin60), (int) Math.round(x - sin60) };
		Polygon p = new Polygon(xcoords, ycoords, 6);

		g.fillPolygon(p);
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
}

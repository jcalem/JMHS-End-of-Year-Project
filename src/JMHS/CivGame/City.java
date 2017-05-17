package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class City {
	
	int locx, locy;
	ArrayList<HexTile> area;
	public City(int locx, int locy){
		this.locx = locx;
		this.locy = locy;
		area = HexMap.getSurroundingTiles(this.locx, this.locy, 2);
	}
	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		g.fillOval(locx, locy, 30, 30);
	}
}

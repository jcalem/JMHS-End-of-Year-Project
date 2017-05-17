package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Unit {
	
	int locx, locy;
	public Unit(int locx, int locy){
		this.locx = locx;
		this.locy = locy;
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		System.out.println(HexMap.gameHexs[locx][locy].getX());
		System.out.println(" " + HexMap.gameHexs[locx][locy].getY());
		g.fillOval((int)HexMap.gameHexs[locx][locy].getX(), (int)HexMap.gameHexs[locx][locy].getY(), 30, 30);
	}
}

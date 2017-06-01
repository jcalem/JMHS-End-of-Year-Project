package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Unit {

	int locx, locy;
	Civilization civ;
	int movingSpeed;
	boolean canMove = true;
	public Unit(int locx, int locy, Civilization civ) {
		this.locx = locx;
		this.locy = locy;
		this.civ = civ;
		movingSpeed = 2;
	}

	public void draw(Graphics g) {
		int radius = 100;
		g.setColor(Color.RED);
		g.fillOval((int) Math.round(HexMap.gameHexs[locx][locy].getBoardX() - (radius * HexMap.ZOOM / 2)),
				(int) Math.round(HexMap.gameHexs[locx][locy].getBoardY() - (radius * HexMap.ZOOM) / 2),
				(int) Math.round(radius * HexMap.ZOOM), (int) Math.round(radius * HexMap.ZOOM));
	}
	public int movingSpeed(){
		return movingSpeed;
	}
	public Civilization getCiv(){
		return civ;
	}
	public void move(int newx, int newy){
		this.locx = newx;
		this.locy = newy;
		canMove = false;
	}
	public void canMove(boolean canMove){
		this.canMove = canMove;
	}
	public boolean canMove(){
		return this.canMove;
	}
}

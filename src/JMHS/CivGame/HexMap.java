package JMHS.CivGame;

import java.awt.Graphics;

public class HexMap {
	
	public HexTile[][] gameHexs;
	int x = 640; int y = 360;
	double ZOOM = 1; 
	double startx = HexTile.RADIUS;
	double starty = (HexTile.RADIUS * Main.SCALE);
	
	public HexMap(int numx, int numy){
		
		
		gameHexs = new HexTile[numx][numy]; 
		
		for(int i = 0; i < gameHexs.length; i++){
			for(int j = 0; j < gameHexs[0].length; j++){
				//gameHexs[i][j] = new HexTile(j * HexTile.RADIUS * Main.SCALE + ( 2 * starty * i) + starty, startx + j * (HexTile.RADIUS + HexTile.RADIUS * Math.sin(Math.PI/6)));
			}
		}
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < gameHexs.length; i++){
			for(int j = 0; j < gameHexs[0].length; j++){
				gameHexs[i][j].draw(g);
			}
		}
	}
	public void drawFromCenter(HexTile a, int ZOOM){
		HexTile.RADIUS = HexTile.RADIUS/ZOOM;
		for(int i = (int)Math.round(a.getX());i < gameHexs.length; i++){
			for(int j = (int)Math.round(a.getY()); j < gameHexs[0].length; j++){
				gameHexs[i][j] = new HexTile(j * HexTile.RADIUS * Main.SCALE + ( 2 * starty * i) + starty, startx + j * (HexTile.RADIUS + HexTile.RADIUS * Math.sin(Math.PI/6)));
			}
		}
	}
}

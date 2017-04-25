package JMHS.CivGame;

import java.awt.Graphics;

public class HexMap {
	
	public HexTile[][] gameHexs;
	
	public HexMap(int numx, int numy){
		
		
		gameHexs = new HexTile[numx][numy]; 
		double startx = HexTile.RADIUS;
		double starty = (HexTile.RADIUS * Main.SCALE);
		double newx = startx;
		double newy = starty;
		
		/*for(int j = 0; j < gameHexs[0].length; j++){
			int[] coords = HexTile.getBottomRight(newx, newy); 
			gameHexs[0][j] = new HexTile((int)Math.round(coords[0] + HexTile.RADIUS), (int)Math.round(coords[1]));
			newx = gameHexs[0][j].getX();
			newy = gameHexs[0][j].getY();
		}*/
		for(int i = 0; i < gameHexs.length; i++){
			for(int j = 0; j < gameHexs[0].length; j++){
				gameHexs[i][j] = new HexTile(startx + j * (HexTile.RADIUS + HexTile.RADIUS * Math.sin(Math.PI/6)), j * HexTile.RADIUS * Main.SCALE + (starty * i));
				System.out.println(gameHexs[i][j].getX() + ",memes" + gameHexs[i][j].getY());
			}
		}
	}
	
	public void draw(Graphics g){
		//for(int i = 0; i < gameHexs.length; i++){
			for(int j = 0; j < gameHexs[0].length; j++){
				gameHexs[0][j].draw(g);
			}
		//}
	}
}

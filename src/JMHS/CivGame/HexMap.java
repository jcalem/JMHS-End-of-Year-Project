package JMHS.CivGame;

import java.awt.Graphics;
import java.util.HashMap;

public class HexMap {

	public HexTile[][] gameHexs;
	public HexTile[][] gameBoard2;
	double x = Main.WIDTH / 2;
	double y = Main.HEIGHT / 2;
	int MapSize = 0;
	double ZOOM = 2;
	double startx = HexTile.RADIUS;
	double starty = (HexTile.RADIUS * Main.SCALE);
	int movingSpeed = 10;
	float[][] color;
	float[][] moisture;

	public HexMap(int numx, int numy) {
        int octaveCount = 5;
		gameHexs = new HexTile[numx][numy];
		gameBoard2 = new HexTile[numx][numy];
		GenerateMap hexColors = new GenerateMap(gameHexs.length, gameHexs[0].length);
		GenerateMap hexMoisture = new GenerateMap(gameHexs.length, gameHexs[0].length);
		float[][] color = hexColors.GeneratePerlinNoise(gameHexs.length, gameHexs[0].length, octaveCount);
		float[][] moisture = hexMoisture.GeneratePerlinNoise(gameHexs.length, gameHexs[0].length, octaveCount - 1);
		
		for (int i = 0; i < gameHexs.length; i++) {
			for (int j = 0; j < gameHexs[0].length; j++) {
				gameHexs[i][j] = new HexTile(
					j * HexTile.RADIUS * Main.SCALE + (2 * starty * i) + starty,
					startx + j * (HexTile.RADIUS + HexTile.RADIUS * Math.sin(Math.PI / 6)), 
					color[i][j],
					moisture[i][j]);
			}
		}
	}

	public void draw(Graphics g){
		HexTile.RADIUS = (int) Math.round(HexTile.r * ZOOM);
		for (int i = 0; i < gameHexs.length; i++) {
			for(int j = 0; j < gameHexs[0].length; j++){
			double boardx = ZOOM * (gameHexs[i][j].getX() - (x - (Main.WIDTH / (ZOOM * 2))));
			double boardy = ZOOM * (gameHexs[i][j].getY() - (y - (Main.HEIGHT / (ZOOM * 2))));
			if(boardx < -(Main.SCALE * HexTile.RADIUS))
				boardx += 2 * gameHexs.length * HexTile.RADIUS * Main.SCALE;
			if(boardx > Main.WIDTH + (Main.SCALE * HexTile.RADIUS))
				boardx -= 2 * gameHexs.length * HexTile.RADIUS * Main.SCALE;
			gameHexs[i][j].setCoords(boardx, boardy);
			gameHexs[i][j].draw(g);
			}
		}
	}
}

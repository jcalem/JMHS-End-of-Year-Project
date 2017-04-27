package JMHS.CivGame;

import java.awt.Graphics;
import java.util.HashMap;

public class HexMap {

	public HexTile[][] gameHexs;
	public HashMap<Integer, HexTile> gameBoard = new HashMap<Integer, HexTile>();
	double x = Main.WIDTH / 2;
	double y = Main.HEIGHT / 2;
	int MapSize = 0;
	double ZOOM = 2;
	double startx = HexTile.RADIUS;
	double starty = (HexTile.RADIUS * Main.SCALE);
	int movingSpeed = 10;

	public HexMap(int numx, int numy) {

		gameHexs = new HexTile[numx][numy];

		for (int i = 0; i < gameHexs.length; i++) {
			for (int j = 0; j < gameHexs[0].length; j++) {
				gameHexs[i][j] = new HexTile(j * HexTile.RADIUS * Main.SCALE + (2 * starty * i) + starty,
						startx + j * (HexTile.RADIUS + HexTile.RADIUS * Math.sin(Math.PI / 6)));
			}
		}
	}

	public void draw(Graphics g){
		MapSize = 0;
		gameBoard.clear();
		calculateRenderBox();
		// System.out.println(gameBoard);
		for (int i = 0; i < gameBoard.size(); i++) {
			double boardx = ZOOM * (gameBoard.get(i).getX() - (x - (Main.WIDTH / (ZOOM * 2))));
			double boardy = ZOOM * (gameBoard.get(i).getY() - (y - (Main.HEIGHT / (ZOOM * 2))));
			if(boardx < -(Main.SCALE * HexTile.RADIUS))
				boardx += 2 * gameHexs.length * HexTile.RADIUS * Main.SCALE;
			if(boardx > Main.WIDTH + (Main.SCALE * HexTile.RADIUS))
				boardx -= 2 * gameHexs.length * HexTile.RADIUS * Main.SCALE;
			gameBoard.get(i).setCoords(boardx, boardy);
			if(i == 0) gameBoard.get(i).draw(g, i);
			else gameBoard.get(i).draw(g);
		}
	}

	public void calculateRenderBox() {
		HexTile.RADIUS = (int) Math.round(HexTile.r * ZOOM);
		for (int i = 0; i < gameHexs.length; i++) {
			for (int j = 0; j < gameHexs[0].length; j++) {
				HexTile current = gameHexs[i][j];
				gameBoard.put(MapSize, gameHexs[i][j]);
				MapSize++;
			}
		}
	}
}

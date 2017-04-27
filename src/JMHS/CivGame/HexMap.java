package JMHS.CivGame;

import java.awt.Graphics;
import java.util.HashMap;

public class HexMap {

	public HexTile[][] gameHexs;
	public HashMap<Integer, HexTile> gameBoard = new HashMap<Integer, HexTile>();
	int x = Main.WIDTH / 2;
	public int y = Main.HEIGHT / 2;
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

	public void draw(Graphics g) {
		MapSize = 0;
		gameBoard.clear();
		calculateRenderBox();
		//System.out.println(gameBoard.size());
		for (int i = 0; i < gameBoard.size(); i++) {
			int boardx = (int) Math.round(ZOOM * (gameBoard.get(i).getX() - (x - (Main.WIDTH / (ZOOM * 2)))));
			int boardy = (int) Math.round(ZOOM * (gameBoard.get(i).getY() - (y - (Main.HEIGHT / (ZOOM * 2)))));
			if(boardx < -(Main.SCALE * HexTile.RADIUS))
				boardx += 2 * gameHexs.length * HexTile.RADIUS * Main.SCALE;
			if(boardx > Main.WIDTH + (Main.SCALE * HexTile.RADIUS))
				boardx -= 2 * gameHexs.length * HexTile.RADIUS * Main.SCALE;
			// int boardx = (int)Math.round(ZOOM*(gameBoard.get(i).getX() %
			// (Main.WIDTH/ZOOM)));
			// int boardy = (int)Math.round(ZOOM*(gameBoard.get(i).getY() %
			// (Main.HEIGHT/ZOOM)));
			gameBoard.get(i).setCoords(boardx, boardy);
			if(i == 0) gameBoard.get(i).draw(g, i);
			else gameBoard.get(i).draw(g);
		}
	}

	public void calculateRenderBox() {
		HexTile.RADIUS = (int) Math.round(HexTile.r * ZOOM);
		int[] xs = calcX(x);
		int[] ys = calcY(y);
		for (int i = 0; i < gameHexs.length; i++) {
			for (int j = 0; j < gameHexs[0].length; j++) {
				// gameHexs[i][j] = new HexTile(j * HexTile.RADIUS * Main.SCALE
				// + ( 2 * starty * i) + starty, startx + j * (HexTile.RADIUS +
				// HexTile.RADIUS * Math.sin(Math.PI/6)));
				HexTile current = gameHexs[i][j];
				// If inside calculated render zone
				gameBoard.put(MapSize, gameHexs[i][j]);
				MapSize++;
			}
		}
	}

	public int[] calcX(int x) {

		int x1 = (int) Math.round(x - (Main.WIDTH / (ZOOM * 2)));
		if(x1 < 0) x1 += Main.WIDTH;
		int x2 = (int) Math.round(x + (Main.WIDTH / (ZOOM * 2)));
		//if(x2 > Main.WIDTH) x2 -= Main.WIDTH;
		int[] a = { x1, x2 };
		return a;
	}

	public int[] calcY(int y) {
		int y1 = (int) Math.round(y - Main.HEIGHT / (ZOOM * 2));
		// if(y1 < 0) y1 += Main.HEIGHT;
		int y2 = (int) Math.round(y + Main.HEIGHT / (ZOOM * 2));
		// if(y2 > Main.HEIGHT) y2 -= Main.HEIGHT;
		int[] a = { y1, y2 };
		return a;
	}
}

package JMHS.CivGame;

import java.awt.Graphics;
import java.util.HashMap;

public class HexMapTest{

	public HexTileTest[][] gameHexs;
	public HashMap<Integer, HexTileTest> gameBoard = new HashMap<Integer, HexTileTest>();
	int x = (int)Main.WIDTH / 2;
	int y = (int)Main.HEIGHT / 2;
	int MapSize = 0;
	double ZOOM = 2;
	int startx = HexTileTest.RADIUS;
	int starty = (int)Math.round(HexTileTest.RADIUS * Main.SCALE);
	int movingSpeed = 10;

	public HexMapTest(int numx, int numy) {

		gameHexs = new HexTileTest[numx][numy];

		for (int i = 0; i < gameHexs.length; i++) {
			for (int j = 0; j < gameHexs[0].length; j++) {
				gameHexs[i][j] = new HexTileTest(j * HexTileTest.lengthToSide + (i * 2 * HexTileTest.lengthToSide), j * HexTileTest.r * 3/2);
			}
		}
	}

	public void draw(Graphics g){
		MapSize = 0;
		gameBoard.clear();
		calculateRenderBox();
		int boardx = 0;
		int boardy = 0;
		//System.out.printlnMai(gameBoard.size());
		for (int i = 0; i < gameBoard.size() - 10; i++) {
			if(i % gameHexs[0].length == 0){
				boardy = HexTileTest.RADIUS - y;
				boardx = 2 * HexTileTest.lengthToSide * (i)/gameHexs[0].length - x;
			}
			gameBoard.get(i).setCoords(boardx, boardy);
			if(i == 0) gameBoard.get(i).draw(g, i);
			else gameBoard.get(i).draw(g);
			/*if(i % 10 == 0){
				System.out.println(gameBoard.get(i + 10).getXx() - gameBoard.get(i).getXx());
				System.out.println(ZOOM + " " + x + " " + gameBoard.get(i).getX() + "=" + boardx);
			}*/
			boardx += HexTileTest.lengthToSide;
			boardy += HexTileTest.RADIUS * (1.5);
			/*if(boardx < -(Main.SCALE * HexTile.RADIUS))
				boardx += 2 * (gameHexs.length - 1) * HexTileTest.lengthToSide;
			if(boardx > Main.WIDTH + (Main.SCALE * HexTile.RADIUS))
				boardx -= 2 * (gameHexs.length - 1) * HexTileTest.lengthToSide;*/
		}
	}

	public void calculateRenderBox() { 
		HexTileTest.RADIUS = (int)Math.round(HexTileTest.r * ZOOM);
		HexTileTest.lengthToSide = (int)Math.round(HexTileTest.RADIUS * Math.sqrt(3)/2);
		for (int i = 0; i < gameHexs.length; i++) {
			for (int j = 0; j < gameHexs[0].length; j++) {
				HexTileTest current = gameHexs[i][j];
				gameBoard.put(MapSize, gameHexs[i][j]);
				MapSize++;
			}
		}
	}
}
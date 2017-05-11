package JMHS.CivGame;

import java.util.ArrayList;

public class City {
	
	int locx, locy;
	ArrayList<HexTile> area;
	public City(int locx, int locy){
		area = getSurroundingTiles(locx, locy, 1);
	}
	public ArrayList<HexTile> getSurroundingTiles(int x, int y, int radius){
		ArrayList<HexTile> tiles = new ArrayList<HexTile>();
		for(int i = 0; i < radius; i++){
			
		}
		return tiles;
	}
}

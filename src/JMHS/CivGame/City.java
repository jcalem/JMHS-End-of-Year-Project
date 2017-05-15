package JMHS.CivGame;

import java.util.ArrayList;

public class City {
	
	int locx, locy;
	ArrayList<HexTile> area;
	public City(int locx, int locy){
		this.locx = locx;
		this.locy = locy;
		//System.out.println(this.locx + " " + this.locy);
		area = HexMap.getSurroundingTiles(this.locx, this.locy, 2);
	}
}

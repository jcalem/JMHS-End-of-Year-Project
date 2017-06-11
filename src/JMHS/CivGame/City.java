package JMHS.CivGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class City {
	
	int locx, locy;
	ArrayList<HexTile> area;
	int FPT, food, PTT, production;
	Buildings building;
	Image img2 = new ImageIcon("land1.jpg").getImage();
	Image img1 = new ImageIcon("sea1.jpg").getImage();
	Image center = new ImageIcon("babysach.jpg").getImage();
	public City(int locx, int locy){
		this.locx = locx;
		this.locy = locy;
		area = HexMap.getSurroundingTiles(this.locx, this.locy, 2);
		building = new Buildings();
	}
	public void draw(Graphics g){
		HexTile cityCenter = Main.map.gameHexs[locx][locy];
		double sin30 = cityCenter.RADIUS * Math.sin(Math.PI / 6);
		double sin60 = cityCenter.RADIUS * Math.sin(Math.PI / 3);
		for(HexTile t: area)
		{
			if(t.equals(cityCenter))
			{
				g.setClip(t.getShape());
				g.drawImage(center, (int)Math.round(cityCenter.x - sin60), (int)Math.round(cityCenter.y - cityCenter.RADIUS), (int)Math.round(sin60 * 2), (int)Math.round(2 * cityCenter.RADIUS), null);
				Unit.draw(g);
			}
			else if(t.getType().equals("land")){
				g.setClip(t.getShape());
				g.drawImage(img2, (int)Math.round(t.x - sin60), (int)Math.round(t.y - t.RADIUS), (int)Math.round(sin60 * 2), (int)Math.round(2 * t.RADIUS), null);
				Unit.draw(g);
			}
			else{
				g.setClip(t.getShape());
				g.drawImage(img1, (int)Math.round(t.x - sin60), (int)Math.round(t.y - t.RADIUS), (int)Math.round(sin60 * 2), (int)Math.round(2 * t.RADIUS), null);
				Unit.draw(g);
			}
		}
	}
	public ArrayList<HexTile> getTiles(){
		return area;
	}
	public void setFPT(int FPT){
		this.FPT = FPT;
	}
	public void setFood(int food){
		this.food = food;
	}
	public int getFood(){
		return food;
	}
	public int getFPT(){
		return FPT;
	}
}

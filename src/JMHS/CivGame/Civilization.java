package JMHS.CivGame;

import java.awt.Graphics;
import java.util.ArrayList;

public class Civilization {
	ArrayList<City> cities = new ArrayList<City>();
	ArrayList<Unit> units = new ArrayList<Unit>();
	public Civilization(){
		int locx;
		int locy;
		do{
			locx = (int)(Math.random() * HexMap.gameHexs.length);
			locy = (int)(Math.random() * HexMap.gameHexs[0].length);
		}while(!HexMap.gameHexs[locx][locy].getType().equals("land"));
		newUnit(locx, locy, "Settler");
		newUnit(locx, locy, "Warrior");
	}
	public void newUnit(int locx, int locy, String type){
		
		Unit spawnedSettler = new Settler(locx, locy);
		units.add(spawnedSettler);
	}
	public void addCity(City city){
		cities.add(city);
	}
	public void draw(Graphics g){
		for(City city: cities)
			city.draw(g);
		for(Unit unit: units)
			unit.draw(g);
	}
	public City getCity(int i){
		return cities.get(i);
	}
	public boolean hasCity(){
		if(cities.isEmpty()) return false;
		else return true;
	}
}

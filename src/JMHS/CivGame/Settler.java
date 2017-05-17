package JMHS.CivGame;

public class Settler extends Unit{
//Ryan Brown is a goober
	public Settler(int locx, int locy) {
		super(locx, locy);
		// TODO Auto-generated constructor stub
	}
	
	public void createCity(){
		City newCity = new City(locx, locy);
		
	}
}

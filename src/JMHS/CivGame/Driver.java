package JMHS.CivGame;


import javax.swing.*;

public class Driver {
	public static void main(String[] args){
	JFrame frame = new JFrame();
	Main panel = new Main();
	frame.setSize(800, 800);
	frame.setResizable(false);
	frame.add(panel);
	frame.setVisible(true);
	}
}

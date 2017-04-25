package JMHS.CivGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements Runnable{

	private static final String title = "End of Year Project";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final double SCALE = Math.sqrt(3)/2;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new Main(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setVisible(true);
	}
	
	private boolean isRunning = false;
	private Thread thread;
	
	HexMap map;
	
	public Main(){
		map = new HexMap(10,8);
		start();
	}
	
	public void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		long start, elapsed, wait;
		while(isRunning){
			start = System.nanoTime();
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start;
			wait = (100/6) - elapsed/1000000;
			if(wait <= 0) wait = 5;
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void tick() {
		//System.out.println("Running");
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		//g.fillRect(0, 0, Driver.WIDTH, Driver.HEIGHT);
		map.draw(g);
	}
}

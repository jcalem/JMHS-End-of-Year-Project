package JMHS.CivGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements Runnable, KeyListener{

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
	
	private boolean movingRight = false;
	private boolean movingLeft = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	
	HexMap map;
	
	public Main(){
		setFocusable(true);
		addKeyListener(this);
		map = new HexMap(8,8);
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
		if(movingLeft)
			map.x -= map.movingSpeed;
		else if(movingRight)
			map.x += map.movingSpeed;
		else if(movingUp)
			map.y -= map.movingSpeed;
		else if(movingDown)
			map.y += map.movingSpeed;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		//g.fillRect(0, 0, Driver.WIDTH, Driver.HEIGHT);
		map.draw(g);
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			movingRight = true;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			movingLeft = true;
		else if(e.getKeyCode() == KeyEvent.VK_UP)
			movingUp = true;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			movingDown = true;
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			movingRight = false;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			movingLeft = false;
		else if(e.getKeyCode() == KeyEvent.VK_UP)
			movingUp = false;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			movingDown = false;
	}
}

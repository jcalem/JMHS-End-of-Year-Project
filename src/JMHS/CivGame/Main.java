package JMHS.CivGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements Runnable, KeyListener, MouseWheelListener {

	private static final String title = "End of Year Project";
	public static final double WIDTH = 1280;
	public static final double HEIGHT = 720;
	public static final double SCALE = Math.sqrt(3) / 2;
	double px1;
	double py1;
	boolean dragging = false;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize((int)WIDTH, (int)HEIGHT);
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

	public Main() {

		setFocusable(true);
		addKeyListener(this);
		addMouseWheelListener(this);
		addMouseMotionListener(mouse);
		addMouseListener(m);
		map = new HexMap(80, 52);
		//map.GenerateMap(1);
		start();
	}

	public void start() {

		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {

		long start, elapsed, wait;
		while (isRunning) {
			start = System.nanoTime();
			repaint();

			elapsed = System.nanoTime() - start;
			wait = (200 / 6) - elapsed / 1000000;
			if (wait <= 0)
				wait = 5;
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tick();
		}
	}

	private void tick() {
		/*try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(map.x + "," + map.y + " " + HexTile.RADIUS);
		if(map.x >= 2 * HexTile.r * map.gameHexs.length * SCALE + (map.ZOOM * WIDTH/2)){ 
			map.x = map.ZOOM * WIDTH/2;
		}
		/*if(map.x <= -2 * HexTile.r * map.gameHexs.length * SCALE + (map.ZOOM * WIDTH/2)){ 
			map.x = -1 * map.ZOOM * WIDTH/2;
		}*/
		//System.out.println(2 * map.gameHexs.length * map.gameHexs[0][0].RADIUS * Main.SCALE + );
		
		if (movingLeft)
			map.x -= map.movingSpeed / map.ZOOM;
		else if (movingRight)
			map.x += map.movingSpeed / map.ZOOM;
		else if (movingUp && map.y > 160)
			map.y -= map.movingSpeed / map.ZOOM;
		else if (movingDown && map.y < 4865)
			map.y += map.movingSpeed / map.ZOOM;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
		g.setColor(Color.BLACK);
		map.draw(g);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			movingRight = true;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			movingLeft = true;
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			movingUp = true;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			movingDown = true;
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			movingRight = false;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			movingLeft = false;
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			movingUp = false;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			movingDown = false;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0 && map.ZOOM > .1875)
			map.ZOOM -= .0625;
		if (e.getWheelRotation() < 0 && map.ZOOM < 2)
			map.ZOOM += .0625;
	}
	MouseListener m =  new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			//dragging = false;
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			px1 = e.getPoint().getX();
			py1 = e.getPoint().getY();
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {

			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {

		}
	};
	MouseMotionListener mouse = new MouseMotionListener() {
		
		
		@Override
		public void mouseMoved(MouseEvent p) {

		}
		
		@Override
		public void mouseDragged(MouseEvent m) {
			Point a = m.getPoint();
			double px2 = a.getX();
			double py2 = a.getY();
			map.x += (px1 - px2)/map.ZOOM;
			if(map.y > 160 && (py1 - py2) < 0) {
				map.y += (py1 - py2);
			}
			else if (map.y < 4865 && (py1 - py2) > 0) {
				map.y += (py1 - py2)/map.ZOOM;
			}
			px1 = px2;
			py1 = py2;
		}
	};

	}

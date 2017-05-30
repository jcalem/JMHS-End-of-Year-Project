package JMHS.CivGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main extends JPanel implements Runnable, KeyListener, MouseWheelListener {

	private static final String title = "End of Year Project";
	public static final double WIDTH = 1280;
	public static final double HEIGHT = 720;
	public static final double SCALE = Math.sqrt(3) / 2;
	double px1;
	double py1;
	boolean dragging = false;
	public static boolean grid = false;
	static JPanel jpanel = new JPanel();
	static boolean framer = false;
	static Container pane = new Container();
	static JFrame frame;
	static int gold, culture, science;
	static int GPT, CPT, SPT;
	static boolean displayingMap, displayingTechTree, displayingVictoryProgress;
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setSize((int) WIDTH, (int) HEIGHT);
		frame.setResizable(false);
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

	public HexMap map;
	public static Civilization playerCiv;
	public static ArrayList<Civilization> civs;
	Object selected;
	boolean isSelected = false;
	ArrayList<HexTile> availableTiles;

	public Main() {
		setFocusable(true);
		addKeyListener(this);
		addMouseWheelListener(this);
		addMouseMotionListener(mouse);
		addMouseListener(m);
		map = new HexMap(80, 52);
		displayingMap = true;
		playerCiv = new Civilization();
		civs = new ArrayList<Civilization>();
		civs.add(playerCiv);
		frame.add(jpanel, BorderLayout.EAST);
		jpanel.setPreferredSize(new Dimension(150, 720));
		jpanel.setVisible(false);
		
		gold = science = culture = 0;
		GPT = CPT = SPT = 0;
		
		JMenuItem goldDisplay = new JMenuItem("Gold: " + gold + " (+" + GPT +")");
		JMenuItem cultureDisplay = new JMenuItem("Culture: " + culture + " (+" + CPT +")");
		JMenuItem scienceDisplay = new JMenuItem("Science:" + science + " (+" + SPT +")");
		
		goldDisplay.setEnabled(false);
		cultureDisplay.setEnabled(false);
		scienceDisplay.setEnabled(false);
		
		JMenuItem map = new JMenuItem("World Map");
		JMenuItem techTree = new JMenuItem("Tech Tree");
		//JMenuItem diplomacy = new JMenuItem("Diplomacy Overview");
		JMenuItem victoryProgress = new JMenuItem("Victory Progress");
		
		/*
		JMenuItem save = new JMenuItem("Save");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem newGame = new JMenuItem("New Game");
		*/

		JMenu displayMenu = new JMenu("Display");
		displayMenu.add(map);
		displayMenu.add(techTree);
		//displayMenu.add(diplomacy);
		displayMenu.add(victoryProgress);
		
		/*
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(newGame);
		fileMenu.add(save);
		fileMenu.add(open);
		*/
		
		JMenuBar bar = new JMenuBar();
		//bar.add(fileMenu);
		
		bar.add(goldDisplay);
		bar.add(cultureDisplay);
		bar.add(scienceDisplay);
		
		bar.add(displayMenu);
		frame.add(bar, BorderLayout.NORTH);
		
		map.addActionListener(new DisplayMapListener());
		techTree.addActionListener(new DisplayTechTreeListener());
		victoryProgress.addActionListener(new DisplayVictoryProgressListener());
		//diplomacy.addActionListener(new DisplayDiplomacyListener());
		
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
			//jpanel.repaint();
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
		/*
		 * try { Thread.sleep(100); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// System.out.println(map.x + "," + map.y + " " + HexTile.RADIUS);
		if (map.x >= 2 * HexTile.r * map.gameHexs.length * SCALE + (map.ZOOM * WIDTH / 2)) {
			map.x = map.ZOOM * WIDTH / 2;
		}

		if (map.x < 0) {
			map.x = 2 * HexTile.r * map.gameHexs.length * SCALE;
		}
		/*
		 * if(map.x <= -2 * HexTile.r * map.gameHexs.length * SCALE + (map.ZOOM
		 * * WIDTH/2)){ map.x = -1 * map.ZOOM * WIDTH/2; }
		 */
		// System.out.println(2 * map.gameHexs.length *
		// map.gameHexs[0][0].RADIUS * Main.SCALE + );

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
		g.fillRect(0, 0, (int) WIDTH, (int) HEIGHT);
		g.setColor(Color.BLACK);
		if(displayingMap)
		{
			map.draw(g);
			playerCiv.draw(g);
		}
		//jpanel.repaint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {//should only work when displayingMap
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			movingRight = true;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			movingLeft = true;
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			movingUp = true;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			movingDown = true;
			toggleSettings();
		}
			
		else if (e.getKeyCode() == KeyEvent.VK_G) {//should only work when displayingMap
			if (grid)
				grid = false;
			else
				grid = true;
		}
	}

	public void keyReleased(KeyEvent e) { //should only work when displayingMap
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			movingRight = false;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			movingLeft = false;
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			movingUp = false;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			movingDown = false;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {//should only work when displayingMap
		if (e.getWheelRotation() > 0 && map.ZOOM > .1875)
			map.ZOOM -= .0625;
		if (e.getWheelRotation() < 0 && map.ZOOM < 2)
			map.ZOOM += .0625;
	}

	MouseListener m = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// dragging = false;

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
		public void mouseClicked(MouseEvent e) { //should only work when displayingMap
			for (int i = 0; i < map.gameHexs.length; i++) {
				for (int j = 0; j < map.gameHexs[0].length; j++) {
					if (map.gameHexs[i][j].getShape().contains((int) e.getPoint().getX(), (int) e.getPoint().getY())) {
						if (map.gameHexs[i][j].hasUnit()) {
							isSelected = true;
							selected = map.gameHexs[i][j].getUnit();
							availableTiles = HexMap.getSurroundingTiles(i, j, ((Unit) selected).movingSpeed);
							for (HexTile tile : availableTiles) {
								Color c = new Color((int) Math.round((tile.getColor().getRed() + 255) / 2),
										(int) Math.round((tile.getColor().getGreen()) / 2),
										(int) Math.round(tile.getColor().getBlue()));

								tile.setColor(c);
							}
						} else if (e.getButton() == e.BUTTON3 && isSelected
								&& availableTiles.contains(map.gameHexs[i][j])) {
							((Unit) selected).move(i, j);
							isSelected = false;
							selected = null;
							for (HexTile tile : availableTiles) {
								Color c;
								if (tile.type.equals("land"))
									c = Color.GREEN;
								else if (tile.type.equals("mountain"))
									c = Color.GRAY;
								else if (tile.type.equals("desert"))
									c = Color.YELLOW;
								else
									c = Color.BLUE;
								tile.setColor(c);
							}
							availableTiles.clear();

						}
					}
				}
			}
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
			map.x += (px1 - px2) / map.ZOOM;
			if (map.y > 160 && (py1 - py2) < 0) {
				map.y += (py1 - py2) / map.ZOOM;
			} else if (map.y < 4865 && (py1 - py2) > 0) {
				map.y += (py1 - py2) / map.ZOOM;
			}
			px1 = px2;
			py1 = py2;
		}
	};
	public void toggleSettings(){
		if(framer){
			jpanel.setVisible(true);
			framer = false;
		}
		else{
			jpanel.setVisible(false);
			framer = true;
		}
	}
	private class DisplayTechTreeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.print("Display Tech Tree");
			displayingMap = false;
		}
	}
	private class DisplayMapListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.print("Display Map");
			displayingMap = true;
		}
	}
	private class DisplayVictoryProgressListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.print("Display Victory Progress");
			displayingMap = false;
		}
	}

}

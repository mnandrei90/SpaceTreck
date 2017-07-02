import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyKeyListener implements KeyListener {
	private StarTrek starTrek;
	
	public MyKeyListener(StarTrek s) {
		starTrek = s;
	}
	
	@Override public void keyTyped(KeyEvent e) {
	}

	@Override public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_A) {
            starTrek.getPlayer().move(-1, starTrek.getDelta());
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            starTrek.getPlayer().move(1, starTrek.getDelta());
        }
	}

	@Override public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            starTrek.createBullet();
        }
	}
}

public class StarTrek extends Canvas {
	private static final long serialVersionUID = 1033091750611217336L;

	/** This strategy allows the use of accelerated page flip */
	private BufferStrategy strategy;
	private volatile boolean gameRunning;
	private List<Entity> entities;
	private List<Entity> toBeRemoved;

	public StarTrek() {
		JFrame container = new JFrame("Star Trek");
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);
		
		this.setBounds(0, 0, 800, 600);
		panel.add(this);
		
		this.setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.requestFocus();
		this.addKeyListener(new MyKeyListener(this));
		this.createBufferStrategy(2);
		this.strategy = this.getBufferStrategy();
	}
	
	private void initEntities() {
		EntityCreator playerCreator = new PlayerCreator();
		entities = new ArrayList<>();
		toBeRemoved = new ArrayList<>();

		Entity player = playerCreator.createEntity();  //new Entity(this, 10, 10, 100, 100);
//		Entity e2 = creator.createEntity(); //new Entity(this, 500, 500, 100, 100);
        Sprite playerSprite = new Sprite("Player.png", 50, 62);
        player.init(this, 400, 510, playerSprite, EntityType.Player);
        player.setSpeedX(300);
//        player.setSpeedY(100);
//      e1.setSpeedX(100);
//		e1.setSpeedY(100);
//		e2.setSpeedX(-100);
//		e2.setSpeedY(-100);
		entities.add(player);
//		entities.add(e2);
	}
	
	public void startGame() {
		initEntities();
		gameRunning = true;
	}

    private long delta;

	public void gameLoop() {
		long lastLoopMillis = System.currentTimeMillis();
		while(gameRunning) {
			delta = System.currentTimeMillis() - lastLoopMillis;
			lastLoopMillis = System.currentTimeMillis();

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			
			for (Entity e : entities) {
			    if (e.type == EntityType.Bullet)
				    e.fly(-1, delta);
			}
			
			for (Entity e : entities) {
				e.draw(g);
			}
			
//			for (int i = 0; i < entities.size(); i++) {
//				for (int j = i + 1; j < entities.size(); j++) {
//					Entity e1 = entities.get(i);
//					Entity e2 = entities.get(j);
//					if (e1.collidesWith(e2)) {
//						e1.hasCollided(e2);
//						e2.hasCollided(e1);
//					}
//				}
//			}
			
			g.dispose();  // draw complete => clear buffer
			strategy.show();  // flip buffer

			try { Thread.sleep(10); } catch(InterruptedException e) {}
		}
	}

	public Player getPlayer(){
	    for (Entity e : entities) {
	        if (e.type == EntityType.Player)
                return (Player) e;
	    }
	    return null;
    }

    public long getDelta() {
	    return delta;
    }

    public void createBullet() {
        entities.add(getPlayer().fire());
    }

	public static void main(String argv[]) {
		StarTrek game = new StarTrek();
		game.startGame();
		game.gameLoop();
	}
}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

enum EntityType {
    Player,
    Enemy,
    Bullet,
    None
}

public abstract class Entity {
    protected EntityType type;
	protected Sprite sprite;
	protected StarTrek ref;
	protected int x, y, w, h;  // w and h will disappear

	/** vertical speed in px/sec */
	protected int dx;

	/** vertical speed in px/sec */
	protected int dy;

	public Entity() {}

	public Entity(StarTrek ref, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	public abstract void init(StarTrek ref, int x, int y, Sprite sprite, EntityType type);

	public void setSpeedX(int dx) { this.dx = dx; }
	public void setSpeedY(int dy) { this.dy = dy; }
	
	public void move(int dir, long delta) {
		x += dir * (dx * delta) / 1000.0;
//		y += (dy * delta) / 1000.0;
	}

    public void fly(int dir, long delta) {
//        x += dir * (dx * delta) / 1000.0;
		y += dir * (dy * delta) / 1000.0;
    }
	
	public void draw(Graphics2D g) {
	    if (sprite == null) return;
        sprite.draw(g, x, y);
//		g.setColor(Color.WHITE);
//		g.fillRect(x, y, w, h);
//		g.setColor(Color.BLACK);
	}
	
	public boolean collidesWith(Entity o) {
		Rectangle r1 = new Rectangle(x, y, w, h);
		Rectangle r2 = new Rectangle(o.x, o.y, o.w, o.h);
		return r1.intersects(r2);
	}
	
	public /*abstract*/ void hasCollided(Entity o) {
		System.out.println("collided");
	}
}

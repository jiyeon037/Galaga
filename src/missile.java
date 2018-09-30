import java.awt.Image;

public class missile extends Sprite {
	private ShootingGame game;
	
	public missile(ShootingGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dy = -3;
	}
	
	public void move() {
		super.move();
		if(y < -100) {
			game.removeSprite(this);
		}
	}
	
	public void handleCollision(Sprite other) {
		if(other instanceof Enemy) {
			game.removeSprite(this);
			game.removeSprite(other);
		}
	}
	
}

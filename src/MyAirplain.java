import java.awt.Image;

public class MyAirplain extends Sprite {
	private ShootingGame game;
	
	public MyAirplain(ShootingGame game, Image image, int x, int y) {
		super(image, x, y);

		this.game = game;
		dx = 0;
		dy = 0;
		
	}
	
	public void move() {
		if((dx < 0) && (x < 10)) {
			return;
		}
		if((dx > 0) && (x > 800)) {
			return;
		}
		super.move();
	}
	
	public void handleCollision(Sprite other) {
		if(other instanceof Enemy) {
			game.endGame();
		}
	}
	
}
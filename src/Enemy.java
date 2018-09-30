import java.awt.Image;

public class Enemy extends Sprite {
	private ShootingGame game;
	boolean directR = false;
	private int step = 7;
	public Enemy(ShootingGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = -10;
	}

	@Override
	public void move() {
		if(directR){
			step++;
			if(step == 30){
				dx = -dx;
				y += 30;
				directR = false;
			}
		}
		else{
			step--;
			if(step == 0){
				dx = -dx;
				y += 30;
				directR = true;
			}
		}

		if(y > 600) {
			game.endGame();
		}

		super.move();
	}
}
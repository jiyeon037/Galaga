import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class EnemyToLevel {
	private static int[] unitCount = {7,14,21,28};
	private  ShootingGame game;

	ArrayList<Sprite> answer;
	private  BufferedImage enemyImage;
	public EnemyToLevel(){

	}
	public EnemyToLevel(ShootingGame game){
		this.game = game;
		try {
			enemyImage = ImageIO.read(new File("img/enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Sprite> getUnit(int level){
		answer = new ArrayList();

		if(level == 1) Level1();
		else if(level == 2) Level2();
		else if(level == 3) Level3();
		else if(level == 4) Level4();
		return answer;
	}
	public void Level1(){
		for(int x=0; x<7; x++) {
			Sprite enemy = new Enemy(game, enemyImage, 100+(x*60),80);
			answer.add(enemy);
		}
	}
	public void Level2(){
		for(int y=0; y < 2; y ++){
			for(int x=0; x<7; x++) {
				Sprite enemy = new Enemy(game, enemyImage, 100+(x*60), 80 + y*(30));
				answer.add(enemy);
			}
		}
	}
	public void Level3(){
		for(int y=0; y < 3; y ++){
			for(int x=0; x<7; x++) {
				Sprite enemy = new Enemy(game, enemyImage, 100+(x*60), 80 + y*(30));
				answer.add(enemy);
			}
		}
	}
	public void Level4(){
		for(int y=0; y < 4; y ++){ // ¤µ¤µ¤²
			for(int x=0; x<7; x++) {
				Sprite enemy = new Enemy(game, enemyImage, 100+(x*60), 80 + y*(30));
				answer.add(enemy);
			}
		}
	}

}

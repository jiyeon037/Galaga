import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ShootingGame extends JPanel implements KeyListener {
	private int[] unitLife = {0,7,14,21,28};
	private int[] unitDelay = {0,400,200,100,50};
	private boolean running = true;
	private ArrayList sprites = new ArrayList();
	private ArrayList missileSprites = new ArrayList();
	private ArrayList character = new ArrayList();
	private Sprite starship;

	private BufferedImage missileImage;
	private BufferedImage airplainImage;

	private static int level = 1;
	JFrame frame;
	public ShootingGame() {
		frame = new JFrame("Shooting Game");
		frame.setSize(800,650);
		frame.add(this);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			missileImage = ImageIO.read(new File("img/missile.png"));
			airplainImage = ImageIO.read(new File("img/aircraft.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.requestFocus();
		this.setBackground(Color.BLACK);
		this.initSprites();
		addKeyListener(this);
	}

	public void initSprites() {
		starship = new MyAirplain(this, airplainImage, 370, 550);

		EnemyToLevel etl = new EnemyToLevel(this);
		sprites = etl.getUnit(level);
		//sprites.add(starship); //character
		character.clear();
		character.add(starship);
		missileSprites.clear();
		System.out.println("Start!");
	}

	private void starGame() {
		sprites.clear();
		initSprites();
	}

	public void endGame() {
		running = false;
		Standby a = new Standby(ShootingGame.this, 6);
		frame.dispose();
		this.disable();
	}

	public void clearGame(){
		Standby a = new Standby(ShootingGame.this, 5);
		frame.dispose();
		this.disable();
		frame.setVisible(false);
	}

	public void removeSprite(Sprite sprite) {
		if (sprite instanceof missile) {
			missileSprites.remove(sprite);
		} else if (sprite instanceof Enemy) {
			sprites.remove(sprite);
		}
	}

	public void fire() {
		missile shot = new missile(this, missileImage, starship.getX()+10, starship.getY()-70);
		missileSprites.add(shot);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		for(int i=0; i<sprites.size(); i++) {
			Sprite sprite = (Sprite) sprites.get(i);
			sprite.draw(g);
		}
		for(int i=0; i< missileSprites.size(); i++) {
			Sprite sprite = (Sprite) missileSprites.get(i);
			sprite.draw(g);
		}
		for(int i=0; i< character.size(); i++) {
			Sprite sprite = (Sprite) character.get(i);
			sprite.draw(g);
		}
	}


	public void ready(){
		while(true){
			if(running){
				restart();
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void restart(){
		initSprites();
		gameLoop();
	}

	public void changeRunning(){
		running = true;
	}
	public void gameLoop() {
		//notify();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(running) {
					for(int i=0; i<sprites.size(); i++) {
						Sprite sprite = (Sprite) sprites.get(i);
						sprite.move();
					}
					repaint();
					try {
						Thread.sleep(unitDelay[level]);
					} catch (Exception e) {}
				}
				System.out.println("Clear!");
			}
		}).start();
		while(running) {
			for(int i=0; i<missileSprites.size(); i++) {
				Sprite sprite = (Sprite) missileSprites.get(i);
				sprite.move();
			}
			for(int i=0; i<character.size(); i++) {
				Sprite sprite = (Sprite) character.get(i);
				sprite.move();
			}

			for(int p=0; p<sprites.size(); p++) {
				for(int s=0; s<missileSprites.size(); s++) {
					try{
						Sprite me = (Sprite) sprites.get(p);
						Sprite other = (Sprite) missileSprites.get(s);

						if(me.checkCollision(other)) {
							me.handleCollision(other);
							other.handleCollision(me);
							unitLife[level]--;
							if(unitLife[level] <= 0){
								if(level == 4){
									running = false; // game end
									clearGame();
								}
								else{
									level++;
									running = false;
									new Thread(new Runnable() {
										@Override
										public void run() {
											Standby a = new Standby(ShootingGame.this, level);
										}
									}).start();
									ready();

								}
							}
						}
					}
					catch(NullPointerException E){
						continue;
					}
					catch(IndexOutOfBoundsException E){
						continue;
					}

				}
			}

			this.repaint();
			try {
				Thread.sleep(10);
			} catch (Exception e) {}
		}

		System.out.println("End Game");
	}


	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(-3);
		if(event.getKeyCode() == KeyEvent.VK_RIGHT)
			starship.setDx(+3);
		if(event.getKeyCode() == KeyEvent.VK_SPACE)
			fire();

	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(0);
		if(event.getKeyCode() == KeyEvent.VK_RIGHT)
			starship.setDx(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}

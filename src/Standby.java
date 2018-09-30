import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Standby {
	private StartGameClass game;
	JFrame J;
	ImageIcon bg = null;
	JButton btn;

	public Standby(){

	}
	public Standby(StartGameClass game){
		this.game = game;
		J = new JFrame();

		J.setSize(500, 200);
		J.setLocation(150,200);
		J.setLayout(null);
		btn  = new JButton(new ImageIcon(
				((new ImageIcon("img/start.png").getImage().getScaledInstance(160, 52, java.awt.Image.SCALE_SMOOTH)))));

		ImageIcon bg = new ImageIcon("img/Level1.png");
		JPanel bg_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(bg.getImage(),0, 0, 500, 200, null);
			}
		};

		bg_panel.setBounds(0, 0, 500, 200);
		bg_panel.setVisible(true);
		btn.setBounds(185, 100, 130, 40);
		btn.setContentAreaFilled(false);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.startGame();
				J.dispose();
			}
		});

		J.add(btn);
		J.add(bg_panel);

		J.setVisible(true);
	}

	public Standby(ShootingGame SG, int level){
		J = new JFrame();

		J.setSize(500, 200);
		J.setLocation(150,200);
		J.setLayout(null);

		if(level == 2) bg = new ImageIcon("img/Level2.png");
		else if(level == 3) bg = new ImageIcon("img/Level3.png");
		else if(level == 4) bg = new ImageIcon("img/Level4.png");
		else if(level == 5) bg = new ImageIcon("img/clear.png");
		else if(level == 6) bg = new ImageIcon("img/gameover.png");

		if(level <=4){
			btn = new JButton(new ImageIcon(
					((new ImageIcon("img/start.png").getImage().getScaledInstance(160, 52, java.awt.Image.SCALE_SMOOTH)))));
		}
		else{
			btn = new JButton(new ImageIcon(
					((new ImageIcon("img/Close-Button.png").getImage().getScaledInstance(160, 52, java.awt.Image.SCALE_SMOOTH)))));
		}

		JPanel bg_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(bg.getImage(),0, 0, 500, 200, null);
			}
		};

		bg_panel.setBounds(0, 0, 500, 200);
		bg_panel.setVisible(true);
		btn.setBounds(185, 100, 130, 40);
		btn.setContentAreaFilled(false);
		if(level <= 4){
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					SG.changeRunning();
					J.dispose();
				}
			});
		}
		else{
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					J.dispose();
				}
			});
		}

		J.add(btn);
		J.add(bg_panel);
		J.setVisible(true);
	}


	public void closeJ(){
		J.dispose();
	}
}


public class StartGameClass {
	public static Standby sb;

	public static void main(String[] args){
		StartGameClass s = new StartGameClass();
		sb = new Standby(s);
	}

	public void startGame(){

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hi");
				ShootingGame g = new ShootingGame();
				g.gameLoop();
			}
		}).start();
	}
}

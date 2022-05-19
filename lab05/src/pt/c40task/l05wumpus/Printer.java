package pt.c40task.l05wumpus;

public class Printer {
	
	private Mapa mapa;

	public Printer(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public char[][] getMapa(){
		return this.mapa.getMapa();
	}
	
	public void turnPrinter(String name, int score, String[] messages) {
		char[][] map = mapa.getMapa();
		int l = map[0].length;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l-1; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println(map[i][l]);
		}
		System.out.println("Player: " + name);
		System.out.println("Score: " + score);
		for (String m : messages) {
			if (m.substring(0, 5) != "Error");
				System.out.println(m);
		}
	}
	
}

package pt.c40task.l05wumpus;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Instancia Brisas ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 * 
 * @author Frost
 */

public class Buraco extends Componente{
	
	int[] pos = new int[2];
	Caverna cave;
	
	/**
	 * Construtor, instancia brisas ao seu redor
	 * 
	 * @param pos, cave
	 * @author Frost
	 */
	public Buraco(int[] pos, Caverna cave){
		this.pos = pos;
		this.cave = cave;
		int[][] brisasPos = {{pos[0]-1, pos[1]}, {pos[0]+1, pos[1]},
							{pos[0], pos[1]-1}, {pos[0], pos[1]+1}};
		int i = 0;
		while (i < 4) {
			try {
				cave.insertInRoom(brisasPos[i], new Brisa());
			} finally {
				i++;
			}
		}
	}

	@Override
	public String toString() {
		return "Buraco";
	}

}

package pt.c40task.l05wumpus;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Instancia Fedores ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 * - Quando o Wumpus acorda, ele inspeciona a sala
 *    - Se encontrar o Herói, mata-o
 *    - Se encontrar uma flecha, tenta desviar-se (50%)
 *       - Se consegue, quebra a flecha irritado
 *       - Se não consegue, flecha encrava-se em suas costas, matando-o
 *  
 * @author Frost
 */

public class Wumpus extends Componente{

	int[] pos = new int[2];
	boolean dormindo = true;
	Caverna cave;
	
	/**
	 * Construtor, instancia fedores ao seu redor
	 * 
	 * @param pos, cave
	 * @author Frost
	 */
	public Wumpus(int[] pos, Caverna cave){
		this.pos = pos;
		this.cave = cave;
		int[][] fedoresPos = {{pos[0]-1, pos[1]}, {pos[0]+1, pos[1]},
							{pos[0], pos[1]-1}, {pos[0], pos[1]+1}};
		int i = 0;
		while (i < 4) {
			try {
				cave.insertInRoom(fedoresPos[i], new Fedor());
			} finally {
				i++;
			}
		}
	}
	
	@Override
	/**
	 * Wumpus não gosta que fiquem olhando pra ele enquanto dorme.
	 * - Verificações ao objeto Wumpus acordam-no, para que tenha um turno logo em seguida.
	 * 
	 * @author Frost
	 */
	public String toString() {
		this.dormindo = false;
		return "Wumpus";
	}

	/**
	 * Função de uso exclusivo para durante montagem da caverna, adormece Wumpus
	 * 
	 * @author Frost
	 */
	public void calmWumpus(boolean init) {
		if (init)
			this.dormindo = true;
	}
}

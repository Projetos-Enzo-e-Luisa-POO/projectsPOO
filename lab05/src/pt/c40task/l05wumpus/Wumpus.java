package pt.c40task.l05wumpus;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Instancia Fedores ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 * - Quando o Wumpus acorda, ele inspeciona a sala
 *    - Se encontrar o Herói, mata-o
 *    - Se encontrar uma flecha, tenta desviar-se (50%)
 *       - Se consegue, quebra a flecha irritado
 *       - Se não consegue, flecha encrava-se em suas costas, matando-o
 */

public class Wumpus extends Componente{

	int[] pos = new int[2];
	boolean dormindo = true;
	
	/**
	 * Construtor, instancia fedores ao seu redor
	 */
	public Wumpus(int[] pos){
		
	}
	
	@Override
	/**
	 * Wumpus não gosta que fiquem olhando pra ele enquanto dorme.
	 * - Verificações ao objeto Wumpus acordam-no, para que tenha um turno logo em seguida.
	 */
	public String toString() {
		this.dormindo = false;
		return "Wumpus";
	}

	/**
	 * Função de uso exclusivo para durante montagem da caverna, adormece Wumpus
	 */
	public void calmWumpus(boolean init) {
		if (init)
			this.dormindo = true;
	}
}

package pt.c40task.l05wumpus;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Instancia Brisas ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 */

public class Buraco extends Componente{
	
	int[] pos = new int[2];
	
	/**
	 * Construtor, instancia brisas ao seu redor
	 */
	public Buraco(int[] pos){
		
	}

	@Override
	public String toString() {
		return "Buraco";
	}

}

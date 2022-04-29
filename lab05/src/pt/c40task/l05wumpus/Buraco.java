package pt.c40task.l05wumpus;

/**
 * 
 * Componente ativo
 * - Instancia Brisas ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 *
 */

public class Buraco extends Componente{
	
	int[] pos = new int[1];
	
	/**
	 *  Construtor, instancia brisas ao seu redor
	 */
	public Buraco(int[] pos){
		
	}

	@Override
	public String getComponente() {
		return "Buraco";
	}

}

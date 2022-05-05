package pt.c40task.l05wumpus;

/**
 * 
 * Componente ativo
 * - Instancia Fedores ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 * - getComponente sobrecarregado para inicialização e para resto do jogo
 *    - Inicialização: Só indica que é um Wumpus
 *    - Resto do Jogo: Acorda o Wumpus, alguém está querendo entrar em sua sala
 * - Quando o Wumpus acorda, ele inspeciona a sala
 *    - Se encontrar o Herói, mata-o
 *    - Se encontrar uma flecha, tenta desviar-se (50%)
 *       - Se consegue, quebra a flecha irritado
 *       - Se não consegue, flecha encrava-se em suas costas, matando-o
 *
 */

public class Wumpus extends Componente{

	int[] pos = new int[1];
	boolean dormindo = true;
	
	/**
	 *  Construtor, instancia fedores ao seu redor
	 */
	public Wumpus(int[] pos){
		
	}
	
	@Override
	public String getComponente() {
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

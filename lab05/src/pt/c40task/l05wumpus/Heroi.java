package pt.c40task.l05wumpus;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Possui uma mochila com número de flechas arbitrário e um mapa
 * - Anda de uma sala para outra, pedindo movimento para Caverna
 * - Inspeciona a sala logo ao entrar na sala
 *    - Pode morrer ao perceber que é um buraco!
 *    - Se encontrar uma flecha, encontra-a quebrada (não havia Wumpus na sala)
 *    - Se sentir fedor, brisa ou ver ouro, atualiza seu mapa!
 * - Arma sua flecha, disparando-a antes do próximo movimento para uma sala
 * - Se viu ouro ao entrar na sala, pode pegá-lo e o colocá-lo em sua mochila (solicita remoção da caverna)
 */

public class Heroi extends Componente{
	
	int[] pos = new int[2];

	/**
	 * Protótipo de construtor:
	 * - Cria todos os pertences do Herói
	 * - Cria mochila com (tamanhoMochila) espaços, e (numFlechas ou menor) flechas
	 * - Cria mapa (size) x (size) e insere-o na mochila
	 */
	public Heroi(int[] pos, int size, int tamanhoMochila, int numFlechas) {
		int k = ((numFlechas < tamanhoMochila) ? numFlechas : tamanhoMochila - 2);
		Componente[] aljava = new Componente[k];
		for (int i = 0; i < k; i++)
			aljava[k] = new Flecha();
		Mochila mochilaDoHeroi = new Mochila(aljava, tamanhoMochila, k);
		mochilaDoHeroi.insere(tamanhoMochila, new Mapa(size));	// Mapa sempre estará na última posição.
		
		//..?
		
	}
	
	@Override
	public String toString() {
		return "Heroi";
	}

}

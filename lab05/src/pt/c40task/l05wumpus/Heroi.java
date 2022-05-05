package pt.c40task.l05wumpus;

/**
 * 
 * Componente ativo
 * - Inicializa mochila com uma flecha
 * - Anda de uma sala para outra, pedindo movimento para Caverna
 *    - Pode morrer ao andar!
 * - Inspeciona a sala logo ao entrar na sala
 *    - Pode morrer ao perceber que é um buraco!
 *    - Se encontrar uma flecha, encontra-a quebrada
 *    - Se sentir fedor, brisa ou ver ouro, atualiza seu mapa!
 * - Arma sua flecha, disparando-a antes do próximo movimento para uma sala
 * - Pega ouro, se viu ouro ao entrar na sala, e o coloca em sua mochila (solicita remoção da caverna)
 *
 */

public class Heroi extends Componente{
	
	int[] pos = new int[1];

	/**
	 * Cria todos os pertences do Herói
	 * Cria mochila com tamanhoMochila espaços, e numFlechas flechas
	 * Cria mapa size x size e insere-o na mochila
	 */
	public Heroi(int[] pos, int size, int tamanhoMochila, int numFlechas) {
		int k = ((numFlechas < tamanhoMochila) ? numFlechas : tamanhoMochila - 2);
		Componente[] aljava = new Componente[k];
		for (int i = 0; i < k; i++)
			aljava[k] = new Flecha();
		Mochila mochilaDoHeroi = new Mochila(aljava, tamanhoMochila, k);
		mochilaDoHeroi.insert(new Mapa(size), tamanhoMochila - 1);	// Mapa sempre estará na penultima posição, última guardada para ouro
	}
	
	@Override
	public String getComponente() {
		return "Heroi";
	}

}

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
	 * Cria mochila com n espaços, e m flechas
	 * Cria mapa size x size
	 */
	public Heroi(int[] pos, int size, int tamanhoMochila, int numFlechas) {
		Mapa mapaDoHeroi = new Mapa(size);
		Componente[] aljava = new Componente[numFlechas];
		int k = ((numFlechas < tamanhoMochila) ? numFlechas : tamanhoMochila - 1);
		for (int i = 0; i < k; i++)
			aljava[k] = new Flecha();
		Mochila mochilaDoHeroi = new Mochila(aljava, tamanhoMochila, k);
		
		
		
	}
	
	@Override
	public String getComponente() {
		return "Heroi";
	}

}

package pt.c40task.l05wumpus;

/**
 * Classe Caverna
 * - Instancia um array de Salas vazias
 * - Recebe componentes e posiciona-os nos índices indicados
 * - Remove componentes de Salas em caso de movimento ou morte
 * - Move o heroi pela caverna
 * 
 * @author mariecurie
 */

public class Caverna {
	
	private Sala[][] caverna;

	/**
	 * Construtor da caverna, instancia salas de tamanho (dimensaoSala)
	 * 		em um array (dimensaoCaverna) x (dimensaoCaverna).
	 * 
	 * @param dimensaoCaverna
	 * @param dimensaoSala
	 */
	public Caverna(int dimensaoCaverna, int dimensaoSala) {
		this.caverna = new Sala[dimensaoCaverna][dimensaoCaverna];
		for (int i = 0; i < dimensaoCaverna; i++) {
			for (int j = 0; j < dimensaoCaverna; j++) {
				caverna[i][j] = new Sala(dimensaoSala);
			}
		}
	}

	/**
	 * Método que intermedia a inspeção da sala na posição (pos) feita por um componente.
	 * - Caso o componente esteja tentando dar scout em uma sala que não a sua, direciona o erro para o componente
	 * 
	 * @param pos
	 */
	public String[] scanRoom(int[] pos, String requester) {
		try {
			return caverna[pos[0]][pos[1]].scan(requester);
		} catch (Error erro) {
			throw new Error(erro.getMessage());
		}
	}
	
	/**
	 * Método que intermedia a inserção de um compontente na sala de posição (pos)
	 * 
	 * @param pos
	 * @param comp
	 */
	public void insertInRoom(int[] pos, Componente comp) {
		try {
			caverna[pos[0]][pos[1]].insert(comp);
		} catch (Error erro) {
			throw new Error(erro.getMessage());
		}
	}

	/**
	 * Método que intermedia a remoção de um componente da sala de posição (pos)
	 * 
	 * @param pos
	 * @param comp
	 */
	public Componente removeFromRoom(int[] pos, String comp) {
		return caverna[pos[0]][pos[1]].remove(comp);	
	}
	
	/**
	 * Sobrecarga do método removeFromRoom que chama o método remove sobrecarregado, 
	 * 		leva em consideração quem solicita a remoção
	 * 
	 * @param pos
	 * @param comp
	 * @param requester
	 */
	public Componente removeFromRoom(int[] pos, String comp, String requester) {
		return caverna[pos[0]][pos[1]].remove(comp, requester);	
	}
}

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
	private int dimensaoCaverna;

	/**
	 * Construtor da caverna, instancia salas de tamanho (dimensaoSala)
	 * 		em um array (dimensaoCaverna) x (dimensaoCaverna).
	 * 
	 * @param dimensaoCaverna
	 * @param dimensaoSala
	 */
	public Caverna(int dimensaoCaverna, int dimensaoSala) {
		this.dimensaoCaverna = dimensaoCaverna;
		this.caverna = new Sala[this.dimensaoCaverna][this.dimensaoCaverna];
		for (int i = 0; i < this.dimensaoCaverna; i++) {
			for (int j = 0; j < this.dimensaoCaverna; j++) {
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

	public void moveComponent(Componente comp, int[] pos) {
		if (pos[0] < 0 || pos[0] >= this.dimensaoCaverna || pos[1] < 0 || pos[1] >= this.dimensaoCaverna) {
			throw new Error("Cannot move out of cave limits, please choose a position in [0," + this.dimensaoCaverna + ']');
		}
		this.insertInRoom(pos, comp);
	}
}

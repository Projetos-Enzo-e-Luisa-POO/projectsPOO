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

	public void insertInRoom(int[] pos, Componente comp) {
		try {
			caverna[pos[0]][pos[1]].insert(comp);
		} catch (Error erro) {
			throw new Error(erro.getMessage());
		}
	}

	public Componente removeFromRoom(int[] pos, Componente comp) {
		return caverna[pos[0]][pos[1]].remove(comp.toString());	
	}
}

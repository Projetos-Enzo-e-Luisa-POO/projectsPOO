package pt.c40task.l05wumpus;

/**
 * 
 * Classe Caverna
 * - Instancia um array de Salas vazias
 * - Recebe componentes e posiciona-os nos índices indicados
 * - Remove componentes de Salas em caso de movimento ou morte
 *
 */

public class Caverna {
	
	private int DIMENSAO_CAVERNA = 3;
	private Sala[][] caverna;
	private boolean[][] emptyRooms;

	public Caverna() {
		this.setCaverna();
		this.setEmptyRooms();
	}

	private void setCaverna () {
		this.caverna = new Sala[this.DIMENSAO_CAVERNA][this.DIMENSAO_CAVERNA];
		for (int i = 0; i < this.DIMENSAO_CAVERNA; i++) {
			for (int j = 0; j < this.DIMENSAO_CAVERNA; j++) {
				caverna[i][j] = null;
			}
		}
	}

	private void setEmptyRooms () {
		this.emptyRooms = new boolean[this.DIMENSAO_CAVERNA][this.DIMENSAO_CAVERNA];
		for (int i = 0; i < this.DIMENSAO_CAVERNA; i++) {
			for (int j = 0; j < this.DIMENSAO_CAVERNA; j++) {
				emptyRooms[i][j] = true;
			}
		}
	}

	private void insereSala (Sala sala) {
		if (this.caverna.length == this.DIMENSAO_CAVERNA * this.DIMENSAO_CAVERNA) {
			throw new Error("Caverna cheia! Nao eh possivel inserir mais salas nela")
		}
		for (int i = 0; i < this.DIMENSAO_CAVERNA; i++) {
			for (int j = 0; j < this.DIMENSAO_CAVERNA; j++) {
				if (emptyRooms[i][j]) {
					caverna[i][j] = sala;
					emptyRooms[i][j] = false;
					return;
				}
			}
		}
	}

	private void removeSala(int idSala) {
		for (int i = 0; i < this.DIMENSAO_CAVERNA; i++) {
			for (int j = 0; j < this.DIMENSAO_CAVERNA; j++) {
				if (caverna[i][j].id == idSala) {
					caverna[i][j] = null;
					emptyRooms[i][j] = true;
					return;
				}
			}
		}
		throw new Error("Nehuma sala com id " + idSala + " foi encontrada na caverna");
	}

	private void insereComponenteNaSala(int idSala, Componente comp) {
		for (int i = 0; i < this.DIMENSAO_CAVERNA; i++) {
			for (int j = 0; j < this.DIMENSAO_CAVERNA; j++) {
				if (caverna[i][j].id == idSala) {
					caverna[i][j].insert(comp);
					return;
				}
			}
		}
	}

	private void removeComponenteDaSala(int idSala, Componente comp) {
		for (int i = 0; i < this.DIMENSAO_CAVERNA; i++) {
			for (int j = 0; j < this.DIMENSAO_CAVERNA; j++) {
				if (caverna[i][j].id == idSala) {
					caverna[i][j].remove(comp.getComponente());
					return;
				}
			}
		}
	}
	
}

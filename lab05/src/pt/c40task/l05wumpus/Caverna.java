package pt.c40task.l05wumpus;

/**
 * 
 * Classe Caverna
 * - Instancia um array de Salas vazias
 * - Recebe componentes e posiciona-os nos índices indicados
 * - Remove componentes de Salas em caso de movimento ou morte
 * - Move o heroi pela caverna
 */

public class Caverna {
	
	private Sala[][] caverna;

	public Caverna(int dimensaoCaverna, int dimensaoSala) {
		this.caverna = new Sala[dimensaoCaverna][dimensaoCaverna];
		for (int i = 0; i < dimensaoCaverna; i++) {
			for (int j = 0; j < dimensaoCaverna; j++) {
				caverna[i][j] = new Sala(dimensaoSala);
			}
		}
	}

	private void insertComponentInRoom(int[] pos, Componente comp) {
		caverna[pos[0]][pos[1]].insert(comp);
	}

	private void removeComponentFromRoom(int[] pos, Componente comp) {
		caverna[pos[0]][pos[1]].remove(comp.getComponente());
	}
}

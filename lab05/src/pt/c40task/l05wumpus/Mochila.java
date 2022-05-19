package pt.c40task.l05wumpus;

/**
 * Componente "passivo" (não sabe sua posição e não age).
 * - Mochila possuída pelo herói, tem tanta inteligência quando um armário.
 * - É inicializada com um número arbitrário de flechas, criando uma mochila de no mínimo
 * 		2 espaços a mais do que o número de flechas, onde será guardado o ouro e o mapa do herói
 * 
 * @author mariecurie
 */

public class Mochila extends Componente {

	public Componente[] itens;
	
	/**
	 * Monta mochila com tamMochila espaços e recebe um array de numFlechas flechas, presumindo tamMoch >= numFlechas + 2.
	 * 
	 * @param aljava, tamMochila, numFlechas
	 * @author Frost
	 * @author mariecurie
	 */
	public Mochila(Componente[] aljava, int tamMochila, int numFlechas) {
		this.itens = new Componente[tamMochila];
		for (int i = 0; i < tamMochila; i++) {
			if (i < numFlechas) {
				itens[i] = aljava[i];
				aljava[i] = null;
			} else {
				itens[i] = null;
			}
		}
	}
	
	/**
	 * Insere componente na posição solicitada na mochila.
	 * - Retorna erro "Position (pos) is already occupied", código "_" 
	 * 		no caso da posição solicitada já estar ocupada.
	 * 
	 * @param pos, comp
	 * @author mariecurie
	 */
	public void insere(int pos, Componente comp) {
		if (this.itens[pos] != null) {
			throw new Error("Position " + pos + " is already occupied");
		}
		this.itens[pos] = comp;
	}

	/**
	 * Remove um componente da posição solicitada na mochila.
	 * - Retorna erro "Cannot remove components from last 2 backpack positions", código "_"
	 * 		no caso do herói tentar remover o ouro de sua mochila.
	 * 
	 * @param pos
	 * @return this.mochila[pos]
	 * @author mariecurie
	 */
	public Componente remove(int pos) {
		if (pos == this.itens.length - 2) {
			throw new Error("Cannot remove gold from backpack");
		}
		Componente aux = this.itens[pos - 1];
		this.itens[pos - 1] = null;
		return aux;
	}
	
	@Override
	public String toString() {
		return "Mochila";
	}

}

package pt.c40task.l05wumpus;

/**
 * 
 * Compomente que pertence ao Herói
 * Contém no máximo uma Flecha e Ouro
 *
 */

public class Mochila extends Componente {

	private Componente[] mochila;
	
	/**
	 * Monta mochila com tamMochila espaços e recebe um array de numFlechas flechas, presumindo tamMoch >= numFlechas + 2;
	 */
	public Mochila(Componente[] aljava, int tamMochila, int numFlechas) {
		this.mochila = new Componente[tamMochila];
		for (int i = 0; i < numFlechas; i++) {
			mochila[i] = aljava[i];
			aljava[i] = null;
		}
	}
	
	/**
	 * Insere comp na posição solicitada na mochila.
	 * E se herói solicitar pra inserir em posição já preenchida?
	 */
	public void insere(int pos, Componente comp) {
		if (this.mochila[pos] != null) {
			throw new Error("Position " + pos + " is already occupied");
		}
		this.mochila[pos] = comp;
	}

	/**
	 * Remove um comp da mochila
	 */
	public Componente remove(int pos) {
		if (pos == this.mochila.length - 1 || pos == this.mochila.length - 2) {
			throw new Error("Cannot remove components from last 2 schoolbag positions");
		}
		Componente aux = this.mochila[pos];
		this.mochila[pos] = null;
		return aux;
	}
	
	@Override
	public String getComponente() {
		return "Mochila";
	}

}

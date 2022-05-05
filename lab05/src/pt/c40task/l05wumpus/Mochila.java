package pt.c40task.l05wumpus;

/**
 * 
 * Compomente que pertence ao Herói
 * Contém no máximo uma Flecha e Ouro
 *
 */

public class Mochila extends Componente{

	Componente[] mochila;
	
	/**
	 * Monta mochila com temMoch espaços e recebe um array de numFlechas flechas, presumindo tamMoch >= numFlechas + 2;
	 */
	public Mochila(Componente[] aljava, int tamMoch, int numFlechas) {
		mochila = new Componente[tamMoch];
		for (int i = 0; i < numFlechas; i++) {
			mochila[i] = aljava[i];
			aljava[i] = null;
		}
	}
	
	/**
	 * Insere comp na posição solicitada na mochila.
	 * E se herói solicitar pra inserir em posição já preenchida?
	 */
	public void insert(Componente comp, int pos) {
		
	}
	
	@Override
	public String getComponente() {
		return "Mochila";
	}

}

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
	 * Monta mochila com n espaços e recebe um array de m flechas, m sempre menor ou igual a n-1
	 */
	public Mochila(Componente[] aljava, int n, int m) {
		mochila = new Componente[n];
		for (int i = 0; i < m; i++)
			mochila[i] = aljava[i];
	}
	
	@Override
	public String getComponente() {
		return "Mochila";
	}

}

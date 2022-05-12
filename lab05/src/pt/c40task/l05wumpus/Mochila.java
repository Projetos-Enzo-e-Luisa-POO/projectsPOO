package pt.c40task.l05wumpus;

/**
 * 
 * Compomente que pertence ao Herói
 * Contém no máximo uma Flecha e Ouro
 *
 */

public class Mochila extends Componente {

	private Componente[] mochila;
	private int posicaoUltimoComponente;
	
	/**
	 * Monta mochila com tamMochila espaços e recebe um array de numFlechas flechas, presumindo tamMoch >= numFlechas + 2;
	 */
	public Mochila(Componente[] aljava, int tamMochila, int numFlechas) {
		this.setMochila(aljava, tamMochila, numFlechas);
		this.posicaoUltimoComponente = -1;
	}

	private void setMochila (Componente[] aljava, int tamMochila, int numFlechas) {
		int tamanho = tamMochila >= (numFlechas + 2) ? tamMochila : (numFlechas + 2);
		this.mochila = new Componente[tamanho];

		for (int i = 0; i < numFlechas; i++) {
			mochila[i] = aljava[i];
			aljava[i] = null;
		}
	}
	
	/**
	 * Insere comp na posição solicitada na mochila.
	 * E se herói solicitar pra inserir em posição já preenchida?
	 */
	public int insere(Componente comp) {
		if (this.posicaoUltimoComponente == this.mochila.length) {
			throw new Error("Mochila cheia! Nao eh possivel guardar o componente " + comp.getComponente());
		}
		this.posicaoUltimoComponente++;
		this.mochila[this.posicaoUltimoComponente] = comp;
		return this.posicaoUltimoComponente;
	}

	/**
	 * Remove o componente guardado numa posicao pos da mochila e,
	 * a partir dessa posição, copia os componentes seguintes que
	 * posuem posição p para uma posição (p - 1)
	 */
	private void reordenaMochilaAPartirDaPosicao(int pos) {
		for (int i = pos; i < (this.mochila.length - 1); i++) {
			this.mochila[i] = this.mochila[i+1];
		}
		this.mochila[this.posicaoUltimoComponente] = null;
		this.posicaoUltimoComponente--;
	}

	/**
	 * Remove um comp da mochila
	 */
	public void remove(Componente comp) {
		for (int i = 0; i < this.mochila.length; i++) {
			if (this.mochila[i].id == comp.id) {
				this.reordenaMochilaAPartirDaPosicao(i);
				break;
			}
		}
	}
	
	@Override
	public String getComponente() {
		return "Mochila";
	}

}

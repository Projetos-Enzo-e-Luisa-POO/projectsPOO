package pt.c40task.l05wumpus;

/**
 * Componente possuída pelo Herói
 * Mapa que será apresentado ao user durante o jogo
 * @author Frost
 */

public class Mapa extends Componente{

	private char[][] mapa;
	
	/**
	 * Inicializa o mapa para o começo da exploração da caverna
	 * @param size
	 * @author Frost
	 */
	public Mapa(int size) {
		mapa = new char[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				mapa[i][j] = '-';
		mapa[0][0] = 'P';
	}
	
	/**
	 * Registra no mapa o caractere que o Herói solicitou, na posição solicitada.
	 * @param c, pos
	 * @author Frost
	 */
	public void register(char c, int[] pos) {
		mapa[pos[0]][pos[1]] = c;
	}
	
	/**
	 * Retorna o array de char "mapa".
	 * @return mapa
	 * @author Frost
	 */
	public char[][] getMapa(){
		return mapa;
	}
	
	@Override
	public String getComponente() {
		return "Mapa";
	}

}

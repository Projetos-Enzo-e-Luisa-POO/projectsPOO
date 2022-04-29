package pt.c40task.l05wumpus;

/**
 * 
 * Componente possuída pelo Herói
 * Mapa que será apresentado ao user durante o jogo
 *
 */

public class Mapa extends Componente{

	char[][] mapa;
	
	public Mapa(int size) {
		mapa = new char[size][size];
	}
	
	@Override
	public String getComponente() {
		return "Mapa";
	}

}

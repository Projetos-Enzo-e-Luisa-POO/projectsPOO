package pt.c40task.l05wumpus;

/**
 * Componente "passivo" (não sabe sua posição e não age).
 * - Sempre (e somente) será instanciada pelo MontadorDaCaverna.
 * - Objetivo do herói, sendo sua presença na mochila condição de vitória do jogo.
 */

public class Ouro extends Componente{

	@Override
	public String toString() {
		return "Ouro";
	}

}

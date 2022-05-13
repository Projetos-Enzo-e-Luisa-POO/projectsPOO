package pt.c40task.l05wumpus;

/**
 * Componente "passivo" (não sabe sua posição e não age).
 * - Sempre (e somente) será instanciada pelo Buraco.
 * - Será sentida pelo herói ao analisar a sala.
 */

public class Brisa  extends Componente{

	@Override
	public String toString() {
		return "Brisa";
	}

}

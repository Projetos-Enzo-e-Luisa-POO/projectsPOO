package pt.c40task.l05wumpus;

/**
 * Componente "passivo" (não sabe sua posição e não age).
 * - Sempre (e somente) será instanciado pelo Wumpus.
 * - Será sentida pelo herói ao analisar a sala.
 */

public class Fedor extends Componente{

	@Override
	public String toString() {
		return "Fedor";
	}

}

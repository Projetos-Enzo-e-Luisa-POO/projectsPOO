package pt.c40task.l05wumpus;

/**
 * Componente "passivo" (não sabe sua posição e não age).
 * - Sempre (e somente) será instanciada pelo Herói.
 * - Manejada pelo herói para tentar matar o Wumpus.
 */

public class Flecha extends Componente{

	@Override
	public String toString() {
		return "Flecha";
	}

}

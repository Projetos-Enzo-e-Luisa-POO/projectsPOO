package pt.c40task.l05wumpus;

/**
 * Superclasse abstrata que padroniza todos os outros componentes
 * - Obriga Compoentes a terem um método toString próprio que
 *		será usado como interface de "detecção" e identificação
 */

public abstract class Componente{
	
	/**
	 * Por padrão, retorna o nome do respectivo Componente.
	 */
	public abstract String toString();
	
}

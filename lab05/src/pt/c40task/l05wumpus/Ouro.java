package pt.c40task.l05wumpus;

/**
 * 
 * Objeto presente na caverna, inanimado
 * só possui método padrão de ComponenteInterface
 * Desconhece sua posição (não é capaz de saber)
 * 
 */

public class Ouro extends Componente{

	@Override
	public String getComponente() {
		return "Ouro";
	}

}

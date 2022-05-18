package pt.c40task.l05wumpus;

import java.util.Random;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Instancia Fedores ao seu redor, calculando suas posições e solicitando que Caverna posicione-as
 * - Quando o Wumpus acorda, ele inspeciona a sala
 *    - Se encontrar o Herói, mata-o
 *    - Se encontrar uma flecha, tenta desviar-se (50%)
 *       - Se consegue, quebra a flecha irritado
 *       - Se não consegue, flecha encrava-se em suas costas, matando-o
 *  
 * @author Frost
 */

public class Wumpus extends Componente{

	int[] pos = new int[2];
	boolean dormindo = true;
	Caverna cave;
	
	/**
	 * Construtor, instancia fedores ao seu redor
	 * 
	 * @param pos, cave
	 * @author Frost
	 */
	public Wumpus(int[] pos, Caverna cave){
		this.pos = pos;
		this.cave = cave;
		int[][] fedoresPos = {{pos[0]-1, pos[1]}, {pos[0]+1, pos[1]},
							{pos[0], pos[1]-1}, {pos[0], pos[1]+1}};
		int i = 0;
		while (i < 4) {
			try {
				cave.insertInRoom(fedoresPos[i], new Fedor());
			} finally {
				i++;
			}
		}
	}
	
	/**
	 * Método de inspeção da sala, trigger para outras ações do componente
	 * - Retorna String outcome, com mensagens que informam ControleDoJogo resultado do turno 
	 * 
	 * @author Frost
	 */
	public String scout() {
		String[] saw;
		String outcome = "Nothing happened";
		try {
			saw = cave.scanRoom(this.pos, "Wumpus");
			for (int i = 0; i < saw.length; i++) {
				if (saw[i] == "Flecha") 
					if (this.dodge())
						outcome = "Wumpus has been killed";
				else if (saw[i] == "Heroi") {
					this.kill("Heroi");
					outcome = "Hero has been killed";
				}
			}
		} catch (Error erro) {
			outcome = erro.getMessage();
		}
		return outcome;
	}
	
	/**
	 * Ação do Wumpus caso veja uma flecha que acabou de entrar em sua sala
	 * - Chance de sobrevivência de 50%
	 */
	private boolean dodge() {
		boolean outcome = false;
		Random d2 = new Random();
		int dexSave = d2.nextInt(2) + 1;
		if (dexSave == 1) {
			this.kill("Wumpus");
			outcome = true;
		}
		else
			this.kill("Flecha");
		return outcome;
	}
	
	/**
	 * Wumpus consegue matar um componente.
	 * - Caso o método seja chamado para o próprio Wumpus por não ter conseguido desviar da flecha
	 * 		método removeFromRoom retornará null, mas terá removido Wumpus de sua Sala
	 * 
	 * @param comp
	 */
	private void kill(String comp) {
		Componente remains = cave.removeFromRoom(pos, comp, "Wumpus");
		if (remains != null)
			remains = null;
	}
	
	
	@Override
	/**
	 * Wumpus não gosta que fiquem olhando pra ele enquanto dorme.
	 * - Verificações ao objeto Wumpus acordam-no, para que tenha um turno logo em seguida.
	 * 
	 * @author Frost
	 */
	public String toString() {
		this.dormindo = false;
		return "Wumpus";
	}

	/**
	 * Função de uso exclusivo para durante montagem da caverna, adormece Wumpus
	 * 
	 * @author Frost
	 */
	public void calmWumpus(boolean init) {
		if (init)
			this.dormindo = true;
	}
}

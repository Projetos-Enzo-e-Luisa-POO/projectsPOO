package pt.c40task.l05wumpus;

/**
 * 
 * Recebe comandos da main (da interface com o player) e direciona os comandos ao Herói
 * Armazena posHerói e sempre verifica se Wumpus acordou para dá-lo ou não um turno
 * Autoriza saída do jogo do herói(?)
 *
 */

public class ControleDoJogo {
	
	private String name;
	private Wumpus wumpus;
	private Heroi heroi;
	private boolean heroiHasOuro;
	private boolean equipedArrow;
	private int score;

	private int POINTS_FOR_CATURE_GOLD = 1000;
	private int POINTS_FOR_DYING = -1000;
	private int POINTS_FOR_MOVE = -15;
	private int POINTS_FOR_USE_ARROW = -100;
	private int POINTS_FOR_KILL_WUMPUS = 500;

	public ControleDoJogo(String name, Heroi heroi, Wumpus wumpus) {
		this.name = name;
		this.heroi = heroi;
		this.wumpus = wumpus;
		this.heroiHasOuro = this.equipedArrow = false;
		this.score = 0;
	}

	/**
	 * Outcomes de erros possíveis:
	 * "Error when scanning room: You cannot scout a Room that you're not inside of"
	 * "Error moving " + comp.toString() + " to position (" + nextPosition[0] + ", " + nextPosition[1] + ")"
	 * "Error storing " + comp.toString() + " in backpack: Position " + pos + " is already occupied"
	 * "No arrow equiped" //não deveia acontecer
	 * "Minhas flechas acabaram?!" (flechas acabaram)
	 * "Ah, tudo que eu queria era um baú cheio de ouro..." (tentou capturar ouro sem ter ouro na sala)
	 * @param c
	 * @return
	 */
	public String[] gameTurn(char c) {
		String[] outcome = new String[8];
		int aux = 0;
		if (!this.equipedArrow) {
			if (c == 'w' || c == 'a' || c == 's' || c == 'd')
				try {
					heroi.move(heroi, c);
					outcome = heroi.scanRoom();
				} 
				catch (Error erro) {
					outcome[aux++] = erro.getMessage();
				}
			else {
				switch(c) {
					case 'k':	// equipa flecha
						try {
							outcome[aux++] = heroi.equipeArrow();
							this.equipedArrow = true;
						} 
						catch (Error erro) {
							outcome[aux++] = erro.getMessage();
						}
					case 'c':	// capura ouro
						try {
							outcome[aux++] = heroi.captureGold();
						} 
						catch (Error erro) {
							outcome[aux++] = erro.getMessage();
						}
					case 'q':	// quita
				}
			}
		} else {
			if (c == 'w' || c == 'a' || c == 's' || c == 'd')
				try {
					outcome[aux++] = heroi.moveArrow(c);
				} 
				catch (Error erro) {
					outcome[aux++] = erro.getMessage();
				}
		}
			switch(c) {
				case 'k':	// equipa flecha
					outcome[aux++] = "O que deu em mim? Já tenho uma flecha em mãos"; //def
				case 'c':	// capura ouro
					outcome[aux++] = "Atirar no baú não deve funcionar..."; //def
				case 'q':	// quita
			}
		if (wumpus.isDormindo()) {
			outcome[aux++] = wumpus.scout();
		}
		score += this.updateScore(outcome);
		return outcome;
	}
	
	private int updateScore(String[] outcome) {
		int sum = 0;
		for (String s : outcome) {
			if (s == "Quanto ouro pra um baúzinho só!");
				sum += POINTS_FOR_CATURE_GOLD;
			if (s == "Wumpus atacou, e não restou nada..." || s == "AAAAAAAaaaaaa......");
				sum += POINTS_FOR_DYING;
			if (s == "Aquilo é um baú?" || s == "O herói para estático diante do grande monstro." || s == "AAAAAAAaaaaaa......" || 
					s == "A brisa assovia na sala..." || s == "Que podridão! Será que tem um cadáver por aqui?" || s == "O silêncio na caverna é ensurdecedor...") // outras mensagens
				sum += POINTS_FOR_MOVE;
			if (s == "Em que sala ele deve estar?...")
				sum += POINTS_FOR_USE_ARROW;
			if (s == "A flecha voa como um raio pela sala, varando a cabeça do Wumpus antes que pudesse reagir.");
				sum += POINTS_FOR_KILL_WUMPUS;
		}
		return sum;
	}
	
	public boolean winnableGame() {
		int[] posExit = {0,0};
		return (this.heroi.getPosition() == posExit) && this.heroiHasOuro;
	}
	
	public String getName() {
		return name;
	}
}

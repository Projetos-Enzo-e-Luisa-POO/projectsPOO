package pt.c40task.l05wumpus;

/**
 * 
 * Recebe comandos da main (da interface com o player) e direciona os comandos ao Herói
 * Armazena posHerói e sempre verifica se Wumpus acordou para dá-lo ou não um turno
 * Autoriza saída do jogo do herói(?)
 *
 */

public class ControleDoJogo {
	
	public String name;
	private Wumpus wumpus;
	private Heroi heroi;
	private boolean heroiHasOuro;
	public int score;

	private int POINTS_FOR_CATURE_GOLD = 1000;
	private int POINTS_FOR_BEING_DEAD = -1000;
	private int POINTS_FOR_MOVE = -15;
	private int POINTS_FOR_USE_ARROW = -100;
	private int POINTS_FOR_KILL_WUMPUS = 500;

	public ControleDoJogo(String name, Heroi heroi, int[] posHeroi) {
		this.name = name;
		this.heroi = heroi;
		this.heroiHasOuro = false;
		this.score = 0;
	}

	private boolean isExitAvaiable() {
		int[] posExit = {0,0};
		return (this.heroi.getPosition() == posExit) && this.heroiHasOuro;
	}

	private void moveHero(int[] nextPosition) {
	}

	private void equipeHeroiWithArrow() {
		try {
			this.heroi.equipeArrow();
			this.score -= this.POINTS_FOR_USE_ARROW;
		} catch (Error error) {
			throw new Error("Error when heroi try to equipe flecha: " + error.getMessage());
		}
	}

	private void makeHeroiCaptureGold() {
		try {
			this.heroi.captureGold();
		} catch (Error error) {
			throw new Error("Error capturing gold: " + error.getMessage());
		}
	}

	//ALTERAR, POIS O SWITCH FOI PARA O HEROIs
	public void handleMovementCommand(char command) {
		int[] nextPosition = new int[2];
		switch(command) {
			case 'w':
				nextPosition[0] = this.posHeroi[0];
				nextPosition[1] = this.posHeroi[1] - 1;
				this.moveHeroi(nextPosition);
				return;
			case 's':
				nextPosition[0] = this.posHeroi[0];
				nextPosition[1] = this.posHeroi[1] + 1;
				this.moveHeroi(nextPosition);
				return;
			case 'd':
				nextPosition[0] = this.posHeroi[0] + 1;
				nextPosition[1] = this.posHeroi[1];
				this.moveHeroi(nextPosition);
				return;
			case 'a':
				nextPosition[0] = this.posHeroi[0];
				nextPosition[1] = this.posHeroi[1] - 1;
				this.moveHeroi(nextPosition);
				return;
			case 'k':
				this.equipeHeroiWithArrow();
				return;
			case 'c':
				this.makeHeroiCaptureGold();
				return;
			case 'q':
				if (!this.isExitAvaiable()) {
					throw new Error("Cannot exit game without capture gold, or being out of initial position (1,1)");
				}
				this.score += this.POINTS_FOR_CATURE_GOLD;
				return;
		}
	};

}

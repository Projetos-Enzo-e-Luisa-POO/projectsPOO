package pt.c40task.l05wumpus;

/**
 * 
 * Recebe comandos da main (da interface com o player) e direciona os comandos ao Herói
 * Armazena posHerói e sempre verifica se Wumpus acordou para dá-lo ou não um turno
 * Autoriza saída do jogo do herói(?)
 *
 */

public class ControleDoJogo {
	
	private Heroi heroi;
	private int[] posHeroi = new int[2];
	public int score;

	private int POINTS_FOR_CATURE_GOLD = 1000;
	private int POINTS_FOR_BEING_DEAD = -1000;
	private int POINTS_FOR_MOVE = -15;
	private int POINTS_FOR_USE_ARROW = -100;
	private int POINTS_FOR_KILL_WUMPUS = 500;

	public ControleDoJogo(Caverna caverna, Heroi heroi, int[] posHeroi) {
		this.heroi = heroi;
		this.posHeroi = posHeroi;
		this.score = 0;
	}

	private boolean isExitAvaiable () {
		int[] posExit = {0,0};
		return (this.posHeroi == posExit) && this.heroi.hasOuro();
	}


	private void moveHeroi(int[] nextPosition) {
		try {
			this.score -= this.POINTS_FOR_MOVE;
			this.heroi.move(nextPosition);
			this.posHeroi = nextPosition;
			this.heroi.scanRoom();
			if (this.heroi.killedWumpus) {
				this.score += this.POINTS_FOR_KILL_WUMPUS;
			}
			if (this.heroi.isDead) {
				this.score -= this.POINTS_FOR_BEING_DEAD;
			}
		} catch (Exception e) {
			throw new Error("Error moving heroi: " + e.getMessage());
		}
	}

	private void equipeHeroiWithArrow() {
		try {
			this.heroi.equipeArrow();
			this.score -= this.POINTS_FOR_USE_ARROW;
		} catch (Exception e) {
			throw new Error("Error when heroi try to equipe flecha: " + e.getMessage());
		}
	}

	private void makeHeroiCaptureGold() {
		try {
			this.heroi.captureGold();
		} catch (Exception e) {
			throw new Error("Error capturing gold: " + e.getMessage());
		}
	}

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

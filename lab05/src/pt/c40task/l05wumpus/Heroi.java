package pt.c40task.l05wumpus;
import pt.c40task.l05wumpus.configs.ComponentDescriptionController;

/**
 * Componente "ativo" (sabe sua posição e age ativamente na caverna).
 * - Possui uma mochila com número de flechas arbitrário e um mapa
 * - Anda de uma sala para outra, pedindo movimento para Caverna
 * - Inspeciona a sala logo ao entrar na sala
 *    - Pode morrer ao perceber que é um buraco!
 *    - Se encontrar uma flecha, encontra-a quebrada (não havia Wumpus na sala)
 *    - Se sentir fedor, brisa ou ver ouro, atualiza seu mapa!
 * - Arma sua flecha, disparando-a antes do próximo movimento para uma sala
 * - Se viu ouro ao entrar na sala, pode pegá-lo e o colocá-lo em sua mochila (solicita remoção da caverna)
 */

public class Heroi extends Componente {
	
	private Caverna caverna;
	private Mochila mochila;
	private int posOuroNaMochila;
	private int posMapaNaMochila;
	private Componente flechaEquipada;
	public int[] pos = new int[2];

	/**
	 * Protótipo de construtor:
	 * - Cria todos os pertences do Herói
	 * - Cria mochila com (tamanhoMochila) espaços, e (numFlechas ou menor) flechas
	 * - Cria mapa (size) x (size) e insere-o na última posição da mochila
	 */
	public Heroi(Caverna caverna, int[] pos, int tamanhoMochila, int numFlechas) {
		this.caverna = caverna;
		
		int qtdeFlechas = (numFlechas <= tamanhoMochila - 2) ? numFlechas : tamanhoMochila - 2;
		Componente[] aljava = new Componente[qtdeFlechas];
		for (int i = 0; i < qtdeFlechas; i++)
			aljava[i] = new Flecha();
		this.mochila = new Mochila(aljava, tamanhoMochila, qtdeFlechas);
		
		this.posOuroNaMochila = tamanhoMochila - 2;
		this.posMapaNaMochila = tamanhoMochila - 1;
		
		this.flechaEquipada = null;

		this.pos = pos;
	}

	public int[] getPosition() {
		return this.pos;
	}

	public int getMapaPosition() {
		return this.posMapaNaMochila;
	}

	private int[] translateDirection(char command) {
		int[] nextPosition = new int[2];
		switch(command) {
			case 'w':
				nextPosition[0] = this.pos[0];
				nextPosition[1] = this.pos[1] - 1;
			case 's':
				nextPosition[0] = this.pos[0];
				nextPosition[1] = this.pos[1] + 1;
			case 'd':
				nextPosition[0] = this.pos[0] + 1;
				nextPosition[1] = this.pos[1];
			case 'a':
				nextPosition[0] = this.pos[0];
				nextPosition[1] = this.pos[1] - 1;
		}
		return nextPosition;
	}

	public void move(Componente comp, char command) {
		int[] nextPosition = this.translateDirection(command);
		try {
			this.caverna.moveComponent(comp, nextPosition);
			this.pos = nextPosition;
		} catch (Error error) {
			throw new Error("Error moving " + comp.toString() + " to position (" + nextPosition[0] + ", " + nextPosition[1] + ")");
		}
	}

	private void registerInMap(char characterForRegisterInMapa) {
		Mapa mapa = (Mapa) this.mochila.remove(this.posMapaNaMochila);
		mapa.register(characterForRegisterInMapa, this.pos);
		this.mochila.insere(this.posMapaNaMochila, mapa);
	}

	private String componentPriority(String[] componentes) {
        String componentPriority = "";
        boolean heroFound = false, stinkFound = false;
		for (int i = 0; i < componentes.length; i++) {
            if ((componentes[i] == "Ouro") || (componentes[i] == "Buraco") || (componentes[i] == "Wumpus")) {
                componentPriority = componentes[i];
                break;
            }
            else if (componentes[i] == "Heroi") {
                componentPriority = componentes[i];
				heroFound = true;
            }
            else if (!heroFound && componentes[i] == "Fedor") {
                componentPriority = componentes[i];
				stinkFound = true;
            }
			else if (!heroFound && !stinkFound && componentes[i] == "Brisa") {
				componentPriority = componentes[i];
			}
        }
        return componentPriority;
    }

	public String[] scanRoom() {
		try {
			ComponentDescriptionController componentController = new ComponentDescriptionController();
			String[] componentsInRoom = this.caverna.scanRoom(this.pos, this.toString());
			String[] messages = new String[componentsInRoom.length];
			for (int i = 0; i < componentsInRoom.length; i++) {
				if (componentsInRoom[i] == "Ouro") {
					messages[i] = "Ouro a vista!";
				}
				if (componentsInRoom[i] == "Wumpus") {
					messages[i] = "Cuidado que o Wumpus vai te pegar!";
				}
				if (componentsInRoom[i] == "Buraco") {
					messages[i] = "Morreu";
					this.kill(this.toString());
				}
				if (componentsInRoom[i] == "Brisa") {
					messages[i] = "A brisa eh fresca, mas o buraco esta perto...";
				}
				if (componentsInRoom[i] == "Fedor") {
					messages[i] = "Fedor na caverna";
				}
				if (componentsInRoom[i] == "Nothing") {
					messages[i] = "Silencio ensurdecedor na caverna...";
				}
			}
			this.registerInMap(
				componentController.convertToCharacter(this.componentPriority(componentsInRoom))
			);
			return messages;
		} catch (Error error) {
			throw new Error("Error when scanning room: " + error.getMessage());
		}
	}
	// INTERFACE
	public void insertComponentIntoMochila(int pos, Componente comp) {
		try {
			this.mochila.insere(pos, comp);
		} catch (Error error) {
			throw new Error("Error keeping component " + comp.toString() + " in schoolbag: " + error.getMessage());
		}
	}
	
	public String captureGold() {
		try {
			Componente ouro = this.caverna.removeFromRoom(this.pos, "Ouro", this.toString());
			this.insertComponentIntoMochila(this.posOuroNaMochila, ouro);
			
			ComponentDescriptionController componentController = new ComponentDescriptionController();
			this.registerInMap(componentController.convertToCharacter(this.toString()));

			return "Ouro capturado!!";
		} catch (Error error) {
			throw new Error("Error keeping gold in schoolbag: " + error.getMessage());
		}
	}

	public void equipeArrow() {
		int arrowPosition = 0;
		try {
			do {
				this.flechaEquipada = this.mochila.remove(arrowPosition++);
			} while (this.flechaEquipada == null);
		} catch (Error error) {
			throw new Error("Error trying to equipe arrow");
		}
	}

	public void moveArrow(char command) {
		if (this.flechaEquipada == null) {
			throw new Error("No arrow equiped");
		}
		this.move(this.flechaEquipada, command);
		this.flechaEquipada = null;
	}

	private void kill(String comp) {
		Componente remains = caverna.removeFromRoom(this.pos, comp, this.toString());
		if (remains != null)
			remains = null;
	}
	
	@Override
	public String toString() {
		return "Heroi";
	}

}

package pt.c40task.l05wumpus;
import pt.c40task.l05wumpus.configs.ComponentDescriptionController;

import java.util.Random;

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

	public String name;
	public int[] pos = new int[2];
	public boolean hasArrowEquipped;
	public boolean killedWumpus;
	public boolean isDead;

	/**
	 * Protótipo de construtor:
	 * - Cria todos os pertences do Herói
	 * - Cria mochila com (tamanhoMochila) espaços, e (numFlechas ou menor) flechas
	 * - Cria mapa (size) x (size) e insere-o na última posição da mochila
	 */
	public Heroi(Caverna caverna, String name, int[] pos, int size, int tamanhoMochila, int numFlechas) {
		this.caverna = caverna;
		
		int qtdeFlechas = (numFlechas <= tamanhoMochila - 2) ? numFlechas : tamanhoMochila - 2;
		Componente[] aljava = new Componente[qtdeFlechas];
		for (int i = 0; i < qtdeFlechas; i++)
			aljava[i] = new Flecha();
		
		this.mochila = new Mochila(aljava, tamanhoMochila, qtdeFlechas);
		this.posOuroNaMochila = tamanhoMochila - 2;
		this.posMapaNaMochila = tamanhoMochila - 1;
		mochila.insere(this.posMapaNaMochila, new Mapa(size));
		
		this.name = name;
		this.pos = pos;
		this.hasArrowEquipped = false;
		this.killedWumpus = false;
		this.isDead = false;
	}

	public void move(int[] nextPosition) {
		try {
			this.caverna.moveComponent(this, nextPosition);
			this.pos = nextPosition;
		} catch (Error error) {
			throw new Error("Error moving hero to position (" + nextPosition[0] + ", " + nextPosition[1] + ")");
		}
	}

	private String getComponentWithHighestPriority(String[] componentes) {
        String componentWithHighestPriority = "Brisa";
        for (int i = 0; i < componentes.length; i++) {
            if ((componentes[i] == "Ouro") || (componentes[i] == "Buraco") || (componentes[i] == "Wumpus")) {
                componentWithHighestPriority = componentes[i];
                break;
            }
            else if (componentes[i] == "Heroi") {
                componentWithHighestPriority = componentes[i];
            }
            else if (componentes[i] == "Fedor" && componentWithHighestPriority == "Brisa") {
                componentWithHighestPriority = componentes[i];
            }
        }
        return componentWithHighestPriority;
    }

	private void registerInMap(char characterForRegisterInMapa) {
		Mapa mapa = (Mapa) this.mochila.remove(this.posMapaNaMochila);
		mapa.register(characterForRegisterInMapa, this.pos);
		this.mochila.insere(this.posMapaNaMochila, mapa);
	}

	public void scanRoom() {
		try {
			ComponentDescriptionController componentController = new ComponentDescriptionController();
			String[] componentsInRoom = this.caverna.scanRoom(this.pos, this.toString());
			String componentWithHighestPriority = this.getComponentWithHighestPriority(componentsInRoom);
			if (componentWithHighestPriority == "Buraco") {
				this.isDead = true;
				return;
			}
			if (componentWithHighestPriority == "Wumpus") {
				if (this.hasArrowEquipped) {
					Random random = new Random();
					boolean killedWumpus = random.nextBoolean();  
					if (killedWumpus) {
						this.killedWumpus = true;
						componentWithHighestPriority = this.toString();
					}
					this.hasArrowEquipped = false;
				} else {
					this.isDead = true;
				}
			}
			char characterForRegisterInMapa = componentController.convertToCharacter(componentWithHighestPriority);
			this.registerInMap(characterForRegisterInMapa);
		} catch (Error error) {
			throw new Error("Error when scanning room: " + error.getMessage());
		}
	}

	public void equipeArrow() {
		int arrowPosition = 0;
		Componente comp = null;
		try {
			do {
				comp = this.mochila.remove(arrowPosition++);
			} while (comp == null);
		} catch (Error error) {
			throw new Error("Error trying to equipe arrow");
		}
	}
	
	public void captureGold() {
		try {
			Componente ouro = this.caverna.removeFromRoom(this.pos, "Ouro");
			this.insertComponentIntoMochila(this.posOuroNaMochila, ouro);
			
			ComponentDescriptionController componentController = new ComponentDescriptionController();
			char heroiCharacter = componentController.convertToCharacter(this.toString());

			this.registerInMap(heroiCharacter);
		} catch (Error error) {
			throw new Error("Error keeping gold in schoolbag: " + error.getMessage());
		}
	}

	public boolean hasOuro() {
		return this.mochila.itens[this.posOuroNaMochila] != null;
	}

	public void insertComponentIntoMochila(int pos, Componente comp) {
		try {
			this.mochila.insere(pos, comp);
		} catch (Error error) {
			throw new Error("Error keeping component " + comp.toString() + " in schoolbag: " + error.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Heroi";
	}

}

package pt.c40task.l05wumpus;
import java.util.ArrayList;
/**
 * 
 * Classe montadora da Caverna
 * - Instancia Caverna
 * - Lê cave.csv recebido da main e instancia Componentes de acordo
 * - Confere condições de inicialização da caverna
 * - Método de montagem 2.0(?)
 *
 */
public class MontadorDaCaverna {

	private Caverna cave;
	private Mapa map;
	private Heroi hero;
	private Wumpus wumpus;

	private int numberHolesInCave;
	private int MIN_NUMBER_HOLES = 2;
	private int MAX_NUMBER_HOLES = 3;
	
	private int numberGoldInCave;
	private int MAX_NUMBER_GOLD = 1;
	
	public MontadorDaCaverna(int caveDimension, int roomDimension) {
		this.cave = new Caverna(caveDimension, roomDimension);
		this.map = null;
		this.hero = null;
		this.wumpus = null;
		this.numberHolesInCave = 0;
		this.numberGoldInCave = 0;
	}

	public Caverna getCaverna() {
		return this.cave;
	}

	public Mapa getMapa() {
		return this.map;
	}

	public Heroi getHeroi() {
		return this.hero;
	}

	public Wumpus getWumpus() {
		return this.wumpus;
	}

	public Componente convertToComponent(char character, int[] pos) {
		switch(character) {
			case 'P':
				Heroi hero = new Heroi(this.cave, pos, 4, 2);
				this.hero = hero;
				return hero;
			case 'W':
				Wumpus wumpus = new Wumpus(pos, this.cave);
				wumpus.calmWumpus(true);
				this.wumpus = wumpus;
				return wumpus;
			case 'B':
				return new Buraco(pos, this.cave);
			case 'O':
				return new Ouro();
			default:
				return null;
		}
	}

	private boolean hasExcludedComponentsInRoom(Componente comp, int[] roomPosition) {
		String[] componentsInRoom = this.cave.scanRoom(roomPosition, "MontadorDaCaverna");
		
		ArrayList<String> components = new ArrayList<String>(componentsInRoom.length);
		for (int i = 0; i < componentsInRoom.length; i++) {
			components.add(componentsInRoom[i]);
		}

		String[] excludedComponents = new String[2];
		if (comp.toString() == "Heroi") {
			excludedComponents[0] = "Ouro";
			excludedComponents[1] = "Wumpus";
		}
		else if (comp.toString() == "Wumpus") {
			excludedComponents[0] = "Ouro";
			excludedComponents[1] = "Heroi";
		}
		else if (comp.toString() == "Ouro") {
			excludedComponents[0] = "Heroi";
			excludedComponents[1] = "Wumpus";
		}
		else {
			excludedComponents[0] = "";
			excludedComponents[1] = "";
		}
		
		return components.contains(excludedComponents[0]) || components.contains(excludedComponents[1]);
	}

	private boolean canInsertComponentInPosition(Componente comp, int[] pos) {
		boolean canInsert = true;
		if (comp.toString() == "Heroi" && (this.hero != null || hasExcludedComponentsInRoom(comp, pos))) {
			canInsert = false;
		}
		else if (comp.toString() == "Wumpus" && (this.wumpus != null || hasExcludedComponentsInRoom(comp, pos))) {
			canInsert = false;
		}
		else if (comp.toString() == "Buraco" && this.numberHolesInCave > this.MAX_NUMBER_HOLES && !hasExcludedComponentsInRoom(comp, pos)) {
			canInsert = false;
		}
		else if (comp.toString() == "Ouro" && this.numberGoldInCave > this.MAX_NUMBER_GOLD) {
			canInsert = false;
		}
		return canInsert;
	}

	public void saveComponentInPosition(char character, int[] pos) {
		Componente comp = null;
		try {
			comp = this.convertToComponent(character, pos);
			if (canInsertComponentInPosition(comp, pos)) {
				this.cave.insertInRoom(pos, comp);
				if (comp.toString() == "Ouro") {
					this.numberGoldInCave++;
				}
				else if (comp.toString() == "Buraco") {
					this.numberHolesInCave++;
				}
			} else {
				throw new Error("Position (" + pos[0] + "," + pos[1] + ") is invalid for insert component " + comp.toString());
			}
		} catch (Error error) {
			throw new Error("Error inserting component " + comp.toString() + " in position (" + pos[0] + "," + pos[1] + ")");
		}
		/* boolean isWumpusComponent = character == 'W';
		if (isWumpusComponent) {
			this.wumpus.calmWumpus(true);
		} */
	}

	public boolean isNumberOfHolesInCaveValid() {
		return this.numberHolesInCave >= this.MIN_NUMBER_HOLES && this.numberHolesInCave <= this.MAX_NUMBER_HOLES;
	}

	public void CreateAndSaveMapInHeroSchoolbag(int size) {
		this.map = new Mapa(size);
		this.hero.insertComponentIntoMochila(this.hero.getMapaPosition(), this.map);
	}
	
}

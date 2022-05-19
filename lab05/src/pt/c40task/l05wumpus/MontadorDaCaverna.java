package pt.c40task.l05wumpus;
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
	private int EXPECTED_NUMBER_GOLD = 1;
	
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

	public void CreateAndSaveMapInHeroSchoolbag(int size) {
		this.map = new Mapa(size);
		this.hero.insertComponentIntoMochila(this.hero.getMapaPosition(), this.map);
	}

	public Componente convertToComponent(char character, int[] pos) {
		switch(character) {
			case 'P':
				Heroi hero = new Heroi(this.cave, pos, 4, 2);
				this.hero = hero;
				this.CreateAndSaveMapInHeroSchoolbag(this.cave.getDimensaoCaverna());
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

	public void saveComponentInPosition(char character, int[] pos) {
		Componente comp = null;
		try {
			comp = this.convertToComponent(character, pos);
			if (comp != null) {
				this.cave.insertInRoom(pos, comp);
				if (comp.toString() == "Ouro") {
					this.numberGoldInCave++;
				}
				else if (comp.toString() == "Buraco") {
					this.numberHolesInCave++;
				}
			}
		} catch (Error error) {
			throw new Error("Error inserting component in position (" + pos[0] + "," + pos[1] + ")" + error.getMessage());
		}
	}

	public void buildCaveByCSVFileContent(String config[][]) {
		for (int i = 0; i < config.length; i++) {
			try {
			   int[] position = new int[2];
			   position[0] = Integer.parseInt(config[i][0]);
			   position[1] = Integer.parseInt(config[i][1]);
   
			   char componentAsCharacter = config[i][2].charAt(0);
   
			   this.saveComponentInPosition(componentAsCharacter, position);
			} catch (Error error) {
			   throw new Error("Error building cave by .csv file: " + error.getMessage());
			}
		 }
		 this.wumpus.calmWumpus(true);
	}

	public boolean isCaveBuildedValid() {
		return this.hero != null
				&& this.wumpus != null // presume 1 wumpus só
				&& this.numberGoldInCave == this.EXPECTED_NUMBER_GOLD
				&& this.numberHolesInCave >= this.MIN_NUMBER_HOLES && this.numberHolesInCave <= this.MAX_NUMBER_HOLES;
	}
	
}

package pt.c40task.l05wumpus;

/**
 * Célula unitária da caverna, 
 * Cada sala tem espaço para size componentes, 
 * Avalia resto da sala antes de receber um novo componente, 
 * Permite remoção de um componente se ele existir dentro da sala
 * @author Frost
 */
public class Sala {
	
	private Componente[] Sala;
	private boolean[] emptyRooms;
	private boolean full;
	private int size;
	
	/**
	 * Inicializa uma sala de tamanho size e seus atributos.
	 * @param size
	 * @author Frost
	 */
	public Sala(int size) {
		this.size = size;
		Sala = new Componente[size - 1];
		emptyRooms = new boolean[size - 1];
		for (int i = 0; i < size; i++)
			emptyRooms[i] = true;
		this.full = false;
	}
	
	/**
	 * Recebe um Componente e verifica se, de acordo com as regras do jogo, o mesmo pode ser inserido na sala em questão. 
	 * Retorna true se a operação foi bem sucedida, false c.c., 
	 * Insere o Componente na primeira posição livre, indo de 0 a size
	 * @param comp
	 * @return able
	 * @author Frost
	 */
	public boolean insert(Componente comp) {
		boolean able = true;
		int capacity = this.size;
		String aux;
		if (this.full)
			able = false;
		else {
			String newComer = comp.getComponente();
			for (int i = 0; i < this.size; i++)
				if (!emptyRooms[i]) {
					capacity--;
					aux = Sala[i].getComponente();
					if (newComer == "Ouro")
						if (aux == "Wumpus" || aux == "Buraco")
							able = false;
					else if (newComer == "Wumpus")
						if (aux == "Ouro" || aux == "Buraco")
							able = false;
					else if (newComer == "Buraco")
						if (aux == "Wumpus" || aux == "Ouro")
							able = false;
				}
		}
		if (able) {
			int i = 0;
			while(!emptyRooms[i])
				i++;
			Sala[i] = comp;
			emptyRooms[i] = false;
			if (capacity - 1 == 0)
				this.full = true;
		}
		return able;
	}
	
	/**
	 * Recebe uma String que identifica o objeto a ser removido da sala e retorna seu ponteiro.
	 * Caso não encontre o objeto desejado, retorna null. 
	 * @param comp
	 * @return retrieve
	 * @author Frost
	 */
	public Componente remove(String comp) {
		Componente retrieve = null;
		String aux;
		for (int i = 0; i < this.size; i++) {
			aux = Sala[i].getComponente();
			if(aux == comp) {
				retrieve = Sala[i];
				Sala[i] = null;
				emptyRooms[i] = true;
				if (this.full)
					this.full = false;
				break;
			}
		}
		return retrieve;
	}
}

package pt.c40task.l05wumpus;

/**
 * Célula unitária da caverna, 
 * - Cada sala tem espaço para (size) componentes, 
 * - Avalia resto da sala antes de receber um novo componente, 
 * - Permite remoção de um componente se ele existir dentro da sala.
 * 
 * @author Frost
 */
public class Sala {
	
	private Componente[] Sala;
	private boolean[] emptyRooms;
	private boolean full;
	
	/**
	 * Inicializa uma sala de tamanho (size) e seus atributos.
	 * @param size
	 * @author Frost
	 */
	public Sala(int size) {
		Sala = new Componente[size];
		emptyRooms = new boolean[size];
		for (int i = 0; i < size; i++)
			emptyRooms[i] = true;
		this.full = false;
	}
	
	/**
	 * Método scaneia todos os compartimentos do vetor Sala e retorna um vetor de Strings
	 * 		com as identificações adequadas se há algo na sala ou não
	 * - Caso um componente esteja tentando dar scout em uma sala que não a sua, retorna o erro
	 * 		"You cannot scout a Room that you're not inside of", código "_".
	 * 
	 * @return aux
	 * @author Frost
	 */
	public String[] scan(String requester) {
		boolean auth = false;
		String[] aux = new String[Sala.length];
		for (int i = 0; i < Sala.length; i++) {
			if (!emptyRooms[i]) {
				aux[i] = Sala[i].toString();
				if (aux[i] == requester)
					auth = true;
			}
			else
				aux[i] = "Nothing";
		}
		if (!auth)
			throw new Error("You cannot scout a Room that you're not inside of");
		return aux;
	}
	
	
	
	/**
	 * Recebe um Componente e verifica se, de acordo com as regras do jogo, 
	 * 		o mesmo pode/deve ser inserido na sala em questão. 
	 * - Gera erros em caso de operação não ser bem sucedida:
	 * 		- "Room already filled, cannot insert component", código "_", 
	 * 			caso a sala esteja cheia
	 * 		- "Not allowed to insert component due game directives", código "_", 
	 * 			caso a inserção não seja válida pelas regras do jogo
	 * - Insere o Componente na primeira posição livre, indo de 0 a size
	 * 
	 * Critérios: Wumpus, Buraco e Ouro não podem ocupar a mesma sala, não 
	 * 				há necessidade de duas Brisas ou dois Fedores na mesma sala.
	 * 
	 * @param comp
	 * @return able
	 * @author Frost
	 */
	public void insert(Componente comp) {
		if (comp != null)
			if (this.full)
				throw new Error("Room already filled, cannot insert component");
			else {
				int occupation = Sala.length;
				boolean able = true;
				String newComer = comp.toString();
				String aux;
				for (int i = 0; i < Sala.length; i++)
					if (!emptyRooms[i]) {
						occupation--;
						aux = Sala[i].toString();
						if (newComer == "Ouro")
							if (aux == "Wumpus" || aux == "Buraco")
								able = false;
						else if (newComer == "Wumpus")
							if (aux == "Ouro" || aux == "Buraco")
								able = false;
						else if (newComer == "Buraco")
							if (aux == "Wumpus" || aux == "Ouro")
								able = false;
						else if (newComer == "Brisa")
							if (aux == "Brisa")
								able = false;
						else if (newComer == "Fedor")
							if (aux == "Fedor")
								able = false;
					}
				if (able) {
					int i = 0;
					while(!emptyRooms[i])
						i++;
					Sala[i] = comp;
					emptyRooms[i] = false;
					if (occupation - 1 == 0)
						this.full = true;
				}
				else
					throw new Error("Not allowed to insert component due game directives");
		}
	}
	
	/**
	 * Recebe uma String que identifica o objeto a ser removido da sala e retorna seu ponteiro.
	 * - Caso não encontre o objeto desejado, retorna null.
	 *  
	 * @param comp
	 * @return retrieve
	 * @author Frost
	 */
	public Componente remove(String comp) {
		Componente retrieve = null;
		String aux;
		for (int i = 0; i < Sala.length; i++) {
			aux = Sala[i].toString();
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
	
	/**
	 * Sobrecarga do método remove que prevê solicitação de remoção do próprio objeto
	 * - Caso não encontre o objeto desejado, ou remove retorne o próprio objeto, retorna null.
	 *  
	 * @param comp, requester
	 * @return retrieve
	 * @author Frost
	 */
	public Componente remove(String comp, String requester) {
		Componente retrieve = this.remove(comp);
		if (retrieve != null && retrieve.toString() == requester)
			retrieve = null;
		return retrieve;
	}
}

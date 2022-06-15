/*
 * Protagonista
 */
public class Songster extends MovableViewElement{
	/* Herança
		Atributos
	 # ID: int
	 # orientation: boolean[]
	 # room: MoveAccess
	 # posID: int
	 # imgByOr: String[]
	 # movByOr: String[]
	 # activeImg: String
		Métodos
	 + getID(): int
	 + roomConnect(a: MoveAccess): void
	 # orientate(direction: int): void
	 # moveToCell(cellID: int): void
	 + move(direction: int, cellID: int): void
	 # updateImg(isMov: boolean, orientation: int): void
	 + getImg(): String
	 */

	// Elemento de visualização
	private Interactable activeItem;
	private Inventory inventory;

	public Songster(String[] imgByOr, String[] movByOr, int direction, MoveAccess a, int[] pos){
		IDTrans t = new IDTrans();
		this.ID = 1;
		this.imgByOr = imgByOr;
		this.movByOr = imgByOr;
		this.orientate(direction);
		this.roomConect(a);
		this.posID = t.posToID(pos);
		this.updateImg(false);
		// inicializa elemento de visualização
		this.inventory = new Inventory();
	}

	public int getOrientation(){
		int i;
		for (i = 0; i < 4; i++){
			if(this.orientation)
				break;
		}
		return i
	}

	@Override
	public void move(int direction, int cellID) {
		super.move(direction, cellID);
		this.updateImg(true);
		//chamada da animação recebendo (this.posID, this.getImg())
		this.updateImg(false);
		//chamada da animação recebendo (this.posID, this.getImg())
	}

	public void changeActiveSlot(int slot){
		this.inventory.setActiveSlot(slot);
		this.ActiveItem = this.inventory.getActiveItem();
	}

	public Interactable getActiveItem(){
		return this.ActiveItem;
	}

	// método para colocar objetos no "chão"
	public Interactable dropActiveItem(){
		return this.inventory.dropItem(this.activeItem.getID);
	}

	public void storeItem(Interactable item){
		this.inventory.storeItem(item);
	}

}
package com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement;

import com.badlogic.amnesia.Model.Inventory;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.ControlInterfaces.MoveAccess;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.FlagManagment.SongsterFlags;

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
	private SongsterFlags f;

	public Songster(String[] imgByOr, String[] movByOr, int direction, int[] pos, MoveAccess access1, SongsterFlags access2){
		IDTrans t = new IDTrans();
		this.ID = 1;
		this.imgByOr = imgByOr;
		this.movByOr = imgByOr;
		this.orientate(direction);
		this.posID = t.posToID(pos);
		System.out.println(this.posID);
		this.roomConect(access1);
		this.f = access2;
		this.updateImg(false);
		// inicializa elemento de visualização
		this.inventory = new Inventory();
	}

	public int getOrientation(){
		int i;
		for (i = 0; i < 4; i++){
			if(this.orientation[i])
				break;
		}
		return i;
	}

	@Override
	public void move(int direction, int cellID) {
		super.move(direction, cellID);
		this.f.setSongsterOrientation(this.getOrientation());
		this.f.setSongsterPos(this.posID);
		this.updateImg(true);
		//chamada da animação recebendo (this.posID, this.getImg())
		this.updateImg(false);
		//chamada da animação recebendo (this.posID, this.getImg())
	}

	public void changeActiveSlot(int slot){
		this.inventory.setActiveSlot(slot);
		this.activeItem = this.inventory.getActiveItem();
	}

	public Interactable getActiveItem(){
		return this.activeItem;
	}

	// método para colocar objetos no "chão"
	public Interactable dropActiveItem(){
		return this.inventory.dropItem(this.activeItem.getID());
	}

	public void storeItem(Interactable item){
		this.inventory.storeItem(item);
	}

}
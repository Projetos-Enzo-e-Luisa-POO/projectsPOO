package com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement;

import com.badlogic.amnesia.Model.Inventory;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.ControlInterfaces.MoveAccess;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.FlagManagment.SongsterFlags;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

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
	private Inventory inventory;
	private SongsterFlags f;
	private Array<Integer> memory;

	public Songster(String[] imgByOr, String[] movByOr, int direction, int[] pos, MoveAccess access1, SongsterFlags access2){
		IDTrans t = new IDTrans();
		this.ID = 1;
		this.imgByOr = imgByOr;
		this.movByOr = imgByOr;
		this.orientate(direction);
		this.posID = t.posToID(pos);
		this.roomConect(access1);
		this.f = access2;
		this.updateImg(false);
		this.memory = new Array<Integer>();
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
		this.updateImg(false);
	}

	public void changeActiveSlot(int slot){
		this.inventory.setActiveSlot(slot);
	}

	public Interactable getActiveItem(){
		return this.inventory.getActiveItem();
	}

	// método para colocar objetos no "chão"
	public Interactable dropActiveItem(){
		return ((this.inventory.getActiveItem() != null) ? this.inventory.dropItem(this.inventory.getActiveItem().getID()) : null);
	}

	public void storeItem(Interactable item){
		this.inventory.storeItem(item);
	}

	public void learnInterface(int i){
		this.memory.add(i);
	}

	public boolean knows(int i){
		return this.memory.contains(i, true);
	}

	public void renderInventory(Batch batch, float imgSize){
		this.inventory.render(batch, imgSize);
	}
}
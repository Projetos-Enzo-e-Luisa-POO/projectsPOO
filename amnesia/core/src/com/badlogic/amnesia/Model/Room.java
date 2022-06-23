package com.badlogic.amnesia.Model;

import com.badlogic.amnesia.Model.ControlInterfaces.ControlAccess;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.ControlInterfaces.MoveAccess;
import com.badlogic.amnesia.Model.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;

/*	Espaço matricial
 * Agrupamento de células, define espaços matriciais do jogo e media comunicações/movimentos
 */
public class Room implements MoveAccess, ControlAccess{

	private Cell[][] space;
	private Interactable[][] elements;

	public Room(int x, int y){
		IDTrans t = new IDTrans();
		t.setL(x);
		this.space = new Cell[x][y];
		this.elements = new Interactable[x][y];
		for(int i = 0; i < this.elements.length; i++)
			for (int j = 0; j < this.elements[0].length; j++)
				this.elements[i][j] = null;
	}

	public void elementConnect(int cellID, Interactable e){
		IDTrans t = new IDTrans();
		int[] aux = t.IDToPos(cellID);
		if (e.isInteractable() && this.elements[aux[0]][aux[1]] == null)
			this.elements[aux[0]][aux[1]] = e;
		// else throw exception? erro de inicialização?
	}

	public Interactable getElement(int cellID){
		IDTrans t = new IDTrans();
		int[] aux = t.IDToPos(cellID);
		return this.elements[aux[0]][aux[1]];
	}

	public Interactable elementRemove(int cellID){
		IDTrans t = new IDTrans();
		int[] aux = t.IDToPos(cellID);
		Interactable e = this.elements[aux[0]][aux[1]];
		this.elements[aux[0]][aux[1]] = null;
		return e;
	}

	public void cellConnect(int cellID, Cell c){
		IDTrans t = new IDTrans();
		int[] aux = t.IDToPos(cellID);
		this.space[aux[0]][aux[1]] = c;
	}
	
	public boolean isWalkable(int cellID){
		IDTrans t = new IDTrans();
		int aux[] = t.IDToPos(cellID);
		return this.space[aux[0]][aux[1]].isWalkable();
	}

	public boolean isInteractable(int cellID){
		IDTrans t = new IDTrans();
		int aux[] = t.IDToPos(cellID);
		return ((this.elements[aux[0]][aux[1]] == null) ? false : true);
	}

	public int[] size(){
		int[] aux = { this.space.length, this.space[0].length };
		return aux;
	}

	@Override
	public Interactable[][] getElements() {
		return this.elements;
	}
}
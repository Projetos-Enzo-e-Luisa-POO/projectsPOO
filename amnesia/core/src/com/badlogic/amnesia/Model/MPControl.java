package com.badlogic.amnesia.Model;

import com.badlogic.amnesia.Model.ControlInterfaces.ControlAccess;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.services.FlagManagment.FlagConfig;

public class MPControl{

	private ControlAccess r;
	private Songster p;
	private int posID;

	public MPControl(ControlAccess r, Songster p, int posID){
		this.r = r;
		this.p = p;
		this.posID = posID;
	}

	private int getNextCell(int orientation){
		int next;
		IDTrans t = new IDTrans();
		int aux[] = t.IDToPos(this.posID);
		int size[] = r.size();
		switch(orientation){
			case 0:
				aux[1]++;
			case 1:
				aux[0]++;
			case 2:
				aux[1]--;
			case 3:
				aux[0]--;
		}
		if (aux[0] > -1 && aux[0] < size[0] && aux[1] > -1 && aux[1] < size[1])
			next = t.posToID(aux);
		else
			next = this.posID;
		return next;
	}

	public void move(int orientation){
		this.p.move(orientation, getNextCell(orientation));
	}

	public void changeActiveSlot(int i){
		if (i > -2 && i < 2)
			this.p.changeActiveSlot(i);
	}

	public void quickInteract(){
		this.deepInteract(p.getActiveItem());
	}

	public void interact(){
		int ID = this.getNextCell(this.p.getOrientation());
		if (this.r.isInteractable(ID))
			this.deepInteract(this.r.getElement(ID));
	}

	private void deepInteract(Interactable item){
		if (item != null){
			//inicia menu de interação e direciona ações
		}
	}

	public void saveGame(){
		FlagConfig fc = new FlagConfig(this.r);
		fc.saveGame();
	}

}
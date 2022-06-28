package com.badlogic.amnesia.Model;

import com.badlogic.amnesia.Model.ControlInterfaces.ControlAccess;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.FlagManagment.FlagConfig;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MPControl implements SongsterView {

	private ControlAccess r;
	private Songster p;
	private castCenter c;
	private int posID;
	private boolean isQuick;

	public MPControl(ControlAccess r, Songster p, int posID){
		this.r = r;
		this.p = p;
		this.c = new castCenter();
		this.posID = posID;
	}

	private int getNextCell(int orientation){
		int next;
		IDTrans t = new IDTrans();
		int aux[] = t.IDToPos(this.p.getPosID());
		int size[] = r.size();
		switch(orientation){
			case 0:
				aux[1]++;
				break;
			case 1:
				aux[0]++;
				break;
			case 2:
				aux[1]--;
				break;
			case 3:
				aux[0]--;
				break;
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

	public String[] quickInteract(){
		this.isQuick = true;
		return this.deepInteract(p.getActiveItem());
	}

	public String[] interact(){
		String[] aux = null;
		aux = this.deepInteract(this.getPossibleInteractable());
		return aux;
	}

	public Interactable getPossibleInteractable(){
		Interactable aux = null;
		int ID = this.p.getPosID();
		if (this.r.isInteractable(ID)) aux = this.r.getElement(ID);
		else{
			ID = this.getNextCell(this.p.getOrientation());
			if (this.r.isInteractable(ID)) aux = this.r.getElement(ID);
		}
		return aux;
	}

	private String[] deepInteract(Interactable item){
		String[] aux = null;
		if (item != null){
			String[] interactions = new String[10];
			//Array<String> interactions = new Array<String>();
			int[] interfaces = item.getInterfaces();
			int k = 0;
			for(int i : interfaces){
				if (this.p.knows(i)){
					switch (i){
						case 3: // Desligar
							interactions[k] = "turnOff";
							break;
						case 5: // Ligar
							interactions[k] = "turnOn";
							break;
						case 7: // Desrosquear
							interactions[k] = "screwOut";
							break;
						case 9: // Rosquear
							interactions[k] = "screwIn";
							break;
						case 11: // Colocar
							interactions[k] = "put";
							break;
						case 13: // Pegar
							interactions[k] = "pick";
							break;
					}
					k++;
				}
			aux = interactions;
			}
		}
		if (aux[0] == null) aux[0] = "investigate";
		return aux;
	}

	public void executeInteraction(String action){
		Interactable item;
		if(isQuick) item = this.p.getActiveItem();
		else item = this.getPossibleInteractable();

		System.out.println("Search in " + item.getClassName());

		if (action.equals("investigate")) this.learn(item.getInterfaces());
		else if (action.equals("turnOff") || action.equals("turnOn")) this.c.act(item, action, null);
		else if (action.equals("screwOut") || action.equals("pick")) this.p.storeItem(this.c.act(item, action));
		else if (action.equals("screwIn")) this.c.act(item, action, this.p.dropActiveItem());
		else if (action.equals("put")){
			this.c.act(item, action, this.p.getPosID());
			this.r.elementConnect(this.p.getPosID(), this.p.dropActiveItem());
		}

		if(isQuick) this.isQuick = false;
	}

	public void saveGame(){
		FlagConfig fc = new FlagConfig(this.r);
		fc.saveGame();
	}

	@Override
	public void renderSongster(Batch batch, float imgSize) {
		this.p.render(batch, imgSize);
	}

	public void learn(int[] newIs){
		for(int i : newIs){
			if (!this.p.knows(i))
				switch (i){
					case 3:
						this.p.learnInterface(3);
						this.p.learnInterface(5);
						break;
					case 5:
						this.p.learnInterface(3);
						this.p.learnInterface(5);
						break;
					case 7:
						this.p.learnInterface(7);
						this.p.learnInterface(9);
						break;
					case 9:
						this.p.learnInterface(7);
						this.p.learnInterface(9);
						break;
					case 11:
						this.p.learnInterface(11);
						this.p.learnInterface(13);
						break;
					case 13:
						this.p.learnInterface(11);
						this.p.learnInterface(13);
						break;
				}
			}
	}

	@Override
	public void renderInventory(Batch batch, float imgSize) {
		this.p.renderInventory(batch, imgSize);
	}

}

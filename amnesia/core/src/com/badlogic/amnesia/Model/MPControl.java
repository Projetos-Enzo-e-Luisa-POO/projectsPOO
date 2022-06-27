package com.badlogic.amnesia.Model;

import com.badlogic.amnesia.Model.ControlInterfaces.ControlAccess;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.FlagManagment.FlagConfig;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

public class MPControl implements SongsterView {

	private ControlAccess r;
	private Songster p;
	private castCenter c;
	private int posID;

	public MPControl(ControlAccess r, Songster p, int posID){
		this.r = r;
		this.p = p;
		this.c = new castCenter();
		this.posID = posID;
	}

	private int getNextCell(int orientation){
		int next;
		IDTrans t = new IDTrans();
		int aux[] = t.IDToPos(this.posID); // Tornar posID private
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

	public String[] quickInteract(){
		return this.deepInteract(p.getActiveItem());
	}

	public String[] interact(){
		String[] aux = null;
		this.deepInteract(this.getPossibleInteractable());
		return aux;
	}

	private Interactable getPossibleInteractable(){
		Interactable aux = null;
		int ID = this.p.posID; //< tornar posID private
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
			Array<String> interactions = new Array<String>();
			int[] interfaces = item.getInterfaces();
			for(int i : interfaces)
				switch (i){
					case 3: // Desligar
						interactions.add("turnOn");
					case 5: // Ligar
						interactions.add("turnOff");
					case 7: // Desrosquear
						interactions.add("screwOut");
					case 9: // Rosquear
						interactions.add("screwIn");
					case 11: // Colocar
						interactions.add("put");
					case 13: // Pegar
						interactions.add("pick");
				}
			aux = interactions.toArray();
		}
		return aux;
	}

	public void executeInteraction(String action){
		Interactable item = this.getPossibleInteractable();
		this.c.act(item, action);
	}

	public void saveGame(){
		FlagConfig fc = new FlagConfig(this.r);
		fc.saveGame();
	}

	@Override
	public void renderSongster(Batch batch, float imgSize) {
		this.p.render(batch, imgSize);
	}
}

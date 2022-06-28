package com.badlogic.amnesia.Model;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.ControlInterfaces.RenderStrategy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/*	Inventário
 * Inventário do protagonista, 2 slots, possui view próprio
 */
public class Inventory implements RenderStrategy{

	private Interactable[] slots;
	private int activeSlot;

	public Inventory() {
		this.slots = new Interactable[2];
		this.slots[0] = this.slots[1] = null;
		this.activeSlot = -1;
	}

	public void setActiveSlot(int i) {
		this.activeSlot = i;
	}

	public void storeItem(Interactable item){
		if (this.activeSlot > -1 && this.slots[this.activeSlot] == null)
			this.slots[this.activeSlot] = item;
		else {
			if (this.activeSlot == -1 ||
				this.activeSlot == 0 && this.slots[this.activeSlot + 1] == null ||
				this.activeSlot == 1 && this.slots[this.activeSlot - 1] == null)
				for (int i = 0; i < 2; i++)
					if (this.slots[i] == null){
						this.slots[i] = item;
						if (this.activeSlot == -1)
							this.activeSlot = i;
						break;
					}
			else {
				// mensagem visual "inventário cheio"?
			}
		}
	}

	public Interactable dropItem(int ID) {
		Interactable aux = null;
		if (this.slots[this.activeSlot].getID() == ID) {
			aux = this.slots[this.activeSlot];
			this.slots[this.activeSlot] = null;
		}
		return aux;
	}

	public Interactable getActiveItem() {
		Interactable aux = null;
		if (this.activeSlot > -1)
			aux = this.slots[activeSlot];
		return aux;
	}

	private String getActiveSlotImg(){
		String aux;
		if (this.activeSlot == 0)	aux = "concreteElement/inventoryfslot.png";
		else if (this.activeSlot == 1)	aux = "concreteElement/inventorysslot.png";
		else aux = "concreteElement/inventory.png";
		return aux;
	}

	@Override
	public void render(Batch batch, float imgSize) {
		batch.draw(new Texture(
						(Gdx.files.internal(this.getActiveSlotImg()))),	
						4 * imgSize - 5*imgSize/8,
						0 * imgSize,
						21*imgSize/10,
						11*imgSize/10
					);
		if(this.slots[0] != null){
			batch.draw(new Texture(
						(Gdx.files.internal(this.slots[0].getImg()))),	
						(4 * imgSize - 5*imgSize/8) - imgSize/5,
						-0.7f * imgSize,
						1.5f*imgSize,
						1.5f*imgSize
					);
		}
		if(this.slots[1] != null){
			batch.draw(new Texture(
						(Gdx.files.internal(this.slots[1].getImg()))),	
						4.15f * imgSize,
						-0.7f * imgSize,
						1.5f*imgSize,
						1.5f*imgSize
					);
		}
	}
		
}
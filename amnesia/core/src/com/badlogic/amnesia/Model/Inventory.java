package com.badlogic.amnesia.Model;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;

/*	Inventário
 * Inventário do protagonista, 2 slots, possui view próprio
 */
public class Inventory {

	private Interactable[] slots;
	private int activeSlot;
	// Elemento de view do inventário

	public Inventory() {
		this.slots = new Interactable[2];
		this.slots[0] = this.slots[1] = null;
		this.activeSlot = -1;
		// inicializa elemento de view
	}

	public void setActiveSlot(int i) {
		this.activeSlot = i;
		// Atualiza view com slot selecionado
	}

	public void storeItem(Interactable item){
		if (this.slots[this.activeSlot] == null)
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
				// mensagem visual "inventário cheio"
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

}
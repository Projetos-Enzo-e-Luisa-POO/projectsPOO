package com.badlogic.amnesia.modelScratch.Elements.ViewElement;
import com.badlogic.amnesia.modelScratch.ControlInterfaces.Interactable;

/*
 * Classe mãe dos diferentes tipos de chão
 */
public abstract class InteractableElement extends ViewElement implements Interactable{

	public boolean isWalkable(){
		return true;
	}

	@Override
	public boolean isInteractable() {
		return true;
	}

	@Override
	public abstract int[] getInterfaces();

	@Override
	public abstract boolean[] getStatus();
}
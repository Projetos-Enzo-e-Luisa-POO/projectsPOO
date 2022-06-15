package com.badlogic.amnesia.modelScratch.Elements.ViewElement;
import com.badlogic.amnesia.modelScratch.ControlInterfaces.Placeable;
/*
 * Classe mãe dos diferentes tipos de chão
 */
public class Floor extends ViewElement implements Placeable{

	public boolean isWalkable(){
		return true;
	}

	public boolean isInteractable(){
		return false;
	}

}
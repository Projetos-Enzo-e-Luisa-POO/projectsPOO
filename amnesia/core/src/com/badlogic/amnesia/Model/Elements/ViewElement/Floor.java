package com.badlogic.amnesia.Model.Elements.ViewElement;
import com.badlogic.amnesia.Model.ControlInterfaces.Placeable;
/*
 * Classe mãe dos diferentes tipos de chão
 */
public class Floor extends ViewElement implements Placeable{

	public boolean isWalkable(){
		return true;
	}
}
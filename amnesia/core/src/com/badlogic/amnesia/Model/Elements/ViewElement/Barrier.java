package com.badlogic.amnesia.Model.Elements.ViewElement;
import com.badlogic.amnesia.Model.ControlInterfaces.Placeable;
/*
 * Classe mãe dos diferentes tipos de barreiras de movimento (Void, paredes, etc)
 */
public class Barrier extends ViewElement implements Placeable {

	public boolean isWalkable(){
		return false;
	}
}
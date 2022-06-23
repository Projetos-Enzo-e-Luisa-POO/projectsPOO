package com.badlogic.amnesia.Model.ControlInterfaces;

/*  Acesso ao Room pelo MPControl
 * Restringe e direciona acesso ao Room
 */

public interface ControlAccess extends SaveAccess{

	public int[] size();

	public boolean isInteractable(int cellID);

	public Interactable getElement(int cellID);

}
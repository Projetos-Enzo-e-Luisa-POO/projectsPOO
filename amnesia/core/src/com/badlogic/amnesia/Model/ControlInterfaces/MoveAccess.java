package com.badlogic.amnesia.Model.ControlInterfaces;

/*	Acesso ao Room por um Movable
 * Restringe e direciona o acesso do objeto que se move à matriz com os dados completos
 */
public interface MoveAccess{

	public int[] size();

	public boolean isWalkable(int ID);

}
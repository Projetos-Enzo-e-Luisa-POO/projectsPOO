package com.badlogic.amnesia.Model.Elements.Movable;

import com.badlogic.amnesia.Model.ControlInterfaces.MoveAccess;
import com.badlogic.amnesia.Model.Elements.Element;

/* Padrão de movimentação na matriz
 * Superclasse que implementa funções de movimentação para um Elemento.
 * Todo Movable é orientado. Sua orientação é explícita através de um vetor de booleans
 * 	0 = Up; 1 = Right; 2 = Down; 3 = Left
 * O Movimento depende de 2 parâmetros, direção e ID da célula destino
 */
public class Movable extends Element {
	protected boolean[] orientation = new boolean[4];
	protected MoveAccess space;
	protected int posID;

	public void roomConect(MoveAccess a){
		this.space = a;
	}

	protected void orientate(int direction) {
		for (int i = 0; i < 4; i++)
			this.orientation[i] = false;
		this.orientation[direction] = true;
	}

	protected void moveToCell(int cellID) {
		if(this.space.isWalkable(cellID)) this.posID = cellID;
	}
	
	public void move(int direction, int cellID) {
		if (direction == 0 && !this.orientation[0] || 
			direction == 1 && !this.orientation[1] ||
			direction == 2 && !this.orientation[2] ||
			direction == 3 && !this.orientation[3]
			)
			this.orientate(direction);
		else this.moveToCell(cellID);
	}

	public int getPosID(){
		return this.posID;
	}
    
}
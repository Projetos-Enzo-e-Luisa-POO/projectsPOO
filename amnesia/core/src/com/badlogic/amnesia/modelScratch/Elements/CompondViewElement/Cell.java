package com.badlogic.amnesia.modelScratch.Elements.CompondViewElement;

import com.badlogic.amnesia.graphicInterface.GameScreen;
import com.badlogic.amnesia.modelScratch.ControlInterfaces.Placeable;
import com.badlogic.amnesia.modelScratch.Toolkit.IDTrans;

import java.util.ArrayList;
/*	Célula
 * Unidade básica que contém Placeables para a construção do cenário e limitar movimentos do protagonista
 * Construtor define ID baseado na posição
 * isWalkable retorna se há algum objeto obstruindo a movimentação naquela célula
 * take retira um determinado Placeable de dentro da célula
 */
public class Cell extends CompondViewElement{

	private ArrayList<Placeable> elements;

	public Cell (GameScreen game, int[] pos){
		IDTrans t = new IDTrans();
		this.ID = t.posToID(pos);
		this.elements = new ArrayList<Placeable>();
		this.imgs = new ArrayList<String>();
		this.game = game;
	}

	public void place(Placeable element){
		IDTrans t = new IDTrans();
		int[] cellPosition = t.IDToPos(this.ID);
		
		this.elements.add(element);
		
		this.imgConnect(element.getImg(), cellPosition);
	}

	public boolean isWalkable(){
		boolean aux = true;
		for (int i = 0; i < this.elements.size(); i++)
			if (!this.elements.get(i).isWalkable()){
				aux = false;
				break;
			}
		return aux;
	}
}

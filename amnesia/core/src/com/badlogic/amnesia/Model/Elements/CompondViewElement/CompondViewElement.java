package com.badlogic.amnesia.Model.Elements.CompondViewElement;

import com.badlogic.amnesia.Model.Elements.Element;

import java.util.ArrayList;

/* Superclasse Cell
 * Precursor para overlay de imagens na mesma célula
 * Idealmente recebe as imagens na mesma ordem em que devem ser sobrepostas
 */
public class CompondViewElement extends Element {

	protected ArrayList<String> imgs;

	protected void imgConnect(String img, int[] pos){
		this.imgs.add(img);
	}

	protected void imgDisconnect(String img) {
		for (int i = 0; i < this.imgs.size(); i++) {
			if (this.imgs.get(i) == img) {
				this.imgs.remove(i);
				break;
			}
		}
	}

	public String[] getImgs(){
		String[] stringImgs = new String[this.imgs.size()];
		for (int i = 0; i < this.imgs.size(); i++) {
			stringImgs[i] = this.imgs.get(i);
		}
		return stringImgs;
	}

}
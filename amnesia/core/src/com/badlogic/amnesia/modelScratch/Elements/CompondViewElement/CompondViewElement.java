package com.badlogic.amnesia.modelScratch.Elements.CompondViewElement;

import com.badlogic.amnesia.graphicInterface.GameScreen;
import com.badlogic.amnesia.modelScratch.Elements.Element;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/* Superclasse Cell
 * Precursor para overlay de imagens na mesma célula
 * Idealmente recebe as imagens na mesma ordem em que devem ser sobrepostas
 */
public class CompondViewElement extends Element {

	protected GameScreen game;
	protected ArrayList<String> imgs;
	protected ArrayList<Texture> textures;

	protected void imgConnect(String img, int[] pos){
		this.imgs.add(img);
		Texture imgAsTexture = this.game.createAndDisplayTextureInScreen(img, pos);
		this.textures.add(imgAsTexture);
	}

	protected void imgDisconnect(String img) {
		int imgIndex = -1;
		
		for (int i = 0; i < this.imgs.size(); i++) {
			if (this.imgs.get(i) == img) {
				imgIndex = i;
				break;
			}
		}
		
		if (imgIndex != -1) {
			this.textures.get(imgIndex).dispose();
		}
	}

	protected void disconnectAllImgs() {
		for (Texture t : this.textures) {
			t.dispose();
		}
	}

	protected String[] getImgs(){
		String[] stringImgs = new String[this.imgs.size()];
		for (int i = 0; i < this.imgs.size(); i++) {
			stringImgs[i] = this.imgs.get(i);
		}
		return stringImgs;
	}

}
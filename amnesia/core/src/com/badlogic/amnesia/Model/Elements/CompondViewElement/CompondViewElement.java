package com.badlogic.amnesia.Model.Elements.CompondViewElement;

import com.badlogic.amnesia.Model.ControlInterfaces.RenderStrategy;
import com.badlogic.amnesia.Model.Elements.Element;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

/* Superclasse Cell
 * Precursor para overlay de imagens na mesma célula
 * Idealmente recebe as imagens na mesma ordem em que devem ser sobrepostas
 */
public class CompondViewElement extends Element implements RenderStrategy {

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

	public void render(Batch batch, float imgSize){
		IDTrans t = new IDTrans();
		for (String img : this.getImgs()) {
			int[] cellPosition = t.IDToPos(this.ID);
			batch.draw(new Texture(
								Gdx.files.internal(img)),
								cellPosition[1] * imgSize,
								cellPosition[0] * imgSize,
								imgSize,
								imgSize
							);
		}
	}

}
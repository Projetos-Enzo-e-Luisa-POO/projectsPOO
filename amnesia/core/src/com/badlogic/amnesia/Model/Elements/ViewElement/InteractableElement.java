package com.badlogic.amnesia.Model.Elements.ViewElement;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/*
 * Classe mãe dos diferentes tipos de chão
 */
public abstract class InteractableElement extends ViewElement implements Interactable {

	public boolean isWalkable(){
		return true;
	}

	@Override
	public boolean isInteractable() {
		return true;
	}

	@Override
	public abstract int[] getInterfaces();

	@Override
	public abstract boolean[] getStatus();

	@Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

	@Override
	public void render(Batch batch, float imgSize){
		System.out.println(this.getImg());
		IDTrans t = new IDTrans();
		int[] cellPosition = t.IDToPos(this.ID);
		batch.draw(new Texture(
						Gdx.files.internal(this.getImg())),
						cellPosition[1] * imgSize,
						cellPosition[0] * imgSize,
						imgSize,
						imgSize
					);
	}
	
}
package com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement;
import com.badlogic.amnesia.Model.ControlInterfaces.RenderStrategy;
import com.badlogic.amnesia.Model.Elements.Movable.Movable;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
/*	Superclasse do protagonista
 * Banco de dados maior para uma classe que precisa ser exibida no view e também se move.
 * Não é capaz de alterar view, somente possui dados
 */
public class MovableViewElement extends Movable implements RenderStrategy {
    
	protected String[] imgByOr = new String[4];
	protected String[] movByOr = new String[4];
	protected String activeImg;

	protected void updateImg(boolean isMov){
		for (int i = 0; i < 4; i++)
			if(this.orientation[i]){
				if (isMov)
					this.activeImg = this.movByOr[i];
			else
					this.activeImg = this.imgByOr[i];
			}
	}

	public String getImg(){
		return this.activeImg;
	}

	@Override
	public void render(Batch batch, float imgSize) {
		IDTrans t = new IDTrans();
		int[] cellPosition = t.IDToPos(this.posID);
		batch.draw(new Texture(
						Gdx.files.internal(this.getImg())),
						cellPosition[0] * imgSize,
						cellPosition[1] * imgSize + (imgSize / 2),
						imgSize,
						imgSize * 2
					);
	}

}
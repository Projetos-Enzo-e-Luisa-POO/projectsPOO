/*	Superclasse do protagonista
 * Banco de dados maior para uma classe que precisa ser exibida no view e também se move.
 * Não é capaz de alterar view, somente possui dados
 */
public class MovableViewElement extends Movable {
    
	protected String[] imgByOr = new String[4];
	protected String[] movByOr = new String[4];
	protected String activeImg;

	protected void updateImg(boolean isMov){
		for (int i = 0; i < 4; i++)
			if(this.orientation){
				if (isMov)
					this.activeImg = this.movByOr[i];
			else
					this.activeImg = this.imgByOr[i];
			}
	}

	public String getImg(){
		return this.activeImg;
	}

}
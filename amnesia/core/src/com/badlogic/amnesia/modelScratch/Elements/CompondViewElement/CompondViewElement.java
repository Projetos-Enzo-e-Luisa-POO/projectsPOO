import java.util.ArrayList;

/* Superclasse Cell
 * Precursor para overlay de imagens na mesma célula
 * Idealmente recebe as imagens na mesma ordem em que devem ser sobrepostas
 */
public class CompondViewElement extends Element{

	protected ArrayList<String> imgs;
	// <Elemento de visualização>

	protected void imgConnect(String img){
		this.imgs.add(img);
		// update no elemento de view
	}

	protected String[] getImgs(){
		return imgs.toArray();
	}

}
import java.util.ArrayList;
/*	Célula
 * Unidade básica que contém Placeables para a construção do cenário e limitar movimentos do protagonista
 * Construtor define ID baseado na posição
 * isWalkable retorna se há algum objeto obstruindo a movimentação naquela célula
 * take retira um determinado Placeable de dentro da célula
 */
public class Cell extends CompondViewElement{

	private ArrayList<Placeable> elements;

	public Cell (int[] pos){
		IDTrans t = new IDTrans();
		this.ID = t.posToID(pos);
		this.elements = new ArrayList<Placeable>();
		this.imgs = new ArrayList<String>();
	}

	public place(Placeable element){
		this.elements.add(element);
		this.imgConnect(element.getImg());
	}

	public boolean isWalkable(){
		boolean aux = true;
		for (int i = 0; i < this.elements.size(); i++)
			if (!this.elementsget(i).isWalkable){
				aux = false;
				break;
			}
		return aux;
	}
}

/*	Auxiliar para conversão de IDs de posição
 * l: Tamanho da sala no eixo x
 * A conversão para ID começa com índice 1, enquanto as formas (x,y) começam em 0
 */
public class IDTrans {
	
	private static int l;

	public void setL(int l){
		IDTrans.l = l;
	}

	/*
	 * Traduz uma posição (x,y) para um ID
	 */
	public int posToID(int[] pos){
		return 2*(pos[0]+IDTrans.l*pos[1]+1);
	}

	/*
	 * Traduz um ID par para uma posição (x,y)
	 */
	public int[] IDToPos(int ID){
		int[] aux = {((ID/2)%IDTrans.l - 1), ((ID/2)/IDTrans.l)};
		return aux;
	}
    
}
/*
 * Classe mãe dos diferentes tipos de barreiras de movimento (Void, paredes, etc)
 */
public class Barrier extends ViewElement implements Placeable {

	public boolean isWalkable(){
		return false;
	}

	public boolean isInteractable(){
		return false;
	}
    
}
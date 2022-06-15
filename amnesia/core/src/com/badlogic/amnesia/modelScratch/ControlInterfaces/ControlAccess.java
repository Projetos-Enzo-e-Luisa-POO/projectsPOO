/*  Acesso ao Room pelo MPControl
 * Restringe e direciona acesso ao Room
 */

public interface ControlAccess{

	public int[] size();

	public boolean isInteractable(int cellID);

	public Interactable getElement(int cellID);

}
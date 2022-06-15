public class MPControl{

	private ControlAccess r;
	private Songster p;
	private int posID;

	public MPControl(ControlAccess r, Songster p, int posID){
		this.r = r;
		this.p = p;
		this.posID = posID;
	}

	private int getNextCell(int orientation){
		int next;
		IDTrans t = new IDTrans();
		int aux[] = t.IDToPos(this.posID);
		int size[] = r.size();
		switch(orientation){
			case 0:
				aux[1]++;
			case 1:
				aux[0]++;
			case 2:
				aux[1]--;
			case 3:
				aux[0]--;
		}
		if (aux[0] > -1 && aux[0] < size[0] && aux[1] > -1 && aux[1] < size[1])
			next = t.posToID(aux);
		else
			next = this.posID;
		return next;
	}

	public void move(int orientation){
		this.p.move(orientation, getNextCell(orientation));
	}

	public void changeActiveSlot(int i){
		if (i > -2 && i < 2)
			this.p.changeActiveSlot(i);
	}

	public void quickInteract(){
		this.deepInteract(p.getActiveItem());
	}

	public void interact(){
		IDTrans t = new IDTrans();
		int ID = this.getNextCell(this.p.getOrientation());
		if (this.r.isInteractable(ID))
			this.deepInteract(this.r.getElement(ID));
	}

	private void deepInteract(Interactable item){
		if (item != null){
			//inicia menu de interação e direciona ações
		}
	}


	/* needed?
	private int[] trialDivision(int n){
		ArrayList<int> aux = new ArrayList<int>();
		while(n % 2 = 0){
			aux.add(2)
			n /= 2;
		}
		int d = 3;
		while(d*d <= n){
			if (n % d = 0){
				aux.add(d);
				n /= d;
			}
			else
				d += 2;
		}
		if(n > 1){
			aux.add(n);
		}
		return aux.toArray();
	}
	*/

}
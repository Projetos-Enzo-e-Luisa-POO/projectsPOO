package com.badlogic.amnesia.Model.Elements.ViewElement.Interactables;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.pickI;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.putI;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class LampBulb extends InteractableElement implements pickI, putI{

    private boolean state;

    public LampBulb(Boolean[] state2, int posID){
        this.imgConnect("concreteElement/lampbulb.png");
        this.ID = 17;
        this.posID = posID;
        this.state = state2[0];
    }

    public LampBulb(Boolean state, int posID){
        this.imgConnect("concreteElement/lampbulb.png");
        this.ID = 17;
        this.posID = posID;
        this.state = state;
    }

    @Override
    public int[] getInterfaces() {
        int[] aux = new int[1];
        if(this.state) aux[0] = 11; // Soltar
        else aux[0] = 13; // Pegar
        this.known = true;
        return aux;
    }

    public Interactable pick(){
        this.state = true;
        return this;
    }

    public void put(int posID){
        this.state = false;
        this.posID = posID;
    }

    @Override
    public boolean[] getStatus() {
        boolean[] aux = {this.state};
        return aux;
    }

	@Override
	public void render(Batch batch, float imgSize){
		IDTrans t = new IDTrans();
		int[] cellPosition = t.IDToPos(this.posID);
        if (!this.state) batch.draw  (new Texture(((this.known) ? Gdx.files.internal(this.getImg()) : Gdx.files.internal("concreteElement/unknownplainobject.png"))),	
                                    cellPosition[0] * imgSize,
                                    cellPosition[1] * imgSize,
                                    imgSize,
                                    imgSize
					);
	}

}

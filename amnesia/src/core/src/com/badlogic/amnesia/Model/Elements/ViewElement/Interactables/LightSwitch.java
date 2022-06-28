package com.badlogic.amnesia.Model.Elements.ViewElement.Interactables;

import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.turnOffI;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.turnOnI;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.FlagManagment.ElementFlags;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class LightSwitch extends InteractableElement implements turnOnI, turnOffI{

    private boolean state;
    private ElementFlags ef;

    public LightSwitch(Boolean[] state2, int posID, ElementFlags ef){
        this.imgConnect("concreteElement/lightSwitchoff.png");
        this.ID = 13;
        this.posID = posID;
        this.ef = ef;
        if (state2[0])
            this.turnOn();
        else
            this.turnOff();
        this.known = false;
    }

    @Override
    public int[] getInterfaces() {
        int[] aux = new int[1];
        if(this.state) aux[0] = 3; // Desligar
        else aux[0] = 5; // Ligar
        this.known = true;
        return aux;
    }

    public void turnOn(){
        this.state = true;
        this.ef.setSwitchFlag(this.state);
    }

    public void turnOff(){
        this.state = false;
        this.ef.setSwitchFlag(this.state);
    }

    public boolean getState(){
        return this.state;
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
        Texture tx;
        if (!this.known) tx = new Texture(Gdx.files.internal("concreteElement/unknownplainobject.png"));
        else if (this.state) tx = new Texture(Gdx.files.internal("concreteElement/lightSwitchon.png"));
        else tx = new Texture(Gdx.files.internal(this.getImg()));
        batch.draw (tx,	
                    cellPosition[0] * imgSize,
                    cellPosition[1] * imgSize,
                    imgSize,
                    imgSize
					);
	}

}

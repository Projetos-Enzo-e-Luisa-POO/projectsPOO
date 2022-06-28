package com.badlogic.amnesia.Model.Elements.ViewElement.Interactables;

import com.badlogic.amnesia.GraphicInterface.StoryTeller;
import com.badlogic.amnesia.GraphicInterface.connectOverload;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.screwInI;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.screwOutI;
import com.badlogic.amnesia.Services.FlagManagment.ElementFlags;

public class TableSocket extends InteractableElement implements screwInI, screwOutI{
    
    private boolean state;
    private Interactable lampBulb;
    private ElementFlags ef;

    public TableSocket(Boolean[] state2, int posID, ElementFlags ef, connectOverload st){
        this.imgConnect("concreteElement/sockettilewoutlamp.png");
        this.ID = 15;
        this.posID = posID;
        this.state = false;
        this.ef = ef;
        this.ef.setLampScrewedFlag(this.state);
        this.lampBulb = null;
        st.connect(this);
    }

    public TableSocket(LampBulb  lamp, int posID, ElementFlags ef, StoryTeller st){
        this.imgConnect("concreteElement/sockettilewoutlamp.png");
        this.ID = 15;
        this.posID = posID;
        this.ef = ef;
        this.screwIn(lamp);
    }

    @Override
    public int[] getInterfaces() {
        int[] aux = new int[1];
        if(this.state) aux[0] = 7; // Desrosquear
        else aux[0] = 9; // Rosquear
        this.known = true;
        return aux;
    }

    public void screwIn(LampBulb lamp){
        this.lampBulb = lamp;
        this.imgConnect("concreteElement/sockettilewofflamp.png");
        this.state = true;
        this.ef.setLampScrewedFlag(this.state);
    }

    public Interactable screwOut(){
        Interactable aux = this.lampBulb;
        this.lampBulb = null;
        this.state = false;
        this.imgConnect("concreteElement/sockettilewoutlamp.png");
        this.ef.setLampScrewedFlag(this.state);
        return aux;
    }

    public boolean getState(){
        return this.state;
    }

    @Override
    public boolean[] getStatus() {
        boolean[] aux = {this.state};
        return aux;
    }

    public void illuminate(){
        if (this.state && this.img != "concreteElement/sockettilewonlamp.png") this.imgConnect("concreteElement/sockettilewonlamp.png");
    }

    public void unilluminate(){
        if (this.state && this.img != "concreteElement/sockettilewofflamp.png") this.imgConnect("concreteElement/sockettilewofflamp.png");
    }

}

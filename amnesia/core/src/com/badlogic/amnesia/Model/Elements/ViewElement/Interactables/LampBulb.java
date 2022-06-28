package com.badlogic.amnesia.Model.Elements.ViewElement.Interactables;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.pickI;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.putI;

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
        return aux;
    }

    public void pick(){
        this.state = true;
        this.known = true;
    }

    public Interactable put(int posID){
        this.state = false;
        this.posID = posID;
        this.known = true;
        return this;
    }

    @Override
    public boolean[] getStatus() {
        boolean[] aux = {this.state};
        return aux;
    }

}

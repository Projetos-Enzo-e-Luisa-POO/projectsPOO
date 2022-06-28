package com.badlogic.amnesia.Model.Elements.ViewElement.Interactables;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.screwInI;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.screwOutI;

public class TableSocket extends InteractableElement implements screwInI, screwOutI{
    
    private boolean state;
    private Interactable lampBulb;

    public TableSocket(Boolean[] state2, int posID){
        this.imgConnect("concreteElement/sockettilewoutlamp.png");
        this.ID = 15;
        this.posID = posID;
        this.state = false;
        this.lampBulb = null;
    }

    public TableSocket(Interactable  lamp, int posID){
        this.imgConnect("concreteElement/sockettilewoutlamp.png");
        this.ID = 15;
        this.posID = posID;
        this.screwIn(lamp);
    }

    @Override
    public int[] getInterfaces() {
        int[] aux = new int[1];
        if(this.state) aux[0] = 7; // Desrosquear
        else aux[0] = 9; // Rosquear
        return aux;
    }

    public void screwIn(Interactable lamp){
        this.lampBulb = lamp;
        this.state = true;
    }

    public Interactable screwOut(){
        Interactable aux = this.lampBulb;
        this.lampBulb = null;
        this.state = false;
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

}

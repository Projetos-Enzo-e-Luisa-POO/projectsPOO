package com.badlogic.amnesia.services.FlagManagment;

public class ElementMemento {

    private int ID;
    private int posID;
    private boolean[] status;

    public ElementMemento(int ID, int posID, boolean[] stauts){
        this.ID = ID;
        this.posID = posID;
        this.status = stauts;
    }

    public String toString(){
        return this.ID + ',' + this.posID + ',' +  this.status.toString() + ',';
    }

}

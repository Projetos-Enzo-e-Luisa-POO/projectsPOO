package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.TableSocket;
import com.badlogic.amnesia.Services.FlagManagment.ElementFlags;
import com.badlogic.amnesia.Services.FlagManagment.FlagDepot;

public class StoryTeller implements connectOverload{

    private ElementFlags ef = FlagDepot.getInstance();
    private TableSocket ts;

    public boolean checkGame(){
        boolean aux = false;
        if (this.ef.getSwitchFlag() && this.ef.getLampScrewedFlag()){
            this.ts.illuminate();
            aux = true;
        }
        else {
            this.ts.unilluminate();
        }
        return aux;
    }

    @Override
    public void connect(TableSocket ts) {
        this.ts = ts;
    }

}

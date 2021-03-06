package com.badlogic.amnesia.Services.FlagManagment;

import com.badlogic.amnesia.Model.ControlInterfaces.SaveAccess;
import com.badlogic.amnesia.Services.FileManagment.FileController;

public class FlagConfig {

    private FlagRead f = FlagDepot.getInstance();
    private SaveAccess r;

    public FlagConfig(SaveAccess a){
        this.r = a;
    }

    public void saveGame(){
        FileController fc = new FileController("SaveFile.csv");
        fc.overwrite(f.toString(r.getElements()));
    }
    
}

package com.badlogic.amnesia.Services.BindManagment;

import com.badlogic.amnesia.Services.FileManagment.FileController;

public class BindConfig {
    private BindSetup b = BindDepot.getInstance();

    public void changeBind(int v, int i){
        FileController fc = new FileController("KeyBindValues.csv");
        String[] source = fc.getFileContent();
        String[] s = source[i].split(":");
        s[1] = Integer.toString(v);
        source[i] = s[0] + ":" + s[1];
        fc.overwrite(String.join(",", source));
        this.b.updateBinds();
    }

}

package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.amnesia.Services.Builders.RoomBuilder;
import com.badlogic.amnesia.Services.FileManagment.FileController;
import com.badlogic.amnesia.Services.FlagManagment.FlagDepot;
import com.badlogic.amnesia.Services.FlagManagment.FlagRead;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadingBrain {

    private Curtain c;

    public LoadingBrain(Curtain c) {
        this.c = c;
    }

    public Screen tune(Viewport v) {
        FileController fc = new FileController("SaveFile.csv");
        String[] source = fc.getFileContent();
        FlagDepot.getInstance().initialize(source);//não é todo o vetor
        BindDepot.getInstance().updateBinds();
        FlagRead fr = FlagDepot.getInstance();
        RoomBuilder rb = RoomBuilder.getInstance();
        return new Level(this.c, rb.buildRoom(fr.getRoomNumber(), new String[0]), v);
    }
    
}

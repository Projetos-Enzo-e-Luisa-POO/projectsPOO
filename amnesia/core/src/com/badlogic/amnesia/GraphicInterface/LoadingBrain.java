package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.amnesia.Model.MPControl;
import com.badlogic.amnesia.Model.Room;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
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

        BindDepot.getInstance().updateBinds();

        FileController fc = new FileController("SaveFile.csv");
        String[] source = fc.getFileContent();
        String[] s = {source[0], source[1], source[2]};
        FlagDepot.getInstance().initialize(s);

        IDTrans t = new IDTrans();

        FlagRead fr = FlagDepot.getInstance();
        RoomBuilder rb = RoomBuilder.getInstance();
        String[] aux = new String[10];//voltar
        if (source.length > 3)
            System.arraycopy(source, 3, aux, 0, source.length - 3);  
        Room r = rb.buildRoom(fr.getRoomNumber(), aux);

        String[] imgByOr = {"songster/Backward.png", "songster/Rightward.png", "songster/Foward.png", "songster/Leftward.png"},
                    movByOr = {"songster/bwcut.gif", "songster/rwcut.gif", "songster/fwcut.gif", "songster/lwcut.gif"};
                    
        MPControl mpc = new MPControl(r, new Songster(imgByOr, movByOr, Integer.parseInt(source[2]), t.IDToPos(Integer.parseInt(source[1])), r, FlagDepot.getInstance()), Integer.parseInt(source[1]));
        
        return new Level(this.c, r, v, mpc);
    }
    
}

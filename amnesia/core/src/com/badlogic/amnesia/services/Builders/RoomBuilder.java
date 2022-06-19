package com.badlogic.amnesia.services.Builders;

import com.badlogic.amnesia.graphicInterface.GameScreen;
import com.badlogic.amnesia.modelScratch.Room;
import com.badlogic.amnesia.modelScratch.ControlInterfaces.Placeable;
import com.badlogic.amnesia.modelScratch.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.DLCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.DRCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.Floor1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.LUCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.RUCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.Table1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.ULCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.URCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.VoidElement;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.Wall1;
import com.badlogic.amnesia.modelScratch.Toolkit.IDTrans;

public class RoomBuilder {

    public RoomBuilder(){}

    private static class Holder{
        static final RoomBuilder instance = new RoomBuilder();
    }

    public static RoomBuilder getInstance(){
        return Holder.instance;
    }

    public Room buildRoom(GameScreen gameScreen, String[] source){
        IDTrans t = new IDTrans();
        int x = Integer.valueOf(source[0]), 
            y =Integer.valueOf(source[1]);
        Room r = new Room(x, y);
        int k = 2;
        for(int i = 0; i < y; i++)
            for(int j = 0; j < x; j++){
                String data = source[k];
                int[] pos = {i, j};
                r.cellConnect(t.posToID(pos), this.buildCell(gameScreen, pos, data));
                k++;
            }
        return r;
    }

    private Cell buildCell(GameScreen gameScreen, int[] pos, String data){
        Cell c = new Cell(gameScreen, pos);
        int[] IDs = this.translateIDs(data);
        for (int n : IDs)
            c.place(this.elementFactory(n));
        return c;
    }

    private int[] translateIDs(String data){
        String[] str = data.split("", ((data.length() > 3) ? data.length()/2 : 1));
        int[] IDs = new int[str.length];
        for(int i = 0; i < str.length; i++)
            IDs[i] = Integer.valueOf(str[i]);
        return IDs;
    }

    private Placeable elementFactory(int n){
        Placeable aux = null;
        switch(n){
            /*
             * 0: Void
             * -1: Floor 1
             * -2: Wall 1
             * -3: upleft corner 1
             * -4: upright corner 1
             * -5: downleft corner 1
             * -6: downright corner 1
             * -7: rightup corner 1
             * -8: leftup corner 1
             * -9: table 1
             */
            case 0:
                aux = new VoidElement();
            case -1:
                aux = new Floor1();
            case -2:
                aux = new Wall1();
            case -3:
                aux = new ULCorner1();
            case -4:
                aux = new URCorner1();
            case -5:
                aux = new DLCorner1();
            case -6:
                aux = new DRCorner1();
            case -7:
                aux = new RUCorner1();
            case -8:
                aux = new LUCorner1();
            case -9:
                aux= new Table1();
        }
        return aux;
    }

}
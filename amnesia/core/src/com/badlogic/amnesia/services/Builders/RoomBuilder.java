package com.badlogic.amnesia.services.Builders;

import com.badlogic.amnesia.graphicInterface.GameScreen;
import com.badlogic.amnesia.modelScratch.Room;
import com.badlogic.amnesia.modelScratch.ControlInterfaces.Placeable;
import com.badlogic.amnesia.modelScratch.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.DBarrier1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.DLCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.DRCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.Floor1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.LBarrier1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.LUCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.LightSwitch;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.RBarrier1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.RUCorner1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.Table1;
import com.badlogic.amnesia.modelScratch.Elements.ViewElement.ConcreteElements.UBarrier1;
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
             * 00: void
             * 01: upright corner 1
             * 02: up barrier 1*
             * 03: upleft corner 1
             * 04: rightup corner 1
             * 05: floor 1
             * 06: leftup corner 1
             * 07: right barrier 1
             * 08: left barrier 1
             * 09: wall 1
             * 10: downright corner 1
             * 11: down barrier 1
             * 12: downleft corner 1
             * 13: light switch 1
             * 14: table 1
             */
            case 0:
                aux = new VoidElement();
            case 1:
                aux = new URCorner1();
            case 2:
                aux = new UBarrier1();
            case 3:
                aux = new ULCorner1();
            case 4:
                aux = new RUCorner1();
            case 5:
                aux = new Floor1();
            case 6:
                aux = new LUCorner1();
            case 7:
                aux = new RBarrier1();
            case 8:
                aux = new LBarrier1();
            case 9:
                aux = new Wall1();
            case 10:
                aux = new DRCorner1();
            case 11:
                aux = new DBarrier1();
            case 12:
                aux = new DLCorner1();
            case 13:
                aux = new LightSwitch(false);
            case 14:
                aux = new Table1();
        }
        return aux;
    }

}
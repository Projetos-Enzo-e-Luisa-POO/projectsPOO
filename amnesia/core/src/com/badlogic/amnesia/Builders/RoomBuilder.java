package com.badlogic.amnesia.Builders;

import com.badlogic.amnesia.modelScratch.Room;
import com.badlogic.amnesia.modelScratch.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.modelScratch.Toolkit.IDTrans;

public class RoomBuilder {

    private RoomBuilder(){}

    private static class Holder{
        static final RoomBuilder instance = new RoomBuilder();
    }

    public static RoomBuilder getInstance(){
        return Holder.instance;
    }

    public Room buildRoom(String[] source){
        IDTrans t = new IDTrans();
        int x = Integer.valueOf(source[0]), 
            y =Integer.valueOf(source[1]);
        Room r = new Room(x, y);
        int k = 2;
        for(int i = 0; i < y; i++)
            for(int j = 0; j < x; j++){
                String data = source[k];
                int[] pos = {i, j};
                r.cellConnect(t.posToID(pos), this.buildCell(pos, data));
                k++;
            }
        return r;
    }

    private Cell buildCell(int[] pos, String data){
        Cell c = new Cell(pos);
        int[] IDs = this.translateIDs(data);
        for (int n : IDs){
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
                case 1:
                    // new songster?
                case 0:
                    c.place(new Void(n));
                case -1:
                    c.place(new Floor1(n));
                case -2:
                    c.place(new Wall1(n));
                case -3:
                    c.place(new ULCorner1(n));
                case -4:
                    c.place(new URCorner1(n));
                case -5:
                    c.place(new DLCorner1(n));
                case -6:
                    c.place(new DRCorner1(n));
                case -7:
                    c.place(new RUCorner1(n));
                case -8:
                    c.place(new LUCorner1(n);
                case -9:
                    c.place(new Table1(n));
            }
        }
        return c;
    }

    private int[] translateIDs(String data){
        String[] str = data.split("", ((data.length() > 3) ? data.length()/2 : 1));
        int[] IDs = new int[str.length];
        for(int i = 0; i < str.length; i++)
            IDs[i] = Integer.valueOf(str[i]);
        return IDs;
    }


}
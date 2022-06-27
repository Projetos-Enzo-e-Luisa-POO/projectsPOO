package com.badlogic.amnesia.Services.Builders;

import com.badlogic.amnesia.Model.Room;
import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.ControlInterfaces.Placeable;
import com.badlogic.amnesia.Model.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.DBarrier1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.DLCorner1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.DRCorner1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.Floor1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.LBarrier1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.LUCorner1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.RBarrier1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.RUCorner1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.Table1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.UBarrier1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.ULCorner1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.URCorner1;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.VoidElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements.Wall1;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.LightSwitch;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.FileManagment.FileController;

public class RoomBuilder {

    public RoomBuilder(){}

    private static class Holder{
        static final RoomBuilder instance = new RoomBuilder();
    }

    public static RoomBuilder getInstance(){
        return Holder.instance;
    }

    public Room buildRoom(int roomNumber, String[] interactableData){
        //Prep
        String s;
        switch (roomNumber){
            case 3:
                s = "Room3.csv";
            case 2:
                s = "Room2.csv";
            default:
                s = "Room1.csv";
        }

        FileController fc = new FileController(s);
        String[] aux = fc.getFileContent();
        
        String[] source = new String[aux.length + interactableData.length];
        System.arraycopy(aux, 0, source, 0, aux.length);  
        System.arraycopy(interactableData, 0, source, aux.length, interactableData.length);

        IDTrans t = new IDTrans();

        //Start
        int x = Integer.parseInt(source[0]),
            y = Integer.parseInt(source[1]);

        Room r = new Room(y, x);
        
        int k = 2;
        for(int i = 0; i < y; i++)
            for(int j = 0; j < x; j++) {
                String data = source[k];
                int[] pos = {i, j};
                r.cellConnect(t.posToID(pos), this.buildCell(pos, data));
                k++;
        }
        
        while(k < source.length){
            int ID = Integer.parseInt(source[k]), posID = Integer.parseInt(source[k + 1]);
            String[] sSplit = source[k + 2].split(";");
            Boolean[] state = new Boolean[sSplit.length];
            int i = 0;
            while(sSplit.length > 1){
                if (sSplit[0] == "false") state[i] = false;
                else if (sSplit[0] == "true") state[i] = true;
                sSplit = sSplit[1].split(";", 2);
                i++;
            }
            if (sSplit[0] == "false") state[i] = false;
            else if (sSplit[0] == "true") state[i] = true;
            r.elementConnect(posID, interactableFactory(ID, state)); 
            k += 3;
        }
        return r;
    }

    private Cell buildCell(int[] pos, String data){
        Cell c = new Cell(pos);
        int[] IDs = this.translateIDs(data);
        for (int n : IDs) {
            c.place(this.placeableFactory(n)); }
        return c;
    }

    private int[] translateIDs(String data){
        String[] str = data.split("(?<=\\G..)");
        int[] IDs = new int[str.length];
        for(int i = 0; i < str.length; i++)
            IDs[i] = Integer.parseInt(str[i].trim().replaceFirst("^0+(?!$)", ""));
        return IDs;
    }

    private Placeable placeableFactory(int n){
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
             * 14: table 1
             */
            case 0:
                aux = new VoidElement();
                break;
            case 1:
                aux = new URCorner1();
                break;
            case 2:
                aux = new UBarrier1();
                break;
            case 3:
                aux = new ULCorner1();
                break;
            case 4:
                aux = new RUCorner1();
                break;
            case 5:
                aux = new Floor1();
                break;
            case 6:
                aux = new LUCorner1();
                break;
            case 7:
                aux = new RBarrier1();
                break;
            case 8:
                aux = new LBarrier1();
                break;
            case 9:
                aux = new Wall1();
                break;
            case 10:
                aux = new DRCorner1();
                break;
            case 11:
                aux = new DBarrier1();
                break;
            case 12:
                aux = new DLCorner1();
                break;
            case 14:
                aux = new Table1();
                break;
            default:
                aux = new Floor1();
        }
        return aux;
    }


    private Interactable interactableFactory(int n, Boolean[] state){
        Interactable aux = null;
        switch(n){
            /*
             * 13: light switch 1
             */
            case 13:
                aux = new LightSwitch(state);
                break;
        }
        return aux;
    }
}
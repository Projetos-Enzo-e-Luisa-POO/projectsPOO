package com.badlogic.amnesia.Services.FlagManagment;

import java.util.ArrayList;

import com.badlogic.amnesia.Model.ControlInterfaces.Interactable;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;

public class FlagDepot implements RoomFlag, FlagRead, SongsterFlags, ElementFlags{
    private int roomNumber;
    private int songsterPos;
    private int songsterOrientation;
    private ArrayList<ElementMemento> status = new ArrayList<ElementMemento>();

    private FlagDepot(){}
    private static class Holder{
        static final FlagDepot instance = new FlagDepot();
    }

    public static FlagDepot getInstance(){
        return Holder.instance;
    }

    public void initialize(String[] source){
        this.setRoomNumber(Integer.parseInt(source[0]));
        this.setSongsterPos(Integer.parseInt(source[1]));
        this.setSongsterOrientation(Integer.parseInt(source[2]));
    }

    //Exclusive methods for inicialization
    private void setRoomNumber(int n){
        if (n > 0 && n < 4)
            this.roomNumber = n;
    }
    
    //Methods for room inicialization
    public int getRoomNumber(){
        return this.roomNumber;
    }

    //Methods for game flow
    public void nextRoom(){
        this.roomNumber++;
    }

    public void setSongsterPos(int cellID){
        this.songsterPos = cellID;
    }

    public void setSongsterOrientation(int d){
        if (d > -1 && d < 4)
            this.songsterOrientation = d;
    }

    //Methods for saving game state
    private void mementoFactory(Interactable[][] elements){
        for (int i = 0; i < elements.length; i++)
            for (int j = 0; j < elements[i].length; j++)
                if (elements[i][j] != null){
                    IDTrans t = new IDTrans();
                    Interactable a = elements[i][j];
                    int[] pos = {i, j};
                    this.status.add(new ElementMemento(a.getID(), t.posToID(pos), a.getStatus()));
                }
    }

    public String toString(Interactable[][] elements){
        this.mementoFactory(elements);
        return this.roomNumber + ',' + this.songsterPos + ',' + this.songsterOrientation + ',' + this.status.toString();
    }

    private boolean isSwitchOn;
    private boolean isLampScrewed;

    @Override
    public void setSwitchFlag(boolean b) {
        this.isSwitchOn = b;
    }

    @Override
    public void setLampScrewedFlag(boolean b) {
        this.isLampScrewed = b;
    }

    @Override
    public boolean getSwitchFlag() {
        return this.isSwitchOn;
    }

    @Override
    public boolean getLampScrewedFlag() {
        return this.isLampScrewed;
    }




}

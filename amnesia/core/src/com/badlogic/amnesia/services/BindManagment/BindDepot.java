package com.badlogic.amnesia.services.BindManagment;

import com.badlogic.amnesia.services.FileManagment.FileController;

public class BindDepot implements BindSetup, BindRead{
    private int[] keyBindValues = new int[12];

    private BindDepot(){
        this.updateBinds();
    }

    private static class Holder{
        static final BindDepot instance = new BindDepot();
    }

    public static BindDepot getInstance(){
        return Holder.instance;
    }

    public void updateBinds(){
        FileController fc = new FileController("KeyBindValues.csv");
        String[] source = fc.getFileContent();
        int k = 0;
        for (String s : source){
            this.keyBindValues[k++] = Integer.valueOf(s.split(":")[1]);
        }
    }

    public int getKeyValueOf(String command){
        int aux = 0;
        switch(command){
            case "pause":
                aux = this.keyBindValues[0];
            case "free hand":
                aux = this.keyBindValues[1];
            case "move up":
                aux = this.keyBindValues[2];
            case "quick interact":
                aux = this.keyBindValues[3];
            case "move left":
                aux = this.keyBindValues[4];
            case "move down":
                aux = this.keyBindValues[5];
            case "move right":
                aux = this.keyBindValues[6];
            case "select":
                aux = this.keyBindValues[7];
            case "up select":
                aux = this.keyBindValues[8];
            case "first slot":
                aux = this.keyBindValues[9];
            case "second slot":
                aux = this.keyBindValues[10];
            case "down select": 
                aux = this.keyBindValues[11];
        }
        return aux;
    }

}

package com.badlogic.amnesia.Services.BindManagment;

import com.badlogic.amnesia.Services.FileManagment.FileController;

public class BindDepot implements BindSetup, BindRead{
    private int[] keyBindValues = new int[12];
    public String[] commandValues = {
        "pause",
        "free hand",
        "move up",
        "quick interact",
        "move left",
        "move down",
        "move right",
        "select",
        "up select",
        "first slot",
        "second slot",
        "down select"
    };

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
            this.keyBindValues[k++] = Integer.parseInt(s.split(":")[1]);
        }
    }

    public int getKeyValueOf(String command){
        int aux;
        switch(command){
            case "pause":
                aux = this.keyBindValues[0];
                break;
            case "free hand":
                aux = this.keyBindValues[1];
                break;
            case "move up":
                aux = this.keyBindValues[2];
                break;
            case "quick interact":
                aux = this.keyBindValues[3];
                break;
            case "move left":
                aux = this.keyBindValues[4];
                break;
            case "move down":
                aux = this.keyBindValues[5];
                break;
            case "move right":
                aux = this.keyBindValues[6];
                break;
            case "select":
                aux = this.keyBindValues[7];
                break;
            case "up select":
                aux = this.keyBindValues[8];
                break;
            case "first slot":
                aux = this.keyBindValues[9];
                break;
            case "second slot":
                aux = this.keyBindValues[10];
                break;
            case "down select": 
                aux = this.keyBindValues[11];
                break;
            default:
                aux = 0;
        }
        return aux;
    }
}

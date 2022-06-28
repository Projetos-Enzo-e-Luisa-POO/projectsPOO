package com.badlogic.amnesia.GraphicInterface;

import java.util.ArrayList;

import com.badlogic.amnesia.Services.BindManagment.BindConfig;
import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SettingsBrain {

    private static BindDepot bd = BindDepot.getInstance();
    private static BindConfig bc = new BindConfig();
    
    public String[] getCommandValues() {
        return SettingsBrain.bd.commandValues;
    }
    
    public int getKeyValueOf(String command) {
        return SettingsBrain.bd.getKeyValueOf(command);
    }

    public void saveBindsFromFields(ArrayList<TextField> textFields) {
        int k = 0;
        for (TextField tf : textFields) {
            if (!tf.getText().isEmpty()) {
                String enterCharacter = "" + tf.getText().toUpperCase().charAt(0);
                this.changeBind(Input.Keys.valueOf(enterCharacter), k);
            }
            k++;
        }
    }

    public void changeBind(int keyValue, int index){
        System.out.println("keyvalue: " + keyValue);
        bc.changeBind(keyValue, index);
    }

}

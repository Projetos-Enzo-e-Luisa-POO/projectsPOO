package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.amnesia.Services.BindManagment.BindConfig;

public class SettingsBrain {

    public void changeBind(int keyV, int i){
        BindConfig bc = new BindConfig();
        bc.changeBind(keyV, i);
    }

}

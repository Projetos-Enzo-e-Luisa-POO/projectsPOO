package com.badlogic.amnesia.Services.EventManagment;

import com.badlogic.amnesia.GraphicInterface.Level;
import com.badlogic.amnesia.Model.MPControl;
import com.badlogic.amnesia.Model.ControlInterfaces.ControlAccess;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.gdx.Gdx;

public class EventHandling extends MPControl {

    private BindDepot bd = BindDepot.getInstance();
    private Level gameScreen;

    public EventHandling (Level gameScreen, ControlAccess r, Songster p, int posID) {
        super(r, p, posID);
        this.gameScreen = gameScreen;
    }

    private boolean isInputKeyForCommand (String command) {
        return Gdx.input.isKeyJustPressed(bd.getKeyValueOf(command));
    }

    public void handleInput(int keycode) {

        if (isInputKeyForCommand("pause")) {
            this.gameScreen.pause();
        }
        
        if (isInputKeyForCommand("free hand")) {
            
        }

        if (isInputKeyForCommand("move up")) {
            this.move(0);
        }

        else if (isInputKeyForCommand("quick interact")){
            this.quickInteract();
        }

        else if (isInputKeyForCommand("move left")){
            this.move(1);
        }

        else if (isInputKeyForCommand("move down")){
            this.move(2);
        }

        else if (isInputKeyForCommand("move right")){
            this.move(3);
        }

        else if (isInputKeyForCommand("select")){
            
        }

        else if (isInputKeyForCommand("up select")){
            
        }

        else if (isInputKeyForCommand("first slot")){

        }

        else if (isInputKeyForCommand("second slot")){
            
        }

        else if (isInputKeyForCommand("down select")){
            
        }
    }

}

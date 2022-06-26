package com.badlogic.amnesia.Model.Elements.ViewElement.ConcreteElements;

import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;

public class LightSwitch extends InteractableElement{

    // rever questões da interface gráfica

    private boolean state;

    public LightSwitch(Boolean[] state2){
        this.imgConnect("concreteElement/lightSwitch.png");
        this.ID = 13;
        if (state2[0])
            this.turnOn();
        else
            this.turnOff();
    }

    @Override
    public int[] getInterfaces() {
        int[] aux = {3};
        return aux;
    }

    public void turnOn(){
        this.state = true;
    }

    public void turnOff(){
        this.state = false;
    }

    public boolean getState(){
        return this.state;
    }

    @Override
    public boolean[] getStatus() {
        boolean[] aux = {this.state};
        return aux;
    }

}

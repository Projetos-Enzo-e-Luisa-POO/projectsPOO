package com.badlogic.amnesia.Model.Elements.ViewElement.Interactables;

import com.badlogic.amnesia.Model.Elements.ViewElement.InteractableElement;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.turnOffI;
import com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces.turnOnI;

public class LightSwitch extends InteractableElement implements turnOnI, turnOffI{

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
        int[] aux = new int[1];
        if(this.state) aux[0] = 3; // Desligar
        else aux[0] = 5; // Ligar
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

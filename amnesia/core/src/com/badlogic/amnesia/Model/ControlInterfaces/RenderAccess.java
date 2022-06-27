package com.badlogic.amnesia.Model.ControlInterfaces;

public interface RenderAccess {
    public RenderStrategy[][] getCells();

    public RenderStrategy[][] getInteractables();
    
    public int[] size();
}

package com.badlogic.amnesia.modelScratch.ControlInterfaces;

/*
 * Versão mais específica de um placeable, possui interações via interfaces a serem descritas em seus métodos
 */

public interface Interactable extends Placeable {

	public boolean isInteractable();

    public int[] getInterfaces();

    public boolean[] getStatus();

}
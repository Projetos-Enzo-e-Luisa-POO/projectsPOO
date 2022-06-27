package com.badlogic.amnesia.GraphicInterface;

public enum ProcessesToLoad {
    INITIALIZE_GAME,
    //INITIALIZE_ROOM,
    //CHANGE_SCREEN,
    //DISPOSE_SCREEN,
    END_GAME;

    static ProcessesToLoad getInitializeGame(){
        return INITIALIZE_GAME;
    }

    static ProcessesToLoad getEndGame(){
        return END_GAME;
    }
}

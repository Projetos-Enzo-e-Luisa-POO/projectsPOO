package com.badlogic.amnesia.Services.MovementManagment;

import com.badlogic.amnesia.GraphicInterface.Level;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.BindManagment.BindDepot;

public class MovementController {
    
    private Level gameScreen;

    private IDTrans t = new IDTrans();
    private BindDepot bd = BindDepot.getInstance();

    public MovementController (Level gameScreen) {
        this.gameScreen = gameScreen;
    }

    private boolean isInputKeyForCommand(int inputKey, String command) {
        return inputKey == bd.getKeyValueOf(command);
    }

    public void handleInputKeyClick(Level gameScreen, int inputKey){
        if (this.isInputKeyForCommand(inputKey, "pause")){
            gameScreen.pause();
        }

        else if (this.isInputKeyForCommand(inputKey, "free hand")){
            
        }

        else if (this.isInputKeyForCommand(inputKey, "move up")){
            int[] newPosition = t.IDToPos(gameScreen.songster.posID);
            newPosition[0] -= 1;
            gameScreen.songster.move(0, t.posToID(newPosition));
        }

        else if (this.isInputKeyForCommand(inputKey, "quick interact")){
            
        }

        else if (this.isInputKeyForCommand(inputKey, "move left")){
            int[] newPosition = t.IDToPos(gameScreen.songster.posID);
            newPosition[1] -= 1;
            gameScreen.songster.move(1, t.posToID(newPosition));
        }

        else if (this.isInputKeyForCommand(inputKey, "move down")){
            int[] newPosition = t.IDToPos(gameScreen.songster.posID);
            newPosition[0] += 1;
            gameScreen.songster.move(2, t.posToID(newPosition));
        }

        else if (this.isInputKeyForCommand(inputKey, "move right")){
            int[] newPosition = t.IDToPos(gameScreen.songster.posID);
            newPosition[1] += 1;
            gameScreen.songster.move(1, t.posToID(newPosition));
        }

        else if (this.isInputKeyForCommand(inputKey, "select")){
            
        }

        else if (this.isInputKeyForCommand(inputKey, "up select")){
            
        }

        else if (this.isInputKeyForCommand(inputKey, "first slot")){
            
        }

        else if (this.isInputKeyForCommand(inputKey, "second slot")){
            
        }

        else if (this.isInputKeyForCommand(inputKey, "down select")){
            
        }
    }
}

package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Curtain extends Game {
    
    private static Loading loading;
    
    private Screen actualScreen, previousScreen;
    private static String PATH_TO_SCREENS = "com.badlogic.amnesia.GraphicInterface.";

    public void create() {
        Curtain.loading = new Loading(this);
        this.actualScreen = new Menu(this);
        this.setScreen(actualScreen);
    }

    public String getActualScreenName() {
        String screenName = this.actualScreen.getClass().getName(),
               screenClassName = screenName.split(PATH_TO_SCREENS)[1];
        return screenClassName;
    }

    public void changeToScreen(Screen screen) {
        this.previousScreen = this.actualScreen;
        this.actualScreen.dispose();

        this.setScreen(screen);
        this.actualScreen = screen;
    }

    public void loadGame(String configFileName) {
        Curtain.loading.startLoading(configFileName);
    }

    public void loadRoom(String configFileName, String roomFile) {
        Curtain.loading.startLoading(configFileName, roomFile);
    }

    public void disposeScreen(Screen screen) {
        screen.dispose();
        this.setScreen(previousScreen);
    }

    public void render() {
        super.render();
    }
    
    public void dispose() {}
}

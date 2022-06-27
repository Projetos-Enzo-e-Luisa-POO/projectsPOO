package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Curtain extends Game {
    
    private static Loading loading;
    
    private Screen menuScreen, actualScreen;
    private static String PATH_TO_SCREENS = "com.badlogic.amnesia.GraphicInterface.";

    public void create() {
        Curtain.loading = new Loading(this);
        this.menuScreen = this.actualScreen = new Menu(this);
        this.setScreen(this.menuScreen);
    }

    public Screen getMenuScreen() {
        return this.menuScreen;
    }

    public String getActualScreenName() {
        String screenName = this.actualScreen.getClass().getName(),
               screenClassName = screenName.split(PATH_TO_SCREENS)[1];
        return screenClassName;
    }

    public void changeToScreen(Screen screen) {
        screen.hide();
        this.setScreen(screen);
        this.actualScreen = screen;
    }

    public void loadGame(String configFileName) {
        this.setScreen(Curtain.loading);
        Curtain.loading.loadGameConfig(configFileName);
        Curtain.loading.loadRoom(configFileName, "Room1.csv");
    }

    public void loadRoom(String configFileName, String roomFile) {
        Curtain.loading.loadRoom(configFileName, roomFile);
    }

    public void disposeScreen(Screen screen) {
        screen.dispose();
    }

    public void render() {
        super.render();
    }
    
    public void dispose() {}
}

package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Curtain extends Game {

    private OrthographicCamera Camera = new OrthographicCamera();
    private FitViewport v = new FitViewport(1280, 720, this.Camera);
    public void create(){

        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.setScreen(new Menu(this, this.v));
    }

    public void callScreen(Screen s){
        this.setScreen(s);
    }

    public void render() {
        super.render();
    }

}














    /*
    private static Loading loading;
    
    private Screen menuScreen, actualScreen;
    private static String PATH_TO_SCREENS = "com.badlogic.amnesia.GraphicInterface.";
/*
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
*/
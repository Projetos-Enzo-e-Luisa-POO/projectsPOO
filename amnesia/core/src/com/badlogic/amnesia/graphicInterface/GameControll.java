package com.badlogic.amnesia.graphicInterface;

//import com.badlogic.amnesia.services.Builders.RoomBuilder;
//import com.badlogic.amnesia.services.FileController.FileController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameControll extends Game {
    //------------------------------------------------------------------------------------
    public SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    public String configFile;
    private int actualRoom = 1;
    //------------------------------------------------------------------------------------
    public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        
        font = new BitmapFont();
        
        this.setScreen(new Menu(this));
    }
    //------------------------------------------------------------------------------------
    //private String[] getRoomSourceFromFile(String fileName) throws Exception {
        //FileController fc = new FileController();
        //try {
        //    return fc.getFileContent(fileName);
        //} catch (Exception e) {
        //    throw new Exception(e.getMessage());
        //}
    //}
    //------------------------------------------------------------------------------------
    //private GameScreen buildRoomBySource(String[] source) {
    //    GameScreen gameScreen = new GameScreen(this.configFile);
//
     //   RoomBuilder roomBuilder = new RoomBuilder();
     //   roomBuilder.buildRoom(gameScreen, source);
//
    //    return gameScreen;
    //}
    //------------------------------------------------------------------------------------
    public void loadNewRoom() throws Exception {
        try {
    //        String[] source = this.getRoomSourceFromFile("Room" + (actualRoom++) + ".csv");
    //        GameScreen newRoomScreen = this.buildRoomBySource(source);
    //        this.setScreen(newRoomScreen);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    //------------------------------------------------------------------------------------
    public void render() {
        super.render();


        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
    //------------------------------------------------------------------------------------
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

package com.badlogic.amnesia.GraphicInterface;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Loading implements Screen {

    private static Curtain c;
    private ProcessesToLoad ptl;
    private LoadingBrain lb;

    private Viewport v;
    private SpriteBatch batch = new SpriteBatch();
    private Texture backgroundImage = new Texture(Gdx.files.internal("loading/loadingBackground.png"));

    private boolean rendered = false;

    public Loading(Curtain c, ProcessesToLoad ptl, Viewport v){
        Loading.c = c;
        this.ptl = ptl;
        this.v = v;
        this.lb = new LoadingBrain(Loading.c);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        v.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        this.v.getCamera().update();    
        batch.setProjectionMatrix(this.v.getCamera().combined);

        if (this.rendered)
            switch (this.ptl){
                case END_GAME:
                    Loading.c.callScreen(new Menu(Loading.c, this.v));
                case INITIALIZE_GAME:
                    Loading.c.callScreen(this.lb.tune(this.v));
                default:
                    break;
            }

        ScreenUtils.clear(0,0,0,1);

        batch.begin();
        batch.draw(this.backgroundImage, 0, 0);
        batch.end();

        this.rendered = true;
        
        try {
            TimeUnit.SECONDS.sleep((long) 0.5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        this.dispose();
        
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
    }

}
















    
    /*private static Curtain curtain;
    private ProcessesToLoad processInLoading;

    private Viewport viewport;
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera Camera = new OrthographicCamera();
    private Texture backgroundImage = new Texture(Gdx.files.internal("loading/loadingBackground.png"));

    public Loading(Curtain curtain) {
        Loading.curtain = curtain;
    }

    public ProcessesToLoad getProcessInLoading() {
        return this.processInLoading;
    }

    public void startLoading(ProcessesToLoad process, Screen screen) {
        processInLoading = process;
        switch(process) {
            case CHANGE_SCREEN:
                Loading.curtain.changeToScreen(screen);
            
            case DISPOSE_SCREEN:
                screen.dispose();
            
            case END_GAME:
                saveGameFlags();
                screen.dispose();
            
            default:
                break;
        }
        this.dispose();
    }
    
    public void loadGameConfig(String configFileName) {
        processInLoading = ProcessesToLoad.INITIALIZE_GAME;
        
        FileController fc = new FileController(configFileName);
        String[] source = fc.getFileContent();

        FlagDepot.getInstance().initialize(source);
        BindDepot.getInstance().updateBinds();
    }

    public void loadRoom(String configFileName, String roomFile) {
        processInLoading = ProcessesToLoad.INITIALIZE_ROOM;
        
        Level roomScreen = this.createRoomScreen(configFileName, roomFile);
        
        curtain.changeToScreen(roomScreen);
    }

    private Level createRoomScreen(String configFileName, String roomFile) {
        int roomNumber = roomFile.charAt(4);

        RoomBuilder roomBuilder = new RoomBuilder();
        Room room = roomBuilder.buildRoom(roomNumber, new String[0]);

        Level roomScreen = new Level(Loading.curtain, room);

        return roomScreen;
    }

    private void saveGameFlags() {
        String configs = FlagDepot.getInstance().toString();
        FileController fc = new FileController("ResetSaveFile.csv");
        fc.overwrite(configs);
    }

    private void Setup() {
        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);
    }

    @Override
    public void render(float delta) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        this.Camera.update();    
        batch.setProjectionMatrix(this.Camera.combined);

        batch.begin();
        batch.draw(this.backgroundImage, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
    }

    @Override
    public void show() {
        this.Setup();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        this.dispose();
    }
}
 */
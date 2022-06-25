package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.amnesia.Services.Builders.RoomBuilder;
import com.badlogic.amnesia.Services.FileManagment.FileController;
import com.badlogic.amnesia.Services.FlagManagment.FlagDepot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Loading implements Screen {
    
    private static Curtain curtain;
    private ProcessesToLoad processInLoading;

    private FitViewport viewport;
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera Camera = new OrthographicCamera();
    private Texture backgroundImage = new Texture(Gdx.files.internal("loadingBackground.png"));

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
        }
        this.dispose();
    }
    
    public void startLoading(String configFileName) {
        processInLoading = ProcessesToLoad.INITIALIZE_GAME;
        this.initializeGameConfigs(configFileName);
        this.dispose();
    }

    public void startLoading(String configFileName, String roomFile) {
        processInLoading = ProcessesToLoad.INITIALIZE_ROOM;
        Level room = this.createRoom(configFileName, roomFile);
        curtain.changeToScreen(room);
        this.dispose();
    }

    private void saveGameFlags() {
        String configs = FlagDepot.getInstance().toString();
        FileController fc = new FileController("ResetSaveFile.csv");
        fc.Overwrite(configs);
    }

    private void initializeGameConfigs(String configFileName) {
        FileController fc = new FileController(configFileName);
        String[] source = fc.getFileContent();

        FlagDepot.getInstance().initialize(source);
        BindDepot.getInstance().updateBinds();
    }

    private Level createRoom(String configFileName, String roomFile) {
        int roomNumber = roomFile.charAt(4);
    
        Level level = new Level(configFileName);

        FileController fc = new FileController(roomFile);
        String[] interactableData = fc.getFileContent();

        RoomBuilder roomBuilder = new RoomBuilder();
        roomBuilder.buildRoom(level, roomNumber, interactableData);

        return level;
    }

    private void Setup() {
        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        
        this.viewport = new FitViewport(1280, 720, this.Camera);

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

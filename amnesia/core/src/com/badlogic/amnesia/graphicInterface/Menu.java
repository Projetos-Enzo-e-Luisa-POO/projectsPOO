package com.badlogic.amnesia.graphicInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Menu implements Screen {
    //------------------------------------------------------------------------------------
    private static GameControll gameControll;
    
    private Texture backgroundImage = new Texture(Gdx.files.internal("menuBackground.png"));
    private Viewport viewport;
    
    private OrthographicCamera Camera;
    
    private Vector3 touchPosition = new Vector3();

    private Rectangle newGameButton;
    private Rectangle configButton;
    private Rectangle Title;
    //------------------------------------------------------------------------------------
    public Menu (GameControll game) {
        Menu.gameControll = game;

        this.Camera = new OrthographicCamera();
        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);

        Title = new Rectangle();
        Title.width = 3*Gdx.graphics.getWidth()/5;
        Title.height = Gdx.graphics.getHeight()/6;
        Title.x = Gdx.graphics.getWidth()/2 - Title.width/2;
        Title.y = 3*Gdx.graphics.getHeight()/4 - Title.height/2;


        newGameButton = new Rectangle();
        newGameButton.width = Gdx.graphics.getWidth()/5;
        newGameButton.height = Gdx.graphics.getHeight()/8;
        newGameButton.x = Gdx.graphics.getWidth()/2 - newGameButton.width/2;
        newGameButton.y = Gdx.graphics.getHeight()/2 - newGameButton.height/2;

        configButton = new Rectangle();
        configButton.width = Gdx.graphics.getWidth()/5;
        configButton.height = Gdx.graphics.getHeight()/8;
        configButton.x = Gdx.graphics.getWidth()/2 - configButton.width/2;
        configButton.y = Gdx.graphics.getHeight()/4 - configButton.height/2;

    }
    //------------------------------------------------------------------------------------
    private void renderMenuBackground() {
        gameControll.batch.draw(this.backgroundImage, 0, 0);
    }
    //------------------------------------------------------------------------------------
    private void confirmGameMode() {
        Stage stage = new Stage();
        Skin skin = new Skin();
        TextButton confirmButton = new TextButton("Sim", skin),
                    cancelButton = new TextButton("Não", skin);

        Dialog dialogGameMode = new Dialog("Modo de Jogo", skin, "dialog") {
            public void result(Object obj) {
                if (obj.equals("confirmButton")) {
                    Menu.gameControll.configFile = "ResetSaveFile.csv";
                } else if (obj.equals("cancelButton")) {
                    Menu.gameControll.configFile = "ResetSaveFile.csv";
                }
            }
        };
        dialogGameMode.text("Deseja recuperar as configurações de seu último jogo?");
        dialogGameMode.button(confirmButton, "confirmButton");
        dialogGameMode.button(cancelButton, "cancelButton");
        dialogGameMode.show(stage);
    }
    //------------------------------------------------------------------------------------
    private void showBindConfigScreen() {
        // insert new screen here
    }
    //------------------------------------------------------------------------------------
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

        this.Camera.update();
        gameControll.batch.setProjectionMatrix(this.Camera.combined);

        gameControll.batch.begin();
        renderMenuBackground();
        gameControll.batch.end();

        if (Gdx.input.justTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            this.Camera.unproject(touchPosition);
            
            if (newGameButton.contains(touchPosition.x, touchPosition.y)) {
                this.confirmGameMode();
                try {
                    gameControll.loadNewRoom();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            if (configButton.contains(touchPosition.x, touchPosition.y)) {
                System.out.println("config!");
                //this.showBindConfigScreen();
            }
        }
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
    //------------------------------------------------------------------------------------
    @Override
    public void dispose() {
        backgroundImage.dispose();
    }
    //------------------------------------------------------------------------------------
    @Override
    public void show() {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }
}

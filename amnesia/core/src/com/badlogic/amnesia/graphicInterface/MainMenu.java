package com.badlogic.amnesia.graphicInterface;

import com.badlogic.amnesia.services.FileController.error.CopyFileException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {
    //------------------------------------------------------------------------------------
    private static GameControll gameControll;
    
    private Texture backgroundImage;
    private Sprite menuBackground;
    private SpriteBatch spriteBatch;
    
    private OrthographicCamera orthographicCamera;
    
    private Vector3 touchPosition = new Vector3();

    private Rectangle newGameButton;
    private Rectangle configButton;
    //------------------------------------------------------------------------------------
    public MainMenu (GameControll game) {
        MainMenu.gameControll = game;

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, 700, 500);

        newGameButton = new Rectangle();
        newGameButton.x = 212;
        newGameButton.y = 256;
        newGameButton.width = 271;
        newGameButton.height = 33;

        configButton = new Rectangle();
        configButton.x = 158;
        configButton.y = 339;
        configButton.width = 401;
        configButton.height = 34;

    }
    //------------------------------------------------------------------------------------
    private void renderMenuBackground() {
        backgroundImage = new Texture(Gdx.files.internal("menuBackground.png"));
        gameControll.batch.draw(backgroundImage, 0, 0);
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
                    MainMenu.gameControll.configFile = "ResetSaveFile.csv";
                } else if (obj.equals("cancelButton")) {
                    MainMenu.gameControll.configFile = "ResetSaveFile.csv";
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
        orthographicCamera.update();
        gameControll.batch.setProjectionMatrix(orthographicCamera.combined);

        gameControll.batch.begin();
        renderMenuBackground();
        gameControll.batch.end();

        if (Gdx.input.justTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            orthographicCamera.unproject(touchPosition);
            
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
    }
    //------------------------------------------------------------------------------------
    @Override
    public void dispose() {
        backgroundImage.dispose();
        spriteBatch.dispose();
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

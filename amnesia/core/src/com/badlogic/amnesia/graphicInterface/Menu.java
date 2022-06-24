package com.badlogic.amnesia.graphicInterface;

import com.badlogic.amnesia.services.FileManagment.FileController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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
    
    private Texture backgroundImage = new Texture(Gdx.files.internal("menuBackground.png")),
                    titleTexture = new Texture(Gdx.files.internal("Title.png")),
                    newGameButtonTexture = new Texture(Gdx.files.internal("NewGameButton.png")),
                    loadGameButtonTexture = new Texture(Gdx.files.internal("LoadGameButton.png")),
                    configButtonTexture = new Texture(Gdx.files.internal("SettingsButton.png"));
    private Viewport viewport;
    
    private OrthographicCamera Camera;
    
    private Vector3 touchPosition = new Vector3();

    private MenuBrain mb = new MenuBrain();

    private Rectangle newGameButton;
    private Rectangle loadGameButton;
    private Rectangle configButton;
    private Rectangle Title;
    //------------------------------------------------------------------------------------
    public Menu (GameControll game) {
        Menu.gameControll = game;

        this.Camera = new OrthographicCamera();
        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);

        //viewport.getScreenHeight();
        //viewport.getScreenWidth();
        
        Title = new Rectangle();
        Title.width = Gdx.graphics.getWidth()*1.5f;
        Title.height = Gdx.graphics.getHeight()/2;
        Title.x = Gdx.graphics.getWidth()/4;
        Title.y = 7*Gdx.graphics.getHeight()/8;

        newGameButton = new Rectangle();
        newGameButton.width = 4*Gdx.graphics.getWidth()/5;
        newGameButton.height = 3*Gdx.graphics.getHeight()/11;
        newGameButton.x = 3*Gdx.graphics.getWidth()/5;
        newGameButton.y = 12*Gdx.graphics.getHeight()/20;

        loadGameButton = new Rectangle();
        loadGameButton.width = 4*Gdx.graphics.getWidth()/5;
        loadGameButton.height = 3*Gdx.graphics.getHeight()/11;
        loadGameButton.x = 3*Gdx.graphics.getWidth()/5;
        loadGameButton.y = 6.25f*Gdx.graphics.getHeight()/20;

        configButton = new Rectangle();
        configButton.width = 4*Gdx.graphics.getWidth()/5;
        configButton.height = Gdx.graphics.getHeight()/4;
        configButton.x = 3*Gdx.graphics.getWidth()/5;
        configButton.y = Gdx.graphics.getHeight()/20;

    }
    //------------------------------------------------------------------------------------
    private void confirmGameMode() {
        
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
        gameControll.batch.draw(this.backgroundImage, 0, 0);
        gameControll.batch.draw(titleTexture, Title.x, Title.y, Title.width, Title.height);
        gameControll.batch.draw(newGameButtonTexture, newGameButton.x, newGameButton.y, newGameButton.width, newGameButton.height);
        gameControll.batch.draw(loadGameButtonTexture, loadGameButton.x, loadGameButton.y, loadGameButton.width, loadGameButton.height);
        gameControll.batch.draw(configButtonTexture, configButton.x, configButton.y, configButton.width, configButton.height);
        gameControll.batch.end();

        if (Gdx.input.justTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            this.viewport.unproject(touchPosition);
            
            if (newGameButton.contains(touchPosition.x, touchPosition.y)) {
                System.out.println("newGame!");
                if(mb.saveExists()){
                    Stage stage = new Stage();
                    Skin skin = new Skin();
                    TextButton  confirmButton = new TextButton("Sim", skin),
                                cancelButton = new TextButton("Não", skin);
                    Dialog dialogGameMode = new Dialog("Modo de Jogo", skin, "dialog") {
                        public void result(Object obj) {
                            FileController fc = new FileController("SaveFile.csv");
                            if (obj.equals("cancelButton")) {
                                fc.copyFileContent(new FileHandle("ResetSaveFile.csv"));
                            }
                        //Ir para tela de Loading de sala com "SaveFile.csv"
                        }
                    };
                    dialogGameMode.text("Deseja recuperar as configurações de seu último jogo?");
                    dialogGameMode.button(confirmButton, "confirmButton");
                    dialogGameMode.button(cancelButton, "cancelButton");
                    dialogGameMode.show(stage);
                }
                else {
                    //Ir para tela de Loading de sala com "SaveFile.csv"
                }
            }

            if (loadGameButton.contains(touchPosition.x, touchPosition.y)) {
                System.out.println("loadGame!");
            }
            
            if (configButton.contains(touchPosition.x, touchPosition.y)) {
                System.out.println("config!");
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

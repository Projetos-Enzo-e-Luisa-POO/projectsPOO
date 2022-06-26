package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Menu implements Screen {
    
    private static Curtain curtain;

    private Viewport viewport;
    private Vector3 touchPosition = new Vector3();
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera Camera = new OrthographicCamera();;

    private Texture backgroundImage = new Texture(Gdx.files.internal("menu/menuBackground.png")),
                    titleTexture = new Texture(Gdx.files.internal("menu/Title.png")),
                    newGameButtonTexture = new Texture(Gdx.files.internal("menu/NewGameButton.png")),
                    loadGameButtonTexture = new Texture(Gdx.files.internal("menu/LoadGameButton.png")),
                    SaveTexture = new Texture(Gdx.files.internal("menu/RecoverLastSave.png")),
                    configButtonTexture = new Texture(Gdx.files.internal("menu/SettingsButton.png")),
                    noTexture = new Texture(Gdx.files.internal("menu/NO.png")),
                    yesTexture = new Texture(Gdx.files.internal("menu/Yes.png"));

    private Rectangle newGameButton;
    private Rectangle loadGameButton;
    private Rectangle configButton;
    private Rectangle Title;
    private Rectangle SaveMenu;
    private Rectangle No;
    private Rectangle Yes;

    private MenuBrain mb = new MenuBrain();
    private boolean newGamePressed = false;
    
    public Menu (Curtain curtain) {
        Menu.curtain = curtain;
    }

    private void loadGame() {
        this.dispose();
        this.mb.setLoading("SaveFile.csv", Menu.curtain);
    }
    
    public void Setup(){

        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);
        
        float   h = this.viewport.getWorldHeight(),
                w = this.viewport.getWorldWidth();

        //x, y, width, height
        Title = new Rectangle(6*w/32, 11*h/16, 10*w/16, 2*h/9);
        newGameButton = new Rectangle(11*w/32, 8*h/16, 5*w/16, h/9);
        loadGameButton = new Rectangle(11*w/32, 5*h/16, 5*w/16, h/9);
        configButton = new Rectangle(11*w/32, 2*h/16, 5*w/16, h/9);
        SaveMenu = new Rectangle(w/2, h/2, 0, 0);
        Yes = new Rectangle(27*w/64, 13*h/32, 4*h/16, w/16);
        No = new Rectangle(27*w/64, 8*h/32, 4*h/16, w/16);
    }
    
    @Override
    public void render(float delta) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        float   h = this.viewport.getWorldHeight(),
                w = this.viewport.getWorldWidth();

        this.Camera.update();
        batch.setProjectionMatrix(this.Camera.combined);

        batch.begin();

        batch.draw(this.backgroundImage, 0, 0);
        batch.draw(titleTexture, Title.x, Title.y, Title.width, Title.height);

        batch.draw(newGameButtonTexture, newGameButton.x, newGameButton.y, newGameButton.width, newGameButton.height);
        batch.draw(loadGameButtonTexture, loadGameButton.x, loadGameButton.y, loadGameButton.width, loadGameButton.height);
        batch.draw(configButtonTexture, configButton.x, configButton.y, configButton.width, configButton.height);

        if(this.newGamePressed){
            batch.draw(SaveTexture, SaveMenu.x, SaveMenu.y, SaveMenu.width, SaveMenu.height);

            if(SaveMenu.width < 4*w/12)
                SaveMenu.width+=16*2*2;
            if(SaveMenu.height < 8*h/12)
                SaveMenu.height+=9*3*2;
            if(SaveMenu.x > 4*w/12)
                SaveMenu.x-=16*2;
            if(SaveMenu.y > 2*h/12)
                SaveMenu.y-=9*3;
            else{
                batch.draw(yesTexture, Yes.x, Yes.y, Yes.width, Yes.height);
                batch.draw(noTexture, No.x, No.y, No.width, No.height);
            }
        }
        else {
            SaveMenu.width = 0;
            SaveMenu.height = 0;
            SaveMenu.x = w/2;
            SaveMenu.y = h/2;
        }
        batch.end();

        if (Gdx.input.justTouched()) {
            this.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            this.viewport.unproject(touchPosition);
            if(!this.newGamePressed){
                if (newGameButton.contains(touchPosition.x, touchPosition.y)) {
                    if(this.mb.saveExists()) this.newGamePressed = true;
                    else this.loadGame(); // Loading if completely new game
                }
                if (loadGameButton.contains(touchPosition.x, touchPosition.y)) this.loadGame(); // Loading with possible load file
                if (configButton.contains(touchPosition.x, touchPosition.y)) System.out.println("config!"); //Menu.curtain.setScreen(Settings);
            }
            else {
                if (Yes.contains(touchPosition.x, touchPosition.y)){
                    this.newGamePressed = false;
                    this.loadGame();
                }
                else if (No.contains(touchPosition.x, touchPosition.y)) {
                    this.newGamePressed = false;
                    this.mb.overwriteSaveFile();
                    this.loadGame();
                }
                else if (SaveMenu.contains(touchPosition.x, touchPosition.y)){}
                else this.newGamePressed = false;
                }
            }
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
        titleTexture.dispose();
        newGameButtonTexture.dispose();
        loadGameButtonTexture.dispose();
        SaveTexture.dispose();
        configButtonTexture.dispose();
        noTexture.dispose();
        yesTexture.dispose();
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

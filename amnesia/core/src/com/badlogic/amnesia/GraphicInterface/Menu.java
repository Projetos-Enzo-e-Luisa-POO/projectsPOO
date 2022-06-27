package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Menu implements Screen {

    private static Curtain c;

    private Viewport v;
    private Vector3 touchPosition = new Vector3();
    private SpriteBatch batch = new SpriteBatch();

    private Texture backgroundImage = new Texture(Gdx.files.internal("menu/menuBackground.png")),
                    titleTexture = new Texture(Gdx.files.internal("menu/Title.png")),
                    newGameButtonTexture = new Texture(Gdx.files.internal("menu/NewGameButton.png")),
                    loadGameButtonTexture = new Texture(Gdx.files.internal("menu/LoadGameButton.png")),
                    SaveTexture = new Texture(Gdx.files.internal("menu/RecoverLastSave.png")),
                    configButtonTexture = new Texture(Gdx.files.internal("menu/SettingsButton.png")),
                    noTexture = new Texture(Gdx.files.internal("menu/NO.png")),
                    yesTexture = new Texture(Gdx.files.internal("menu/Yes.png"));

    private Rectangle newGameButton, loadGameButton, configButton, 
                        Title, SaveMenu, No, Yes;

    private MenuBrain mb = new MenuBrain();
    private boolean newGamePressed = false;
    
    public Menu (Curtain c, Viewport v) {
        Menu.c = c;
        this.v = v;
    }
    
    public void Setup(){
        
        float   h = this.v.getWorldHeight(),
                w = this.v.getWorldWidth();

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

        ScreenUtils.clear(0,0,0,1);

        float   h = this.v.getWorldHeight(),
                w = this.v.getWorldWidth();

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
            this.v.unproject(touchPosition);
            if(!this.newGamePressed){
                if (newGameButton.contains(touchPosition.x, touchPosition.y)) {
                    if(this.mb.saveExists()) this.newGamePressed = true;
                    else c.callScreen(new Loading(Menu.c, ProcessesToLoad.getInitializeGame(), this.v));
                }
                if (loadGameButton.contains(touchPosition.x, touchPosition.y)) c.callScreen(new Loading(Menu.c, ProcessesToLoad.getInitializeGame(), this.v));
                if (configButton.contains(touchPosition.x, touchPosition.y)) c.callScreen(new Settings(Menu.c, this.v));
            }
            else {
                if (Yes.contains(touchPosition.x, touchPosition.y)){
                    this.newGamePressed = false;
                    c.callScreen(new Loading(Menu.c, ProcessesToLoad.getInitializeGame(), this.v));
                }
                else if (No.contains(touchPosition.x, touchPosition.y)) {
                    this.newGamePressed = false;
                    this.mb.overwriteSaveFile();
                    c.callScreen(new Loading(Menu.c, ProcessesToLoad.getInitializeGame(), this.v));
                }
                else if (!SaveMenu.contains(touchPosition.x, touchPosition.y)) this.newGamePressed = false;
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
    public void resize(int width, int height) {
        v.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        this.v.getCamera().update();
        batch.setProjectionMatrix(this.v.getCamera().combined);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        this.dispose();
    }
}

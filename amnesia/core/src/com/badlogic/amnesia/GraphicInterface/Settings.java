package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Settings implements Screen {
    //------------------------------------------------------------------------------------
    private final Curtain curtain;

    private Viewport viewport;
    private Vector3 touchPosition = new Vector3();
    private OrthographicCamera Camera = new OrthographicCamera();

    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/pixelemulator.fnt"), Gdx.files.internal("font/pixelemulator.png"),false);

    private Texture backgroundImage = new Texture(Gdx.files.internal("settings/settingsBackground.png"));

    private Rectangle background;

    private SettingsBrain sb = new SettingsBrain();

    private boolean setted = false;
    //------------------------------------------------------------------------------------
    public Settings (Curtain game, Viewport v) {
        this.curtain = game;
    }
    //------------------------------------------------------------------------------------
    public void Setup(){

        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);
        
        float   h = this.viewport.getWorldHeight(),
                w = this.viewport.getWorldWidth();

        //x, y, width, height
        background = new Rectangle(0, h, w, h);
    }
    //------------------------------------------------------------------------------------
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        float   h = this.viewport.getWorldHeight(),
                w = this.viewport.getWorldWidth();

        this.Camera.update();
        //gameControll.batch.setProjectionMatrix(this.Camera.combined);

        //gameControll.batch.begin();

        //gameControll.batch.draw(this.backgroundImage, background.x, background.y, background.width, background.height);
        if (background.y > 0) background.y -= 9;
        else this.setted = true;

        if (setted) {
            //render menu
        }

        //gameControll.batch.end();

        if (Gdx.input.justTouched()) {
            this.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            this.viewport.unproject(touchPosition);

            if (background.contains(touchPosition.x, touchPosition.y)) {
            }
        }
    }

    @Override
    public void dispose() {
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

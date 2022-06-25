package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level implements Screen {
    //------------------------------------------------------------------------------------
    public SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    private Texture songsterImage;
    private Rectangle songster;
    //------------------------------------------------------------------------------------
    public Level(String configFileName) {
        batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 500);
        
        font = new BitmapFont();
        
        songsterImage = new Texture(Gdx.files.internal("songster.png"));

        songster = new Rectangle();
        songster.x = 200;
        songster.y = 200;
        songster.width = 32;
        songster.height = 32;
    }
    //------------------------------------------------------------------------------------
    public String toString () {
        return "Level";
    }
    //------------------------------------------------------------------------------------
    public Texture createAndDisplayTextureInScreen(String imgFileName, int[] pos) {
        batch.begin();
        Texture t = new Texture(Gdx.files.internal(imgFileName));
        batch.draw(t, pos[0], pos[1]);
        batch.end();
        return t;
    }
    //------------------------------------------------------------------------------------
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(songsterImage, songster.x, songster.y);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            songster.x += 32;
        }

        if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            songster.x -= 32;
        }
    }
    //------------------------------------------------------------------------------------
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
    //------------------------------------------------------------------------------------
    @Override
    public void show() {}
    
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}

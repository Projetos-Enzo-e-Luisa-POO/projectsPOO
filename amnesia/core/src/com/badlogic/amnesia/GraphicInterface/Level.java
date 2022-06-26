package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Screen;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level implements Screen {
    
    private BitmapFont font;
    private Viewport viewport;
    public SpriteBatch batch;
    private OrthographicCamera Camera;

    public Songster songster;
    
    public Level() {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.Camera = new OrthographicCamera();
    }
    
    public Texture createAndDisplayTextureInScreen(String imgFileName, int[] pos) {
        System.out.println("cheguei aquii");
        System.out.println("data received: " + imgFileName + " | " + pos[0] + " " + pos[1]);
        this.batch.begin();
        Texture t = new Texture(Gdx.files.internal(imgFileName));
        this.batch.draw(t, pos[0], pos[1]);
        this.batch.end();
        return t;
    }

    private void Setup() {
        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);
    }
    
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        this.Camera.update();
        batch.setProjectionMatrix(this.Camera.combined);

        batch.begin();
        //batch.draw(songsterImage, songster.x, songster.y);
        batch.end();
    }
    
    public void dispose() {
        batch.dispose();
        font.dispose();
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
    public void hide() {}
}

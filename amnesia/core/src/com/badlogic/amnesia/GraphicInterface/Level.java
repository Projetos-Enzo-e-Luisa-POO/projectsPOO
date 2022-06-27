package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Screen;
import com.badlogic.amnesia.Model.MPControl;
import com.badlogic.amnesia.Model.ControlInterfaces.RenderAccess;
import com.badlogic.amnesia.Model.ControlInterfaces.RenderStrategy;
import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Level implements Screen {

    private static Curtain c;
    private static RenderAccess room;
    private static MPControl commandFacade;

    private Viewport v;
    private Vector3 touchPosition = new Vector3();
    private BitmapFont font = new BitmapFont();
    public SpriteBatch batch = new SpriteBatch();

    private Texture pauseMenuBackground = new Texture(Gdx.files.internal("pauseMenu/pauseMenuBackground.png")),
                    resume = new Texture(Gdx.files.internal("pauseMenu/resumeButton.png")),
                    quit = new Texture(Gdx.files.internal("pauseMenu/quitButton.png"));

    private Rectangle pauseMenu, resumeButton, quitButton;

    private boolean isGamePaused = false;
    
    //----------------------------------------------------------------------------------------------------------------

    public Level(Curtain c, RenderAccess room, Viewport v, MPControl mpc) {
        Level.c = c;
        Level.room = room;
        this.v = v;
        Level.commandFacade = mpc;
    }

    private int getImageSize() {
        int width = (int) this.v.getWorldWidth() / Level.room.size()[0];
        int height = (int) this.v.getWorldHeight() / Level.room.size()[1];
        return (width < height) ? width : height;
    }

    private void Setup() {
        ScreenUtils.clear(0,0,0,1);

        batch.setProjectionMatrix(this.v.getCamera().combined);

        batch.begin();

        for (RenderStrategy[] collumn : Level.room.getCells()) {
            for (RenderStrategy c : collumn) {
                c.render(this.batch, this.getImageSize());
            }
        }

        for (RenderStrategy[] collumn : Level.room.getInteractables()) {
            for (RenderStrategy c : collumn) {
                if (c != null) {
                    c.render(this.batch, this.getImageSize());
                }
            }
        }

        batch.end();

        float h = this.v.getWorldHeight(),
                w = this.v.getWorldWidth();

        pauseMenu = new Rectangle(w/2 - 225, h/2 - 300, 450, 600);
        resumeButton = new Rectangle(w/2 - 130, h/2 - 51, 260, 102);
        quitButton = new Rectangle(w/2 - 130, h/2 - 202, 260, 102);
    }

    @Override
    public void render(float delta) {

        batch.begin();
        
        if (this.isGamePaused) {
            batch.draw(pauseMenuBackground, pauseMenu.x, pauseMenu.y, pauseMenu.width, pauseMenu.height);
            batch.draw(resume, resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height);
            batch.draw(quit, quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        }

        batch.end();
        
        if (Gdx.input.isKeyJustPressed(BindDepot.getInstance().getKeyValueOf("pause"))) {
            this.isGamePaused = !this.isGamePaused;
        }

        if (this.isGamePaused){
            if (Gdx.input.justTouched()) {
                this.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                this.v.unproject(touchPosition);
                if (this.isGamePaused) {
                    if (quitButton.contains(touchPosition.x, touchPosition.y)) {
                        Level.c.callScreen(new Menu(Level.c, this.v));
                    }
                    else if (resumeButton.contains(touchPosition.x, touchPosition.y) ||
                                !pauseMenu.contains(touchPosition.x, touchPosition.y)) {
                        this.isGamePaused = false;
                    }
                }
            }
        }
        else {

        }
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        pauseMenuBackground.dispose();
        resume.dispose();
        quit.dispose();
    }

    public void show() {
        v.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        this.v.getCamera().update();
        batch.setProjectionMatrix(this.v.getCamera().combined);

        this.Setup();
    }

    public void hide() {
        this.dispose();
    }

    public void resize(int width, int height) {
        this.show();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

}

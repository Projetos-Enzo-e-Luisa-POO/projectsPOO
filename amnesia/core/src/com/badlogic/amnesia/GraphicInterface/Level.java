package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Screen;
import com.badlogic.amnesia.Model.Room;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.amnesia.Model.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level implements Screen {
    
    private boolean isGamePaused = false;

    private static Curtain curtain;
    private static Room room;
    public Songster songster;

    private Viewport viewport;
    private Vector3 touchPosition = new Vector3();
    private BitmapFont font = new BitmapFont();
    public SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera Camera = new OrthographicCamera();

    private Texture pauseMenuBackground = new Texture(Gdx.files.internal("pauseMenu/pauseMenuBackground.png")),
                    resume = new Texture(Gdx.files.internal("pauseMenu/resumeButton.png")),
                    quit = new Texture(Gdx.files.internal("pauseMenu/quitButton.png"));

    private Rectangle pauseMenu, resumeButton, quitButton;
    
    //----------------------------------------------------------------------------------------------------------------

    public Level(Curtain curtain, Room room) {
        Level.curtain = curtain;
        Level.room = room;
        this.Setup();
    }

    private int getImageSize() {
        int width = (int) this.viewport.getWorldWidth() / Level.room.getSpace()[0].length;
        int height = (int) this.viewport.getWorldHeight() / Level.room.getSpace().length;
        return (width < height) ? width : height;
    }

    private void Setup() {
        ScreenUtils.clear(0,0,0,1);

        this.Camera.position.set(0,0,0);
        this.Camera.update();

        this.viewport = new FitViewport(1280, 720, this.Camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        float h = this.viewport.getWorldHeight(),
              w = this.viewport.getWorldWidth();

        batch.setProjectionMatrix(this.Camera.combined);

        batch.begin();

        IDTrans t = new IDTrans();

        Cell[][] cells = Level.room.getSpace();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length - 1; j++) {
                for (String img : cells[i][j].getImgs()) {
                    int[] cellPosition = t.IDToPos(cells[i][j].getID());
                    this.batch.draw(new Texture(
                                        Gdx.files.internal(img)),
                                        cellPosition[1] * this.getImageSize(),
                                        cellPosition[0] * this.getImageSize(),
                                        this.getImageSize(),
                                        this.getImageSize()
                                    );
                }
            }
        }

        batch.end();

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

        if (Gdx.input.justTouched()) {
            this.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            this.viewport.unproject(touchPosition);
            if (this.isGamePaused) {
                if (quitButton.contains(touchPosition.x, touchPosition.y)) {
                    Level.curtain.changeToScreen(Level.curtain.getMenuScreen());
                }
                else if (resumeButton.contains(touchPosition.x, touchPosition.y) ||
                            !pauseMenu.contains(touchPosition.x, touchPosition.y)) {
                    this.pause();
                }
            }
        }
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        pauseMenuBackground.dispose();
        resume.dispose();
        quit.dispose();
    }

    public void show() {}

    public void hide() {}

    public void pause() {
        this.isGamePaused = true;
    }

    public void resume() {}

    public void resize(int width, int height) {}

}

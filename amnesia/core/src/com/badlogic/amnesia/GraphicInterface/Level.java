package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Screen;
import com.badlogic.amnesia.Model.Room;
import com.badlogic.amnesia.Model.Toolkit.IDTrans;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.amnesia.Model.Elements.CompondViewElement.Cell;
import com.badlogic.amnesia.Model.Elements.Movable.MovableViewElement.Songster;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level implements Screen {
    
    private static Room room;
    public Songster songster;

    private Viewport viewport;
    private BitmapFont font = new BitmapFont();
    public SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera Camera = new OrthographicCamera();
    
    public Level(Room room) {
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
    }

    public void render(float delta) {
        System.out.println("Renderizei a sala");
    }
    
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void show() {}
    
    public void resize(int width, int height) {}

    public void pause() {}

    public void resume() {}

    public void hide() {}

}

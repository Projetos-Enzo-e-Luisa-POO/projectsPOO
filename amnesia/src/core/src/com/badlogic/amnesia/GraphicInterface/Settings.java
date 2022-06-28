package com.badlogic.amnesia.GraphicInterface;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Settings extends WidgetGroup implements Screen {

    private static Curtain curtain;
    private static SettingsBrain sb = new SettingsBrain();

    private Viewport v;
    private SpriteBatch batch = new SpriteBatch();

    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/pixelemulator.fnt"), Gdx.files.internal("font/pixelemulator.png"),false);

    private Texture backgroundImage = new Texture(Gdx.files.internal("settings/settingsBackground.png"));

    private ArrayList<TextField> textFields = new ArrayList<TextField>();
    
    private TextField.TextFieldStyle style = new TextField.TextFieldStyle();

    private String[] binds = Settings.sb.getCommandValues();    

    private Stage stage;
    
    public Settings (Curtain curtain, Viewport v) {
        Settings.curtain = curtain;
        
        this.v = v;
        this.stage = new Stage(v);

        this.style.font = this.font;
        this.style.fontColor = Color.WHITE;
    }
    
    @Override
    public void render(float delta) {
        batch.begin();

        float width = this.v.getWorldWidth();
        float height = this.v.getWorldHeight() / 4;

        batch.draw(this.backgroundImage, 0, 0);

        font.draw(this.batch, "Bind", width / 4 - width / 5, this.v.getWorldHeight() - height/ 4);
        font.draw(this.batch, "Actual Value", width / 2 + width / 20 - width / 5, this.v.getWorldHeight() - height/ 4);
        font.draw(this.batch, "New Value", width / 2 + width / 5, this.v.getWorldHeight() - height/ 4);

        for (int i = 0; i < binds.length; i++) {
            font.draw(this.batch, this.binds[i], width / 4 - width / 5, ((2 + i) * height) / 4);

            font.draw(this.batch, Input.Keys.toString(Settings.sb.getKeyValueOf(binds[i])), width / 2 + width / 10 - width / 5, ((2 + i) * height) / 4);

            TextField tf = new TextField("", this.style);
            tf.setX((3 * width) / 4);
            tf.setY(((2 + i) * height) / 4 - height / 7);
            tf.setWidth(width / 22);
            tf.setColor(Color.WHITE);

            this.textFields.add(tf);
            stage.addActor(tf);

            font.draw(this.batch, "-", (3 * width) / 4, ((2 + i) * height) / 4 - height / 10);
        }

        font.draw(this.batch, ">>> Press ESCAPE to save and back to menu <<<", width / 16, 35);

        stage.draw();
        stage.act();

        batch.end();

        Gdx.input.setInputProcessor(stage);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Settings.curtain.callScreen(new Menu(Settings.curtain, this.v));
            Settings.sb.saveBindsFromFields(this.textFields);
        }
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
        batch.dispose();
        font.dispose();
    }
    
    @Override
    public void show() {
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

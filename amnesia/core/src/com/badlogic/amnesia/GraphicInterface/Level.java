package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.gdx.Screen;
import com.badlogic.amnesia.Model.MPControl;
import com.badlogic.amnesia.Model.ControlInterfaces.RenderAccess;
import com.badlogic.amnesia.Model.ControlInterfaces.RenderStrategy;
import com.badlogic.amnesia.Services.BindManagment.BindDepot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private static BindDepot bd = BindDepot.getInstance();
    private static MPControl commandFacade;

    private Viewport v;
    private Vector3 touchPosition = new Vector3();
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font/pixelemulator.fnt"), Gdx.files.internal("font/pixelemulator.png"),false);
    public SpriteBatch batch = new SpriteBatch();

    private Texture arrowBackground = new Texture(Gdx.files.internal("interactableMenu/arrow.png")),
                    interactableMenuBackground = new Texture(Gdx.files.internal("interactableMenu/interactableMenuBackground.png")),
                    pauseMenuBackground = new Texture(Gdx.files.internal("pauseMenu/pauseMenuBackground.png")),
                    resume = new Texture(Gdx.files.internal("pauseMenu/resumeButton.png")),
                    quit = new Texture(Gdx.files.internal("pauseMenu/quitButton.png"));

    private Rectangle interactableMenu, arrow, pauseMenu, resumeButton, quitButton;

    private String[] interactableMenuOptions;
    private int interactableIndexSelected = 0;

    private boolean renderPauseMenu = false,
                    renderInteractMenu = false;
    private StoryTeller st;
    
    //----------------------------------------------------------------------------------------------------------------

    public Level(Curtain c, RenderAccess room, Viewport v, MPControl mpc, StoryTeller st) {
        Level.c = c;
        Level.room = room;
        this.v = v;
        Level.commandFacade = mpc;
        this.st = st;
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

        Level.commandFacade.renderSongster(this.batch, this.getImageSize());

        Level.commandFacade.renderInventory(batch, this.getImageSize());

        if (this.st.checkGame()){
            font.draw(batch, "You Won!", (33 * this.v.getWorldWidth()) / 50, (3 * this.v.getWorldHeight()) / 4);
        }

        batch.end();

        float h = this.v.getWorldHeight(),
                w = this.v.getWorldWidth();

        interactableMenu = new Rectangle((3 * w)/4 - 230, h/2 - 300, 450, 600);
        arrow = new Rectangle((3 * this.v.getWorldWidth()) / 5, (3 * this.v.getWorldHeight()) / 4 - this.v.getWorldHeight() / 5 - 30, 32, 32);

        pauseMenu = new Rectangle(w/2 - 225, h/2 - 300, 450, 600);
        resumeButton = new Rectangle(w/2 - 130, h/2 - 51, 260, 102);
        quitButton = new Rectangle(w/2 - 130, h/2 - 202, 260, 102);
    }

    private boolean isInputKeyForCommand (String command) {
        return Gdx.input.isKeyJustPressed(Level.bd.getKeyValueOf(command));
    }

    private void handleUpSelect() {
        if (this.interactableIndexSelected != 0) {
            --this.interactableIndexSelected;
            this.arrow.y += this.v.getWorldWidth() / 10;
        }
    }

    private void handleDownSelect() {
        if (this.interactableIndexSelected != this.interactableMenuOptions.length - 1 && this.arrow.y - this.v.getWorldWidth() / 10 > 0) {
            ++this.interactableIndexSelected;
            this.arrow.y -= this.v.getWorldWidth() / 10;
        }
    }

    private void interactMenuRender() {
        this.renderInteractMenu = true;
        this.Setup();
    }

    @Override
    public void render(float delta) {

        batch.begin();

        if (this.renderPauseMenu) {

            batch.draw(pauseMenuBackground, pauseMenu.x, pauseMenu.y, pauseMenu.width, pauseMenu.height);
            batch.draw(resume, resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height);
            batch.draw(quit, quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        }

        if (this.renderInteractMenu) {
            batch.draw(interactableMenuBackground, interactableMenu.x, interactableMenu.y, interactableMenu.width, interactableMenu.height);
            font.draw(batch, "Select an", (33 * this.v.getWorldWidth()) / 50, (3 * this.v.getWorldHeight()) / 4);
            font.draw(batch, "interaction", (32 * this.v.getWorldWidth()) / 50, (3 * this.v.getWorldHeight()) / 4 - this.v.getWorldHeight() / 20);
            
            float optionHeight = (3 * this.v.getWorldHeight()) / 4 - this.v.getWorldHeight() / 5;
            for (String option : this.interactableMenuOptions) {
                if (option != null){
                    font.draw(batch, option, (33 * this.v.getWorldWidth()) / 50, optionHeight);
                    optionHeight -= this.v.getWorldWidth() / 10;
                }
            }

            batch.draw(arrowBackground, arrow.x, arrow.y, arrow.width, arrow.height);
        }

        batch.end();

        if (this.st.checkGame()){
            this.Setup();
        }

        if(!this.renderPauseMenu && !this.renderInteractMenu){
            
            if (isInputKeyForCommand("pause")) {
                this.renderPauseMenu = !this.renderPauseMenu;
                this.Setup();
            }
            
            if (isInputKeyForCommand("free hand")) {
                Level.commandFacade.changeActiveSlot(-1);
                this.Setup();
            }

            if (isInputKeyForCommand("move up")) {
                Level.commandFacade.move(0);
                this.Setup();
            }

            if (isInputKeyForCommand("move left")){
                Level.commandFacade.move(3);
                this.Setup();
            }

            if (isInputKeyForCommand("move down")){
                Level.commandFacade.move(2);
                this.Setup();
            }

            if (isInputKeyForCommand("move right")){
                Level.commandFacade.move(1);
                this.Setup();
            }

            if (isInputKeyForCommand("first slot")){
                Level.commandFacade.changeActiveSlot(0);
                this.Setup();
            }

            if (isInputKeyForCommand("second slot")){
                Level.commandFacade.changeActiveSlot(1);
                this.Setup();
            }

            if (isInputKeyForCommand("select")){
                if (Level.commandFacade.getPossibleInteractable() != null){
                    this.interactableMenuOptions = Level.commandFacade.interact();
                    this.interactMenuRender();
                }
            }

            if (isInputKeyForCommand("quick interact")){
                this.interactableMenuOptions = Level.commandFacade.quickInteract();
                this.interactMenuRender();
            }
        }
        else if (this.renderPauseMenu){
            if (Gdx.input.justTouched()) {
                this.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                this.v.unproject(touchPosition);
                if (quitButton.contains(touchPosition.x, touchPosition.y)) {
                    Level.c.callScreen(new Menu(Level.c, this.v));
                }
                else if (resumeButton.contains(touchPosition.x, touchPosition.y) ||
                            !pauseMenu.contains(touchPosition.x, touchPosition.y)) {
                    this.renderPauseMenu = false;
                    this.Setup();
                }
            }
        }
        else if (this.renderInteractMenu) {
            if (isInputKeyForCommand("up select")){
                this.handleUpSelect();
            }

            if (isInputKeyForCommand("down select")) {
                this.handleDownSelect();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                this.renderInteractMenu = false;
                Level.commandFacade.executeInteraction(this.interactableMenuOptions[this.interactableIndexSelected]);
                this.Setup();
            }
            if (isInputKeyForCommand("pause")) {
                this.renderInteractMenu = !this.renderInteractMenu;
                this.Setup();
            }
        }

    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        pauseMenuBackground.dispose();
        interactableMenuBackground.dispose();
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

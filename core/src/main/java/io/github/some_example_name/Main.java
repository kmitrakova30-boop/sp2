package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import io.github.some_example_name.screens.ScreenGame;
import io.github.some_example_name.screens.ScreenMenu;
import io.github.some_example_name.screens.ScreenRestart;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public static final int SCR_WIDTH = 1280, SCR_HEIGHT = 720;

    public ScreenGame screenGame;
    public ScreenRestart screenRestart;
    public ScreenMenu screenMenu;



    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        screenGame = new ScreenGame(this);
        screenRestart = new ScreenRestart(this);
        screenMenu = new ScreenMenu(this);
        setScreen(screenGame);
        setScreen(screenMenu);
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}

package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.TextButton;


public class ScreenRestart implements Screen {
    Main main;

    MovingBackground background;
    TextButton buttonRestart;
    TextButton buttonMenu;
    PointCounter pointCounter;
    ScreenMenu screenMenu;

    int gamePoints;

    public ScreenRestart(Main main) {
        this.main = main;

        pointCounter = new PointCounter(750, 530);
        buttonRestart = new TextButton(100, 400, "Restart");
        buttonMenu = new TextButton(100, 150, "Menu");
        background = new MovingBackground("backgrounds/restart_bg.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            int tx = Gdx.input.getX();
            int ty = Main.SCR_HEIGHT - Gdx.input.getY();

            if (buttonRestart.isHit(tx, ty)) {
                main.setScreen(main.screenGame);
            }
            if (buttonMenu.isHit(tx, ty)) {
                main.setScreen(main.screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        background.draw(main.batch);
        buttonRestart.draw(main.batch);
        buttonMenu.draw(main.batch);
        pointCounter.draw(main.batch, gamePoints);

        main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        buttonRestart.dispose();
        buttonMenu.dispose();

    }
}

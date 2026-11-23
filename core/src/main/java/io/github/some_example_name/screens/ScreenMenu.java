package io.github.some_example_name.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.characters.Tube;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.TextButton;

public class ScreenMenu implements Screen {

    Main main;

    MovingBackground background;
    TextButton buttonStart;
    TextButton buttonExit;

    public ScreenMenu(Main myGdxGame) {
        this.main = myGdxGame;

        buttonStart = new TextButton(100, 400, "Start");
        buttonExit = new TextButton(700, 400, "Exit");
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

            if (buttonStart.isHit(tx, ty)) {
                main.setScreen(main.screenGame);
            }
            if (buttonExit.isHit(tx, ty)) {
                Gdx.app.exit();
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        background.draw(main.batch);
        buttonStart.draw(main.batch);
        buttonExit.draw(main.batch);

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
        buttonExit.dispose();
        buttonStart.dispose();
    }
}

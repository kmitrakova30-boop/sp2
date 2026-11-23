package io.github.some_example_name.screens;

import static io.github.some_example_name.Main.SCR_HEIGHT;
import static io.github.some_example_name.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.characters.Tube;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.TextButton;



public class ScreenGame implements Screen {

    Main main;

    Bird bird;
    PointCounter pointCounter;
    Sound cawSound;
    Sound fallSound;

    MovingBackground movingBackground;
    ScreenRestart screenRestart;
    TextButton textButton;



    int tubeCount = 3;
    Tube[] tubes;

    boolean isGameOver;
    int gamePoints;
    float gameTime;
    MovingBackground background;
    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    public ScreenGame(Main main) {
        this.main = main;

        initTubes();
        background = new MovingBackground("backgrounds/game_bg.png");
        bird = new Bird(20, SCR_HEIGHT / 2, 10, 170, 150);
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);

    }



    @Override
    public void show() {
        gamePoints = 0;
        gameTime = 0f;
        isGameOver = false;
        bird.setY(SCR_HEIGHT / 2);
        initTubes();
    }

    @Override
    public void render(float delta) {
        if (isGameOver) {
            main.screenRestart.gamePoints = gamePoints;
            main.setScreen(main.screenRestart);
            return;
        }
        gameTime += delta;

        if (Gdx.input.justTouched()) {
            bird.onClick();
        }


        background.move();
        bird.fly();
        if (!bird.isInField()) {
            System.out.println("not in field");
            bird.playFallSound();
            isGameOver = true;
        }

        for (Tube tube : tubes) {
            tube.move();
            tube.moveUpAndDown(gameTime);
            if (tube.isHit(bird)) {
                System.out.println("hit");
                bird.playFallSound();
                isGameOver = true;
            } else if (tube.needAddPoint(bird)) {
                gamePoints += 1;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        background.draw(main.batch);
        bird.draw(main.batch);
        for (Tube tube : tubes) tube.draw(main.batch);
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
        bird.dispose();
        background.dispose();
        pointCounter.dispose();
        cawSound.dispose();
        fallSound.dispose();
        for (int i = 0; i < tubeCount; i++) {
            tubes[i].dispose();
        }
    }

    void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }

}

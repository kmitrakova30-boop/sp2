package io.github.some_example_name.characters;

import static io.github.some_example_name.Main.SCR_HEIGHT;
import static io.github.some_example_name.Main.SCR_WIDTH;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {

    Texture textureUpperTube;
    Texture textureDownTube;

    Random random;

    int x, gapY;
    int distanceBetweenTubes;



    int speed = 10;
    final int width = 200;
    final int height = 700;
    int gapHeight = 400;
    int padding = 100;
    boolean isPointReceived = false;
    float moveDelay = 10f;
    float moveAmplitude = 60f;
    float moveSpeed = 3f;

    int startGapY;



    public void setPointReceived() {
        isPointReceived = true;
    }

    public Tube(int tubeCount, int tubeIdx) {
        random = new Random();

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
        startGapY = gapY;
    }
    public boolean needAddPoint(Bird bird) {
        return bird.x > x + width && !isPointReceived;
    }

    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }

    public void moveUpAndDown(float gameTime) {
        if (gameTime < moveDelay) {
            return;
        }

        float offset = moveAmplitude
            * (float) Math.sin((gameTime - moveDelay) * moveSpeed);

        gapY = (int) (startGapY + offset);
    }

    public boolean isHit(Bird bird) {

        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;

        return false;
    }
    public void move() {
        x -= speed;

        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;

            gapY = gapHeight / 2 + padding
                + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));

            startGapY = gapY;

        }
    }

    public void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }
}

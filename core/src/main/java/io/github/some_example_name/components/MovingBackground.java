package io.github.some_example_name.components;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import io.github.some_example_name.Main;

public class MovingBackground {
    int x,x2;
    int speed = 3;
    Texture texture;

    public MovingBackground(String pathToTexture) {
        texture = new Texture(pathToTexture);
        x = 0;
        x2 = Main.SCR_WIDTH;
    }


    public void move() {
        x -= speed;
        x2 -= speed;

        if (x <= -Main.SCR_WIDTH) {
            x = Main.SCR_WIDTH;
        }
        if (x2 <= -Main.SCR_WIDTH) {
            x2= Main.SCR_WIDTH;
        }
    }
    public void draw(Batch batch) {
        batch.draw(texture, x, 0, Main.SCR_WIDTH + 2, Main.SCR_HEIGHT);
        batch.draw(texture, x2, 0, Main.SCR_WIDTH + 2, Main.SCR_HEIGHT);
    }
    public void dispose() {
        texture.dispose();
    }
}

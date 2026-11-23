package io.github.some_example_name.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import static io.github.some_example_name.Main.SCR_HEIGHT;
import static io.github.some_example_name.Main.SCR_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;




public class Bird {
    int x, y;
    int width, height;

    int speed;
    int jumpHeight;
    boolean jump = true;
    Sound cawSound;
    Sound fallSound;

    int frameCounter;
    Texture[] framesArray;

    public Bird(int x, int y, int speed, int width, int height) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.width = width;
            this.height = height;
            frameCounter = 0;

            framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png"),

            };
        cawSound = Gdx.audio.newSound(Gdx.files.internal("sounds/crow-croaks.mp3"));
        fallSound = Gdx.audio.newSound(Gdx.files.internal("sounds/caw_fall.mp3"));
        }

    public void setY(int y) {
        this.y = y;
    }

    public void onClick() {
        jump = true;
        jumpHeight = 100 + y;
        cawSound.play(0.7f);
    }
    public void playFallSound() {
        fallSound.play(1.0f);
    }

        public void fly() {
            if (y >= jumpHeight) {
                jump = false;
            }

            if (jump) {
                y += speed;
            } else {
                y -= speed;
            }
        }

        public boolean isInField() {
            if (y + height < 0) return false;
            if (y > SCR_HEIGHT) return false;
            return true;
        }

        public void draw(Batch batch) {
            int frameMultiplier = 10;
            batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
            if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
        }

        public void dispose() {
            for (Texture texture : framesArray) {
                texture.dispose();
            }
        }

    }

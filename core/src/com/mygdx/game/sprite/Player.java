package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.PlatformerGame;

public class Player {
    public static final int MOVEMENT_ON_X = 120;
    public static final int RESISTANSE = -10;
    public static final int GRAVITY = -10;

    private Vector2 velocity;
    private Vector2 position;
    private Texture image;
    private Rectangle bounds;
    private boolean stayOnObject;
    private boolean jumping;

    public Player() {
        image = new Texture("player.png");
        position = new Vector2(0, 50);
        velocity = new Vector2(0, 0);
        bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        stayOnObject = false;
        jumping = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isStayOnObject() {
        return stayOnObject;
    }

    public void setStayOnObject(boolean stayOnObject) {
        this.stayOnObject = stayOnObject;
        if (stayOnObject) {
            resetVelocity();
            setJumping(false);
        }
    }

    public void jump() {
        System.out.println("jumping = " + jumping + ", stayOnObject = " + stayOnObject);
        if (!isJumping()) {
            velocity.y = 450;
        }
        setJumping(true);
    }

    public void right() {
        System.out.println("right");
        velocity.x = MOVEMENT_ON_X;
    }

    public void left() {
        System.out.println("left");
        velocity.x = -MOVEMENT_ON_X;
    }

    private void resetVelocity() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public void update(float delta) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY);
        }

        if (velocity.x != 0) {
            position.add(velocity.x * delta, 0);
        }
        if (!stayOnObject || velocity.y > 0) {
            position.add(0, velocity.y * delta);
        }

        if (position.y < 0) {
            position.y = 0;
            resetVelocity();
        }
        if (position.x < 0) {
            position.x = 0;
            resetVelocity();
        }
        if (position.y > PlatformerGame.HEIGHT - image.getHeight()) {
            position.y = PlatformerGame.HEIGHT - image.getHeight();
            resetVelocity();
        }
        if (position.x > PlatformerGame.WIDTH - image.getWidth()) {
            position.x = PlatformerGame.WIDTH - image.getWidth();
            resetVelocity();
        }
        bounds.setPosition(position);
    }

    public void dispose() {
        image.dispose();
    }
}

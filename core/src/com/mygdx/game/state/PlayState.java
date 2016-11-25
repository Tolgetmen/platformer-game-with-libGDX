package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.PlatformerGame;
import com.mygdx.game.sprite.Ground;
import com.mygdx.game.sprite.Player;
import com.mygdx.game.sprite.SkyPlatform;

import java.util.HashSet;
import java.util.Set;

import static com.badlogic.gdx.Input.*;

public class PlayState extends AbstractState {
    private Texture backgroundImg;
    private BitmapFont font;
    private int counter;
    private final Ground ground;
    private Set<SkyPlatform> platforms;
    private Player player;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        backgroundImg = new Texture("sky.png");
        counter = 0;
        font = new BitmapFont();
        ground = new Ground();
        platforms = new HashSet<SkyPlatform>();
        platforms.add(new SkyPlatform(new Vector2(0, 300)));
        platforms.add(new SkyPlatform(new Vector2(PlatformerGame.WIDTH / 2 + 30, 150)));
        player = new Player();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
            player.left();
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
            player.right();
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
            player.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
        player.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        font.draw(batch, "Catched: " + counter, 10, PlatformerGame.HEIGHT - 10);
        batch.draw(ground.getImage(), ground.getPosition().x, ground.getPosition().y);
        //TODO loop for ground
        batch.draw(ground.getImage(), ground.getPosition().x + ground.getImage().getWidth(), ground.getPosition().y);
        for (SkyPlatform platform : platforms) {
            batch.draw(platform.getImage(), platform.getPosition().x, platform.getPosition().y);
        }
        batch.draw(player.getImage(), player.getPosition().x, player.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        font.dispose();
        ground.dispose();
        for (SkyPlatform platform : platforms) {
            platform.dispose();
        }
        player.dispose();

    }
}

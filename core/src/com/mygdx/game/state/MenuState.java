package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlatformerGame;

public class MenuState extends AbstractState {
    private Texture backgroundImg;
    private Texture btnPlay;
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private String greeting;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        backgroundImg = new Texture("sky.png");
        btnPlay = new Texture("playbtn.png");
        font = new BitmapFont(Gdx.files.internal("YellowPurpleFont.fnt"));
        glyphLayout = new GlyphLayout();
        greeting = "Touch for play";
        glyphLayout.setText(font, greeting);
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        font.draw(batch, glyphLayout,
                PlatformerGame.WIDTH / 2 - glyphLayout.width / 2,
                PlatformerGame.HEIGHT / 2 + btnPlay.getHeight() / 2 + 50);
        batch.draw(btnPlay,
                PlatformerGame.WIDTH / 2 - btnPlay.getWidth() / 2,
                PlatformerGame.HEIGHT / 2 - btnPlay.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        btnPlay.dispose();
        font.dispose();
    }
}

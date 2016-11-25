package com.mygdx.game.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private final Stack<AbstractState> states;

    public GameStateManager() {
        this.states = new Stack<AbstractState>();
    }

    public void push(AbstractState state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(AbstractState state) {
        this.pop();
        this.push(state);
    }

    public void update(float delta) {
        states.peek().update(delta);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }
}

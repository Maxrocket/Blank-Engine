package blankengine;

import blankengine.input.KeyManager;
import blankengine.input.MouseManager;
import blankengine.states.State;

public class Handler {

    private Game game;
    private int windowX, windowY;
    private int currentFPS;
    private State currentState;

    public Handler(Game game) {
        this.game = game;
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getWindowX() {
        return windowX;
    }

    public void setWindowX(int windowX) {
        this.windowX = windowX;
    }

    public int getWindowY() {
        return windowY;
    }

    public void setWindowY(int windowY) {
        this.windowY = windowY;
    }

    public int getCurrentFPS() {
        return currentFPS;
    }

    public void setCurrentFPS(int currentFPS) {
        this.currentFPS = currentFPS;
    }

    public State getCurrentState() {
        return currentState;
    }
    
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}

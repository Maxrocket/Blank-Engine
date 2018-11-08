package blankengine.states;

import java.awt.Graphics;
import blankengine.Handler;
import blankengine.ui.UIManager;

public class DefaultState extends State {

    private UIManager uiManager;

    public DefaultState(final Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }

    public void tick() {
        uiManager.tick();
    }

    public void render(Graphics g) {
        uiManager.render(g);
    }

    public void init() {
        handler.getMouseManager().setUIManager(uiManager);
    }
}

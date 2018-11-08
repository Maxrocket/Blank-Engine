package blankengine.states;

import java.awt.Graphics;
import blankengine.Handler;

public abstract class State {
    
    public static void setState(State state, Handler handler){
        handler.setCurrentState(state);
        handler.getCurrentState().init();
    }
    
    //Class
    
    protected Handler handler;
    
    public State(Handler handler){
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract void init();
}

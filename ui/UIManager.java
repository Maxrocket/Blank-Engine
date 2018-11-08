package blankengine.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import blankengine.Handler;

public class UIManager {
    
    private Handler handler;
    private ArrayList<UIObject> objects, newObjects;
    
    public UIManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<UIObject>();
        newObjects = new ArrayList<UIObject>();
    }
    
    public void tick(){
        objects = newObjects;
        for (UIObject o : objects) {
            o.tick();
        }
    }
    
    public void render(Graphics g){
        for (UIObject o : objects) {
            o.render(g);
        }
    }
    
    public void onMouseMove(MouseEvent me){
        for (UIObject o : objects) {
            o.onMouseMove(me);
        }
    }
    
    public void onMouseRelease(MouseEvent me){
        for (UIObject o : objects) {
            o.onMouseRelease(me);
        }
    }
    
    public void addUIObject(UIObject object){
        newObjects.add(object);
    }
    
    public void removeUIObject(UIObject object){
        newObjects.remove(object);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.newObjects = objects;
    }
    
}

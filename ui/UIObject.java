package blankengine.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {
    
    protected float x, y, offsetx, offsety;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering;
    
    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        offsetx = 0;
        offsety = 0;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract void onClick();
    
    public abstract void onRClick();
    
    public void onMouseMove(MouseEvent me){
        if (bounds.contains(me.getPoint())){
            hovering = true;
        } else {
            hovering = false;
        }
    }
    
    public void onMouseRelease(MouseEvent me){
        if (me.getButton() == MouseEvent.BUTTON1){
            if (hovering){
                onClick();
            }
        }
        if (me.getButton() == MouseEvent.BUTTON3){
            if (hovering){
                onRClick();
            }
        }
    }
    
    //getters and setters
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }
    
    public void setOffsetX(float offsetx) {
        this.offsetx = offsetx;
        bounds = new Rectangle((int) (x + offsetx), (int) (y + offsety), width, height);
    }
    
    public void setOffsetY(float offsety) {
        this.offsety = offsety;
        bounds = new Rectangle((int) (x + offsetx), (int) (y + offsety), width, height);
    }
    
}

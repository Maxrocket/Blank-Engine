package blankengine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

    private boolean[] keys, justPressed, cantPress;
    
    public KeyManager (){
        keys = new boolean[1000];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }
    
    public void tick(){
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if (justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (keys[i] && !cantPress[i]){
                justPressed[i] = true;
            }
        }
    }
    
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
            return;
        }
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
            return;
        }
        keys[e.getKeyCode()] = false;
    }
    
    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length){
            return false;
        }
        return justPressed[keyCode];
    }
    
}

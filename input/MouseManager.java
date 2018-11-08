package blankengine.input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import blankengine.Handler;
import blankengine.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean leftPressed, rightPressed, middlePressed, leftJustPressed, rightJustPressed, leftCantPressed, rightCantPressed;
    private int mouseX, mouseY;
    private int wheelDistance;
    private UIManager uiManager;
    private Handler handler;
    private Rectangle window;

    public MouseManager(Handler handler) {
        wheelDistance = 0;
        this.handler = handler;
        window = new Rectangle(handler.getWindowX(), handler.getWindowY(), handler.getWidth(), handler.getHeight());
    }

    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public boolean isMiddlePressed() {
        return middlePressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void mouseClicked(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {

        }
    }

    public void mousePressed(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {
            if (me.getButton() == MouseEvent.BUTTON1) {
                leftPressed = true;
            }
            if (me.getButton() == MouseEvent.BUTTON2) {
                middlePressed = true;
            }
            if (me.getButton() == MouseEvent.BUTTON3) {
                rightPressed = true;
            }
        }
    }

    public void mouseReleased(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {
            if (me.getButton() == MouseEvent.BUTTON1) {
                leftPressed = false;
            }
            if (me.getButton() == MouseEvent.BUTTON2) {
                middlePressed = false;
            }
            if (me.getButton() == MouseEvent.BUTTON3) {
                rightPressed = false;
            }
            if (uiManager != null) {
                uiManager.onMouseRelease(me);
            }
        }
    }

    public void tick() {
        if (leftCantPressed && !leftPressed) {
            leftCantPressed = false;
        } else if (leftJustPressed) {
            leftCantPressed = true;
            leftJustPressed = false;
        }
        if (leftPressed && !leftCantPressed) {
            leftJustPressed = true;
        }
        if (rightCantPressed && !rightPressed) {
            rightCantPressed = false;
        } else if (rightJustPressed) {
            rightCantPressed = true;
            rightJustPressed = false;
        }
        if (rightPressed && !rightCantPressed) {
            rightJustPressed = true;
        }
    }

    public void mouseEntered(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {

        }
    }

    public void mouseExited(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {

        }
    }

    public void mouseDragged(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {
            mouseX = me.getX();
            mouseY = me.getY();
        }
    }

    public void mouseMoved(MouseEvent me) {
        if (window.contains(me.getLocationOnScreen())) {
            mouseX = me.getX();
            mouseY = me.getY();
            if (uiManager != null) {
                uiManager.onMouseMove(me);
            }
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            wheelDistance -= e.getScrollAmount();
        } else {
            wheelDistance += e.getScrollAmount();
        }
    }

    public int getWheelDistance() {
        return wheelDistance;
    }

    public boolean isLeftJustPressed() {
        return leftJustPressed;
    }

    public boolean isRightJustPressed() {
        return rightJustPressed;
    }
    
    

}

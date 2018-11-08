package blankengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import blankengine.gfx.Assets;
import blankengine.input.KeyManager;
import blankengine.input.MouseManager;
import blankengine.states.DefaultState;
import blankengine.states.State;

public class Game implements Runnable {

    //display
    private Display display;
    private int width, height, x, y;
    public String title;
    //thread
    private Thread gameThread;
    public boolean running = false;
    //render
    private BufferStrategy bs;
    private Graphics g;
    //states
    public DefaultState defaultState;
    
    //input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //handler
    private Handler handler;

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Game(String title, int width, int height, int x, int y, boolean dev) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    private void init() {

        Assets.init();

        handler = new Handler(this);
        handler.setWindowX(x);
        handler.setWindowY(y);

        keyManager = new KeyManager();
        mouseManager = new MouseManager(handler);

        display = new Display(title, width, height, x, y);

        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getFrame().addMouseWheelListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseWheelListener(mouseManager);

        defaultState = new DefaultState(handler);
        State.setState(defaultState, handler);
    }

    public void run() {
        init();
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long startRender;
        long renderTime;
        int tickCounter = 0;
        int renderCounter = 0;
        double framesToSkip = 0.0;
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                tick();
                tickCounter++;
                if (framesToSkip < 1.0) {
                    startRender = System.nanoTime();
                    render();
                    renderCounter++;
                    renderTime = System.nanoTime() - startRender;
                    framesToSkip += (renderTime / timePerTick);
                } else {
                    framesToSkip--;
                }
                delta--;
                if (tickCounter >= fps) {
                    handler.setCurrentFPS(renderCounter);
                    tickCounter = 0;
                    renderCounter = 0;
                }
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        try {
            gameThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tick() {
        keyManager.tick();
        mouseManager.tick();
        if (handler.getCurrentState() != null) {
            handler.getCurrentState().tick();
        }
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        //draw
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        if (handler.getCurrentState() != null) {
            handler.getCurrentState().render(g);
        }

        g.setColor(Color.gray);
        g.setFont(new Font("monospaced", Font.PLAIN, 15));
        g.drawString(handler.getCurrentFPS() + " FPS ", 10, 20);

        bs.show();
        g.dispose();
    }

    public Display getDisplay() {
        return display;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

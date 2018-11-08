package blankengine;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import blankengine.gfx.Assets;
import javax.swing.UIManager;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height, x, y;

    public Display(String title, int width, int height, int x, int y) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        createDisplay();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void createDisplay() {

        try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        } catch (Exception ex) {
        }

        //create frame
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusTraversalKeysEnabled(false);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocation(x, y);
        //create canvas
        canvas = new Canvas();
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        //add canvas to frame
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

}

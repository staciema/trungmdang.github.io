/*
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JFrame;

import tools.ColorAction;
import tools.Ellipse;
import tools.Line;
import tools.Pencil;
import tools.Rectangle;

/**
 * @author Trung
 * @version November 4, 2014
 */
public class PowerPaintGUI {
    
    /** A toolkit.*/
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** Hold the screen size of the current display.*/
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** Hold the width of the screen.*/
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** Hold the height of the screen.*/
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /** A scale used to set the frame relative to the display size.*/
    private static final int SCALE = 3;
    
    /***/
    private static final String TITLE = "TCSS 305 PowerPaint";

    /***/
    private final JFrame myFrame;
    
    /***/
    private final DrawingPanel myPanel;
    /**
     * 
     */
    public PowerPaintGUI() {
        myFrame = new JFrame(TITLE);
        myPanel = new DrawingPanel();
        myFrame.add(myPanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        readIcon();
        setUpMenus();
        myFrame.pack();
        myFrame.setLocation(SCREEN_WIDTH / 2 - myFrame.getWidth() / 2, SCREEN_HEIGHT / 2
                    - myFrame.getHeight() / 2);
        myFrame.setVisible(true);
    }
    /**
     * 
     */
    private void readIcon() {
        try {                              
            myFrame.setIconImage(ImageIO.read(new File("icons/paint.png")));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void setUpMenus() {
        final MenuBar menu = new MenuBar(myFrame, myPanel, myPanel.getBrushProperties());
        final ToolBar toolBar = new ToolBar();
        toolBar.add(new ColorAction(myPanel));
        toolBar.addSeparator();
        
        final Action[] actions = {new Pencil(myPanel), new Line(), 
                                  new Rectangle(myPanel), new Ellipse(myPanel)};
        
        for(Action action : actions) {
            menu.createMenuButtons(action);
            toolBar.createToolBarButton(action);
        }
        
        myFrame.setJMenuBar(menu);
        myFrame.add(toolBar, BorderLayout.SOUTH);
        //JToolbar
    }

}

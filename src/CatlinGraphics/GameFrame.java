package CatlinGraphics;

import java.awt.*;
import javax.swing.*;

/***********************************
  * 
  * A Window (possibly full screen) for your graphics or animation.
  * 
  <p>
  IN YOUR MAIN FUNCTION, PICK ONE OF THE FOLLOWING THREE OPTIONS:
  <ul>
    
     <li>make a normal window<br>
       <code> GameFrame.makeGraphicsWindow(gameCanvas, "My Game!"); </code>
    
     <li>make a full-screen window without a frame (true full screen)<br>
       <code> GameFrame.makeGraphicsFullScreen(gameCanvas, true); </code>
    
     <li>make a maximized window without a frame (fake full screen, may show menu bar)<br>
       <code> GameFrame.makeGraphicsFullScreen(gameCanvas, false); </code>
    
 </ul>
 */

public class GameFrame extends JFrame
{
  /**
   * Creates a regular window with a title bar
   * 
   * @param gameCanvas The Component to display in the window
   * @param title The title of the window (displayed in the title bar)
   */
  public static GameFrame makeGraphicsWindow(Component gameCanvas, String title)
  { 
    return new GameFrame(gameCanvas, title, false, false);
  }


  /**
   * Creates a full-screen window
   * 
   * @param gameCanvas The Component to display in the window
   * 
   */
  
  public static GameFrame makeGraphicsFullScreen(Component gameCanvas)
  {
    return makeGraphicsFullScreen(gameCanvas, true);
  }
  

  /**
   * Creates a full-screen window
   * 
   * @param gameCanvas The Component to display in the window
   * @param trueFullScreen If this parameter is true, 
   * then we use Java's full screen exclusive mode.  
   * If this parameter is false, then the window will simply
   * be maximized.
   */
  
  public static GameFrame makeGraphicsFullScreen(Component gameCanvas, boolean trueFullScreen)
  {
    return new GameFrame(gameCanvas, "", true, trueFullScreen);
  }
   
  //////////////////////////////////////////////////////////////////////////////////
  
  private GameFrame(Component gameCanvas, String title, boolean maximized, boolean fullscreen)
  {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().add(gameCanvas);
    setIgnoreRepaint(true);
    GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    if (fullscreen && graphicsDevice.isFullScreenSupported()) {
      setUndecorated(true);
      graphicsDevice.setFullScreenWindow(this);
    } else if (maximized) {
      setUndecorated(true);
      setExtendedState(JFrame.MAXIMIZED_BOTH);
    } else {
      pack();
      setLocationRelativeTo(null); // center the window on the screen
    }
    setVisible(true);
    bringToFront();
  }
  
  private void bringToFront()
  {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        toFront();
      }
    });
  }

}
package CatlinGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;

/****************************************
  * 
  * A Canvas for double-buffered graphics.
   
  Useful methods in this class:
  
  <pre>
  public Graphics2D getPen();  // gets a pen for drawing on off-screen buffer
  public void display();       // displays the off-screen buffer on the screen
  public void clear();         // clears the off-screen buffer
  
  public int getWidth();
  public int getHeight();
  
  public Point getMousePoint();
  public void hideMouse();
  public void showMouse();
  
  public void setBackgroundColor(Color newBackgroundColor);
  public Color getBackgroundColor();
  </pre>
 */

public class ImageCanvas extends EventfulCanvas
{   
  private BufferStrategy strategy = null;
  private Color backgroundColor = Color.WHITE;
   
  //**********************************************************************
  // Constructor: pass the width and height you want for this canvas.
  
  public ImageCanvas(int width, int height) 
  {
    setPreferredSize(new Dimension(width, height));
    setIgnoreRepaint(true);
  }
  
  //*****************************************************************
  // This is called automatically to notify the Canvas that it has been added to the GUI
  public void addNotify()
  {
    super.addNotify();
    createBufferStrategy(2);
    strategy = getBufferStrategy();
  }  
  
  /************************
    * Returns the Graphics2D pen used to draw on the off-screen buffer.
    */
  
  public Graphics2D getPen()
  {
    return (Graphics2D) strategy.getDrawGraphics();
  }
  
  
  /************************
    * Call display() when you are done drawing a frame on the off-screen buffer.
    * This displays the contents of the buffer on the screen.
    */
  
  public void display()
  {
    strategy.show();
  }
  
  //**********************************************************************
  // returns the Point where the mouse is
  
  public java.awt.Point getMousePoint()
  {
    PointerInfo pointerInfo = MouseInfo.getPointerInfo();
    java.awt.Point mousePoint = pointerInfo.getLocation();
    java.awt.Point panelCorner = getLocationOnScreen();
    mousePoint.translate(-panelCorner.x, -panelCorner.y);
    return mousePoint;
  }
  
   /******************
     * Hides the mouse cursor
     */
   
   public void hideMouse()
   {
     BufferedImage blank = new BufferedImage(16,16,BufferedImage.TYPE_4BYTE_ABGR);
     Cursor blankCursor = getToolkit().createCustomCursor(blank,new Point(0,0),"blankCursor");
     setCursor(blankCursor);
   }
   
   /******************
     * Restores the mouse cursor
     */
   
   public void showMouse()
   {
     setCursor(Cursor.getDefaultCursor());
   }
  
  //**********************************************************************
  
  public void setBackgroundColor(Color newBackgroundColor)
  {
    backgroundColor = newBackgroundColor;
  }
  
  //**********************************************************************
  
  public Color getBackgroundColor()
  {
    return backgroundColor;
  }
  
  //**********************************************************************
  // clears the image panel
  
  public void clear(Graphics2D pen)
  {
    pen.setColor(backgroundColor);
    pen.fillRect(0, 0, getWidth(), getHeight());
  }
  
}

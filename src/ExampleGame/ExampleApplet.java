package ExampleGame;

import javax.swing.*;
import java.applet.*;
import java.awt.*;
import CatlinGraphics.*;


// Use this to create an applet that can run on a webpage
// Make sure to change the ExampleGameCanvas class to your class that extends AnimationCanvas

public class ExampleApplet extends JApplet
{
  private AnimationCanvas gameCanvas;
  
  public void init()
  {
    gameCanvas = new ExampleGameCanvas(getWidth(), getHeight()); // change ExampleGameCanvas!
    getContentPane().add(gameCanvas);
  }
  
  public void start()
  {
    gameCanvas.start();
  }
  
  public void stop()
  {
    gameCanvas.stop();
  }
}

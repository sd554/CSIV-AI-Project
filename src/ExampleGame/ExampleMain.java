package ExampleGame;

import java.awt.Component;
import CatlinGraphics.*;

public class ExampleMain
{
  public static void main(String[] args)
  {
    Component gameCanvas = new ExampleGameCanvas(1000, 700);
    
    // PICK ONE OF THE FOLLOWING THREE OPTIONS
    
    // make a normal window
    GameFrame.makeGraphicsWindow(gameCanvas, "My Game!");
    
    // make a full-screen window without a frame (true full screen)
   //  GameFrame.makeGraphicsFullScreen(gameCanvas, true);
    
    // make a maximized window without a frame (fake full screen, may show menu bar)
   //  GameFrame.makeGraphicsFullScreen(gameCanvas, false);
    
  }
}
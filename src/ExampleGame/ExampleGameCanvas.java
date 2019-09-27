package ExampleGame;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import CatlinGraphics.*;


// This is a simple example game.  

class ExampleGameCanvas extends AnimationCanvas
{
  final String path = "/ExampleGame/";
  Butterfly butterfly;
  BufferedImage butterflyImage, earthImage;
  SimpleSound scoreSound;
  LinkedList<Ball> balls;
  int score = 0;
  Random random = new Random();
  Font scoreFont = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
  
  
  ExampleGameCanvas(int width, int height)
  {
    super(width, height, 1000);
    butterflyImage = ImageUtilities.loadImage(path+"butterfly.png");
    earthImage = ImageUtilities.loadImage(path+"earth.png");
    scoreSound = new SimpleSound(path+"blip.wav");
    butterfly = new Butterfly(butterflyImage);
    setBackgroundColor(new Color(100,250,185));
    balls = new LinkedList<Ball>();
    hideMouse();
  }
  
  public void update(double elapsedMilliseconds)
  {
    
    butterfly.move(elapsedMilliseconds);
    if (random.nextDouble() < elapsedMilliseconds/1000.0) {
      balls.add(new Ball(earthImage));
    }
    
    Iterator<Ball> ballIterator = balls.iterator();
    while (ballIterator.hasNext()) {
      Ball ball = ballIterator.next();
      if (distance(ball.x, ball.y, butterfly.x, butterfly.y) < 2*ball.radius) {
        ballIterator.remove();
        score++;
        scoreSound.play();
      }
    }
    
  }
  
  
  public void draw(Graphics2D pen)
  { 
    
    for (Ball ball : balls) {
      ball.draw(pen);
    }
    butterfly.draw(pen);
    pen.setColor(Color.RED);
    pen.setFont(scoreFont);
    pen.drawString("Score: " + score, 10, getHeight()-20);
    pen.drawString("FPS: " + String.format("%.1f", getActualFPS()), 10, 30);
  }
  
 
  public void keyPressed(KeyEvent event)
  {
    super.keyPressed(event);
    int code = event.getKeyCode();
    if (code == KeyEvent.VK_F) {
      System.out.printf("%.3f FPS\n", getActualFPS());
    }
    if (code == KeyEvent.VK_LEFT) 
      butterfly.rotatingLeft();
    if (code == KeyEvent.VK_RIGHT)
      butterfly.rotatingRight();
  }
  
  public void keyReleased(KeyEvent event)
  {
    super.keyReleased(event);
    int code = event.getKeyCode();
    
    if (code == KeyEvent.VK_LEFT) 
      butterfly.stopRotating();
    if (code == KeyEvent.VK_RIGHT)
      butterfly.stopRotating();
  }
  
  class Butterfly
  {
    BufferedImage image;
    int imageSize;
    double x, y, direction, speed, rotating;
    
    Butterfly(BufferedImage image)
    {
      this.image = image;
      imageSize = image.getWidth();
      x = 300;
      y = 100;
      direction = 0;
      rotating = 0;  // in degrees per millisecond
      speed = 0.5; // in pixels per millisecond
    }
    
    void move(double elapsedMilliseconds)
    {
      int width = getWidth();
      int height = getHeight();
      x += speed * elapsedMilliseconds * Math.cos(Math.toRadians(direction));
      y += speed * elapsedMilliseconds * Math.sin(Math.toRadians(direction));
 
      if (x > width+imageSize/2)
        x = -imageSize/2;
      if (x < -imageSize/2)
        x = width+imageSize/2;
      if (y > height+imageSize/2)
        y = -imageSize/2;
      if (y < -imageSize/2)
        y = height+imageSize/2;
      
      direction += rotating * elapsedMilliseconds;
    }
    
    void rotatingLeft()
    {
      rotating = -0.2;
    }
    
    void rotatingRight()
    {
      rotating = 0.2;
    }
    
    void stopRotating()
    {
      rotating = 0;
    }
    
    void draw(Graphics2D pen)
    {
      ImageUtilities.drawRotatedImage(pen, image, (int)x, (int)y, direction);
    }
  }
  
  class Ball
  {
    BufferedImage image;
    int radius;
    int x, y;
    
    Ball(BufferedImage image)
    {
      this.image = image;
      radius = image.getWidth()/2;
      x = random.nextInt(getWidth()-2*radius) + radius;
      y = random.nextInt(getHeight()-2*radius) + radius;    
    }
    
    void draw(Graphics2D pen)
    {
      ImageUtilities.drawCenteredImage(pen, image, x, y);
    }
  
  }
  
  double distance(double x1, double y1, double x2, double y2)
  {
    return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
  }
  
}

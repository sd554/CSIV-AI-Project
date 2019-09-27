import CatlinGraphics.AnimationCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends AnimationCanvas implements ActionListener {

    Random random = new Random();

    ArrayList<GamePoint> pointArrayList = new ArrayList<>();

    int width = 0;
    int height = 0;

    GameCanvas(int width, int height) {
        super(width, height, 100);
        this.width = width;
        this.height = height;
        setBackgroundColor(new Color(100,250,185));
        generatePoints(30);
        new Timer(5, this).start();
    }

    public void drawPoint(int x, int y, int width) {
        getPen().fillOval(x, y, width, width);
    }

    public void drawPoint(GamePoint point) {
        drawPoint(point.x, point.y, 8);
        getPen().drawString(String.valueOf(point.id), point.x + 10, point.y + 10);
    }


    /* Generate point generates a random point on the frame and returns it. */
    public Point generatePoint() {

        int x = random.nextInt(width);
        int y = random.nextInt(height);

        GamePoint p = new GamePoint(x, y, pointArrayList.size());
        pointArrayList.add(p);

        return p;
    }

    public void generatePoints(int num) {

        for (int x = 0; x < num; x++) {
            generatePoint();
        }

    }

    @Override
    public void update(double elapsedMilliseconds) {

    }

    @Override
    public void draw(Graphics2D pen) {

        pen.setColor(Color.BLACK);
        pen.drawString("i am an apple lol", 30, getHeight() - 50);

        for (GamePoint point : pointArrayList) {
            drawPoint(point);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pointArrayList.get(0).x += 1;
    }
}

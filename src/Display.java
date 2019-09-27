import CatlinGraphics.GameFrame;
import java.awt.*;

public class Display {

    public static void main(String[] args) {

        Component gameCanvas = new GameCanvas(800, 600);

        GameFrame.makeGraphicsWindow(gameCanvas, "Net Neurality");

    }

}

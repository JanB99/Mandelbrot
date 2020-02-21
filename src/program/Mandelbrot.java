package program;

import java.awt.*;

public class Mandelbrot {

    public double xoff, yoff;
    public double minValue, maxValue;
    public double screenOffsetx, screenOffsety;
    public int infinityLimit, maxIterations;

    public Mandelbrot(){
        xoff = Main.WIDTH/2;
        yoff = Main.HEIGHT/2;
        minValue = -2.5f;
        maxValue = 2.5f;
        screenOffsetx = 0;
        screenOffsety = 0;
        infinityLimit = 64;
        maxIterations = 100;
    }

    public void render(Graphics g){
        for (int x = 0; x < Main.WIDTH; x++) {
            for (int y = 0; y < Main.HEIGHT; y++) {

                Color col = getMandelbrotColor(x, y, xoff, yoff);

                g.setColor(col);
                g.fillRect(x, y, 1, 1);
            }
        }
//        infinityLimit++;
//        maxIterations++;
    }

    public void tick(){

    }

    public Color getMandelbrotColor(double x, double y, double xoff, double yoff){
        x = (float) map(x + screenOffsetx, 0, Main.WIDTH, minValue, maxValue);
        y = (float) map(y + screenOffsety, 0, Main.HEIGHT, minValue, maxValue);

        xoff = map(xoff, 0, Main.WIDTH, -0.5, 0.5);
        yoff = map(yoff, 0, Main.HEIGHT, -0.5, 0.5);

        double originalx = x;
        double originaly = y;

        int n = 0;

        while (n < maxIterations) {

            double xx = x * x - y * y;
            double yy = 2 * x * y;

            x = xx + originalx + xoff;
            y = yy + originaly + yoff;

            if (Math.abs(xx + yy) > infinityLimit) {
                break;
            }

            n++;
        }
        float color = (float) map(n, 0, infinityLimit, 0, 1);//(n*infinityLimit) % 360;

        return Color.getHSBColor(color, 1, 1);//new Color(200, color, 100);//map(n, 0, 100, 0, 255);
    }

    public double map(double n, double start1, double stop1, double start2, double stop2){
        return ((n - start1) / (stop1 - start1)) * (stop2 - start2) + start2;
    }
}

package program;

import java.awt.*;

public class Mandelbrot {

    public float xoff, yoff, minValue, maxValue;

    public Mandelbrot(){
        xoff = Main.WIDTH/2;
        yoff = Main.HEIGHT/2;
        minValue = -2;
        maxValue = 2;
    }

    public void render(Graphics g){
        for (int x = 0; x < Main.WIDTH; x++) {
            for (int y = 0; y < Main.HEIGHT; y++) {

                Color col = getMandelbrotColor(x, y, xoff, yoff);

                g.setColor(col);
                g.fillRect(x, y, 1, 1);
            }
        }
    }

    public void tick(){

    }

    public Color getMandelbrotColor(float x, float y, float xoff, float yoff){
        x = map(x, 0, Main.WIDTH, minValue, maxValue);
        y = map(y, 0, Main.HEIGHT, minValue, maxValue);

        xoff = map(xoff, 0, Main.WIDTH, minValue, maxValue);
        yoff = map(yoff, 0, Main.HEIGHT, minValue, maxValue);

        float originalx = x;
        float originaly = y;

        int n = 0;

        while (n < 50) {

            float xx = x * x - y * y;
            float yy = 2 * x * y;

            if (Math.abs(xx + yy) > 16) {
                break;
            }

            x = xx + originalx + xoff;
            y = yy + originaly + yoff;

            n++;
        }
        int color = (n*16) % 255;

        return new Color(color, color, color);//map(n, 0, 100, 0, 255);
    }

    public float map(float n, float start1, float stop1, float start2, float stop2){
        return ((n - start1) / (stop1 - start1)) * (stop2 - start2) + start2;
    }
}

package program;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class MouseWheelController extends MouseAdapter {

    private Mandelbrot mandelbrot;

    public MouseWheelController(Mandelbrot mandelbrot){
        this.mandelbrot = mandelbrot;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mandelbrot.maxValue += e.getWheelRotation() * 0.2;
        mandelbrot.minValue -= e.getWheelRotation() * 0.2;
        mandelbrot.xoff = e.getX();
        mandelbrot.yoff = e.getY();

    }
}

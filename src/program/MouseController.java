package program;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseController extends MouseMotionAdapter {

    private Mandelbrot mandelbrot;

    public MouseController(Mandelbrot mandelbrot){
        this.mandelbrot = mandelbrot;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mandelbrot.xoff = e.getX();
        mandelbrot.yoff = e.getY();
    }

}

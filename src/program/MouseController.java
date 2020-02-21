package program;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseController extends MouseMotionAdapter {

    private Mandelbrot mandelbrot;
    public int zoomLevel;

    public MouseController(Mandelbrot mandelbrot){
        this.mandelbrot = mandelbrot;
        zoomLevel = 1;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

//        if (zoomLevel != 0){
//            mandelbrot.xoff = e.getX() / zoomLevel;
//            mandelbrot.yoff = e.getY() / zoomLevel;
//        } else {
//            mandelbrot.xoff = e.getX() / (zoomLevel+1);
//            mandelbrot.yoff = e.getY() / (zoomLevel+1);
//        }

        mandelbrot.xoff = e.getX();
        mandelbrot.yoff = e.getY();
    }

}

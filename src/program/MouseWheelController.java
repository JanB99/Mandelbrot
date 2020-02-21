package program;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class MouseWheelController extends MouseAdapter {

    private Mandelbrot mandelbrot;
    private MouseController mouseController;

    public MouseWheelController(Mandelbrot mandelbrot, MouseController mouseController){
        this.mandelbrot = mandelbrot;
        this.mouseController = mouseController;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if (e.getWheelRotation() == -1){
            mandelbrot.maxValue *= 0.5;
            mandelbrot.minValue *= 0.5;
//            mouseController.zoomLevel -= 1;
        }

        if (e.getWheelRotation() == 1) {
            mandelbrot.maxValue *= 1.5;
            mandelbrot.minValue *= 1.5;
//            mouseController.zoomLevel += 1;
        }
//        mandelbrot.maxValue *= e.getWheelRotation() ==  1 ?  0.2 : 1;
//        mandelbrot.minValue *= e.getWheelRotation() == -1 ? -0.2 : 1;
//        System.out.println(mouseController.zoomLevel);
//
//        double distX = e.getX() - Main.WIDTH/2;//Math.sqrt(Math.pow(e.getX() - Main.WIDTH/2, 2) + Math.pow(e.getY() - Main.HEIGHT/2, 2));
//        double distY = e.getY() - Main.HEIGHT/2;
//
//        mandelbrot.screenOffsetx  = distX;
//        mandelbrot.screenOffsety  = distY;

//
//        mandelbrot.xoff  = (float) distX;
//        mandelbrot.yoff  = (float) distY;
    }
}

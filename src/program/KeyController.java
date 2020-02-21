package program;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {

    private Main main;

    public KeyController(Main main){
        this.main = main;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S){
            main.saveCanvasToImage("image_"+randomImageID());
        }
    }

    public int randomImageID(){
        return (int) (Math.random() * 1000);
    }
}

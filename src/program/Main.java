package program;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class Main extends Canvas implements Runnable{

    public static final int WIDTH = 500, HEIGHT = 500;

    private Window window;
    private Thread thread;
    private boolean running;
    private Mandelbrot mandelbrot;
    private MouseController mouseAdapter;
    private MouseWheelController mouseWheelAdapter;
    private KeyController keyAdapter;

    public Main(){

        mandelbrot = new Mandelbrot();
        mouseAdapter = new MouseController(mandelbrot);
        mouseWheelAdapter = new MouseWheelController(mandelbrot, mouseAdapter);
        keyAdapter = new KeyController(this);

        this.addMouseMotionListener(mouseAdapter);
        this.addMouseWheelListener(mouseWheelAdapter);
        this.addKeyListener(keyAdapter);

        window = new Window("Mandelbrot", WIDTH, HEIGHT, this);
    }

    public static void main(String[] args) {
        new Main();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        mandelbrot.render(g);

        g.dispose();
        bs.show();

    }

    private void tick() {
        mandelbrot.tick();
    }

    public void saveCanvasToImage(String name){
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        mandelbrot.render(g);
        g.dispose();
        try {
            System.out.println("Exporting image: "+name);
            FileOutputStream out = new FileOutputStream("mandelbrot_images/"+name+".png");
            ImageIO.write(image, "png", out);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

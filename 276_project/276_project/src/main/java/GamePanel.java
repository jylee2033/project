import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    private static final int FPS = 60;
    final int originalTitelSize = 16;
    final int scale = 3;

    public final int titleSize = originalTitelSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;

    public int num_crop = 0;
    public int num_honey = 0;

    keyHandler key = new keyHandler();
    Thread gameThread;
    Player player = new Player(this, key, 1);

    Crop [] crops;
    Honey [] honeys;

    int play_x = 100;
    int play_y = 100;
    int play_speed = 5;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public GamePanel(int num_crop, int num_honey){
        this.num_crop = num_crop;
        this.num_honey = num_honey;
        this.crops = new Crop[num_crop];
        this.honeys = new Honey[num_honey];
        generateCropHoney();
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void generateCropHoney() {
        Random rn = new Random();
        //int [][] crop_honey_x_y = new int[num_honey + num_crop][2];

        for(int i = 0; i < num_crop; i++) {
            crops[i] = new Crop(rn.nextInt(screenWidth), rn.nextInt(screenHeight), 1);
        }
        for(int i = 0; i < num_honey; i++) {
            honeys[i] = new Honey(rn.nextInt(screenWidth), rn.nextInt(screenHeight), 1);
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drwaInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drwaInterval;

        while(gameThread != null){

            update();
            repaint();
            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drwaInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        for(int i = 0; i < num_crop; i++) {
            if (crops[i].visible == 1) {
                crops[i].draw(g2);
            }
        }
        for(int i = 0; i < num_honey; i++) {
            if (honeys[i].visible == 1) {
                honeys[i].draw(g2);
            }
        }
        g2.dispose();
    }
}

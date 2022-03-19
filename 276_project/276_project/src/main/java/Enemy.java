import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends Entity{

    GamePanel gp;
    public int level;

    public Enemy(GamePanel gp, int level) {
        this.level = level;
        this.gp = gp;
        setDefaultValue();
        getEnemyImage();
    }

    public void setDefaultValue(){
        x = 10; // Enemy Location
        y = 10; // Enemy Location
        speed = 5;
    }
    public void getEnemyImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("Bear_3"));
            // up2 = ImageIO.read(getClass().getResourceAsStream("Bear_4"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        // Movement of enemy
        // Location of Player == Enemy -> Die
    }

    public void draw(Graphics2D g2){
        BufferedImage image = up1;
        // BufferedImage image2 = up2;
        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
    }
}
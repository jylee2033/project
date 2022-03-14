import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    keyHandler key;
    public int honeyScore;
    public int cropScore;
    public int level;

    public Player(GamePanel gp, keyHandler key, int level){
        this.level = level;
        this.gp = gp;
        this.key = key;
        honeyScore = 0;
        cropScore = 0;
        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue(){
        x = 0;
        y = 0;
        speed = 5;
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("Daff.jpg"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if(key.upPress == true)
        {
            direction = "up";
            y -= speed;
            //System.out.println("x: " + x + ", y: " + y);

        }
        else if(key.downPress == true){
            direction = "down";
            y += speed;
            //System.out.println("x: " + x + ", y: " + y);

        }
        else if(key.leftPress == true){
            direction = "left";
            x -= speed;
            //System.out.println("x: " + x + ", y: " + y);

        }
        else if(key.rightPress == true){
            direction = "right";
            x += speed;
            //System.out.println("x: " + x + ", y: " + y);

        }
        if (checkCrop(x, y)) {
            cropScore += level;
            System.out.println("Crop detected! Crop score: " + cropScore);
        }
        if (checkHoney(x, y)) {
            honeyScore += level * 2;
            System.out.println("Honey detected! Honey score: " + honeyScore);
        }
    }

    public boolean checkCrop(int x, int y) {
        for (int i = 0; i < gp.num_crop; i++) {
            if ((gp.crops[i].x >= x && gp.crops[i].x <= (x + gp.titleSize))
                    && (gp.crops[i].y >= y && gp.crops[i].y <= (y + gp.titleSize))
                    && gp.crops[i].visible == 1) {
                gp.crops[i].visible = 0;
                return true;
            }
        }
        return false;
    }

    public boolean checkHoney(int x, int y) {
        for (int i = 0; i < gp.num_honey; i++) {
            if ((gp.honeys[i].x >= x && gp.honeys[i].x <= (x + gp.titleSize))
                    && (gp.honeys[i].y >= y && gp.honeys[i].y <= (y + gp.titleSize))
                    && gp.honeys[i].visible == 1) {
                gp.honeys[i].visible = 0;
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = up1;
        //System.out.println(image.getWidth() + " " + image.getHeight());
        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
    }
}

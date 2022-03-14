import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Honey extends Entity {

    int visible;

    public Honey(int x, int y, int visible) {
        this.x = x;
        this.y = y;
        this.visible = visible;
        getCropImage();
    }

    public void getCropImage() {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Crop.jpg"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = up1;
        g2.drawImage(image, x, y, 25, 25, null);
    }
}

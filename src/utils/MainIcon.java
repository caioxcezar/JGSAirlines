package utils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainIcon {
	public MainIcon(JFrame frame, String imageIcon) {
        try {
            URL resource = getClass().getResource(imageIcon);
            BufferedImage image = ImageIO.read(resource);
            frame.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

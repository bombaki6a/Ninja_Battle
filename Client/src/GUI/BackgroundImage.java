package GUI;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;

// Class that visualizes images in the app pages
public class BackgroundImage extends JPanel {
    private Image image;

    public BackgroundImage(String path, Integer width, Integer height) {
        setOpaque(false);
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        image = Toolkit.getDefaultToolkit().getImage(path);
    }

    // Drawing image function
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

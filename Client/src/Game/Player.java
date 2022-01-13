package Game;

import GUI.BackgroundImage;

import javax.swing.JPanel;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// Class that visualizes the player
public class Player extends JPanel {
    private BackgroundImage up;
    private BackgroundImage left;
    private BackgroundImage right;

    public volatile GridBagConstraints constraints = new GridBagConstraints();

    public Player(Integer width, Integer height) {
        setLayout(new GridBagLayout());

        setOpaque(false);
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Function that sets player sprites
    public void SetSprites(String up, String left, String right) {
        this.up = new BackgroundImage(up, getWidth(), getHeight());
        this.left = new BackgroundImage(left, getWidth(), getHeight());
        this.right = new BackgroundImage(right, getWidth(), getHeight());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);

        add(this.up, constraints);
        add(this.left, constraints);
        add(this.right, constraints);
    }

    // Function that visualizes only the "LEFT" sprite
    public void Left() {
        up.setVisible(false);
        right.setVisible(false);
        left.setVisible(true);
    }

    // Function that visualizes only the "RIGHT" sprite
    public void Right() {
        up.setVisible(false);
        left.setVisible(false);
        right.setVisible(true);
    }

    // Function that visualizes only that "UP" sprite
    public void Up() {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(true);
    }
}

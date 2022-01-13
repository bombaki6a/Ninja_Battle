package Game;

import GUI.BackgroundImage;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// Class that visualizes player life
public class Hearts extends JPanel {
    private BackgroundImage heart1;
    private BackgroundImage heart2;
    private BackgroundImage heart3;

    private Integer borderSpace = 5;

    private String path = "Images/heart.png";
    private static GridBagConstraints constraints = new GridBagConstraints();

    public Hearts(Integer width, Integer height) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0, borderSpace, 0, 0));

        setSize(width, height);

        constraints.gridx = 0;
        constraints.gridy = 0;

        heart1 = new BackgroundImage(path, height, height);
        add(heart1, constraints);

        constraints.gridx = 1;
        heart2 = new BackgroundImage(path, height, height);
        add(heart2, constraints);

        constraints.gridx = 2;
        heart3 = new BackgroundImage(path, height, height);
        add(heart3, constraints);
    }

    // Function that removes a heart
    public void Remove(Integer index) {
        switch (index) {
            case 2:
                remove(heart3);
                break;
            case 1:
                remove(heart2);
                break;
            case 0:
                remove(heart1);
                break;
        }

        repaint();
        revalidate();
    }
}

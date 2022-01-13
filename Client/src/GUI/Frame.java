package GUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// Th main app frame
public class Frame extends JFrame {
    private final GridBagConstraints constraints = new GridBagConstraints();

    public Frame(String title, Integer width, Integer height) {
        super(title);
        setLayout(new GridBagLayout());

        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Function that sets app icon
    public void SetIcon(String path) {
        setIconImage(new ImageIcon(path).getImage());
    }

    // Function that sets the background color
    public void SetBackgroundColor(Color color) {
        getContentPane().setBackground(color);
    }

    // Fucntion that adds components on certain position
    public void Add(Component component, Integer gridY) {
        constraints.gridx = 0;
        constraints.gridy = gridY;

        if (component != null) {
            add(component, constraints);
            pack();
        }
    }

    // Function that adds components on certain position and anchor
    public void Add(Component component, Integer gridY, Integer anchor) {
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.anchor = anchor;

        if (component != null) {
            add(component, constraints);
            pack();
        }
    }
}

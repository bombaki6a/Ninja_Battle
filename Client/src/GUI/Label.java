package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

// The main class of the labels
public class Label extends JLabel {
    public final static Integer LEFT = SwingConstants.LEFT;
    public final static Integer CENTER = SwingConstants.CENTER;
    public final static Integer RIGHT = SwingConstants.RIGHT;

    public Label(String title, Color color) {
        super(title);
        setOpaque(false);
        setForeground(color);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    // Label size setting function
    public void SetSize(Integer width, Integer height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Label border setting function
    public void SetBorder(Color color, Integer thickness) {
        setBorder(BorderFactory.createLineBorder(color, thickness));
    }
}

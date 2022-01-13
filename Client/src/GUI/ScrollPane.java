package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

//Scroll panel class
public class ScrollPane extends JScrollPane {
    public ScrollPane(Component component) {
        super(component);

        setOpaque(false);
        getVerticalScrollBar().setOpaque(false);
        getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    // Scroll panel size setting function
    public void SetSize(Integer width, Integer height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Function that sets background color on the scroll panel
    public void SetBackground(Color color) {
        getViewport().setBackground(color);
    }

    // Scroll panel border setting function
    public void SetBorder(Color color, Integer thickness) {
        setBorder(BorderFactory.createLineBorder(color, thickness));
    }
}

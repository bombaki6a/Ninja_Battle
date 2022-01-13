package GUI;

import Colors.Colors;
import Actions.Action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

// The main class of the buttons
public class Button extends JButton {
    private Integer borderThickness = 2;

    public Button(String title) {
        super(title);
        super.setContentAreaFilled(false);

        setFocusable(false);
        setHorizontalAlignment(SwingConstants.CENTER);

        setBorderPainted(true);
        setBorder(BorderFactory.createLineBorder(Colors.borderColor, borderThickness));
    }

    // Function that sets an action of the button
    public void SetListening(Action action) {
        if (action != null) {
            action.Listening(this);
        }
    }

    // Button border setting function
    public void SetBorder(Color color, Integer thickmess) {
        setBorder(BorderFactory.createLineBorder(color, thickmess));
    }

    // Button size setting function
    public void SetSize(Integer width, Integer height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Button drawing function
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Colors.pressedColor);
        } else if (getModel().isRollover()) {
            g.setColor(Colors.hoverColor);
        } else {
            g.setColor(Colors.backgroundButtonColor);
        }

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}

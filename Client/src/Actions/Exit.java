package Actions;

import GUI.Frame;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Record to exit the app
public record Exit(Frame parent) implements Action, ActionListener {
    private final static Integer status = 0;

    // Function for assigning an action to the button
    @Override
    public void Listening(JButton component) {
        if (component != null) {
            component.addActionListener(this);
        }
    }

    // Function performed when the button is pressed
    @Override
    public void actionPerformed(ActionEvent event) {
        parent.dispose();
        System.exit(status);
    }
}

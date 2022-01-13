package Actions;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Record to open a web page
public record Info(String url) implements Action, ActionListener {
    private final static Desktop desktop = Desktop.getDesktop();

    // Function for assigning an action to the button
    @Override
    public void Listening(JButton component) {
        if (component != null) {
            component.addActionListener(this);
        }
    }

    // Function performed when the button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            desktop.browse(new URI(url));
        } catch (URISyntaxException | IOException error) {
            error.getStackTrace();
        }
    }
}

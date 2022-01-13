package Actions;

import GUI.Page;
import GUI.Frame;
import GUI.RoomsPage;

import Colors.Colors;

import java.util.ArrayList;

import SharedObjects.Room;
import Connections.SocketData;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class to open the rooms page
public class LoadGame implements Action, ActionListener {
    private Frame parent;
    private JButton component;

    public LoadGame(Frame parent) {
        this.parent = parent;
    }

    // Function to gives the rooms list
    private void GetList() {
        try {
            SocketData.output.reset();
            SocketData.output.writeObject(new ArrayList<Room>());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // Function for assigning an action to the button
    @Override
    public void Listening(JButton component) {
        this.component = component;
        if (component != null) {
            component.addActionListener(this);
        }
    }

    // Function performed when the button is pressed
    @Override
    public void actionPerformed(ActionEvent event) {
        Page afterPage = ((Page) component.getParent());
        RoomsPage page = new RoomsPage(parent, afterPage.getWidth(), afterPage.getHeight());

        GetList();

        page.SetAfterPage(afterPage);
        page.setBackground(Colors.background);
        page.SetBackgroundImage(afterPage.backgroundPath, afterPage.width, afterPage.height);

        afterPage.setVisible(false);
        parent.Add(page, 0);
    }
}

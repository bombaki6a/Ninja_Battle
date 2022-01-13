package Actions;

import GUI.Page;
import GUI.Frame;

import SharedObjects.Room;

import Connections.SocketData;
import Connections.TransferPlayersData;

import javax.swing.JButton;

import java.util.List;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class to return to previous page
public class Back implements Action, ActionListener {
    private Frame parent;
    private JButton component;

    private Boolean remove = false;

    public Back(Frame parent) {
        this.parent = parent;
    }

    public Back(Frame parent, Boolean remove) {
        this.parent = parent;
        this.remove = remove;
    }

    // Function to remove rooms
    private void RemoveRoom(Page currentPage) {
        if (remove) {

            currentPage.StopWaithingPlayer();

            try {
                List<Room> list = new ArrayList<Room>();
                SocketData.room.status = Room.Status.REMOVE;
                list.add(SocketData.room);

                SocketData.output.writeObject(list);
                SocketData.output.reset();
            } catch (Exception e) {
                e.getStackTrace();
            }

            SocketData.playerB.position = null;
            TransferPlayersData.Send(SocketData.playerB);
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
    public void actionPerformed(ActionEvent action) {
        Page currentPage = ((Page) component.getParent());

        RemoveRoom(currentPage);

        currentPage.setVisible(false);
        currentPage.afterPage.setVisible(true);
        parent.remove(currentPage);

        component.removeActionListener(this);
    }
}

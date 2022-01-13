package Actions;

import Game.Game;

import GUI.Frame;
import GUI.RoomsPage;

import Colors.Colors;
import Settings.Configs;
import SharedObjects.Room;

import Connections.SocketData;
import Connections.TransferPlayersData;

import javax.swing.JButton;

import java.util.List;
import java.util.ArrayList;

import java.net.DatagramSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class to join the game
public class JoinGame implements Action, ActionListener {
    private Frame parent;
    private JButton component;
    private static Boolean fix = true;

    public JoinGame(Frame parent) {
        this.parent = parent;
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
        RoomsPage afterPage = (RoomsPage) component.getParent();

        if ((afterPage.GetRoom() != null) && (fix)) {
            SetRoom(afterPage.GetRoom());
            SendPlayerData();

            Game page = new Game(parent, afterPage.getWidth(), afterPage.getHeight(), false);

            page.SetAfterPage(afterPage);
            page.setBackground(Colors.background);
            afterPage.setVisible(false);

            parent.remove(afterPage);
            parent.Add(page, 0);
            fix = false;
        }

        component.removeActionListener(this);
    }

    // Function to set the room
    private void SetRoom(Room room) {
        SocketData.room = room;
        SocketData.room.player2 = Configs.getName();
        SocketData.room.status = Room.Status.JOIN;

        List<Room> list = new ArrayList<Room>();
        list.add(SocketData.room);

        try {
            SocketData.output.reset();
            SocketData.output.writeObject(list);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // Function to send player data
    private void SendPlayerData() {
        SocketData.playerA.position = 0;
        SocketData.playerB.position = 0;

        try {
            SocketData.socketUDP = new DatagramSocket();
            TransferPlayersData.Send(SocketData.playerB);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

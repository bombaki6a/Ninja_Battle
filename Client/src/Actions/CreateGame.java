package Actions;

import GUI.Page;
import GUI.Frame;
import GUI.CreatePage;

import Settings.Configs;

import SharedObjects.Room;

import java.util.List;
import java.util.ArrayList;

import Colors.Colors;

import javax.swing.JButton;

import Connections.SocketData;
import Connections.WaitingPlayer;
import Connections.TransferPlayersData;

import java.net.DatagramSocket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class to create a game
public class CreateGame implements Action, ActionListener {
    private Frame parent;
    private JButton component;

    public CreateGame(Frame parent) {
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
    public void actionPerformed(ActionEvent event) {
        Page afterPage = ((Page) component.getParent());
        CreatePage page = new CreatePage(parent, afterPage.getWidth(), afterPage.getHeight());

        page.SetAfterPage(afterPage);
        page.setBackground(Colors.background);
        page.SetBackgroundImage(afterPage.backgroundPath, afterPage.width, afterPage.height);

        CreateSession(page, afterPage);

        afterPage.setVisible(false);
        parent.Add(page, 0);

        SendPlayerData();
    }

    // Function to create a game session
    @SuppressWarnings("unchecked")
    private void CreateSession(CreatePage page, Page afterPage) {
        SocketData.room.player1 = Configs.getName();
        SocketData.room.status = Room.Status.CREAT;

        try {
            SocketData.output.writeObject(new ArrayList<Room>());
            SocketData.room.id = ((List<Room>) SocketData.input.readObject()).size();
            SocketData.output.reset();

            List<Room> list = new ArrayList<Room>();
            list.add(SocketData.room);

            SocketData.output.writeObject(list);
            SocketData.room = ((List<Room>) SocketData.input.readObject()).get(SocketData.room.id);
            SocketData.output.reset();

            WaitingPlayer waitPlayer = new WaitingPlayer(parent, page, afterPage);
            page.SetWaitPlayer(waitPlayer);

            waitPlayer.start();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // Funtion to send player data
    private void SendPlayerData() {
        SocketData.playerA.position = 0;
        SocketData.playerB.position = 0;

        try {
            SocketData.socketUDP = new DatagramSocket();
            TransferPlayersData.Send(SocketData.playerA);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

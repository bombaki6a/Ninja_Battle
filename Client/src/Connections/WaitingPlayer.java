package Connections;

import Game.Game;

import GUI.Page;
import GUI.Frame;

import Colors.Colors;
import SharedObjects.Room;

import java.util.List;
import java.util.ArrayList;

// Thread to wait for opponent
public class WaitingPlayer extends Thread {
    private Frame parent;
    private Page afterPage;
    private Page currentPage;

    public volatile Boolean isRunning = true;

    public WaitingPlayer(Frame parent, Page currentPage, Page afterPage) {
        this.parent = parent;
        this.afterPage = afterPage;
        this.currentPage = currentPage;
    }

    // Fucntion to join the game
    private void JoinGame() {
        Game page = new Game(parent, afterPage.getWidth(), afterPage.getHeight(), true);
        page.SetAfterPage(afterPage);
        page.setBackground(Colors.background);

        parent.Add(page, 0);
    }

    private void RemovePage() {
        currentPage.setVisible(false);
        parent.remove(currentPage);
    }

    // Thread on hold
    @Override
    @SuppressWarnings("unchecked")
    public void run() {
        while (isRunning) {
            try {
                SocketData.output.writeObject(new ArrayList<Room>());
                SocketData.output.reset();

                List<Room> data = ((List<Room>) SocketData.input.readObject());

                if (data.get(SocketData.room.id).status == Room.Status.JOIN) {
                    SocketData.room = data.get(SocketData.room.id);

                    List<Room> list = new ArrayList<Room>();
                    SocketData.room.status = Room.Status.REMOVE;
                    list.add(SocketData.room);

                    SocketData.output.writeObject(list);
                    SocketData.output.reset();

                    JoinGame();
                    RemovePage();
                    break;
                }

                sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}

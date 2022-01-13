package Actions;

import Game.Game;
import Game.Kunai;
import Game.Player;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Connections.SocketData;
import Connections.TransferPlayersData;

// Class responsible for the player movements
public class PlayerMovement implements KeyListener {
    private final Game parent;
    private final Player component;
    private final Integer position;
    private final Boolean isHost;

    private Integer minX = -690;
    private Integer maxX = 700;

    public PlayerMovement(Game parent, Player component, Integer position, Boolean isHost) {
        this.isHost = isHost;
        this.parent = parent;
        this.component = component;
        this.position = position;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Function that starts when a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A:
                Left();
                break;
            case KeyEvent.VK_D:
                Right();
                break;
            case KeyEvent.VK_SPACE:
                Up();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Function to update the frame
    private void FrameUpdate() {
        parent.invalidate();
        parent.validate();
        parent.repaint();
    }

    // Function to send player data when the "W" key is pressed
    private void Up() {
        if (isHost) {
            SocketData.playerA.attack = true;
            TransferPlayersData.Send(SocketData.playerA);
            Integer enemy = (SocketData.playerB.position * -1);
            parent.add(new Kunai(parent, SocketData.playerA.position, enemy, true, false), parent.constraints);
        } else {
            SocketData.playerB.attack = true;
            TransferPlayersData.Send(SocketData.playerB);
            Integer enemy = (SocketData.playerA.position * -1);
            parent.add(new Kunai(parent, SocketData.playerB.position, enemy, true, false), parent.constraints);
        }

        component.Up();

        FrameUpdate();
    }

    // Function to send player data when the "A" key is pressed
    private void Left() {
        if (parent.left > minX) {
            parent.left -= 50;

            if (isHost) {
                SocketData.playerA.attack = false;
                SocketData.playerA.position = parent.left;
                TransferPlayersData.Send(SocketData.playerA);
            } else {
                SocketData.playerB.attack = false;
                SocketData.playerB.position = parent.left;
                TransferPlayersData.Send(SocketData.playerB);
            }
        }

        parent.bottom = 0;
        parent.constraints.insets = new Insets(position, parent.left, parent.bottom, parent.right);
        parent.bagLayout.setConstraints(component, parent.constraints);

        component.Left();

        FrameUpdate();
    }

    // Function to send player data when the "D" key is pressed
    private void Right() {
        if (parent.left < maxX) {
            parent.left += 50;

            if (isHost) {
                SocketData.playerA.attack = false;
                SocketData.playerA.position = parent.left;
                TransferPlayersData.Send(SocketData.playerA);
            } else {
                SocketData.playerB.attack = false;
                SocketData.playerB.position = parent.left;
                TransferPlayersData.Send(SocketData.playerB);
            }
        }

        parent.bottom = 0;
        parent.constraints.insets = new Insets(position, parent.left, parent.bottom, parent.right);
        parent.bagLayout.setConstraints(component, parent.constraints);

        component.Right();

        FrameUpdate();
    }
}

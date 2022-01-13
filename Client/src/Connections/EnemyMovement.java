package Connections;

import Game.Game;
import Game.Kunai;
import Game.Player;

import java.awt.Insets;

// Class that updates the enemy player
public class EnemyMovement {
    private final Game parent;
    private final Player component;
    private final Integer postion;
    private final Boolean isHost;

    private Integer left = 0;

    public EnemyMovement(Game parent, Player component, Integer position, Boolean isHost) {
        this.isHost = isHost;
        this.parent = parent;
        this.postion = position;
        this.component = component;
    }

    // Function that updates the enemy movements
    public void Update() {
        if (isHost) {
            Integer newLeft = (SocketData.playerB.position * -1);

            if (SocketData.playerB.attack) {
                component.Up();

                SocketData.playerB.attack = false;
                Integer you = SocketData.playerA.position;

                parent.add(new Kunai(parent, newLeft, you, false, false), parent.constraints);
            } else if (newLeft > left) {
                component.Right();
            } else if (newLeft < left) {
                component.Left();
            }

            left = newLeft;
        } else {
            Integer newLeft = (SocketData.playerA.position * -1);

            if (SocketData.playerA.attack) {
                component.Up();

                SocketData.playerA.attack = false;
                Integer you = SocketData.playerB.position;

                parent.add(new Kunai(parent, newLeft, you, false, true), parent.constraints);
            } else if (newLeft > left) {
                component.Right();
            } else if (newLeft < left) {
                component.Left();
            }

            left = newLeft;
        }

        parent.top = 0;
        parent.constraints.insets = new Insets(parent.top, left, postion, parent.right);
        parent.bagLayout.setConstraints(component, parent.constraints);

        parent.invalidate();
        parent.validate();
        parent.repaint();
    }
}

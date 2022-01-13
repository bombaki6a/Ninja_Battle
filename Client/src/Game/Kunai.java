package Game;

import Connections.SocketData;
import Connections.TransferPlayersData;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class resposible for shooting in the game
public class Kunai extends JPanel implements ActionListener {
    private Image image;
    private Integer width = 15;
    private Integer height = 60;

    private final Game parent;
    private final Boolean isHost;
    private final Boolean playerA;

    private Integer enemySize = 70;
    private Integer enemyHit = 120;
    private Integer hostHit = 250;

    private Integer top;
    private Integer right = 0;
    private Integer bottom = 0;

    private final Integer left;
    private final Integer enemy;

    private Integer hostTop = 300;
    private Integer enemyTop = -300;

    private Integer ms = 15;
    private Integer speed = 25;
    private Timer timer = new Timer(ms, this);

    private String upSprite = "Images/attack_up.png";
    private String downSprite = "Images/attack_down.png";

    private GridBagConstraints constraints = new GridBagConstraints();

    public Kunai(Game parent, Integer left, Integer enemy, Boolean isHost, Boolean playerA) {
        this.playerA = playerA;
        this.parent = parent;
        this.isHost = isHost;
        this.enemy = enemy;
        this.left = left;

        constraints.gridx = 0;
        constraints.gridy = 0;

        setOpaque(false);
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        if (isHost) {
            top = hostTop;
            image = Toolkit.getDefaultToolkit().getImage(upSprite);
        } else {
            top = enemyTop;
            image = Toolkit.getDefaultToolkit().getImage(downSprite);
        }

    }

    // Update function that responsible for shooting in game
    private void Update() {
        parent.remove(this);
        parent.repaint();
    }

    // Function that monitors whether a player has been hit
    private void Hit(Integer limit) {
        if ((left >= enemy - enemySize) && (left <= enemy + enemySize)) {
            if ((top < limit) && isHost) {
                timer.stop();
                Update();
            }

            if ((top > limit) && !isHost) {
                timer.stop();
                Update();

                if (playerA) {
                    SocketData.playerB.life -= 1;
                    TransferPlayersData.Send(SocketData.playerB);
                    parent.hearts.Remove(SocketData.playerB.life);

                    if (SocketData.playerB.life == 0) {
                        parent.SetWinner(SocketData.room.player1);
                    }
                } else {
                    SocketData.playerA.life -= 1;
                    TransferPlayersData.Send(SocketData.playerA);
                    parent.hearts.Remove(SocketData.playerA.life);

                    if (SocketData.playerA.life == 0) {
                        parent.SetWinner(SocketData.room.player2);
                    }
                }
            }
        }
    }

    // Drawing Kunai function
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        timer.start();
    }

    // Function performed when creating Kunai
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isHost) {
            if (top > -parent.getHeight()) {
                top -= speed;

                Hit((-parent.getHeight() + hostHit));

                constraints.insets = new Insets(top, left, bottom, right);
                parent.bagLayout.setConstraints(this, constraints);
            } else {
                timer.stop();
                Update();
            }
        } else {
            if (top < (parent.getHeight() - (height * 2))) {
                top += speed;

                Hit((parent.getHeight() - (height * 2)) - enemyHit);

                constraints.insets = new Insets(top, left, bottom, right);
                parent.bagLayout.setConstraints(this, constraints);
            } else {
                timer.stop();
                Update();
            }
        }

        revalidate();
    }
}

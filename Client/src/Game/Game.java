package Game;

import GUI.Page;
import GUI.Frame;

import Actions.ExitGame;

import Actions.PlayerMovement;
import Connections.DisconectHook;
import Connections.EnemyMovement;
import Connections.TransferPlayersData;

import java.awt.Insets;
import java.awt.GridBagConstraints;

// Class for the visual part of the game
public class Game extends Page {
    private Integer heatsWidth = 80;
    private Integer heatsHeight = 25;
    private Integer playerWidth = 60;
    private Integer playerHeight = 80;

    private Integer graundPosition;

    public Hearts hearts = new Hearts(heatsWidth, heatsHeight);

    private Player player1 = new Player(playerWidth, playerHeight);
    private Player player2 = new Player(playerWidth, playerHeight);

    private String player1_up = "Images/player1_attack.png";
    private String player1_left = "Images/player1_left.png";
    private String player1_right = "Images/player1_right.png";
    private String player1_down = "Images/player1_attack_down.png";

    private String player2_up = "Images/player2_attack.png";
    private String player2_left = "Images/player2_left.png";
    private String player2_right = "Images/player2_right.png";
    private String player2_down = "Images/player2_attack_down.png";

    public Frame parent;
    private PlayerMovement keys;

    private EnemyMovement enemy;
    private TransferPlayersData playerData;

    public Game(Frame parent, Integer width, Integer height, Boolean isHost) {
        super(width, height);
        this.parent = parent;

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new DisconectHook(isHost));

        if (isHost) {
            player1.SetSprites(player1_up, player1_left, player1_right);
            player2.SetSprites(player2_down, player2_left, player2_right);
        } else {
            player1.SetSprites(player1_down, player1_left, player1_right);
            player2.SetSprites(player2_up, player2_left, player2_right);
        }

        SetPlayers(isHost);

        if (isHost) {
            keys = new PlayerMovement(this, player1, graundPosition, isHost);
            parent.addKeyListener(keys);
            enemy = new EnemyMovement(this, player2, graundPosition, isHost);
        } else {
            keys = new PlayerMovement(this, player2, graundPosition, isHost);
            parent.addKeyListener(keys);
            enemy = new EnemyMovement(this, player1, graundPosition, isHost);
        }

        try {
            playerData = new TransferPlayersData(this, isHost, enemy);
            playerData.start();
        } catch (Exception e) {
            e.getStackTrace();
        }

        parent.requestFocus();
    }

    // Function that announces the winner
    public void SetWinner(String name) {
        parent.Add(new WinPage(name, getWidth(), getHeight()), 0);
        parent.repaint();
        parent.revalidate();
        parent.remove(hearts);
        parent.removeKeyListener(keys);
        parent.addKeyListener(new ExitGame());

        setVisible(false);
        parent.remove(this);
    }

    // Function that adjusts players
    private void SetPlayers(Boolean isHost) {
        constraints.gridy = 0;
        constraints.gridx = 0;

        right = (playerWidth / 2);
        top = (getHeight() / 2) + (playerHeight * 2);
        graundPosition = top;

        constraints.insets = new Insets(top, left, bottom, right);

        if (isHost) {
            add(player1, constraints);
            player1.Right();
        } else {
            add(player2, constraints);
            player2.Right();
        }

        parent.Add(hearts, 1, GridBagConstraints.LINE_START);

        top = 0;
        right = 0;
        left = (playerWidth / 2);
        bottom = (getHeight() / 2) + (playerHeight * 2);

        constraints.insets = new Insets(top, left, bottom, right);

        if (isHost) {
            add(player2, constraints);
            player2.Left();
        } else {
            add(player1, constraints);
            player1.Left();
        }
    }
}

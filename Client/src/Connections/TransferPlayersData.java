package Connections;

import Game.Game;

import Settings.Configs;
import SharedObjects.PlayerA;
import SharedObjects.PlayerB;

import java.net.InetAddress;
import java.net.DatagramPacket;

import java.io.ObjectOutput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// Thread to transfer and receive the player data
public class TransferPlayersData extends Thread {
    private final Boolean isHost;
    private final EnemyMovement enemy;

    private final Game parent;

    public TransferPlayersData(Game parent, Boolean isHost, EnemyMovement enemy) {
        this.parent = parent;
        this.isHost = isHost;
        this.enemy = enemy;
    }

    // Function for receiving player data
    public static Object Receive() {
        Object data;

        try {
            DatagramPacket packet = new DatagramPacket(SocketData.buffer, SocketData.buffer.length);
            SocketData.socketUDP.receive(packet);

            ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
            data = input.readObject();
            input.close();
        } catch (Exception e) {
            data = new Object();
            SocketData.socketUDP.close();
            e.getStackTrace();
        }

        return data;
    }

    // Function for sending the player data
    public static void Send(Object player) {
        try {
            ByteArrayOutputStream byteOuput = new ByteArrayOutputStream();
            ObjectOutput output = new ObjectOutputStream(byteOuput);

            output.writeObject(player);
            output.close();

            SocketData.buffer = byteOuput.toByteArray();

            InetAddress ip = InetAddress.getByName(Configs.getIp());
            Integer port = SocketData.room.port;

            DatagramPacket packet = new DatagramPacket(SocketData.buffer, SocketData.buffer.length, ip, port);
            SocketData.socketUDP.send(packet);
        } catch (Exception e) {
            SocketData.socketUDP.close();
            e.getStackTrace();
        }
    }

    // Function of the thread to receive the player data
    @Override
    public void run() {
        try {
            while (true) {
                if (isHost) {
                    SocketData.playerB = ((PlayerB) Receive());
                } else {
                    SocketData.playerA = ((PlayerA) Receive());
                }

                enemy.Update();

                if ((SocketData.playerB.life == 0) || (SocketData.playerA.life == 0)) {
                    if (SocketData.playerA.life == 0) {
                        parent.SetWinner(SocketData.room.player2);
                    } else {
                        parent.SetWinner(SocketData.room.player1);
                    }

                    break;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

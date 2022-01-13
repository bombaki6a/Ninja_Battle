package Servers;

import java.util.List;
import java.util.ArrayList;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.io.ObjectOutput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import SharedObjects.Room;
import SharedObjects.PlayerA;
import SharedObjects.PlayerB;

// The thread responsible for game created
public class GameRoom extends Thread {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] buffer = new byte[2048];

    private ObjectOutput output;
    private ObjectInputStream input;
    private ByteArrayOutputStream byteOutput;

    private Boolean isRunning = true;

    private List<Integer> ports = new ArrayList<Integer>();
    private List<InetAddress> addresses = new ArrayList<InetAddress>();

    public GameRoom(Room room) {
        try {
            socket = new DatagramSocket(room.port);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // Raceiving data from players
    private Object Receive() {
        Object data;

        try {
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            input = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
            data = input.readObject();
            input.close();

            addresses.add(packet.getAddress());
            ports.add(packet.getPort());
        } catch (Exception e) {
            socket.close();
            data = new Object();
            e.getStackTrace();
        }

        return data;
    }

    // Sending data to players
    private void Send(Object object, InetAddress address, Integer port) {
        try {
            byteOutput = new ByteArrayOutputStream();
            output = new ObjectOutputStream(byteOutput);

            output.writeObject(object);
            output.close();

            buffer = byteOutput.toByteArray();

            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            socket.close();
            e.getStackTrace();
        }
    }

    // The thread function for communication between clients
    @Override
    public void run() {
        PlayerA playerA;
        PlayerB playerB;

        playerA = (PlayerA) Receive();
        playerB = (PlayerB) Receive();

        if (playerB.position == null) {
            isRunning = false;
        }

        while (isRunning) {
            Object player = Receive();

            if (ports.indexOf(packet.getPort()) == 0) {
                playerA = (PlayerA) player;
                Send(playerA, addresses.get(1), ports.get(1));
            } else if (ports.indexOf(packet.getPort()) == 1) {
                playerB = (PlayerB) player;
                Send(playerB, addresses.get(0), ports.get(0));
            }

            if ((playerA.life == 0) || (playerB.life == 0)) {
                isRunning = false;
            }
        }
    }
}

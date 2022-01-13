package Connections;

import SharedObjects.Room;
import SharedObjects.PlayerA;
import SharedObjects.PlayerB;

import java.net.Socket;
import java.net.DatagramSocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Class with the shared app objects
public class SocketData {
    // Shared Objects
    public volatile static Room room = new Room();
    public volatile static PlayerA playerA = new PlayerA();
    public volatile static PlayerB playerB = new PlayerB();

    // TCP
    public volatile static Socket socketTCP;
    public volatile static ObjectInputStream input;
    public volatile static ObjectOutputStream output;

    // UDP
    public volatile static byte[] buffer;
    public volatile static DatagramSocket socketUDP;
}

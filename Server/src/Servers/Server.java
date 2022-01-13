package Servers;

import SharedObjects.Room;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.ServerSocket;

// The Server class
public class Server extends Thread {
    public volatile static Integer port;
    private volatile ServerSocket server;

    public volatile static Integer roomsPort;
    public volatile static List<Room> rooms = new ArrayList<Room>();

    public Server(Integer port) {
        Server.port = port;
    }

    // The thread function that accepts the clients
    @Override
    public void run() {
        try {
            server = new ServerSocket(port);

            while (true) {
                Socket socket = server.accept();
                ObjectOutputStream outputData = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputData = new ObjectInputStream(socket.getInputStream());

                ClientHandler client = new ClientHandler(socket, inputData, outputData);
                client.start();
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void StopServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}

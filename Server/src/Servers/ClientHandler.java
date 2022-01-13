package Servers;

import SharedObjects.Room;

import java.util.List;

import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// The thread each new client
public class ClientHandler extends Thread {
    private final Socket socket;
    private final ObjectInputStream inputData;
    private final ObjectOutputStream outputData;

    private GameRoom gameRoom;

    public ClientHandler(Socket socket, ObjectInputStream inputData, ObjectOutputStream outputData) {
        this.socket = socket;
        this.inputData = inputData;
        this.outputData = outputData;
    }

    // Function that is responsible for creating a room
    private void CreateRoom(List<Room> data) {
        data.get(0).port = Server.port += 1;
        Server.rooms.add(data.get(0));

        gameRoom = new GameRoom(data.get(0));
        gameRoom.start();
    }

    // Monitoring customer requirements
    @Override
    @SuppressWarnings("unchecked")
    public void run() {
        try {
            while (!socket.isClosed()) {
                List<Room> data = (List<Room>) inputData.readObject();
                outputData.reset();

                if (data.isEmpty()) {
                    outputData.writeObject(Server.rooms);
                } else if (data.get(0).status == Room.Status.CREAT) {
                    CreateRoom(data);
                    outputData.writeObject(Server.rooms);
                } else if (data.get(0).status == Room.Status.REMOVE) {
                    Server.rooms.remove((int) data.get(0).id);
                } else if (data.get(0).status == Room.Status.JOIN) {
                    Server.rooms.set(data.get(0).id, data.get(0));
                    outputData.writeObject(Server.rooms);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

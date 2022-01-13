package Connections;

import Settings.Configs;

import java.net.Socket;
import java.net.InetAddress;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Thread for connecting to the server, via TCP
public class Connection extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                try {
                    InetAddress ip = InetAddress.getByName(Configs.getIp());

                    if (SocketData.socketTCP == null) {
                        SocketData.socketTCP = new Socket(ip, Configs.getPort());
                        SocketData.input = new ObjectInputStream(SocketData.socketTCP.getInputStream());
                        SocketData.output = new ObjectOutputStream(SocketData.socketTCP.getOutputStream());
                    }

                    sleep(2000);
                } catch (Exception e) {
                    e.getStackTrace();
                }

                sleep(2000);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

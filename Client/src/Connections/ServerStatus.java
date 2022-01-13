package Connections;

import GUI.StatusLabel;

// Class to monitor the status of the server
public class ServerStatus extends Thread {
    private final StatusLabel status;

    public ServerStatus(StatusLabel status) {
        this.status = status;
        status.SetStatus(false);
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(2000);
                status.SetStatus(!SocketData.socketTCP.isClosed());
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}

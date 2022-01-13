package Connections;

// Thread that stats when app is closed
public class DisconectHook extends Thread {
    private final Boolean isHost;

    public DisconectHook(Boolean isHost) {
        this.isHost = isHost;
    }

    @Override
    public void run() {
        if (isHost) {
            SocketData.playerA.life = 0;
            TransferPlayersData.Send(SocketData.playerA);
        } else {
            SocketData.playerB.life = 0;
            TransferPlayersData.Send(SocketData.playerB);
        }
    }
}

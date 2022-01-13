package SharedObjects;

import java.io.Serializable;

public class Room implements Serializable {
    public static enum Status {
        NONE, JOIN, CREAT, REMOVE;
    }

    public Integer id = null;
    public Status status = Status.NONE;

    public String player1 = null;
    public String player2 = null;
    public Integer port;

    @Override
    public String toString() {
        return player1;
    }
}

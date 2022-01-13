package GUI;

import Fonts.Fonts;
import Colors.Colors;

import SharedObjects.Room;
import Connections.SocketData;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

// Thread that gets the list of rooms
public class RoomsList extends JList<Room> implements Runnable {
    public Boolean isRunning = true;

    private Integer cellWidth = 50;
    private List<Room> data = new ArrayList<Room>();

    public RoomsList() {
        setOpaque(false);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListRenderer renderer = new ListRenderer(Fonts.textFont, cellWidth);
        renderer.SetColors(Colors.ForegroundColor, Colors.background, Colors.hoverColor);

        setCellRenderer(renderer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run() {
        try {
            data = ((List<Room>) SocketData.input.readObject());
            setListData(data.toArray(Room[]::new));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

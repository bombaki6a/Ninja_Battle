package GUI;

import Fonts.Fonts;
import Colors.Colors;

import javax.swing.JPanel;

import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// Class that visualizes the status
public class StatusLabel extends JPanel {
    Integer top = 0;
    Integer left = 0;
    Integer bottom = 5;
    Integer right = 5;

    public static Integer LEFT = GridBagConstraints.WEST;
    public static Integer CENTE = GridBagConstraints.CENTER;
    public static Integer RIGHT = GridBagConstraints.EAST;

    private final static String statusText = "SERVER STATUS:";
    private final static String onlineText = "ONLINE";
    private final static String offlineText = "OFFLINE";

    private final static Label serverStatus = new Label(statusText, Colors.ForegroundColor);
    private final static Label onlineStatus = new Label(onlineText, Colors.statusOnline);
    private final static Label offlineStatus = new Label(offlineText, Colors.statusOffline);

    private final static GridBagConstraints constraints = new GridBagConstraints();

    public StatusLabel() {
        super(new GridBagLayout());
        setOpaque(false);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(top, left, bottom, right);

        serverStatus.setFont(Fonts.statusLabel);
        add(serverStatus, constraints);

        constraints.gridx = 1;

        onlineStatus.setFont(Fonts.statusLabel);
        add(onlineStatus, constraints);

        offlineStatus.setFont(Fonts.statusLabel);
        add(offlineStatus, constraints);
    }

    // Function that changes the status
    public void SetStatus(Boolean isOnline) {
        if (isOnline) {
            onlineStatus.setVisible(isOnline);
            offlineStatus.setVisible(!isOnline);
        } else {
            offlineStatus.setVisible(!isOnline);
            onlineStatus.setVisible(isOnline);
        }
    }
}

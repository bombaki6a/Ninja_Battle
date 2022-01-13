package GUI;

import Actions.Back;
import Actions.JoinGame;

import Fonts.Fonts;
import Colors.Colors;

import SharedObjects.Room;

import java.awt.Insets;
import java.awt.GridBagConstraints;

// Class that visualizes the rooms page
public class RoomsPage extends Page {
    private final static String joinText = "JOIN";
    private final static String backText = "BACK";

    private final static Integer buttonWidth = 160;
    private final static Integer buttonHeight = 50;
    private final static Integer paneWidth = 360;
    private final static Integer paneHeight = 250;

    private RoomsList roomsList = new RoomsList();
    private ScrollPane scrollPanel = new ScrollPane(roomsList);

    private final static Integer thickness = 2;
    private final static Integer fixPaneWidth = 30;

    private final static Button joinButton = new Button(joinText);
    private final static Button backButton = new Button(backText);

    public RoomsPage(Frame parent, Integer width, Integer height) {
        super(width, height);

        bottom = 10;
        left = (getWidth() / 2) - paneWidth + fixPaneWidth;
        top = (getHeight() / 2) - paneHeight + (buttonHeight - bottom);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(top, left, bottom, right);

        Thread listening = new Thread(roomsList);
        listening.start();

        scrollPanel.SetSize(paneWidth, paneHeight);
        scrollPanel.SetBackground(Colors.background);
        scrollPanel.SetBorder(Colors.borderColor, thickness);
        add(scrollPanel, constraints);

        top = 0;

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(top, left, bottom, right);

        joinButton.setFont(Fonts.buttonsFont);
        joinButton.SetSize(buttonWidth, buttonHeight);
        joinButton.setForeground(Colors.ForegroundColor);
        joinButton.SetListening(new JoinGame(parent));
        add(joinButton, constraints);

        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(top, left, bottom, right);

        backButton.setFont(Fonts.buttonsFont);
        backButton.SetSize(buttonWidth, buttonHeight);
        backButton.setForeground(Colors.ForegroundColor);
        backButton.SetListening(new Back(parent));
        add(backButton, constraints);

        left = 0;
        bottom = 0;
    }

    public Room GetRoom() {
        return roomsList.getSelectedValue();
    }
}

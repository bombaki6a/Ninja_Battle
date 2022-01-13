package GUI;

import Fonts.Fonts;
import Colors.Colors;

import Actions.Info;
import Actions.Exit;
import Actions.LoadGame;
import Actions.CreateGame;

import java.awt.Insets;
import java.awt.GridBagConstraints;

// Class that visualizes the app main page
public class MainPage extends Page {
    private final static Integer buttonWidth = 250;
    private final static Integer buttonHeight = 60;

    private final static String createButtonText = "CREATE GAME";
    private final static String loadButtonText = "LOAD GAME";
    private final static String infoButtonText = "INFORMATION";
    private final static String exitButtonText = "EXIT";

    private final static String infoUrl = "https://github.com/bombaki6a/Ninja_Battle/";

    private final static Button createButton = new Button(createButtonText);
    private final static Button loadButton = new Button(loadButtonText);
    private final static Button infoButton = new Button(infoButtonText);
    private final static Button exitButton = new Button(exitButtonText);

    public MainPage(Frame parent, Integer width, Integer height) {
        super(width, height);

        left = buttonWidth / 2;
        top = (getHeight() / 2) - ((int) (buttonHeight * 3));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(top, left, bottom, right);

        // Create game button
        createButton.setFont(Fonts.buttonsFont);
        createButton.SetSize(buttonWidth, buttonHeight);
        createButton.setForeground(Colors.ForegroundColor);
        createButton.SetListening(new CreateGame(parent));
        add(createButton, constraints);

        top = 10;

        // Load game button
        constraints.gridy = 1;
        constraints.insets = new Insets(top, left, bottom, right);

        loadButton.setFont(Fonts.buttonsFont);
        loadButton.SetSize(buttonWidth, buttonHeight);
        loadButton.setForeground(Colors.ForegroundColor);
        loadButton.SetListening(new LoadGame(parent));
        add(loadButton, constraints);

        // Information button
        constraints.gridy = 2;

        infoButton.setFont(Fonts.buttonsFont);
        infoButton.SetSize(buttonWidth, buttonHeight);
        infoButton.setForeground(Colors.ForegroundColor);
        infoButton.SetListening(new Info(infoUrl));
        add(infoButton, constraints);

        // Exit app button
        constraints.gridy = 3;

        exitButton.setFont(Fonts.buttonsFont);
        exitButton.SetSize(buttonWidth, buttonHeight);
        exitButton.setForeground(Colors.ForegroundColor);
        exitButton.SetListening(new Exit(parent));
        add(exitButton, constraints);

        top = 0;
        left = 0;
        bottom = 0;
    }
}

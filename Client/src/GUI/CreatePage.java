package GUI;

import Actions.Back;

import Fonts.Fonts;
import Colors.Colors;

import java.awt.Insets;
import java.awt.GridBagConstraints;

// Class that visualizes the game creation page
public class CreatePage extends Page {
    private final static Integer labelWidth = 500;
    private final static Integer labelHeight = 60;

    private final static Integer buttonWidth = 260;
    private final static Integer buttonHeight = 60;

    private final static String labelText = "Waiting for another player...";
    private final static String buttonText = "BACK";

    private final static Button backButton = new Button(buttonText);
    private final static Label label = new Label(labelText, Colors.ForegroundColor);

    public CreatePage(Frame parent, Integer width, Integer height) {
        super(width, height);

        top = ((int) ((getHeight() / 2) - (buttonHeight * 1.5)));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(top, left, bottom, right);

        label.setFont(Fonts.textFont);
        label.SetSize(labelWidth, labelHeight);
        add(label, constraints);

        top = ((int) (buttonHeight * 1.5));
        constraints.gridy = 1;
        constraints.insets = new Insets(top, left, bottom, right);

        backButton.setFont(Fonts.buttonsFont);
        backButton.SetSize(buttonWidth, buttonHeight);
        backButton.setForeground(Colors.ForegroundColor);
        backButton.SetListening(new Back(parent, true));
        add(backButton, constraints);

        top = 0;
    }
}

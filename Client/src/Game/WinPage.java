package Game;

import GUI.Page;
import GUI.Label;

import Fonts.Fonts;
import Colors.Colors;

import java.awt.Dimension;

// Class that visualizes the win page
public class WinPage extends Page {
    private Integer labelWidth = 500;
    private Integer labelHeight = 60;

    private String isWinner = "The Winner is ";
    private String exitText = "Press ENTER to exit...";

    public WinPage(String winner, Integer width, Integer height) {
        super(width, height);

        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setBackground(Colors.opacity);

        Label labelWinner = new Label(isWinner + winner, Colors.ForegroundColor);
        labelWinner.SetSize(labelWidth, labelHeight);
        labelWinner.setFont(Fonts.winnerLabel);

        add(labelWinner, constraints);

        Label exitLable = new Label(exitText, Colors.ForegroundColor);
        exitLable.SetSize(labelWidth, labelHeight);
        exitLable.setFont(Fonts.statusLabel);

        constraints.gridy = 1;
        add(exitLable, constraints);
    }
}

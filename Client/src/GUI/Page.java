package GUI;

import Connections.WaitingPlayer;

import javax.swing.JPanel;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// The main class of the app pages
public class Page extends JPanel {
    public Integer top = 0;
    public Integer left = 0;
    public Integer bottom = 0;
    public Integer right = 0;

    public Integer width;
    public Integer height;
    public String backgroundPath;

    public Page afterPage = null;

    private WaitingPlayer waitPlayer;

    public volatile GridBagLayout bagLayout = new GridBagLayout();
    public volatile GridBagConstraints constraints = new GridBagConstraints();

    public Page(Integer width, Integer height) {
        setLayout(bagLayout);
        setOpaque(true);
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Function for setting the previous page
    public void SetAfterPage(Page aftePage) {
        this.afterPage = aftePage;
    }

    // Fucntion for setting of wait player event
    public void SetWaitPlayer(WaitingPlayer waitPlayer) {
        this.waitPlayer = waitPlayer;
    }

    // Player wait event stop function
    public void StopWaithingPlayer() {
        waitPlayer.isRunning = false;
    }

    // Function that sets background image on the page
    public void SetBackgroundImage(String path, Integer width, Integer height) {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = getWidth();
        constraints.gridheight = getHeight();
        constraints.insets = new Insets(top, left, bottom, right);

        this.width = width;
        this.height = height;
        this.backgroundPath = path;

        BackgroundImage backgroind = new BackgroundImage(path, width, width);
        add(backgroind, constraints);
    }
}

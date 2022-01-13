import GUI.Frame;
import GUI.MainPage;
import GUI.StatusLabel;

import Colors.Colors;
import Connections.Connection;
import Connections.ServerStatus;

public class App {
    public static void main(String[] args) {
        Integer width = 800;
        Integer height = 600;
        Integer backgroundWidth = 500;
        Integer backgroundHeight = 500;

        String title = "Ninja Battle";
        String iconPath = "Images/icon.png";
        String backgroundPath = "Images/background.png";

        StatusLabel status = new StatusLabel();
        Frame window = new Frame(title, width, height);
        MainPage mainPage = new MainPage(window, width, height);

        Connection connection = new Connection();
        ServerStatus serverStatus = new ServerStatus(status);
        connection.start();
        serverStatus.start();

        window.SetIcon(iconPath);

        mainPage.setBackground(Colors.background);
        mainPage.SetBackgroundImage(backgroundPath, backgroundWidth, backgroundHeight);

        window.Add(mainPage, 0);
        window.Add(status, 1, StatusLabel.RIGHT);

        window.SetBackgroundColor(Colors.background);
        window.setVisible(true);
    }
}

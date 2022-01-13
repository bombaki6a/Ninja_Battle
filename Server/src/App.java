import Servers.Server;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    private static Integer getPort(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception e) {
            e.getStackTrace();
            return 6666;
        }
    }

    public static void main(String[] args) {
        Integer port = getPort(args);
        Server server = new Server(port);
        server.start();

        System.out.println("Server is started!");

        while (true) {
            if (scanner.nextLine().equals("Stop")) {
                server.StopServer();
                break;
            }
        }

        System.out.println("Server is stopped!");
    }
}

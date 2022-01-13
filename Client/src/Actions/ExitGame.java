package Actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Class to exit the game
public class ExitGame implements KeyListener {
    private final static Integer status = 0;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Function that starts when a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            System.exit(status);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

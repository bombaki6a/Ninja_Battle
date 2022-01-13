package GUI;

import SharedObjects.Room;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ListCellRenderer;

// Class to edit the design of the lists
public class ListRenderer extends JPanel implements ListCellRenderer<Room> {
    private volatile Color selected;
    private volatile Color forgraound;
    private volatile Color background;

    private final static JLabel label = new JLabel();

    public ListRenderer(Font font, Integer cellHeight) {
        setOpaque(false);
        setLayout(new BorderLayout());

        label.setFont(font);
        label.setOpaque(true);
        label.setSize(getWidth(), cellHeight);
        label.setPreferredSize(new Dimension(getWidth(), cellHeight));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        add(label);
    }

    // Colors settings function
    public void SetColors(Color foregraund, Color background, Color selected) {
        this.selected = selected;
        this.forgraound = foregraund;
        this.background = background;
    }

    // List renderer function
    @Override
    public Component getListCellRendererComponent(JList<? extends Room> list, Room value, int index, boolean isSelected,
            boolean cellHasFocus) {

        label.setText(value.player1);

        if (isSelected) {
            label.setBackground(selected);
            label.setForeground(forgraound);
        } else {
            label.setBackground(background);
            label.setForeground(forgraound);
        }

        return this;
    }
}

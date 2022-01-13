package Fonts;

import java.awt.Font;

// All fonts used in the app
public interface Fonts {
    final static String lableFont = "Calibri";
    final static String mainFount = "Cambria";

    final static Integer lableFontSize = 26;
    final static Integer buttonFontSize = 28;
    final static Integer statusLabelSize = 20;
    final static Integer winnerLabelSize = 36;

    Font textFont = new Font(lableFont, Font.PLAIN, lableFontSize);
    Font buttonsFont = new Font(mainFount, Font.PLAIN, buttonFontSize);
    Font statusLabel = new Font(mainFount, Font.PLAIN, statusLabelSize);
    Font winnerLabel = new Font(mainFount, Font.PLAIN, winnerLabelSize);
}

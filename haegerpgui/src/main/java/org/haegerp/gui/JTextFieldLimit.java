package org.haegerp.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

;

/**
 * Diese Klasse kontrolliert das Maximum Große, dass ein TextField haben darf
 *
 * @author Wolf
 */
public class JTextFieldLimit extends PlainDocument {

    private static final long serialVersionUID = 1L;
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    /**
     * Wenn die Große mehr als die Begrenzung ist, dann sind die folgende
     * Einträge ignoriert
     *
     * @param offset 
     * @param str Inhalt des Textfields
     * @param attr Werte vom Textfields
     * @throws BadLocationException
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        } else {
            super.insertString(offset, str.substring(0, limit), attr);
        }
    }
}

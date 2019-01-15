/*
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org/>
 */

package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * PasswordDialog class.
 */
public class PasswordDialog {
    /**
     * 
     * 
     * @param parentComponent
     * @param message
     * @param title
     * @return 
     */
    public static String showDialog(Component parentComponent, String message, String title) {
        JPanel panel = new JPanel();
        JLabel messageLabel = new JLabel(message);
        JPasswordField passValue = new JPasswordField();
        
        /* Add panel elements*/
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints;
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 0, 2, 0);
        panel.add(messageLabel, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(2, 0, 0, 0);
        panel.add(passValue, constraints);
        
        if (JOptionPane.showConfirmDialog(parentComponent, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
            char[] array = passValue.getPassword();
            String pass = String.copyValueOf(array);
            Arrays.fill(array, '\0');

            return pass;
        }
        
        return null;
    }
}

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

package main;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ArticlesTable;
import model.DBManager;
import view.AboutDialog;
import view.FileChooser;
import view.MainWindow;
import view.SettingsDialog;

/**
 * Price Updater main class
 */
public class PriceUpdater {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* MVC objects */
        DBManager dbManager;
        ArticlesTable articles = new ArticlesTable();
        MainWindow mainWindow = new MainWindow();
        FileChooser chooser = new FileChooser();
        SettingsDialog settingsDialog = new SettingsDialog(mainWindow);
        AboutDialog aboutDialog = new AboutDialog(mainWindow);
        Controller controller = new Controller();
        
        /* Initialize objects */
        try {
            dbManager = new DBManager();
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: Microsoft SQL Server JDBC driver could not be found");
            Logger.getLogger(PriceUpdater.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se consigue el driver Microsoft SQL Server JDBC", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            dbManager = null;
        }
        
        /* Initialize MVC */
        controller.setManager(dbManager);
        controller.setArticlesTable(articles);
        controller.setMainWindow(mainWindow);
        controller.setFileChooser(chooser);
        controller.setSettings(settingsDialog);
        controller.setAbout(aboutDialog);
        
        if (!controller.initMVC()) {
            System.err.println("Error: could not initialize MVC application");
            JOptionPane.showMessageDialog(null, "No se puede iniciar la aplicación MVC", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
}

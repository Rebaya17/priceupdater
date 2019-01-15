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

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.DBManager;
import model.ArticlesTable;
import static model.Kryptos.decode;
import static model.Kryptos.encode;
import static model.Kryptos.sha256;
import view.AboutDialog;
import view.FileChooser;
import view.MainWindow;
import view.PasswordDialog;
import view.SettingsDialog;

/**
 * Controller class 
 */
public class Controller extends WindowAdapter implements ChangeListener, ActionListener {
    private static final String KEY = "eIR2doSBcXJ3dnSCdoR1dIaBd3WBdHl3hoN2g3NyhXN5cHdxcnJ4goJzcHiGhXF2dHVwhXJxgoVwdnhwcXJ0cw===64";
    
    private static final String HOST = "XVxYUVVKTFhV=9";
    private static final String PORT = "Nzc4NQ===4";
    private static final String INSTANCE = "XV1PXFpiT1ZbXQ===10";
    private static final String DATABASE = "TVtJSklcSUw==8";
    private static final String USER = "VklXWQ===4";
    private static final String RATIO = "OjY4MzU==5";
    
    private static Preferences pref;
    
    private DBManager dbManager;
    private ArticlesTable articles;
    private MainWindow mainWindow;
    private FileChooser chooser;
    private SettingsDialog settingsDialog;
    private AboutDialog aboutDialog;
    

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        
        /* JSpinner */
        if (source instanceof JSpinner) {
            float ratio = mainWindow.getRatio() / 100.0F;
            
            if (ratio != 0F) {
                articles.setRatio(ratio);
                pref.put("ratio", encode(valueOf(ratio)));
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Command: " + command);
        
        /* NULL command */
        if (command == null) {
            System.err.println("Error: empty command");
            return;
        }
        
        /* Handle command */
        switch (command) {
            /* File menu */
            case "clearFiltersItem": mainWindow.clearFilters(); return;
            case "importItem": importFile(); return;
            case "consultItem": consult(); return;
            case "exitItem": exit(); return;

            /* Connection menu */
            case "connectItem": connect(); return;
            case "disconnectItem": disconnect(); return;

            /* Help menu */
            case "aboutItem": about(); return;

            
            /* Text fields*/
            case "codValue": consult(); return;
            case "desValue": consult(); return;
            
            /* Buttons */
            case "consultButton": consult(); return;
            case "updateButton": update(); return;


            /* Settings dialog */
            case "editSettingsButton": editConnection(); return;
            case "acceptSettingsButton": closeSettings(SettingsDialog.ACCEPTED); return;
            case "cancelSettingsButton": closeSettings(SettingsDialog.CANCELLED); return;

            /* About dialog */
            case "closeAboutButton": aboutDialog.close(); return;

            /* Error */
            default: System.err.println("Error: can't find action \"" + command + "\"");
        } 
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        Object source = e.getSource();
        
        /* Close main window */
        if (source instanceof MainWindow)
            exit();
        
        /* Close settingsDialog dialog */
        else if (source instanceof SettingsDialog)
            settingsDialog.close(SettingsDialog.CANCELLED);
    }
    
    /**
     * Creates new Controller
     */
    public Controller() {
        pref = Preferences.userRoot().node("priceupdater");
        
        dbManager = null;
        articles = null;
        mainWindow = null;
        chooser = null;
        settingsDialog = null;
        aboutDialog = null;
    }
    
    /**
     * Restore stored settings into settings dialog
     */
    private void restoreSettings() {
        int portVal;
        String host = decode(pref.get("host", HOST));
        String port = decode(pref.get("port", PORT));
        String instance = decode(pref.get("instance", INSTANCE));
        String db = decode(pref.get("db", DATABASE));
        String id = decode(pref.get("id", USER));
        
        try {portVal = parseInt(port);} catch (NumberFormatException ex) {portVal = parseInt(decode(PORT));}
        
        settingsDialog.setHost(host);
        settingsDialog.setPort(portVal);
        settingsDialog.setInstance(instance);
        settingsDialog.setDB(db);
        settingsDialog.setID(id);
        settingsDialog.setPass("");
    }
    
    /**
     * Initialize Model-View-Controller.
     *
     * @return True if all is well.
     */
    public boolean initMVC()
    {
        /* Check MVC objects */
        if (dbManager == null) return false;
        if (articles == null) return false;
        if (mainWindow == null) return false;
        if (chooser == null) return false;
        if (settingsDialog == null) return false;
        if (aboutDialog == null) return false;
        
        /* Establish connection with controller */
        mainWindow.setController((ActionListener) this);
        mainWindow.setController((ChangeListener) this);
        mainWindow.addWindowListener(this);
        
        settingsDialog.setController(this);
        settingsDialog.addWindowListener(this);
        
        aboutDialog.setController(this);
        
        /* Set icon and show main window */
        settingsDialog.setIconImage(mainWindow.getIconImage());
        aboutDialog.setIconImage(mainWindow.getIconImage());
        mainWindow.setVisible(true);
        
        /* Set settings hash */
        settingsDialog.setKey(KEY);
        
        /* First connection */
        mainWindow.setConnected(false);
        connect();
        
        return true;
    }
    
    /**
     * Edit the connection value settings
     */
    public void editConnection() {
        /* Update status */
        boolean editable = settingsDialog.isEditable();
        settingsDialog.setEditable(settingsDialog.isEditable());
        
        /* Validate empty fields */
        if (!editable && settingsDialog.hasEmptyConnectionFields()) {
            editable = true;
            settingsDialog.setEditable(true);
            System.err.println("Error: Settings dialog can't have empty fields");
            JOptionPane.showMessageDialog(settingsDialog, "Error: No pueden haber campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        /* Finish if is unlocked */
        if (editable)
            return;
        
        
        /* Ask password */
        settingsDialog.authenticate(encode(sha256(PasswordDialog.showDialog(settingsDialog, "Ingrese la contraseña de Price Updater", "Seguridad"))));
        
        switch (settingsDialog.getAuthStatus()) {
            case SettingsDialog.ACCEPTED:
                pref.put("host", encode(settingsDialog.getHost()));
                pref.put("port", encode(valueOf(settingsDialog.getPort())));
                pref.put("instance", encode(settingsDialog.getInstance()));
                pref.put("db", encode(settingsDialog.getDB()));
                pref.put("id", encode(settingsDialog.getID()));
                return;
            
            case SettingsDialog.CANCELLED:
                editable = false;
                break;
                
            case SettingsDialog.BAD:
                editable = false;
                System.err.println("Error: invalid password");
                JOptionPane.showMessageDialog(settingsDialog, "Contraseña inválida", "Error", JOptionPane.ERROR_MESSAGE);
        }

        /* Restore values */
        restoreSettings();

        settingsDialog.setEditable(editable);
    }
    
    /**
     * Close the settings dialog with the selected status
     * 
     * @param status ACCEPTED or CANCELED
     */
    public void closeSettings(int status) {
        if (status == SettingsDialog.ACCEPTED && settingsDialog.hasEmptyFields()) {
            System.err.println("Error: Settings dialog can't have empty fields");
            JOptionPane.showMessageDialog(settingsDialog, "Error: No pueden haber campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (status == SettingsDialog.ACCEPTED && settingsDialog.isEditable()) {
            settingsDialog.setEditable(false);
            editConnection();

            status = settingsDialog.getAuthStatus();
            
            if (status == SettingsDialog.CANCELLED) {
                settingsDialog.close(SettingsDialog.BAD);
                return;
            }
        }
        
        /* Close settings dialog */
        settingsDialog.close(status);
        
        if (status != SettingsDialog.ACCEPTED)
            restoreSettings();
    }
    
    /**
     * Establish connect with database
     */
    public void connect() {
        /* Set connection values and show settingsDialog dialog */
        restoreSettings();
        settingsDialog.setEditable(false);
        
        settingsDialog.focusPass();
        settingsDialog.setVisible(true);
        
        /* If accepted is pressed */
        if (settingsDialog.getOption() == SettingsDialog.ACCEPTED) {
            try {
                /* Lock window and connect */
                mainWindow.setMenusEnabled(false);
                dbManager.connect(settingsDialog.getHost(), settingsDialog.getPort(), settingsDialog.getInstance(), settingsDialog.getDB(), settingsDialog.getID(), settingsDialog.getPass());
                
                /* Validate connection */
                if (dbManager.isConnected()) {
                    /* Load ratio value */
                    float ratioVal;
                    String ratio = decode(pref.get("ratio", RATIO));
                    try {ratioVal = parseFloat(ratio);} catch (NumberFormatException ex) {ratioVal = parseFloat(decode(RATIO));}
                    articles.setRatio(ratioVal);
                    
                    /* Set connected */
                    System.out.println("Connected");
                    mainWindow.setConnected(true);
                    mainWindow.setRatio(ratioVal * 100.0F);
                    consult();
                
                /* No connected */
                } else {
                    throw new SQLException();
                }
            
            /* Exception */
            } catch (SQLException ex) {
                mainWindow.setConnected(false);
                System.err.println("Error: can't connect with database");
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error: Los datos introducidos no son válidos", JOptionPane.ERROR_MESSAGE);
            
            /* Unlock window */
            } finally {
                mainWindow.setMenusEnabled(true);
            }
        }
    }
    
    /**
     * Disconnect of database
     */
    public void disconnect() {
        if (articles.getModified().length != 0) {
            switch (JOptionPane.showConfirmDialog(mainWindow, "¿Desea aplicar los cambios antes de cerrar la conexión a la base de datos?", "Cerrar conexión", JOptionPane.YES_NO_CANCEL_OPTION)) {
                case JOptionPane.CANCEL_OPTION: return;
                case JOptionPane.YES_OPTION: update();
            }
        }
        
        mainWindow.setConnected(false);
        
        try {
            dbManager.disconnect();
        } catch (SQLException ex) {
            System.err.println("Error: can't disconnect to database");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error: No se pudo desconectar correctamente de la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Get articles from database
     */
    public void consult() {
        if (articles.getModified().length != 0) {
            switch (JOptionPane.showConfirmDialog(mainWindow, "¿Desea aplicar los cambios antes de hacer una nueva consulta?", "Guardar cambios", JOptionPane.YES_NO_CANCEL_OPTION)) {
                case JOptionPane.CANCEL_OPTION: return;
                case JOptionPane.YES_OPTION: update();
            }
        }
        
        String cod = "articulo.co_art LIKE '%" + mainWindow.getCod() + "%'";
        String des = "articulo.art_des LIKE '%" + mainWindow.getDes() + "%'";
        
        /* Build query */
        String query = "SELECT DISTINCT"
                + " RTRIM(articulo.co_art) AS cod,"
                + " RTRIM(articulo.art_des),"
                + " precio.monto"
                + " FROM dbo.saArticulo articulo"
                + " INNER JOIN dbo.saArtPrecio precio"
                + " ON articulo.co_art = precio.co_art";
        
        if (!cod.isEmpty() && des.isEmpty())
            query += " WHERE " + cod;
        
        else if (cod.isEmpty() && !des.isEmpty())
            query += " WHERE " + des;
        
        else if (!cod.isEmpty() && !des.isEmpty())
            query += " WHERE " + cod + " AND " + des;
        
        query += " ORDER BY cod";
        
        /* Consult to database and set articles*/
        try {
            articles.clear();
            dbManager.executeQuery(query, articles);
            
            mainWindow.setTable(articles);
        } catch (SQLException ex) {
            System.err.println("Error: can't run the query or connecting with database");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error realizando la consulta a la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Get articles from excel file
     */
    public void importFile() {
        switch (chooser.showOpenDialog(mainWindow)) {
            /* Import from excel */
            case FileChooser.APPROVE_OPTION:
                if (articles.getModified().length != 0) {
                    switch (JOptionPane.showConfirmDialog(mainWindow, "¿Desea aplicar los cambios antes de hacer importar el archivo?", "Guardar cambios", JOptionPane.YES_NO_CANCEL_OPTION)) {
                        case JOptionPane.CANCEL_OPTION: return;
                        case JOptionPane.YES_OPTION: update();
                    }
                }
                
                try {
                    articles.clear();
                    articles.read(chooser.getSelectedFile().getAbsolutePath());
            
                    mainWindow.setTable(articles);
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error importando hoja de cálculo", JOptionPane.ERROR_MESSAGE);
                }
            
            /* Import cancelled */
            case FileChooser.CANCEL_OPTION:
                System.out.println("Canceled");
                return;
            
            /* Error */
            case FileChooser.ERROR_OPTION:
                System.err.println("An error has occurred with the file chooser.");
        }
    }
    
    /**
     * Update database
     */
    public void update() {
        int[] modified = articles.getModified();
        
        /* Update modified rows */
        try {
            for (int i = 0; i < modified.length; i++) {
                int row = modified[i];
                
                String statement = "UPDATE dbo.saArtPrecio"
                        + " SET monto = " + articles.getValueAt(row, 2).toString() + ","
                        + " desde = GETDATE()"
                        + " WHERE co_art = '" + articles.getValueAt(row, 0).toString() + "'";
                
                dbManager.executeStatement(statement);
            }
            
        /* Error */
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error aplicando actualizaciones", JOptionPane.ERROR_MESSAGE);
        }
        
        /* Clear modified rows */
        articles.clearModified();
    }
    
    /**
     * Show about dialog
     */
    public void about() {
        aboutDialog.setVisible(true);
    }
    
    /**
     * Exit from application
     */
    public void exit() {
        disconnect();
        
        try {
            if (!dbManager.isConnected())
                mainWindow.dispose();
        } catch (SQLException ex) {
            System.err.println("Error: can't disconnect to database");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error: No se pudo desconectar correctamente de la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /* Setters */
    
    /**
     * Set the data base dbManager
     * 
     * @param model Data base dbManager
     */
    public void setManager(DBManager model) {
        dbManager = model;
    }
    
    /**
     * Set the articles table model
     * 
     * @param model Articles table model
     */
    public void setArticlesTable(ArticlesTable model) {
        articles = model;
    }
    
    /**
     * Set the main window
     * 
     * @param view Main window
     */
    public void setMainWindow(MainWindow view) {
        mainWindow = view;
    }
    
    /**
     * Set the file chooser
     * 
     * @param view File chooser
     */
    public void setFileChooser(FileChooser view) {
        chooser = view;
    }
    
    /**
     * Set the settingsDialog dialog
     * 
     * @param view Settings dialog
     */
    public void setSettings(SettingsDialog view) {
        settingsDialog = view;
    }
    
    /**
     * Set the aboutDialog dialog
     * 
     * @param view About dialog
     */
    public void setAbout(AboutDialog view) {
        aboutDialog = view;
    }
}

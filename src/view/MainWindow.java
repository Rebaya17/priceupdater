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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * MainWindow class
 */
public class MainWindow extends JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        // Set the native look and feel
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        queryPanel = new javax.swing.JPanel();
        javax.swing.JLabel codLabel = new javax.swing.JLabel();
        codValue = new javax.swing.JTextField();
        javax.swing.JLabel desLabel = new javax.swing.JLabel();
        desValue = new javax.swing.JTextField();
        consultButton = new javax.swing.JButton();
        panelSeparator = new javax.swing.JSeparator();
        javax.swing.JLabel ratioLabel = new javax.swing.JLabel();
        ratioValue = new javax.swing.JSpinner();
        updateButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        rowCount = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        importItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator separator1 = new javax.swing.JPopupMenu.Separator();
        clearFiltersItem = new javax.swing.JMenuItem();
        consultItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator separator2 = new javax.swing.JPopupMenu.Separator();
        exitItem = new javax.swing.JMenuItem();
        connectionMenu = new javax.swing.JMenu();
        connectItem = new javax.swing.JMenuItem();
        disconnectItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualizador de Precios");
        setIconImage(new ImageIcon(getClass().getResource("/resources/icon32.png")).getImage());
        setName("mainWindow"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        queryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        queryPanel.setName("queryPanel"); // NOI18N
        queryPanel.setLayout(new java.awt.GridBagLayout());

        codLabel.setText("Código:");
        codLabel.setName("codLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        queryPanel.add(codLabel, gridBagConstraints);

        codValue.setToolTipText("Filtrar por código");
        codValue.setName("codValue"); // NOI18N
        codValue.setActionCommand(codValue.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 5);
        queryPanel.add(codValue, gridBagConstraints);

        desLabel.setText("Descripción:");
        desLabel.setName("desLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        queryPanel.add(desLabel, gridBagConstraints);

        desValue.setToolTipText("Filtrar por descripción");
        desValue.setName("desValue"); // NOI18N
        desValue.setActionCommand(desValue.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        queryPanel.add(desValue, gridBagConstraints);

        consultButton.setMnemonic('O');
        consultButton.setText("Consultar");
        consultButton.setToolTipText("Consulta con los criterios indicados");
        consultButton.setName("consultButton"); // NOI18N
        consultButton.setActionCommand(consultButton.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        queryPanel.add(consultButton, gridBagConstraints);

        panelSeparator.setForeground(java.awt.Color.lightGray);
        panelSeparator.setName("panelSeparator"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        queryPanel.add(panelSeparator, gridBagConstraints);

        ratioLabel.setText("Relación precio - costo:");
        ratioLabel.setName("ratioLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        queryPanel.add(ratioLabel, gridBagConstraints);

        ratioValue.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.01f));
        ratioValue.setEnabled(false);
        ratioValue.setName("ratioValue"); // NOI18N
        ratioValue.setPreferredSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        queryPanel.add(ratioValue, gridBagConstraints);

        updateButton.setMnemonic('P');
        updateButton.setText("Aplicar");
        updateButton.setToolTipText("Aplicar cambios en la base de datos");
        updateButton.setName("updateButton"); // NOI18N
        updateButton.setActionCommand(updateButton.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        queryPanel.add(updateButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(queryPanel, gridBagConstraints);

        scrollPane.setBorder(null);
        scrollPane.setName("scrollPane"); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setName("table"); // NOI18N
        scrollPane.setViewportView(table);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(scrollPane, gridBagConstraints);

        rowCount.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        rowCount.setName("rowCount"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(rowCount, gridBagConstraints);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('A');
        fileMenu.setText("Archivo");
        fileMenu.setToolTipText("");
        fileMenu.setName("fileMenu"); // NOI18N

        importItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        importItem.setText("Importar ...");
        importItem.setName("importItem"); // NOI18N
        importItem.setActionCommand(importItem.getName());
        fileMenu.add(importItem);
        fileMenu.add(separator1);

        clearFiltersItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        clearFiltersItem.setText("Limpiar filtros");
        clearFiltersItem.setName("clearFiltersItem"); // NOI18N
        clearFiltersItem.setActionCommand(clearFiltersItem.getName());
        fileMenu.add(clearFiltersItem);

        consultItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        consultItem.setText("Consultar");
        consultItem.setName("consultItem"); // NOI18N
        consultItem.setActionCommand(consultItem.getName());
        fileMenu.add(consultItem);

        separator2.setName("separator2"); // NOI18N
        fileMenu.add(separator2);

        exitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitItem.setMnemonic('S');
        exitItem.setText("Salir");
        exitItem.setName("exitItem"); // NOI18N
        exitItem.setActionCommand(exitItem.getName());
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        connectionMenu.setMnemonic('C');
        connectionMenu.setText("Conexión");
        connectionMenu.setName("connectionMenu"); // NOI18N

        connectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        connectItem.setMnemonic('C');
        connectItem.setName("connectItem"); // NOI18N
        connectItem.setActionCommand(connectItem.getName());
        connectionMenu.add(connectItem);

        disconnectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        disconnectItem.setMnemonic('D');
        disconnectItem.setText("Desconectar");
        disconnectItem.setName("disconnectItem"); // NOI18N
        disconnectItem.setActionCommand(disconnectItem.getName());
        connectionMenu.add(disconnectItem);

        menuBar.add(connectionMenu);

        helpMenu.setMnemonic('Y');
        helpMenu.setText("Ayuda");
        helpMenu.setName("helpMenu"); // NOI18N

        aboutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        aboutItem.setText("Acerca de ...");
        aboutItem.setName("aboutItem"); // NOI18N
        aboutItem.setActionCommand(aboutItem.getName());
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Clear filter fields
     */
    public void clearFilters() {
        codValue.setText("");
        desValue.setText("");
        
        codValue.repaint();
        desValue.repaint();
    }
    
    
    /* Getters */
    
    /**
     * Get code filter
     * 
     * @return Code filter
     */
    public String getCod() {
        return codValue.getText();
    }
    
    /**
     * Get description filter
     * 
     * @return Description filter
     */
    public String getDes() {
        return desValue.getText();
    }
    
    /**
     * Get the ratio value
     * 
     * @return Ratio value
     */
    public float getRatio() {
        return (float) ratioValue.getValue();
    }
    
    
    /* Setters */
    
    /**
     * Set connected status and enable and disable some components
     * 
     * @param status Connected status
     */
    public void setConnected(boolean status) {
        /* Clear filter fields */
        clearFilters();
        
        /* Query panel */
        for (Component component : queryPanel.getComponents())
            component.setEnabled(status);
        
        /* Menus */
        importItem.setEnabled(status);
        clearFiltersItem.setEnabled(status);
        consultItem.setEnabled(status);
        connectItem.setText(status ? "Reconectar ..." : "Conectar ...");
        disconnectItem.setEnabled(status);
        
        /* Clear table if status is not connected */
        if (!status) {
            ratioValue.setValue(0F);
            table.setModel(new DefaultTableModel());
            rowCount.setText(" No conectado ");
        }
    }
    
    /**
     * Enables or disables the menus items
     * 
     * @param status true to enable the items
     */
    public void setMenusEnabled(boolean status) {
        fileMenu.setEnabled(status);
        connectionMenu.setEnabled(status);
        helpMenu.setEnabled(status);
    }
    
    /**
     * Set the ratio value
     * 
     * @param ratio Ratio value
     */
    public void setRatio(float ratio) {
        ratioValue.setValue(ratio);
    }
    
    /**
     * Set table model with the data
     * 
     * @param tableModel Table model
     */
    public void setTable(TableModel tableModel) {
        table.setModel(tableModel);
        rowCount.setText(" Filas selectionadas: " + tableModel.getRowCount());
        
        table.repaint();
    }
    
    /**
     * Set controller object
     * 
     * @param controller Controller object
     */
    public void setController(ActionListener controller) {
        /* Menu bar */
        /* File menu */
        fileMenu.addActionListener(controller);
        importItem.addActionListener(controller);
        clearFiltersItem.addActionListener(controller);
        consultItem.addActionListener(controller);
        exitItem.addActionListener(controller);
        
        /* Connection menu */
        connectionMenu.addActionListener(controller);
        connectItem.addActionListener(controller);
        disconnectItem.addActionListener(controller);
        
        /* Help menu  */
        helpMenu.addActionListener(controller);
        aboutItem.addActionListener(controller);
        
        
        /* Query panel */
        /* Text fields */
        codValue.addActionListener(controller);
        desValue.addActionListener(controller);
        
        /* Buttons */
        consultButton.addActionListener(controller);
        updateButton.addActionListener(controller);
    }
    
    /**
     * Set controller object
     * 
     * @param controller Controller object
     */
    public void setController(ChangeListener controller) {
        /* Query panel */
        ratioValue.addChangeListener(controller);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutItem;
    private javax.swing.JMenuItem clearFiltersItem;
    private javax.swing.JTextField codValue;
    private javax.swing.JMenuItem connectItem;
    private javax.swing.JMenu connectionMenu;
    private javax.swing.JButton consultButton;
    private javax.swing.JMenuItem consultItem;
    private javax.swing.JTextField desValue;
    private javax.swing.JMenuItem disconnectItem;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JSeparator panelSeparator;
    private javax.swing.JPanel queryPanel;
    private javax.swing.JSpinner ratioValue;
    private javax.swing.JLabel rowCount;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

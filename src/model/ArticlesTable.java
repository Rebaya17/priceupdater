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

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import static java.lang.Float.parseFloat;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ArticlesTable table class
 */
public class ArticlesTable extends DefaultTableModel {
    private boolean columnStatus[];
    private ArrayList<Integer> modified;
    private float ratio;
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return columnStatus[column];
    }

    //@Override
    //public void setValueAt(Object newValue, int row, int column) {
    //    /* Other columns */
    //    //if (column != 3) {
    //    if (column != 2) {
    //        super.setValueAt(newValue, row, column);
    //        return;
    //    }
    //    
    //    /* Price column */
    //    float newVal = parseFloat(newValue.toString());
    //    float oldVal = parseFloat(getValueAt(row, column).toString());
    //    
    //    try {
    //        if (newVal < 0) throw new NumberFormatException("Error: Negative number are not allowed");
    //        
    //        super.setValueAt(newVal, row, column);
    //        super.setValueAt(newVal / (1 + ratio), row, column - 1);
    //        
    //        addModified(row);
    //    } catch (NumberFormatException ex) {
    //        super.setValueAt(oldVal, row, column);
    //        Logger.getLogger(ArticlesTable.class.getName()).log(Level.SEVERE, null, ex);
    //    }
    //}
    
    /**
     * Creates new table
     */
    public ArticlesTable() {
        ratio = 0F;
        modified = new ArrayList<>();
        
        //columnStatus = new boolean[] {false, false, false, true};
        //super.setColumnIdentifiers(new String[]{"Código", "Descripción", "Costo", "Precio"});

        columnStatus = new boolean[] {false, false, true};
        super.setColumnIdentifiers(new String[]{"Código", "Descripción", "Precio"});
    }
    
    /**
     * Read xlsx file and import data
     * 
     * @param path File path
     * @throws FileNotFoundException If file could not be found
     * @throws IOException If occour an input/output error
     */
    public void read(String path) throws FileNotFoundException, IOException {
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(new File(path)));
        XSSFSheet sheet = book.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        
        for (int i = 7, j = 0; i < rows; i++, j++) {
            XSSFRow row = sheet.getRow(i);
            
            if (row == null || row.getCell(0) == null) return;
            
            String cod = String.format("%-14s", row.getCell(0).getStringCellValue()).replace(' ', '0');
            String des = row.getCell(1).getStringCellValue();
            float vta = (float) row.getCell(2).getNumericCellValue();
            //float cos = vta / (1 + ratio);
            
            //addRow(new String[]{cod, des, String.valueOf(cos), String.valueOf(vta)});
            addRow(new String[]{cod, des, String.valueOf(vta)});
            modified.add(j);
        }
    }
    
    /**
     * Add modified row
     * 
     * @param i Modified row
     */
    public void addModified(int i) {
        if (!modified.contains(i))
            modified.add(i);
    }
    
    /**
     * Clear modified rows
     */
    public void clearModified() {
        modified.clear();
    }
    
    /**
     * Remove all rows
     */
    public void clear() {
        while (getRowCount() > 0)
            removeRow(0);
        
        modified.clear();
    }
    
    
    /* Getters */
    
    /**
     * Get the current ratio
     * 
     * @return Current ratio
     */
    public float getRatio() {
        return ratio;
    }
    
    /**
     * Get column enabled status
     * 
     * @return Column enabled status
     */
    public boolean[] getEditableColumns() {
        return Arrays.copyOf(columnStatus, columnStatus.length);
    }
    
    /**
     * Get modified rows
     * 
     * @return Array of index of modified rows
     */
    public int[] getModified() {
        int[] array = new int[modified.size()];
        
        for (int i = 0; i < array.length; i++)
            array[i] = modified.get(i);
        
        return array;
    }
    
    
    /* Setters */
    
    /**
     * Set the a new ratio
     * 
     * @param rat New ratio
     */
    public void setRatio(float rat) {
        ratio = rat;
    }
    
    /**
     * Set column enabled status
     * 
     * @param status Column enabled status
     */
    public void setEditableColumns(boolean status[]) {
        columnStatus = Arrays.copyOf(status, status.length);
    }
}

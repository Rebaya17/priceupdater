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

import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erick
 */
public class RatesTable extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    private boolean columnStatus[];

    @Override
    public boolean isCellEditable(int row, int column) {
        return columnStatus[column];
    }

    @Override
    public void setValueAt(Object newValue, int row, int column) {
    }

    /**
     * Create new table
     */
    public RatesTable() {
        columnStatus = new boolean[] {false, false, false};
        super.setColumnIdentifiers(new String[]{"Código", "Descripción", "Tasa"});
    }

    /**
     * Remove all rows
     */
    public void clear() {
        super.setRowCount(0);
    }


    /* Getters */

    /**
     * Get column enabled status
     *
     * @return Column enabled status
     */
    public boolean[] getEditableColumns() {
        return Arrays.copyOf(columnStatus, columnStatus.length);
    }

    /**
     * Get the currencies codes
     *
     * @return Currencies codes
     */
    public ArrayList<String> getCurrencies() {
        ArrayList<String> currencies = new ArrayList<>();
        int rows = getRowCount();

        for (int i = 0; i < rows; i++) {
            currencies.add(getValueAt(i, 0).toString());
        }

        return currencies;
    }


    /* Setters */

    /**
     * Set column enabled status
     *
     * @param status Column enabled status
     */
    public void setEditableColumns(boolean status[]) {
        columnStatus = Arrays.copyOf(status, status.length);
    }
}

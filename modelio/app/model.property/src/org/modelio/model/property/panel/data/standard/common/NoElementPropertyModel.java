/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.model.property.panel.data.standard.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Empty data model.
 */
@objid ("f4cc91a4-fb65-46d9-a683-3e8cbbfcc52c")
public class NoElementPropertyModel extends AbstractPropertyModel<MObject> {
    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("1156ff45-d99f-41f7-aa33-59caa807452d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("0c2cbd7e-ab98-40a4-9658-ffc8c815a3d8")
    @Override
    public int getRowsNumber() {
        return 1;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("82466bf1-8351-486e-99cd-1708090ecb80")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.PROPERTY_ID);
            default:
                return null;
            }
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Return the type of the element displayed at the specified row and column.
     * <p>
     * This type will be used to choose an editor and a renderer for each cell
     * of the properties table.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("6f3e895e-2e85-4f8f-b514-6c869dc22f3c")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("91761533-766a-4066-95f3-7e7bb5163864")
    @Override
    public boolean isEditable(int row, int col) {
        return false;
    }

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("f3493588-3f15-4f0c-9c82-286215618fea")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            default:
                return;
            }
        default:
            return;
        }
    }

    /**
     * Create a new data model from any MObject.
     */
    @objid ("de047fe0-cae4-464f-b6e2-a00daabf143c")
    public NoElementPropertyModel() {
        super(null);
    }

}

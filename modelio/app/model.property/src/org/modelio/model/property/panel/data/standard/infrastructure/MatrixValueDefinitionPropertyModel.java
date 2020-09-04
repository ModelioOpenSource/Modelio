/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.model.property.panel.data.standard.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;

/**
 * <i>MatrixValueDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>MatrixValueDefinition</i> metaclass.
 */
@objid ("b0d6d1be-d12b-4492-a0d4-0b4a4c6a0ae9")
public class MatrixValueDefinitionPropertyModel extends AbstractPropertyModel<MatrixValueDefinition> {
    /**
     * Properties to display for <i>MatrixValueDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for other rows the values usually match the meta-attributes and roles
     * names of the metaclass
     * </ul>
     */
    @objid ("781ccd9e-f097-4aea-83e0-cdf6c3b08525")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID };

    /**
     * Create a new <i>MatrixValueDefinition</i> data model from an
     * <i>MatrixValueDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("26c9a45a-c515-41b6-8b88-0b9e4fc5f029")
    public MatrixValueDefinitionPropertyModel(MatrixValueDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("7762958e-96b6-44b5-9e43-0fc70a2c1107")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("29f021da-3563-44a3-8544-c05accd49f9f")
    @Override
    public int getRowsNumber() {
        return MatrixValueDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("2067cd7f-06fa-4e2b-a27c-b3c0816e8d35")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
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
    @objid ("a819ea97-39fb-408a-8eda-af8e5561a8a2")
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

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("13686566-0fe3-4a32-9fb3-dbf1b5348815")
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

}

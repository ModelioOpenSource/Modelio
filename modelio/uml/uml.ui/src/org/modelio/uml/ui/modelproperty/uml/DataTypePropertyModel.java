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

package org.modelio.uml.ui.modelproperty.uml;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>DataType</i> data model.
 * <p>
 * This class provides the list of properties for the <i>DataType</i> metaclass.
 */
@objid ("f997ff1a-5a55-4490-bbdf-bafca9549bd0")
public class DataTypePropertyModel extends AbstractPropertyModel<DataType> {
    /**
     * Properties to display for <i>DataType</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("b6e14874-f859-4d4a-b896-4301902125d6")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Visibility",
			"IsAbstract", "IsElementary", "IsLeaf", "IsRoot" };

    /**
     * Create a new <i>DataType</i> data model from an <i>DataType</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("c8613b9c-98ce-4df8-b0e5-16bd1bdeb41e")
    public DataTypePropertyModel(DataType theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("fffa5c7f-43be-485f-aae2-f47a2ca8bf99")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("9db03a98-8cc7-46d9-91c5-c1a7ac4a5373")
    @Override
    public int getRowsNumber() {
        return DataTypePropertyModel.PROPERTIES.length;
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
    @objid ("842c7d9a-9ba6-4314-8c36-adaa0b4dbee2")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getVisibility();
            case 3:
                return this.theEditedElement.isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.isIsElementary() ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                // The logic here has been inverted to allow a positive logic:
                // The displayed field is now can be inherited.
                return (!this.theEditedElement.isIsLeaf()) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return this.theEditedElement.isIsRoot() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("3f441efa-81ad-490c-8d63-faef6e5e2488")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), VisibilityMode.class);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 6:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
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
    @objid ("5b2878b2-86a9-4496-9d0a-6c35a2bb160a")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                this.theEditedElement.setName((String) value);
                break;
            case 2:
                this.theEditedElement.setVisibility((VisibilityMode) value);
                break;
            case 3:
                this.theEditedElement.setIsAbstract(((Boolean) value).booleanValue());
                if (((Boolean) value).booleanValue() == true) {
                    this.theEditedElement.setIsLeaf(false);
                }
                break;
            case 4:
                this.theEditedElement.setIsElementary(((Boolean) value).booleanValue());
                break;
            case 5:
                // The logic here has been inverted to allow a positive logic:
                // The displayed field is now can be inherited.
                this.theEditedElement.setIsLeaf(!((Boolean) value).booleanValue());
                if (((Boolean) value).booleanValue() == false) {
                    this.theEditedElement.setIsAbstract(false);
                }
                break;
            case 6:
                this.theEditedElement.setIsRoot(((Boolean) value).booleanValue());
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

}

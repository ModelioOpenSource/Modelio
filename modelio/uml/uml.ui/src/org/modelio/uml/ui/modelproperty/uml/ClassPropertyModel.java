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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>Class</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Class</i> metaclass.
 */
@objid ("aa2594c0-aae9-4c1c-88b1-208ff1c560f8")
public class ClassPropertyModel extends AbstractPropertyModel<Class> {
    /**
     * Properties to display for <i>Class</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("b9db0739-2ee3-4a4e-96b0-a8756adc066f")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Visibility",
    			"IsAbstract", "IsElementary", "IsActive", "IsLeaf", "IsRoot", "IsMain" };

    /**
     * Create a new <i>Class</i> data model from an <i>Class</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("777f63a0-dd84-4ef9-84ed-c53f43e9372b")
    public  ClassPropertyModel(Class theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("6c62adb5-60b4-4509-9644-08e6a100ccfc")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("d42747f4-2dfd-492a-8a2b-1de525cac019")
    @Override
    public int getRowsNumber() {
        return ClassPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("72c7a16e-c6a0-4a84-9585-d0632dad2a34")
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
                return this.theEditedElement.isIsActive() ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                // The logic here has been inverted to allow a positive logic:
                // The displayed field is now can be inherited.
                return !this.theEditedElement.isIsLeaf() ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return this.theEditedElement.isIsRoot() ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return this.theEditedElement.isIsMain() ? Boolean.TRUE : Boolean.FALSE;
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("ebacde35-c1ce-401c-a934-b5cf1fb96411")
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
            case 7:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 8:
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("63788434-b210-4bf7-acbc-556b4bf1d7ce")
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
                this.theEditedElement.setIsActive(((Boolean) value).booleanValue());
                break;
            case 6:
                // The logic here has been inverted to allow a positive logic:
                // The displayed field is now can be inherited.
                this.theEditedElement.setIsLeaf(!((Boolean) value).booleanValue());
                if (((Boolean) value).booleanValue() == false) {
                    this.theEditedElement.setIsAbstract(false);
                }
                break;
            case 7:
                this.theEditedElement.setIsRoot(((Boolean) value).booleanValue());
                break;
            case 8:
                this.theEditedElement.setIsMain(((Boolean) value).booleanValue());
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

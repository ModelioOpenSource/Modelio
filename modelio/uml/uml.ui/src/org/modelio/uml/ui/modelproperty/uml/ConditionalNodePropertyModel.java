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
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>ConditionalNode</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ConditionalNode</i>
 * metaclass.
 */
@objid ("1b958c2f-2525-40ec-a9a9-534a546664b0")
public class ConditionalNodePropertyModel extends AbstractPropertyModel<ConditionalNode> {
    /**
     * Properties to display for <i>ConditionalNode</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("a4b8f090-bb1a-4f8b-b14d-384032157c06")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
    			"IsDeterminate", "IsAssured", "mustIsolate" };

    /**
     * Create a new <i>ConditionalNode</i> data model from an
     * <i>ConditionalNode</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("1fe19dd9-7991-4027-93b2-a7a7b5b71848")
    public  ConditionalNodePropertyModel(ConditionalNode theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("87526214-bb1b-4140-a313-e9f91c9282df")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("205ed5e1-4329-48ce-b6a9-5f17924d3681")
    @Override
    public int getRowsNumber() {
        return ConditionalNodePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("be0113ff-8cf7-441a-bb30-29e4b538e13f")
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
                return this.theEditedElement.isIsDeterminate() ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return this.theEditedElement.isIsAssured() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.isMustIsolate() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("1bad2ad3-8342-483e-b6e1-2e2ac37eee4f")
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
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
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
    @objid ("dcf3b623-4043-4c91-ba85-0abd10681854")
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
                this.theEditedElement.setIsDeterminate(((Boolean) value).booleanValue());
                break;
            case 3:
                this.theEditedElement.setIsAssured(((Boolean) value).booleanValue());
                break;
            case 4:
                this.theEditedElement.setMustIsolate(((Boolean) value).booleanValue());
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

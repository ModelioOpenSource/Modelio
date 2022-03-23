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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ExceptionHandler</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ExceptionHandler</i>
 * metaclass.
 */
@objid ("1c247f84-c249-44b9-8285-01ba85efa171")
public class ExceptionHandlerPropertyModel extends AbstractPropertyModel<ExceptionHandler> {
    /**
     * Properties to display for <i>ExceptionHandler</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("c48c7e16-bfd8-4d2e-bec7-1a363e679620")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
    			"ExceptionType", "ExceptionInput" };

    /**
     * Create a new <i>ExceptionHandler</i> data model from an
     * <i>ExceptionHandler</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("a6c4f567-27dc-4e8b-b308-8c02d01bf8a5")
    public  ExceptionHandlerPropertyModel(ExceptionHandler theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("695d4294-852a-441d-b8ff-03ccfabc3506")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("b7bda5ac-497b-4c10-a504-25c4001489ce")
    @Override
    public int getRowsNumber() {
        return ExceptionHandlerPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("0a2783fc-38bc-4695-95ce-8dac625588ec")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1: // Name
                return this.theEditedElement.getName();
            case 2: // Type
                return this.theEditedElement.getExceptionType();
            case 3: // Target
                return this.theEditedElement.getExceptionInput();
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
    @objid ("71754095-9815-4743-90cc-b2d22010477f")
    @SuppressWarnings("unchecked")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1: // Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2: // Exception types
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(GeneralClass.class));
            case 3:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(InputPin.class));
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
    @objid ("858f95e1-a3c5-4f5a-b036-de1d0ea90d89")
    @Override
    @SuppressWarnings("unchecked")
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
                for (GeneralClass s : new ArrayList<>(this.theEditedElement.getExceptionType())) {
                    this.theEditedElement.getExceptionType().remove(s);
                }
        
                List<GeneralClass> newcontent = (List<GeneralClass>) value;
                for (GeneralClass s : newcontent) {
                    this.theEditedElement.getExceptionType().add(s);
                }
                break;
            case 3:
                this.theEditedElement.setExceptionInput((InputPin) value);
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

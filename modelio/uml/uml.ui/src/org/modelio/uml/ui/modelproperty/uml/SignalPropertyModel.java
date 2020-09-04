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

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Signal</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Signal</i> metaclass.
 */
@objid ("2239fd83-3940-4e7b-b367-15fe293127bf")
public class SignalPropertyModel extends AbstractPropertyModel<Signal> {
    /**
     * Properties to display for <i>Signal</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("525176aa-b287-4e01-95d0-ef66335b9ca6")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Base",
			"IsEvent", "IsException" };

    /**
     * Create a new <i>Signal</i> data model from an <i>Signal</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("771033d7-5881-4b8b-b887-9d4e738a38c5")
    public SignalPropertyModel(Signal theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("0bda0fef-612d-4e6e-9807-b257e256b49e")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("9f244586-ae00-4a31-a81f-0516e8edab92")
    @Override
    public int getRowsNumber() {
        return SignalPropertyModel.PROPERTIES.length;
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
    @objid ("a4a6d977-123e-4cb0-bff9-15ce6fb0795d")
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
                return getBaseValue(this.theEditedElement);
            case 3:
                return this.theEditedElement.isIsEvent() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.isIsException() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("6ab289d5-0cf6-451b-ab63-08f2a9ea2b7c")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Arrays.asList(Parameter.class, GeneralClass.class, Operation.class));
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
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("246bdb85-0f20-4c93-856a-5e2188acd2bc")
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
                setBaseValue(this.theEditedElement, value);
                break;
            case 3:
                this.theEditedElement.setIsEvent(((Boolean) value).booleanValue());
                break;
            case 4:
                this.theEditedElement.setIsException(((Boolean) value).booleanValue());
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    /**
     * Returns the element represented by the given instance node.
     * 
     * @return the represented element
     */
    @objid ("26bc2a6e-ab32-42af-bd70-5208a6272824")
    private ModelElement getBaseValue(Signal elt) {
        ModelElement ret = elt.getPBase();
        if (ret != null) {
            return ret;
        }
        
        ret = elt.getOBase();
        if (ret != null) {
            return ret;
        }
        
        ret = elt.getBase();
        return ret;
    }

    /**
     * Set the ObjectNode represented elements. This method set the right
     * dependency and clears the otheEditedElement.
     * 
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("0d40553c-0d4b-4239-969e-b6107c9b31f8")
    private void setBaseValue(Signal theEditedElement, Object value) {
        // Erase old value or exit if old value is new value
        Parameter old1 = theEditedElement.getPBase();
        if (old1 != null) {
            if (old1.equals(value)) {
                return;
            }
            theEditedElement.setPBase(null);
        } else {
            Operation old2 = theEditedElement.getOBase();
            if (old2 != null) {
                if (old2.equals(value)) {
                    return;
                }
                theEditedElement.setOBase(null);
            } else {
                GeneralClass old3 = theEditedElement.getBase();
                if (old3 != null) {
                    if (old3.equals(value)) {
                        return;
                    }
                    theEditedElement.setBase(null);
                }
            }
        }
        
        if (value != null) {
            // Set new value
            if (Parameter.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setPBase((Parameter) value);
            } else if (Operation.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setOBase((Operation) value);
            } else if (GeneralClass.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setBase((GeneralClass) value);
            }
        }
    }

}

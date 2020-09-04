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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ConnectionPointReference</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>ConnectionPointReference</i> metaclass.
 */
@objid ("c0a04c1a-702d-43ce-9f4c-57f9cf0743f2")
public class ConnectionPointReferencePropertyModel extends AbstractPropertyModel<ConnectionPointReference> {
    /**
     * Properties to display for <i>ConnectionPointReference</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("c8630aaa-b6fe-41ab-8007-5867dbb296ac")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "EntryExit" };

    /**
     * Create a new <i>ConnectionPointReference</i> data model from an
     * <i>ConnectionPointReference</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("3b4a5ad8-fce4-45ca-8776-8e6a72ed510f")
    public ConnectionPointReferencePropertyModel(ConnectionPointReference theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("e1c2c3ee-7f9c-4d2b-bf49-4645d3c9532f")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("201f5eef-6123-417a-acef-5732c67d0fe6")
    private ModelElement getEntryExit() {
        ModelElement ret = this.theEditedElement.getEntry();
        if (ret != null) {
            return ret;
        }
        ret = this.theEditedElement.getExit();
        return ret;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("134ca817-567c-41af-b927-b5ba0eefa34e")
    @Override
    public int getRowsNumber() {
        return ConnectionPointReferencePropertyModel.PROPERTIES.length;
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
    @objid ("6021fc3b-035c-4af6-b22f-5956e9c009e1")
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
                return getEntryExit();
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
    @objid ("7f420fa6-3d29-45b3-9561-a0d7df733211")
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
                        Arrays.asList(EntryPointPseudoState.class, ExitPointPseudoState.class));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("b211f011-a281-43bd-ac91-64e834e34f84")
    private void setEntryExit(ConnectionPointReference theEditedElement, Object value) {
        // Erase old value or exit if old value is new value
        EntryPointPseudoState old1 = theEditedElement.getEntry();
        if (old1 != null) {
            if (old1.equals(value)) {
                return;
            }
            theEditedElement.setEntry(null);
        } else {
            ExitPointPseudoState old2 = theEditedElement.getExit();
            if (old2 != null) {
                if (old2.equals(value)) {
                    return;
                }
                theEditedElement.setExit(null);
            }
        }
        
        if (value != null) {
            // Set new value
            if (EntryPointPseudoState.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setEntry((EntryPointPseudoState) value);
            } else if (ExitPointPseudoState.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setExit((ExitPointPseudoState) value);
            }
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
    @objid ("0eb86036-dd62-4c68-b2c6-8d2e9d8ccba7")
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
                setEntryExit(this.theEditedElement, value);
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

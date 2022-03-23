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
package org.modelio.uml.ui.modelproperty.bpmn;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>BpmnStandardLoopCharacteristics</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnStandardLoopCharacteristics</i> metaclass.
 */
@objid ("fca7fcbe-1d71-4a61-ae50-cae346c422df")
public class BpmnStandardLoopCharacteristicsPropertyModel extends AbstractPropertyModel<BpmnStandardLoopCharacteristics> {
    /**
     * Properties to display for <i>BpmnStandardLoopCharacteristics</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("92fd7e1c-d7bb-4514-9c2b-b5a5dc12a10e")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "TestBefore",
    			"LoopCondition", "LoopMaximum" };

    /**
     * Create a new <i>BpmnStandardLoopCharacteristics</i> data model from an
     * <i>BpmnStandardLoopCharacteristics</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("6c2789c1-509e-4420-840b-7e096656f02b")
    public  BpmnStandardLoopCharacteristicsPropertyModel(BpmnStandardLoopCharacteristics theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("393efb6d-8982-4886-a507-d01c903d81a8")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("225d7032-1700-4363-9aad-39f9923aee4b")
    @Override
    public int getRowsNumber() {
        return BpmnStandardLoopCharacteristicsPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("5da61d1e-c674-4aeb-8fa8-532785156c8f")
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
                return this.theEditedElement.isTestBefore() ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return this.theEditedElement.getLoopCondition();
            case 4:
                return this.theEditedElement.getLoopMaximum();
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
    @objid ("1321c67b-9866-4ba9-b58e-ef3fc6f705f3")
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
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("8040dc79-b99f-4200-81b5-5ca974a060b2")
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
                this.theEditedElement.setTestBefore((Boolean) value);
                break;
            case 3:
                this.theEditedElement.setLoopCondition((String) value);
                break;
            case 4:
                this.theEditedElement.setLoopMaximum((String) value);
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

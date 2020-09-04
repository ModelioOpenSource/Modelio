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

package org.modelio.uml.ui.modelproperty.bpmn.delegated;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnEscalationEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnEscalationEventDefinition</i> metaclass.
 */
@objid ("9588f2c7-02d4-4509-8d91-e8b218049e26")
public class BpmnEscalationEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnEscalationEventDefinition> {
    /**
     * Properties to display for <i>BpmnEscalationEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("27ef99fc-4e89-4cf4-adb1-2326ab0f3a1f")
    private static final String[] PROPERTIES = new String[] {"EscalationCode" };

    /**
     * Create a new <i>BpmnEscalationEventDefinition</i> data model from an
     * <i>BpmnEscalationEventDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("44cbb45f-2652-4a7d-bd93-a4c61bd925a6")
    public BpmnEscalationEventDefinitionPropertyModel(BpmnEscalationEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("24c83ffa-0b30-4566-8fab-22eb076ca3ca")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("95dd9c99-f338-4735-a684-589d47e90a62")
    @Override
    public int getRowsNumber() {
        return BpmnEscalationEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("20f7d8f3-9f77-42fb-ae84-f3b045a559a0")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return this.theEditedElement.getEscalationCode();
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
    @objid ("29384943-f247-4af1-836b-9d17daf4a45d")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
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
    @objid ("6b60ea93-2a42-4c1c-b344-f047d059eb7c")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                this.theEditedElement.setEscalationCode((String) value);
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

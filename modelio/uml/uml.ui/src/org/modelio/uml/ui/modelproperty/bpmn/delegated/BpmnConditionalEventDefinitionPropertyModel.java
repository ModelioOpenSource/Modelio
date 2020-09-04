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

package org.modelio.uml.ui.modelproperty.bpmn.delegated;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnConditionalEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnConditionalEventDefinition</i> metaclass.
 */
@objid ("aad135c1-7865-4fb4-b827-e32f367bcc0a")
public class BpmnConditionalEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnConditionalEventDefinition> {
    /**
     * Properties to display for <i>BpmnConditionalEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("9f8e00f8-23e1-42b4-8c47-75b9c666d55b")
    private static final String[] PROPERTIES = new String[] {"Condition" };

    /**
     * Create a new <i>BpmnConditionalEventDefinition</i> data model from an
     * <i>BpmnConditionalEventDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("9db8e19c-57bd-483b-a529-9f7dce27ba89")
    public BpmnConditionalEventDefinitionPropertyModel(BpmnConditionalEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("a12757dd-4c67-4056-8bb3-b9075a7b1726")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("c1051993-2610-4176-b7d0-0295718c36e9")
    @Override
    public int getRowsNumber() {
        return BpmnConditionalEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("689787e6-4ac2-4e50-b705-53faed7b36cb")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return this.theEditedElement.getCondition();
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
    @objid ("4c22c0ba-91b3-4bbe-9cb9-50baf6090df6")
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
    @objid ("c21f6256-760f-4b9c-ab57-38f3ca785216")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                this.theEditedElement.setCondition((String) value);
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

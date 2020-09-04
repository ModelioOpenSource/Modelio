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
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnTerminateEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnTerminateEventDefinition</i> metaclass.
 */
@objid ("1cc25e99-420b-4efe-b4a9-403857ef0b8f")
public class BpmnTerminateEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnTerminateEventDefinition> {
    /**
     * Properties to display for <i>BpmnTerminateEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("c32cc75f-1f60-48b7-9063-31db1c63b87e")
    private static final String[] PROPERTIES = new String[] {  };

    /**
     * Create a new <i>BpmnTerminateEventDefinition</i> data model from an
     * <i>BpmnTerminateEventDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("16aa60a9-cbef-49c2-a6a1-c5ed2c8931b2")
    public BpmnTerminateEventDefinitionPropertyModel(BpmnTerminateEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("91706c4e-d26c-42f8-bd39-3b48b0d3ff6c")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("9d686036-efae-4298-9651-b8d104ec7c56")
    @Override
    public int getRowsNumber() {
        return BpmnTerminateEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("39e3ce47-7213-4af5-860d-3254d17823f5")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
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
    @objid ("277edeff-4e5d-44cc-939b-38e4a2cd36b9")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
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
    @objid ("10bc0d4b-0fd1-4bdf-b57a-c5028b881e60")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            default:
                return;
            }
        default:
            return;
        }
    }

}

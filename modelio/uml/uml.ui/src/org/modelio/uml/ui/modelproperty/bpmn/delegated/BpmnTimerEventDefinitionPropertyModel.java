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
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.uml.ui.plugin.UmlUi;

/**
 * <i>BpmnTimerEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnTimerEventDefinition</i> metaclass.
 */
@objid ("7230ce5e-c5de-4799-b2c0-ca2435e6977f")
public class BpmnTimerEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnTimerEventDefinition> {
    /**
     * Properties to display for <i>BpmnTimerEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("4dbe0a97-eb3a-4168-bf55-3aaf1e7fc21f")
    private static final String[] PROPERTIES = new String[] {"TimeCycle",
			"TimeDate", "TimeDuration" };

    /**
     * Create a new <i>BpmnTimerEventDefinition</i> data model from an
     * <i>BpmnTimerEventDefinition</i>.
     * 
     * @param theEditedElement the edited element.
     */
    @objid ("dd4d371a-34c4-45e2-8dec-19cf2cf35b16")
    public BpmnTimerEventDefinitionPropertyModel(BpmnTimerEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("47ae5c29-3150-44f0-b024-45c7c87921e6")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("95176799-46eb-459f-b4cf-d792cc3bfe38")
    @Override
    public int getRowsNumber() {
        return BpmnTimerEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("489ff94a-6932-4142-85d2-ea341dd2f5fe")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return this.theEditedElement.getTimeCycle();
            case 1:
                return this.theEditedElement.getTimeDate();
            case 2:
                return this.theEditedElement.getTimeDuration();
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
    @objid ("e59a0b82-18e6-4c8d-a8eb-834690943b50")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
            case 2:
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
    @objid ("0625ba0a-9f80-498a-b45e-ce3905143982")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {  
            case 0:
                this.theEditedElement.setTimeCycle((String) value);
                break;
            case 1:
                this.theEditedElement.setTimeDate((String) value);
                break;
            case 2:
                this.theEditedElement.setTimeDuration((String) value);
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

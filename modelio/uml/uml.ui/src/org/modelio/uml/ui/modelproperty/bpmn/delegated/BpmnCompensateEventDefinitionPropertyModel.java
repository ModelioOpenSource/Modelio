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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnCompensateEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnCompensateEventDefinition</i> metaclass.
 */
@objid ("fff26bb5-d5dd-48dd-ac0e-ee64d5e65f3a")
public class BpmnCompensateEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnCompensateEventDefinition> {
    /**
     * Properties to display for <i>BpmnCompensateEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("b8aec97a-185b-47a0-ace3-c4129704d5c7")
    private static final String[] PROPERTIES = new String[] { "Activity" };

    /**
     * Create a new <i>BpmnCompensateEventDefinition</i> data model from an
     * <i>BpmnCompensateEventDefinition</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("7fa26d82-51d4-45a6-a8df-8f285acbb030")
    public BpmnCompensateEventDefinitionPropertyModel(BpmnCompensateEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("e624c9fa-70d8-439c-badb-f604175d42da")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("0d63e6f5-ddb5-4e35-b6c1-578cef9b7c12")
    @Override
    public int getRowsNumber() {
        return BpmnCompensateEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("f1951a15-f2b2-4f13-bdfb-5a85f831063d")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return this.theEditedElement.getActivityRef();
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
    @objid ("e7bfa04e-bf18-4a11-b8cd-766719a106db")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(BpmnActivity.class));
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
    @objid ("35ae4497-1347-4b39-9bf1-6bbd0c64e266")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                this.theEditedElement.setActivityRef((BpmnActivity) value);
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

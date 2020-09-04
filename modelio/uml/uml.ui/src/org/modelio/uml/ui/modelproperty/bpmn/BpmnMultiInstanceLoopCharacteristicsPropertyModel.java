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

package org.modelio.uml.ui.modelproperty.bpmn;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnMultiInstanceLoopCharacteristics</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnMultiInstanceLoopCharacteristics</i> metaclass.
 */
@objid ("9768b328-bc2b-42b8-b241-8372fa5f9e47")
public class BpmnMultiInstanceLoopCharacteristicsPropertyModel extends AbstractPropertyModel<BpmnMultiInstanceLoopCharacteristics> {
    /**
     * Properties to display for <i>BpmnMultiInstanceLoopCharacteristics</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("9e42ec31-33f8-4127-b0d8-9ef4ed195bf7")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Sequencial",
			"Behavior", "LoopCardinality", "CompletionCondition", "EventDefinition" };

    /**
     * Create a new <i>BpmnMultiInstanceLoopCharacteristics</i> data model from
     * an <i>BpmnMultiInstanceLoopCharacteristics</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("6ca0e8f7-7326-46d6-b333-2e02d5a0d8fe")
    public BpmnMultiInstanceLoopCharacteristicsPropertyModel(BpmnMultiInstanceLoopCharacteristics theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("7c043421-c9ae-4dcb-a3e4-d671f05d6082")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("4ffda3a0-2770-4ad7-852b-4512c340e84f")
    @Override
    public int getRowsNumber() {
        return BpmnMultiInstanceLoopCharacteristicsPropertyModel.PROPERTIES.length;
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
    @objid ("8801b8cd-c0d3-4b57-9349-edf4607b2a75")
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
                return this.theEditedElement.isIsSequencial() ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return this.theEditedElement.getBehavior();
            case 4:
                return this.theEditedElement.getLoopCardinality();
            case 5:
                return this.theEditedElement.getCompletionCondition();
            case 6:
                return this.theEditedElement.getCompletionEventRef();
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
    @objid ("4d2b35b0-60b8-4459-8bca-0dc7b5d6350e")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), MultiInstanceBehavior.class);
            case 4:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 5:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 6:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(BpmnEventDefinition.class));
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
    @objid ("8527113a-40ec-4260-a92f-ff91a8550d71")
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
                this.theEditedElement.setIsSequencial((Boolean) value);
                break;
            case 3:
                this.theEditedElement.setBehavior((MultiInstanceBehavior) value);
                break;
            case 4:
                this.theEditedElement.setLoopCardinality((String) value);
                break;
            case 5:
                this.theEditedElement.setCompletionCondition((String) value);
                break;
            case 6:
                this.theEditedElement.setCompletionEventRef((BpmnEventDefinition) value);
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

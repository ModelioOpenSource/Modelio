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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnMessageEventDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnMessageEventDefinition</i> metaclass.
 */
@objid ("2a1a3d16-7021-45a7-becb-1690e1fbca90")
public class BpmnMessageEventDefinitionPropertyModel extends AbstractPropertyModel<BpmnMessageEventDefinition> {
    /**
     * Properties to display for <i>BpmnMessageEventDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("2e7116dd-cfe4-4668-8788-f7c1ddfe0a97")
    private static final String[] PROPERTIES = new String[] {"Operation",
			"Message" };

    /**
     * Create a new <i>BpmnMessageEventDefinition</i> data model from an
     * <i>BpmnMessageEventDefinition</i>.
     * 
     * @param theEditedElement the edited element
     */
    @objid ("b7039a70-297d-424c-95bd-c2430a1ed45a")
    public BpmnMessageEventDefinitionPropertyModel(BpmnMessageEventDefinition theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("7fbf2d2b-5941-4d54-975d-d1aa9cb07579")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("f6534d5a-668f-4a7d-922e-d13981c01954")
    @Override
    public int getRowsNumber() {
        return BpmnMessageEventDefinitionPropertyModel.PROPERTIES.length;
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
    @objid ("a137839b-248e-4f8d-9c6c-e3379b3a70e9")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return UmlUi.I18N.getString("BpmnEventDefinitionType.indent") + getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return this.theEditedElement.getOperationRef();
            case 1:
                return this.theEditedElement.getMessageRef();
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
    @objid ("56b58ec9-ef5b-454f-9d61-cbc126f0273d")
    @SuppressWarnings("unchecked")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0:
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(BpmnOperation.class));
            case 1:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(BpmnMessage.class));
        
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
    @objid ("5dba1654-c129-4d19-aa20-2432c425cc1a")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                @SuppressWarnings("unchecked")
                List<BpmnOperation> newcontent = (List<BpmnOperation>) value;
        
                this.theEditedElement.getOperationRef().clear();
                this.theEditedElement.getOperationRef().addAll(newcontent);
                break;
            case 1:
                this.theEditedElement.setMessageRef((BpmnMessage) value);
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

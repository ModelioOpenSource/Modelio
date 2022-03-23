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
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGatewayType;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>BpmnEventBasedGateway</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>BpmnEventBasedGateway</i> metaclass.
 */
@objid ("e760e356-aa9e-4381-a9b8-a7fff72d9004")
public class BpmnEventBasedGatewayPropertyModel extends AbstractPropertyModel<BpmnEventBasedGateway> {
    /**
     * Properties to display for <i>BpmnEventBasedGateway</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("ef8cf17e-5287-4579-9ee0-4e264cda304e")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
    			"GatewayDirection", "Instanciate", "EventGatewayType" };

    /**
     * Create a new <i>BpmnEventBasedGateway</i> data model from an
     * <i>BpmnEventBasedGateway</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("cab1a3f9-7938-4879-9594-bfe8456bc65e")
    public  BpmnEventBasedGatewayPropertyModel(BpmnEventBasedGateway theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("73da1df7-4072-41ea-ae99-11e285147466")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("ea78b62a-b236-4ee8-a1a3-325c86ddd333")
    @Override
    public int getRowsNumber() {
        return BpmnEventBasedGatewayPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("9ff92011-6b53-4765-a854-75413cb6258c")
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
                return this.theEditedElement.getGatewayDirection();
            case 3:
                return this.theEditedElement.isInstanciate() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.getEventGatewayType();
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
    @objid ("184a8e6d-0f68-4806-aa42-b21fe7a2494d")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), BpmnGatewayDirection.class);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), BpmnEventBasedGatewayType.class);
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
    @objid ("dccd820d-ba3a-4cbd-924b-db4c83219608")
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
                this.theEditedElement.setGatewayDirection((BpmnGatewayDirection) value);
                break;
            case 3:
                this.theEditedElement.setInstanciate((Boolean) value);
                break;
            case 4:
                this.theEditedElement.setEventGatewayType((BpmnEventBasedGatewayType) value);
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

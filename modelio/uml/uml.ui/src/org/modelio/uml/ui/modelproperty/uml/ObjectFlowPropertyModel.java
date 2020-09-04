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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlowEffectKind;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ObjectFlow</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ObjectFlow</i>
 * metaclass.
 */
@objid ("87b13273-3c4a-4e39-bca4-7e84704dbf79")
public class ObjectFlowPropertyModel extends AbstractPropertyModel<ObjectFlow> {
    /**
     * Properties to display for <i>ObjectFlow</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("b77d5e8e-bf3f-466e-a078-c28f3895fb08")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Target",
			"Guard", "Weight", "TransformationBehavior", "Effect", "SelectionBehavior", "IsMultiCast",
			"IsMultiReceive" };

    /**
     * Create a new <i>ObjectFlow</i> data model from an <i>ObjectFlow</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("0f527a21-6518-4375-b928-53bab54146fc")
    public ObjectFlowPropertyModel(ObjectFlow theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("fffb31e3-63f3-41b9-9552-bf460a2c3878")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("a3b4627b-e275-4e5e-a5be-fb336fd09923")
    @Override
    public int getRowsNumber() {
        return ObjectFlowPropertyModel.PROPERTIES.length;
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
    @objid ("35aabcf3-799c-44c9-ac78-487aea0f0b34")
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
                return this.theEditedElement.getTarget();
            case 3:
                return this.theEditedElement.getGuard();
            case 4:
                return this.theEditedElement.getWeight();
            case 5:
                return this.theEditedElement.getTransformationBehavior();
            case 6:
                return this.theEditedElement.getEffect();
            case 7:
                return this.theEditedElement.getSelectionBehavior();
            case 8:
                return this.theEditedElement.isIsMultiCast() ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return this.theEditedElement.isIsMultiReceive() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("8fb174ce-afac-4703-a76f-35776091133a")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(ActivityNode.class));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 5:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 6:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ObjectFlowEffectKind.class);
            case 7:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 8:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
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
    @objid ("a8e2a02b-6aac-4b06-b591-4e13665baeb5")
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
                this.theEditedElement.setTarget((ActivityNode) value);
                break;
            case 3:
                this.theEditedElement.setGuard((String) value);
                break;
            case 4:
                this.theEditedElement.setWeight((String) value);
                break;
            case 5:
                this.theEditedElement.setTransformationBehavior((String) value);
                break;
            case 6:
                this.theEditedElement.setEffect((ObjectFlowEffectKind) value);
                break;
            case 7:
                this.theEditedElement.setSelectionBehavior((String) value);
                break;
            case 8:
                this.theEditedElement.setIsMultiCast(((Boolean) value).booleanValue());
                break;
            case 9:
                this.theEditedElement.setIsMultiReceive(((Boolean) value).booleanValue());
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

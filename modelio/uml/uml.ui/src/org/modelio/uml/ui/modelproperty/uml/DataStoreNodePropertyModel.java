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

import java.util.Arrays;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>DataStoreNode</i> data model.
 * <p>
 * This class provides the list of properties for the <i>DataStoreNode</i>
 * metaclass.
 */
@objid ("7f07d28f-5575-465c-8a58-d53aacb9dd4f")
public class DataStoreNodePropertyModel extends AbstractPropertyModel<DataStoreNode> {
    /**
     * Properties to display for <i>DataStoreNode</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("bea71c8f-3afd-47f5-897a-dbbca39992a2")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
    			"UpperBound", "IsControlType", "Ordering", "SelectionBehavior", "InState", "Represented" };

    /**
     * Create a new <i>DataStoreNode</i> data model from an <i>DataStoreNode</i>
     * .
     * @param theEditedElement the model to edit.
     */
    @objid ("9df35d15-da71-4136-a48d-166a878182f1")
    public  DataStoreNodePropertyModel(DataStoreNode theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("1616c3d7-744d-4c6e-81d8-6fa26510194d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element represented by the given instance node.
     * @return the represented element
     */
    @objid ("1ef08c05-995d-4586-9fd2-83cd64a43fd1")
    private ModelElement getRepresented(ObjectNode elt) {
        ModelElement ret = elt.getRepresented();
        if (ret != null) {
            return ret;
        }
        ret = elt.getRepresentedAttribute();
        if (ret != null) {
            return ret;
        }
        ret = elt.getRepresentedRole();
        if (ret != null) {
            return ret;
        }
        ret = elt.getRepresentedRealParameter();
        return ret;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("6925a6bf-2615-40f0-a598-d5d5e61c4bb4")
    @Override
    public int getRowsNumber() {
        return DataStoreNodePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("8738a8bf-5723-475d-913b-c8b0c7d9b660")
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
                return this.theEditedElement.getType();
            case 3:
                return this.theEditedElement.getUpperBound();
            case 4:
                return this.theEditedElement.isIsControlType() ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return this.theEditedElement.getOrdering();
            case 6:
                return this.theEditedElement.getSelectionBehavior();
            case 7:
                return this.theEditedElement.getInState();
            case 8:
                return getRepresented(this.theEditedElement);
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
    @objid ("a4f06720-7263-44ec-95dc-a312ce60df62")
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
                        Collections.singletonList(GeneralClass.class));
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ObjectNodeOrderingKind.class);
            case 6:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 7:
                return new DefaultElementNatValue((MObject) getValue(row, col), false, Collections.singletonList(State.class));
            case 8:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Arrays.asList(Instance.class, Attribute.class, AssociationEnd.class, BehaviorParameter.class));
            default:
                return null;
            }
        default:
            return null;
        }
        
    }

    /**
     * Set the InstanceNode represented elements. This method set the right
     * dependency and clears the otheEditedElement.
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("b450df50-2c0f-47da-be38-7a2b342ae3c0")
    private void setRepresented(ObjectNode theEditedElement, Object value) {
        // Erase old value or exit if old value is new value
        Instance old1 = theEditedElement.getRepresented();
        if (old1 != null) {
            if (old1.equals(value)) {
                return;
            }
            theEditedElement.setRepresented(null);
        } else {
            Attribute old2 = theEditedElement.getRepresentedAttribute();
            if (old2 != null) {
                if (old2.equals(value)) {
                    return;
                }
                theEditedElement.setRepresentedAttribute(null);
            } else {
                AssociationEnd old3 = theEditedElement.getRepresentedRole();
                if (old3 != null) {
                    if (old3.equals(value)) {
                        return;
                    }
                    theEditedElement.setRepresentedRole(null);
                } else {
                    BehaviorParameter old4 = theEditedElement.getRepresentedRealParameter();
                    if (old4 != null) {
                        if (old4.equals(value)) {
                            return;
                        }
                        theEditedElement.setRepresentedRealParameter(null);
                    }
                }
            }
        }
        
        if (value != null) {
            // Set new value
            if (Instance.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresented((Instance) value);
            } else if (Attribute.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresentedAttribute((Attribute) value);
            } else if (AssociationEnd.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresentedRole((AssociationEnd) value);
            } else if (BehaviorParameter.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresentedRealParameter((BehaviorParameter) value);
            }
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
    @objid ("a0e1026e-2a94-4834-bc4f-2e2841ee03a4")
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
                this.theEditedElement.setType((GeneralClass) value);
                break;
            case 3:
                this.theEditedElement.setUpperBound((String) value);
                break;
            case 4:
                this.theEditedElement.setIsControlType(((Boolean) value).booleanValue());
                break;
            case 5:
                this.theEditedElement.setOrdering((ObjectNodeOrderingKind) value);
                break;
            case 6:
                this.theEditedElement.setSelectionBehavior((String) value);
                break;
            case 7:
                this.theEditedElement.setInState((State) value);
                break;
            case 8:
                setRepresented(this.theEditedElement, value);
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

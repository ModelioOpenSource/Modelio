/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>InstanceNode</i> data model.
 * <p>
 * This class provides the list of properties for the <i>InstanceNode</i>
 * metaclass.
 */
@objid ("903e0121-89bc-4326-9afa-6b994c1bc186")
public class InstanceNodePropertyModel extends AbstractPropertyModel<InstanceNode> {
    /**
     * Properties to display for <i>InstanceNode</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("66089b1a-33cd-4421-b846-3f6fd2811b42")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
			"TypeRepresented", "UpperBound", "IsControlType", "Ordering", "SelectionBehavior", "InState" };

    /**
     * Create a new <i>InstanceNode</i> data model from an <i>InstanceNode</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("d3b39468-9cfd-46cf-9e6d-83b3699b1a52")
    public InstanceNodePropertyModel(InstanceNode theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("26d368be-0905-4f2e-8261-8c2d37c65998")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element represented by the given instance node.
     * @return the represented element
     */
    @objid ("8fcf0890-7b86-447f-bf47-c86c4d032a86")
    private ModelElement getRepresented() {
        ModelElement ret = this.theEditedElement.getRepresented();
        if (ret != null) {
            return ret;
        }
        ret = this.theEditedElement.getRepresentedAttribute();
        if (ret != null) {
            return ret;
        }
        ret = this.theEditedElement.getRepresentedRole();
        if (ret != null) {
            return ret;
        }
        ret = this.theEditedElement.getRepresentedRealParameter();
        if (ret != null) {
            return ret;
        }
        ret = this.theEditedElement.getType();
        return ret;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("2b3c55f6-53d7-4858-bc47-0f2db1d3807d")
    @Override
    public int getRowsNumber() {
        return InstanceNodePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("cfb336fa-6739-493a-8855-273d78d251ed")
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
                return getRepresented();
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
    @objid ("dd8bbb94-581b-4ba6-b3d5-850c0f868998")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), true, Arrays.asList(Instance.class,
                        Attribute.class, AssociationEnd.class, BehaviorParameter.class, GeneralClass.class));
            case 3:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 5:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ObjectNodeOrderingKind.class);
            case 6:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 7:
                return new DefaultElementNatValue((MObject) getValue(row, col), false, Collections.singletonList(State.class));
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
    @objid ("5e1c03cb-e3b3-4819-bf15-607f65db80f6")
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
                    } else {
                        GeneralClass old5 = theEditedElement.getType();
                        if (old5 != null) {
                            if (old5.equals(value)) {
                                return;
                            }
                            theEditedElement.setType(null);
                        }
                    }
                }
            }
        }
        
        // Set new value
        if (value != null) {
            if (Instance.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresented((Instance) value);
            } else if (Attribute.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresentedAttribute((Attribute) value);
            } else if (AssociationEnd.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresentedRole((AssociationEnd) value);
            } else if (BehaviorParameter.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setRepresentedRealParameter((BehaviorParameter) value);
            } else if (GeneralClass.class.isAssignableFrom(value.getClass())) {
                theEditedElement.setType((GeneralClass) value);
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
    @objid ("b8cfc93f-1d2f-4b56-ac8d-14dbf73a3cce")
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
                setRepresented(this.theEditedElement, value);
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
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

}

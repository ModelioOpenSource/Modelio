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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
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
 * <i>ActivityParameterNode</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>ActivityParameterNode</i> metaclass.
 */
@objid ("b72fe758-3798-454d-bed6-c76617234cad")
public class ActivityParameterNodePropertyModel extends AbstractPropertyModel<ActivityParameterNode> {
    /**
     * Properties to display for <i>ActivityParameterNode</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("cc4d1a3f-894f-4495-8aaa-e38e18691b14")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
	        "UpperBound", "IsControlType", "Ordering", "SelectionBehavior", "InState", "Represented" };

    /**
     * Create a new <i>ActivityParameterNode</i> data model from an
     * <i>ActivityParameterNode</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("19255586-b5ea-4f6e-84ee-3c7b23a9b2e0")
    public ActivityParameterNodePropertyModel(ActivityParameterNode theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("54099655-4b6d-4cf9-a0c7-30e510a14062")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element represented by the given instance node.
     * 
     * @return the represented element
     */
    @objid ("37fb8b38-10b0-45ec-857f-849f6bbdedfe")
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
     * 
     * @return the number of rows
     */
    @objid ("d30ad5e5-5c42-4e9f-bd3a-96e440ef3023")
    @Override
    public int getRowsNumber() {
        return ActivityParameterNodePropertyModel.PROPERTIES.length;
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
    @objid ("03c8f552-77e8-4320-814c-44db9ca9610c")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ActivityParameterNodePropertyModel.PROPERTIES[row]);
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
                return new Boolean(this.theEditedElement.isIsControlType());
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
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("f183dc6e-e0a8-4bc3-b2ca-8c23c0548f71")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
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
                return new DefaultElementNatValue((MObject) getValue(row, col), true, Collections.singletonList(State.class));
            case 8:
                List<java.lang.Class<? extends MObject>> allowedClasses = new ArrayList<>();
                allowedClasses.add(Instance.class);
                allowedClasses.add(Attribute.class);
                allowedClasses.add(AssociationEnd.class);
                allowedClasses.add(BehaviorParameter.class);
                return new DefaultElementNatValue((MObject) getValue(row, col), true, allowedClasses);
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
     * 
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("b5667c64-70b6-45a9-8e52-011501322add")
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
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("ecb291ea-1ec2-4932-b8b3-823e9425d6fc")
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

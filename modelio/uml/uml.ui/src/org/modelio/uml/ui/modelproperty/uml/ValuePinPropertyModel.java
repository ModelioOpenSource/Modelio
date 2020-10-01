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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ValuePin</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ValuePin</i> metaclass.
 */
@objid ("e2b9fa08-c747-4cc2-a465-776783919669")
public class ValuePinPropertyModel extends AbstractPropertyModel<ValuePin> {
    /**
     * Properties to display for <i>ValuePin</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("f73e3110-6efe-4c8a-8b32-5ea8f263b976")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
			"Value", "UpperBound", "IsExpansion", "Matched", "IsControlType", "InState",
			"Represented" };

    /**
     * Create a new <i>ValuePin</i> data model from an <i>ValuePin</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("77a59661-3757-4ab9-8948-78a7362f3ce6")
    public ValuePinPropertyModel(ValuePin theEditedElement) {
        // Removed properties: "Ordering", "SelectionBehavior",
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("166eb7b7-2663-41ef-9bf1-9e14b450b22d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element represented by the given instance node.
     * 
     * @return the represented element
     */
    @objid ("8f2a29d9-ffe7-4d89-9b12-aa2f0acde4ec")
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
    @objid ("d89322d6-f069-420f-b04f-8e748a9ccad9")
    @Override
    public int getRowsNumber() {
        return ValuePinPropertyModel.PROPERTIES.length;
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
    @objid ("19e17db9-cbb7-4eaf-b616-2bb0fe7035df")
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
                return this.theEditedElement.getValue();
            case 4:
                return this.theEditedElement.getUpperBound();
            case 5:
                return this.theEditedElement.isIsExpansion() ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return this.theEditedElement.getMatched();
            case 7:
                return this.theEditedElement.isIsControlType() ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return this.theEditedElement.getInState();
            case 9:
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
    @objid ("5a6fda2b-2c75-4feb-adaf-ac699794907b")
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
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 6:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Parameter.class));
            case 7:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 8:
                return new DefaultElementNatValue((MObject) getValue(row, col), false, Collections.singletonList(State.class));
            case 9:
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
     * 
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("48135a98-558e-49dd-b551-c9976debabcd")
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
    @objid ("78f896fb-a392-44b0-9e44-3fbc4d84663e")
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
                return;
            case 2:
                this.theEditedElement.setType((GeneralClass) value);
                return;
            case 3:
                this.theEditedElement.setValue((String) value);
                return;
            case 4:
                this.theEditedElement.setUpperBound((String) value);
                return;
            case 5:
                this.theEditedElement.setIsExpansion(((Boolean) value).booleanValue());
                return;
            case 6:
                this.theEditedElement.setMatched((Parameter) value);
                return;
            case 7:
                this.theEditedElement.setIsControlType(((Boolean) value).booleanValue());
                return;
            case 8:
                this.theEditedElement.setInState((State) value);
                return;
            case 9:
                setRepresented(this.theEditedElement, value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}

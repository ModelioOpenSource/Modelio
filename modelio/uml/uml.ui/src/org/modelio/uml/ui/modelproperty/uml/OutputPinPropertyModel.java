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
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>OutputPin</i> data model.
 * <p>
 * This class provides the list of properties for the <i>OutputPin</i>
 * metaclass.
 */
@objid ("2d1f6dc8-706a-4ae2-a681-30e36d17d094")
public class OutputPinPropertyModel extends AbstractPropertyModel<OutputPin> {
    /**
     * Properties to display for <i>OutputPin</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("1ba1dc66-2693-41b6-87ea-e570974c27ee")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
			"Matched", "UpperBound", "IsExpansion", "IsControlType", "InState", "Represented" };

    /**
     * Create a new <i>OutputPin</i> data model from an <i>OutputPin</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("90a64260-94fa-455e-b59d-e0868a720508")
    public OutputPinPropertyModel(OutputPin theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("d81a4cb3-453f-40ee-84d8-134635422d41")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("3d466893-097b-49ea-8294-046974910344")
    @Override
    public int getRowsNumber() {
        return OutputPinPropertyModel.PROPERTIES.length;
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
    @objid ("6c97024e-da8d-438d-835d-57ac1d954a36")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName(); // "Name",
            case 2:
                return this.theEditedElement.getType(); // "Type"
            case 3:
                return this.theEditedElement.getMatched(); // "Matched"
            case 4:
                return this.theEditedElement.getUpperBound(); // "UpperBound"
            case 5:
                return this.theEditedElement.isIsExpansion() ? Boolean.TRUE : Boolean.FALSE; // "IsExpansion"
            case 6:
                return this.theEditedElement.isIsControlType() ? Boolean.TRUE : Boolean.FALSE; // "IsControlType"
            case 7:
                return this.theEditedElement.getInState(); // "InState"
            case 8:
                return getRepresentedValue(this.theEditedElement); // "Represented
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
    @objid ("2a966e29-a6bb-4915-90ac-752780bdb22f")
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
                return new DefaultStringNatValue((String) getValue(row, col), false); // "Name",
            case 2:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(GeneralClass.class)); // "Type"
            case 3:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Parameter.class)); // "Matched"
            case 4:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true); // "UpperBound"
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col)); // "IsExpansion"
            case 6:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col)); // "IsControlType"
            case 7:
                return new DefaultElementNatValue((MObject) getValue(row, col), false, Collections.singletonList(State.class)); // "InState"
            case 8:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Arrays.asList(Instance.class, Attribute.class, AssociationEnd.class, BehaviorParameter.class)); // "Represented"
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
    @objid ("16cb952b-f314-466f-8a94-5acac89fc28f")
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
                this.theEditedElement.setMatched((Parameter) value);
                break;
            case 4:
                this.theEditedElement.setUpperBound((String) value);
                break;
            case 5:
                this.theEditedElement.setIsExpansion(((Boolean) value).booleanValue());
                break;
            case 6:
                this.theEditedElement.setIsControlType(((Boolean) value).booleanValue());
                break;
            case 7:
                this.theEditedElement.setInState((State) value);
                break;
            case 8:
                setRepresentedValue(this.theEditedElement, value);
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    /**
     * Returns the element represented by the given instance node.
     * 
     * @return the represented element
     */
    @objid ("129e93aa-a6e0-4613-929c-ceccf64d5dc8")
    private ModelElement getRepresentedValue(ObjectNode elt) {
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
     * Set the ObjectNode represented elements. This method set the right
     * dependency and clears the otheEditedElement.
     * 
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("1f96ad0b-5db4-473e-9773-37aa8a08cecb")
    private void setRepresentedValue(ObjectNode theEditedElement, Object value) {
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

}

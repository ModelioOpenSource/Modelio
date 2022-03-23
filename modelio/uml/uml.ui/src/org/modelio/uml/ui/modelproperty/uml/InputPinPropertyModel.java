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
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
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
 * <i>InputPin</i> data model.
 * <p>
 * This class provides the list of properties for the <i>InputPin</i> metaclass.
 */
@objid ("8bbcb0b2-ccfb-485f-a788-898097fed982")
public class InputPinPropertyModel extends AbstractPropertyModel<InputPin> {
    /**
     * Properties to display for <i>InputPin</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("dfefed5d-d139-475a-97b8-fb9c523a062a")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
    			"UpperBound", "IsSelf", "IsExpansion", "Matched", "IsControlType", "InState", "Represented" };

    /**
     * Create a new <i>InputPin</i> data model from an <i>InputPin</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("07cd7818-953a-45ae-b482-955726c4b761")
    public  InputPinPropertyModel(InputPin theEditedElement) {
        // Removed properties: "Ordering", "SelectionBehavior",
        super(theEditedElement);
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("b89a2db3-fd59-4c95-bea4-77acc747c22e")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element represented by the given instance node.
     * @return the represented element
     */
    @objid ("7e516a16-97e6-4287-89f7-e985d58b2790")
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
    @objid ("7481d339-15a0-4abe-947f-8b46fc5a7dbd")
    @Override
    public int getRowsNumber() {
        return InputPinPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("4e314a6f-d1fd-4d81-b011-edac4205c6b8")
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
                return this.theEditedElement.isIsSelf() ? Boolean.TRUE : Boolean.FALSE;
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("50160e6d-6043-459e-84a9-9cfd0271f78a")
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
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 4:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
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
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("8218309b-6eb7-4299-b765-6602d7550ab6")
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
    @objid ("3a80b943-4b99-4689-9138-39751a5a599f")
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
                this.theEditedElement.setUpperBound((String) value);
                return;
            case 4:
                this.theEditedElement.setIsSelf(((Boolean) value).booleanValue());
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

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
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
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
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ExpansionNode</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ExpansionNode</i>
 * metaclass.
 */
@objid ("9341f9cc-2055-44e0-a461-5937e612f23e")
public class ExpansionNodePropertyModel extends AbstractPropertyModel<ExpansionNode> {
    /**
     * Properties to display for <i>ExpansionNode</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("9b3aeff3-00aa-4e35-a340-77a8a28f294b")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
    			"TypeRepresented", "UpperBound", "IsControlType", "Ordering", "SelectionBehavior", "InState" };

    /**
     * Create a new <i>ExpansionNode</i> data model from an <i>ExpansionNode</i>
     * .
     * @param theEditedElement the model to edit.
     */
    @objid ("1f76f15f-e30b-404f-9e04-e7862da8897e")
    public  ExpansionNodePropertyModel(ExpansionNode theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("77839605-2a23-4111-b0d4-f99a2231cf20")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element represented by the given instance node.
     * @return the represented element
     */
    @objid ("58c22615-604e-4299-b5df-1a4decd46beb")
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
    @objid ("a89b4928-036e-417e-b4ba-be1f44190bc4")
    @Override
    public int getRowsNumber() {
        return ExpansionNodePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("874befb6-e1f2-49e3-a813-4e9b4fdacef7")
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
    @objid ("bd548b79-8f5f-42b6-a0c7-2dd1dc7d0edd")
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
    @objid ("5698ffaf-d399-4c41-a4fa-66023cbfd3a6")
    private void setRepresented(final ExpansionNode theEditedElement, final Object value) {
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
    @objid ("775ece7a-d409-46a0-84a0-c30a621e5417")
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

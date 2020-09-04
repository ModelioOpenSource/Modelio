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
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.ParameterEffectKind;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Parameter</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Parameter</i>
 * metaclass.
 */
@objid ("10b20d31-799e-4017-aab4-0cdb99533f43")
public class IOParameterPropertyModel extends AbstractPropertyModel<Parameter> {
    /**
     * Properties to display for <i>Parameter</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("85b56464-abde-435f-b190-a5e16238c043")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
			"MultiplicityMin", "MultiplicityMax", "ParameterPassing", "DefaultValue", "TypeConstraint", "Effect",
			"IsException", "IsStream", "IsOrdered", "IsUnique" };

    /**
     * Create a new <i>Parameter</i> data model from an <i>Parameter</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("14e88586-a9f8-4286-b6de-de94ba6a3cae")
    public IOParameterPropertyModel(Parameter theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("faaef5c4-34e3-482c-83fe-ae2ebd794a0a")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("dc6e4208-bc4e-4675-b83c-df7c0c878eb1")
    @Override
    public int getRowsNumber() {
        return PROPERTIES.length;
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
    @objid ("6c09d2b0-d8d1-412b-8ab4-bf2ee5e4c568")
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
                return this.theEditedElement.getMultiplicityMin();
            case 4:
                return this.theEditedElement.getMultiplicityMax();
            case 5:
                return this.theEditedElement.getParameterPassing();
            case 6:
                return this.theEditedElement.getDefaultValue();
            case 7:
                return this.theEditedElement.getTypeConstraint();
            case 8:
                return this.theEditedElement.getEffect();
            case 9:
                return this.theEditedElement.isIsException() ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                return this.theEditedElement.isIsStream() ? Boolean.TRUE : Boolean.FALSE;
            case 11:
                return this.theEditedElement.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
            case 12:
                return this.theEditedElement.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("18d0f563-dcf0-4f40-ab9b-5b2095c7931d")
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
                List<String> cardinalityMinValues = new ArrayList<>();
                cardinalityMinValues.add("0");
                cardinalityMinValues.add("1");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMinValues, true);
            case 4:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 5:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), PassingMode.class);
            case 6:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 7:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 8:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ParameterEffectKind.class);
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 10:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 11:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 12:
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
    @objid ("eefec8c4-c234-48da-89a3-71da3a41a551")
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
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 4:
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 5:
                this.theEditedElement.setParameterPassing((PassingMode) value);
                break;
            case 6:
                this.theEditedElement.setDefaultValue((String) value);
                break;
            case 7:
                this.theEditedElement.setTypeConstraint((String) value);
                break;
            case 8:
                this.theEditedElement.setEffect((ParameterEffectKind) value);
                break;
            case 9:
                this.theEditedElement.setIsException(((Boolean) value).booleanValue());
                break;
            case 10:
                this.theEditedElement.setIsStream(((Boolean) value).booleanValue());
                break;
            case 11:
                this.theEditedElement.setIsOrdered(((Boolean) value).booleanValue());
                break;
            case 12:
                this.theEditedElement.setIsUnique(((Boolean) value).booleanValue());
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

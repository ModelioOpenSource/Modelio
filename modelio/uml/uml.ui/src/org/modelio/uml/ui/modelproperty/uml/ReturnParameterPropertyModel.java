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
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Parameter</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Parameter</i>
 * metaclass.
 */
@objid ("eb685885-4cea-4064-a36c-cd1b687736c8")
public class ReturnParameterPropertyModel extends AbstractPropertyModel<Parameter> {
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
    @objid ("1f2e7ed0-27a4-4d49-8684-6395ad9db68f")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Type",
			"MultiplicityMin", "MultiplicityMax", "TypeConstraint", "Effect", "IsException", "IsStream", "IsOrdered",
			"IsUnique" };

    /**
     * Create a new <i>Parameter</i> data model from an <i>Parameter</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("7b714388-bcdc-4a88-9d28-6fbf5570aaa6")
    public ReturnParameterPropertyModel(Parameter theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("a3893abd-f76c-4c2c-a7fa-76c411813d2c")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("fff1cc3b-ad84-4e0b-99b4-3fe7d15448c6")
    @Override
    public int getRowsNumber() {
        return PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("1dab0ec3-a960-401b-9855-a6e8bc05cf58")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getType();
            case 2:
                return this.theEditedElement.getMultiplicityMin();
            case 3:
                return this.theEditedElement.getMultiplicityMax();
            case 4:
                return this.theEditedElement.getTypeConstraint();
            case 5:
                return this.theEditedElement.getEffect();
            case 6:
                return this.theEditedElement.isIsException() ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return this.theEditedElement.isIsStream() ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return this.theEditedElement.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
            case 9:
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("fed3783a-099c-4c10-9513-421d6e483c51")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(GeneralClass.class));
            case 2:
                List<String> cardinalityMinValues = new ArrayList<>();
                cardinalityMinValues.add("0");
                cardinalityMinValues.add("1");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMinValues, true);
            case 3:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
        
            case 4:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 5:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), ParameterEffectKind.class);
            case 6:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 7:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("4dc86bd3-7bcc-4868-9a0b-80bfa51d9f5c")
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
                this.theEditedElement.setType((GeneralClass) value);
                break;
            case 2:
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 3:
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 4:
                this.theEditedElement.setTypeConstraint((String) value);
                break;
            case 5:
                this.theEditedElement.setEffect((ParameterEffectKind) value);
                break;
            case 6:
                this.theEditedElement.setIsException(((Boolean) value).booleanValue());
                break;
            case 7:
                this.theEditedElement.setIsStream(((Boolean) value).booleanValue());
                break;
            case 8:
                this.theEditedElement.setIsOrdered(((Boolean) value).booleanValue());
                break;
            case 9:
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

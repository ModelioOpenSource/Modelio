/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Attribute</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Attribute</i>
 * metaclass.
 */
@objid ("5dbe7a0e-d814-44e0-9c0f-b800cc351c26")
public class AttributePropertyModel extends AbstractPropertyModel<Attribute> {
    /**
     * Properties to display for <i>Attribute</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("ad44875e-213b-4226-b220-51e33564b6bc")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Type",
			"Visibility", "MultiplicityMin", "MultiplicityMax", "Value", "Changeable",
			"TypeConstraint", "IsAbstract", "IsClass", "IsDerived", "IsOrdered", "IsUnique", "TargetIsClass" };

    /**
     * Create a new <i>Attribute</i> data model from an <i>Attribute</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("0b8d45d7-ccd5-4ddb-85d5-b1ed82b8a8aa")
    public AttributePropertyModel(Attribute theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("36e250fa-f792-4214-a32d-e232ad3774b4")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("273a391b-0d99-47d5-8ce7-64d44d3cf85d")
    @Override
    public int getRowsNumber() {
        return AttributePropertyModel.PROPERTIES.length;
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
    @objid ("3f480052-d22a-4a91-ae7d-821515bde4b3")
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
                return this.theEditedElement.getVisibility();
            case 4:
                return this.theEditedElement.getMultiplicityMin();
            case 5:
                return this.theEditedElement.getMultiplicityMax();
            case 6:
                return this.theEditedElement.getValue();
            case 7:
                return this.theEditedElement.getChangeable();
            case 8:
                return this.theEditedElement.getTypeConstraint();
            case 9:
                return this.theEditedElement.isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                return this.theEditedElement.isIsClass() ? Boolean.TRUE : Boolean.FALSE;
            case 11:
                return this.theEditedElement.isIsDerived() ? Boolean.TRUE : Boolean.FALSE;
            case 12:
                return this.theEditedElement.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
            case 13:
                return this.theEditedElement.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
            case 14:
                return this.theEditedElement.isTargetIsClass() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("b13cffa4-f534-43c0-91d4-d7b986d1b672")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), VisibilityMode.class);
            case 4:
                List<String> cardinalityMinValues = new ArrayList<>();
                cardinalityMinValues.add("0");
                cardinalityMinValues.add("1");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMinValues, true);
            case 5:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 6:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 7:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), KindOfAccess.class);
            case 8:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 10:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 11:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 12:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 13:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 14:
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
    @objid ("2e714c5c-ad23-43ef-a5f9-4e89a5830a1d")
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
                this.theEditedElement.setVisibility((VisibilityMode) value);
                break;
            case 4:
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 5:
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 6:
                this.theEditedElement.setValue((String) value);
                break;
            case 7:
                this.theEditedElement.setChangeable((KindOfAccess) value);
                break;
            case 8:
                this.theEditedElement.setTypeConstraint((String) value);
                break;
            case 9:
                this.theEditedElement.setIsAbstract(((Boolean) value).booleanValue());
                break;
            case 10:
                this.theEditedElement.setIsClass(((Boolean) value).booleanValue());
                break;
            case 11:
                this.theEditedElement.setIsDerived(((Boolean) value).booleanValue());
                break;
            case 12:
                this.theEditedElement.setIsOrdered(((Boolean) value).booleanValue());
                break;
            case 13:
                this.theEditedElement.setIsUnique(((Boolean) value).booleanValue());
                break;
            case 14:
                this.theEditedElement.setTargetIsClass(((Boolean) value).booleanValue());
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

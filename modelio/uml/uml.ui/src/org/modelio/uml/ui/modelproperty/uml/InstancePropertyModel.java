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
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Instance</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Instance</i> metaclass.
 */
@objid ("2bf7ffd6-3b89-4db9-8534-54d4aa03d28f")
public class InstancePropertyModel extends AbstractPropertyModel<Instance> {
    /**
     * Properties to display for <i>Instance</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("0e734a78-7a36-4076-9e3d-c1606a987dc9")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Base",
			"Value", "MultiplicityMin", "MultiplicityMax", "IsConstant" };

    /**
     * Create a new <i>Instance</i> data model from an <i>Instance</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("ce049d15-e2c0-4289-973e-c3a4a82be2ca")
    public InstancePropertyModel(Instance theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("3f2b1a41-b149-4dfc-aed7-5f6fdc9ad08b")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("fce1d07d-7b41-4899-8293-3a0367d95a4d")
    @Override
    public int getRowsNumber() {
        return InstancePropertyModel.PROPERTIES.length;
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
    @objid ("e8ec8876-4180-4078-8cf8-0355ecf1b5ed")
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
                return this.theEditedElement.getBase();
            case 3:
                return this.theEditedElement.getValue();
            case 4:
                return this.theEditedElement.getMultiplicityMin();
            case 5:
                return this.theEditedElement.getMultiplicityMax();
            case 6:
                return this.theEditedElement.isIsConstant() ? Boolean.TRUE : Boolean.FALSE;
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
    @objid ("c1ab202e-e4c6-4ed6-9abc-048b96ea7efa")
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
                DefaultElementNatValue baseType = new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(NameSpace.class));
                baseType.setElementFilter(new BaseTypeFilter());
                return baseType;
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
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
    @objid ("5371991f-f1b8-4c36-84e4-50f8c40da2bf")
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
                this.theEditedElement.setBase((NameSpace) value);
                break;
            case 3:
                this.theEditedElement.setValue((String) value);
                break;
            case 4:
                this.theEditedElement.setMultiplicityMin((String) value);
                break;
            case 5:
                this.theEditedElement.setMultiplicityMax((String) value);
                break;
            case 6:
                this.theEditedElement.setIsConstant(((Boolean) value).booleanValue());
                break;
            default:
                return;
            }
            return;
        default:
            return;
        }
    }

    @objid ("82a45445-db76-44a8-a5e2-882dedeb27f2")
    protected static class BaseTypeFilter implements IMObjectFilter {
        @objid ("606e9606-cd77-4eaf-b35b-878d1050efe2")
        @Override
        public boolean accept(final MObject element) {
            NameSpace type = (NameSpace) element;
            if (type.getName().equals(PredefinedTypes.UNDEFINED_NAME)) {
                return false;
            } else if (type instanceof Profile) {
                return false;
            } else if (type instanceof ModuleComponent) {
                return false;
            } else {
                return true;
            }
        }

    }

}

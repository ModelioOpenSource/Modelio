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

package org.modelio.uml.ui.modelproperty.uml.templateparameter;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>TemplateParameter</i> data model for "value" template parameters.
 * <p>
 * This class provides the list of properties for the <i>TemplateParameter</i>
 * metaclass.
 * <p>
 * This data model has been manually created
 */
@objid ("aa344095-c3fd-4264-ab8f-bcd53312421a")
class ValueTemplateParameterPropertyModel extends AbstractPropertyModel<TemplateParameter> {
    /**
     * Properties to display for <i>TemplateParameter</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("ad10b153-fa3a-4443-a48a-cc23d4f8b1a5")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "IsValueParameter",
	        "Name", "Type", "DefaultValue" };

    /**
     * Create a new <i>TemplateParameter</i> data model from an
     * <i>TemplateParameter</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("3a32f8ad-cf3b-431c-a349-201cbe54bb9d")
    public ValueTemplateParameterPropertyModel(TemplateParameter theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("12322e7f-57b6-4cf5-b1a9-034616987915")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("0c4aa52f-7e54-4c7a-8e20-89e4cb0d7f59")
    @Override
    public int getRowsNumber() {
        return ValueTemplateParameterPropertyModel.PROPERTIES.length;
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
    @objid ("678e17fe-3e32-4046-b3be-2dd4d3ff14fe")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ValueTemplateParameterPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return TemplateKind.ValueTemplate;
            case 2:
                return this.theEditedElement.getName();
            case 3:
                return this.theEditedElement.getType();
            case 4:
                UmlModelElement ret = this.theEditedElement.getDefaultType();
                if (ret != null) {
                    return ret.getName();
                }
        
                return this.theEditedElement.getDefaultValue();
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
    @objid ("aed8946f-3297-4220-acf7-f532fab30303")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1: // is value parameter
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), TemplateKind.class);
            case 2: // Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 3: // type
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(GeneralClass.class));
            case 4: // default value
                return new DefaultStringNatValue((String) getValue(row, col), false);
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
    @objid ("7140a36c-b678-4149-8c9d-577708d368d2")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1: // Template kind : value parameter/type parameter
                if (value == TemplateKind.TypeTemplate) {
                    this.theEditedElement.setIsValueParameter(false);
                }
                break;
            case 2: // Name
                this.theEditedElement.setName((String) value);
                break;
            case 3: // Type
                this.theEditedElement.setType((UmlModelElement) value);
                break;
            case 4: // Default value
                // Erase old model element value
                UmlModelElement old1 = this.theEditedElement.getDefaultType();
                if (old1 != null) {
                    this.theEditedElement.setDefaultType(null);
                }
        
                // Set new value
                if (value != null) {
                    if (String.class.isAssignableFrom(value.getClass())) {
                        this.theEditedElement.setDefaultValue((String) value);
                    }
                }
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

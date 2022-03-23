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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>TemplateParameter</i> data model for "type" template parameters.
 * <p>
 * This class provides the list of properties for the <i>TemplateParameter</i>
 * metaclass.
 * <p>
 * This data model has been manually created
 * <p>
 */
@objid ("49382b86-bdfe-4ead-ab85-67f69b56646f")
class TypeTemplateParameterPropertyModel extends AbstractPropertyModel<TemplateParameter> {
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
    @objid ("29306428-fd42-4e5c-baf6-fba66daae8b6")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "IsValueParameter",
    	        "Name", "DefaultType", };

    /**
     * Create a new <i>TemplateParameter</i> data model from an
     * <i>TemplateParameter</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("9269858a-039c-4889-83de-28a7bd4139ff")
    public  TypeTemplateParameterPropertyModel(TemplateParameter theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("816881cc-7e6f-482d-8a23-7d094809229f")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("a864a7a8-f285-438e-bae4-a59a49250fdf")
    @Override
    public int getRowsNumber() {
        return TypeTemplateParameterPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("02d79e84-67b2-48fa-bee0-fb79eedc5861")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(TypeTemplateParameterPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return TemplateKind.TypeTemplate;
            case 2:
                return this.theEditedElement.getName();
            case 3:
                Object ret = this.theEditedElement.getDefaultType();
                if (ret != null) {
                    return ret;
                }
                ret = this.theEditedElement.getDefaultValue();
                return ret;
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
    @objid ("ca6f03bc-6c80-4976-915b-461ba8a2ba78")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1: // Is value parameter
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), TemplateKind.class);
            case 2: // Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 3: // default value
                return new DefaultHybridNatValue(getValue(row, col), true, Collections.singletonList(UmlModelElement.class), true);
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
    @objid ("ec66f1ba-113d-4b27-b701-53d7fc4941f7")
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
                if (value == TemplateKind.ValueTemplate) {
                    this.theEditedElement.setIsValueParameter(true);
                }
                break;
            case 2:
                this.theEditedElement.setName((String) value);
                break;
            case 3:
                // Erase old value or exit if old value is new value
                UmlModelElement old1 = this.theEditedElement.getDefaultType();
                if (old1 != null) {
                    if (old1.equals(value)) {
                        return;
                    }
                    this.theEditedElement.setDefaultType(null);
                } else {
                    String old2 = this.theEditedElement.getDefaultValue();
                    if (old2 != null && !old2.equals("")) {
                        if (old2.equals(value)) {
                            return;
                        }
                        this.theEditedElement.setDefaultValue("");
                    }
                }
        
                if (value != null) {
                    // Set new value
                    if (UmlModelElement.class.isAssignableFrom(value.getClass())) {
                        this.theEditedElement.setDefaultType((UmlModelElement) value);
                    } else if (String.class.isAssignableFrom(value.getClass())) {
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

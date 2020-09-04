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

package org.modelio.uml.ui.modelproperty.uml.templateparameter;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.TemplateParameter;

/**
 * <i>TemplateParameter</i> data model for template parameter that own a model
 * element.
 * <p>
 * This class provides the list of properties for the <i>TemplateParameter</i>
 * metaclass.
 * <p>
 * This data model has been manually created
 */
@objid ("c539cbab-b3a5-46cb-81ae-9f2820da1aa3")
class ElementTemplateParameterPropertyModel extends AbstractPropertyModel<TemplateParameter> {
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
    @objid ("19d9d857-6300-4261-93b0-4108862be978")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name",
	        "DefaultType" };

    /**
     * Create a new <i>TemplateParameter</i> data model from an
     * <i>TemplateParameter</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("8bb21d67-191f-4545-8032-c3af50ecf2b7")
    public ElementTemplateParameterPropertyModel(TemplateParameter theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("298da478-98c8-4248-97fe-6149b0e7ee49")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("48762b81-c22e-494c-8434-4b82901cf2bb")
    @Override
    public int getRowsNumber() {
        return ElementTemplateParameterPropertyModel.PROPERTIES.length;
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
    @objid ("91b6649e-cf1d-45e4-bb38-74a273134775")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ElementTemplateParameterPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                Object ret = this.theEditedElement.getDefaultType();
                if (ret != null) {
                    return ret;
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
    @objid ("9d62c0ce-f56f-404d-8136-28e8bc13bf4f")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1: // Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2: // Default type
                return new DefaultHybridNatValue(getValue(row, col), true, Arrays.asList(UmlModelElement.class), true);
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
    @objid ("c17bd758-344f-4dcd-bd5a-e1e616f43482")
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
                // Set the TemplateParameter and its owned parameter name.
                this.theEditedElement.setName((String) value);
                this.theEditedElement.getOwnedParameterElement().setName((String) value);
                break;
            case 2:
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

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

package org.modelio.model.property.panel.data.standard.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.metamodel.uml.infrastructure.ResourceType;

/**
 * <i>Resource</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Resource</i> metaclass.
 */
@objid ("b42fe364-457b-4825-9d5a-77285e9e74ad")
public class ResourceTypePropertyModel extends AbstractPropertyModel<ResourceType> {
    @objid ("5f8e4a75-da8d-4b85-83de-8a98e6e9fa4d")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Label", "IsHidden" };

    /**
     * Instantiate the note type properties view.
     * 
     * @param theEditedElement the current note type.
     */
    @objid ("a1b34859-3b58-4162-8e64-fb6ab1cdf078")
    public ResourceTypePropertyModel(ResourceType theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getColumnNumber()
     */
    @objid ("12ef96b0-1492-4c61-adc0-c8a6000adac8")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getRowsNumber()
     */
    @objid ("c373b0b9-1055-4dee-9fbc-60f178649ac3")
    @Override
    public int getRowsNumber() {
        return ResourceTypePropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int, int)
     */
    @objid ("6badfe5f-5a04-4bd5-8b6a-32588c59021d")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ResourceTypePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getLabelKey();
            case 3:
                return this.theEditedElement.isIsHidden() ? Boolean.TRUE : Boolean.FALSE;
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int, int)
     */
    @objid ("c9ff45f0-b56e-464f-b7ad-ffd4f567c998")
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
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#setValueAt(int, int, java.lang.Object)
     */
    @objid ("ae280919-a769-42f7-814b-12f44a41db6b")
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
                this.theEditedElement.setLabelKey((String) value);
                return;
            case 3:
                this.theEditedElement.setIsHidden(((Boolean) value).booleanValue());
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}

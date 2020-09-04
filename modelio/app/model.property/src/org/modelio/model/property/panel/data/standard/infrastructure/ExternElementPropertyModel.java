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
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.metamodel.uml.infrastructure.ExternElement;

/**
 * <i>ExternElement</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ExternElement</i> metaclass.
 */
@objid ("726df506-62cc-4cb6-8688-19f15cd1c1ce")
public class ExternElementPropertyModel extends AbstractPropertyModel<ExternElement> {
    @objid ("10c231e2-c98c-4c97-b02b-2cacf5de6d9a")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Provider",
            "Location" };

    /**
     * Instantiate the externElement properties view.
     * 
     * @param theEditedElement the current note type.
     */
    @objid ("104ccb69-44cc-4490-a149-40ac148b8e0d")
    public ExternElementPropertyModel(ExternElement theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getColumnNumber()
     */
    @objid ("cd9c70c5-2444-40d3-a82d-bb04b165acad")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getRowsNumber()
     */
    @objid ("35de676b-d791-4d0d-bdc7-c09c2db33a5e")
    @Override
    public int getRowsNumber() {
        return ExternElementPropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("1149e674-4ed2-45ee-81fb-a98498f585ad")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ExternElementPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getProvider();
            case 3:
                return this.theEditedElement.getLocation();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("a0f224e7-3489-4edc-9f0e-f5996274d52b")
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
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#setValueAt(int,
     * int, java.lang.Object)
     */
    @objid ("4f7a26e1-ef6a-410b-aa3d-b0d32c17ff85")
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
                this.theEditedElement.setProvider((String) value);
                return;
            case 3:
                this.theEditedElement.setLocation((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

    @objid ("033b1e6c-5312-4f90-8265-bb8d81cc0032")
    @Override
    public boolean isEditable(int row, int col) {
        return false;
    }

}

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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.mda.Project;

/**
 * <i>Project</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Project</i> metaclass.
 */
@objid ("552150f3-46e2-4c19-b613-d8c72cadf1f1")
public class ProjectPropertyModel extends AbstractPropertyModel<Project> {
    @objid ("00f0e6fc-b86f-46a2-b71f-8bc1b22e6374")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name" };

    /**
     * Instantiate the project properties view.
     * 
     * @param theEditedElement the current project.
     */
    @objid ("b21d763a-bf3e-4c24-9cd1-f58a26768ec2")
    public ProjectPropertyModel(Project theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.property.ui.data.common.model.IPropertyModel#getColumnNumber()
     */
    @objid ("756e7614-6481-4b7f-8d24-9335ec1185f8")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.property.ui.data.common.model.IPropertyModel#getRowsNumber()
     */
    @objid ("fb2b8342-4407-417a-8929-dc9e82dcf597")
    @Override
    public int getRowsNumber() {
        return ProjectPropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.property.ui.data.common.model.IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("b71cf4b4-254d-4018-b5b1-7305ecbc4774")
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
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.property.ui.data.common.model.IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("348f0066-25e9-438a-a637-6427ffa7de84")
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
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.property.ui.data.common.model.IPropertyModel#isEditable(int,
     * int)
     */
    @objid ("24a2ec55-d807-4edc-8032-087eb801c66f")
    @Override
    public boolean isEditable(int row, int col) {
        return false;
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.property.ui.data.common.model.IPropertyModel#setValueAt(int,
     * int, java.lang.Object)
     */
    @objid ("46399047-84e4-42f5-b3c0-d58f5a73ae9b")
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
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
